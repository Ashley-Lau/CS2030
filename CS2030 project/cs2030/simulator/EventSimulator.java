package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue; 

/** 
 * Class that simulates the events given a series of information as stated in 
 * its constructors. 
 */
public class EventSimulator { 
    private int noOfServers; 
    private int noOfSelfCheckout; 
    private int maxQueueLength; 
    private int noOfCustomers;
    private double restingProbability; 
    private double greedyCustomerProbability;
    private RandomGenerator generator; 
    private Statistics stats = new Statistics();

    /** 
     * constructor to initialise the EventSimulator class.
     */
    public EventSimulator(int seed, double arrivalRate, double serviceRate, double restingRate, 
            int noOfServers, int noOfSelfCheckout, int maxQueueLength, int noOfCustomers, 
            double restingProbability, double greedyCustomerProbability) { 
        this.noOfServers = noOfServers;
        this.noOfSelfCheckout = noOfSelfCheckout;
        this.maxQueueLength = maxQueueLength; 
        this.noOfCustomers = noOfCustomers; 
        this.restingProbability = restingProbability; 
        this.greedyCustomerProbability = greedyCustomerProbability; 
        this.generator = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate); 
    }

    /**
     * initialised the type of customers, their arrival times and creates a 
     * CustomerSchedule using these information and put thems into a priority queue.
     * @return PriorityQueue with Schedule of a customer arrival times
     */
    public PriorityQueue<Schedule> makeCustomerArrivalSchedule() {
        PriorityQueue<Schedule> customerArrivalSchedule = 
            new PriorityQueue<>(new ScheduleComparator());
        double currentTime = 0; 
        int currentCustomerId = 1;
        for (int i = 0; i < noOfCustomers; i++) {
            boolean greedyCustomerGenerated = 
                generator.genCustomerType() < greedyCustomerProbability ? true : false;
            Customer customer;
            if (greedyCustomerGenerated) { // greedy customer generated
                customer = new GreedyCustomer(currentCustomerId, currentTime, State.ARRIVES);
            } else { // normal customer generated
                customer = new Customer(currentCustomerId, currentTime, State.ARRIVES);
            }
            CustomerSchedule schedule = new CustomerSchedule(currentTime, customer, null);
            customerArrivalSchedule.add(schedule);
            currentCustomerId++; 
            currentTime += generator.genInterArrivalTime();
        }
        return customerArrivalSchedule; 
    }

    /**
     * generates the Servers and SelfCheckout counters puts them into a array.
     * @return array of servers
     */ 
    public Server[] makeServers() { // generate servers 
        Server[] servers = new Server[noOfServers + noOfSelfCheckout];
        for (int i = 0; i < noOfServers + noOfSelfCheckout; i++) {
            if (i < noOfServers) {
                servers[i] = new Server(i + 1, maxQueueLength);
            } else {
                servers[i] = new SelfCheckout(i + 1, maxQueueLength);
            }
        }
        return servers;
    }

    /**
     * simulates the events given a array of server and priority queue of customer schedule
     * then puts the finalised schedule into a queue and returns this queue.
     * @return a queue with the finalised events which occured 
     */
    public PriorityQueue<Schedule> makeSchedule() {
        PriorityQueue<Schedule> finalizedSchedule = new PriorityQueue<>(new ScheduleComparator());
        PriorityQueue<Schedule> initialSchedule = makeCustomerArrivalSchedule();
        Server[] servers = makeServers();
        // stores the customers waiting in the queue of the servers
        ArrayList<Schedule> waitList = new ArrayList<>(); 
        // selfcheckout queue.
        ArrayList<Customer> selfCheckoutWaitList = new ArrayList<>();

        while (initialSchedule.size() != 0) {
            Schedule schedule = initialSchedule.poll(); 
            // server schedule which signifies that server is coming back from break
            if (schedule instanceof ServerSchedule) { 
                int serverId = schedule.getServer().getId();
                double currentTime = schedule.getTime(); 
                Server updatedServer = servers[serverId - 1].changeState(ServerState.SERVER_BACK);
                Customer waitingCustomer = null;
                if (updatedServer.hasCustomerWaiting()) {
                    updatedServer = updatedServer.promoteWaitingCustomer();
                    double serviceTime = generator.genServiceTime(); 
                    for (int i = 0; i < waitList.size(); i++) {
                        if (waitList.get(i).getServer().getId() == serverId) {
                            waitingCustomer = waitList.get(i).getCustomer();
                            waitList.remove(i);
                            break;
                        }
                    }
                    Customer servedCustomer = 
                        waitingCustomer.changeState(State.SERVED, currentTime);
                    CustomerSchedule servedSchedule = new CustomerSchedule(
                            servedCustomer.getEventTime(), servedCustomer, updatedServer);
                    finalizedSchedule.add(servedSchedule);
                    Customer doneCustomer = servedCustomer.changeState(State.DONE, 
                            servedCustomer.getEventTime() + serviceTime);
                    CustomerSchedule doneSchedule = new CustomerSchedule(
                            doneCustomer.getEventTime(), doneCustomer, updatedServer);
                    initialSchedule.add(doneSchedule);
                    stats = stats.updateWaitingTime(currentTime 
                            - waitingCustomer.getEventTime());
                } 
                servers[serverId - 1] = updatedServer;    
            } else { // manipulating of customerSchedules
                Customer customer = schedule.getCustomer();  
                finalizedSchedule.add(schedule);
                if (customer.getState() == State.ARRIVES) {
                    boolean served = false; 
                    boolean waits = false;
                    // checks if any server can server the customer
                    for (int i = 0; i < servers.length; i++) { 
                        Server server = servers[i];
                        if (server.canServe()) {
                            double serviceTime = generator.genServiceTime();
                            Server newServer = server.serve();
                            servers[i] = newServer;
                            Customer servedCustomer = customer.changeState(State.SERVED);
                            CustomerSchedule servedSchedule = 
                                new CustomerSchedule(servedCustomer.getEventTime(), 
                                servedCustomer, newServer);
                            finalizedSchedule.add(servedSchedule);
                            Customer doneCustomer = servedCustomer
                                .changeState(State.DONE, 
                                servedCustomer.getEventTime() + serviceTime);
                            CustomerSchedule doneSchedule = 
                                new CustomerSchedule(doneCustomer.getEventTime(), 
                                doneCustomer, newServer);
                            initialSchedule.add(doneSchedule);
                            served = true;
                            break;
                        }
                    }

                    if (!served) { // checks if the customer can join any queue
                        Server selectedServer = null;
                        // handling of greedy customers in queue
                        if (customer instanceof GreedyCustomer) { 
                            for (int i = 0; i < servers.length; i++) {
                                if (servers[i].canWait()) {
                                    waits = true;
                                    if (selectedServer == null) {
                                        selectedServer = servers[i];
                                    } else if (servers[i].queueLength() 
                                                < selectedServer.queueLength()) {
                                        selectedServer = servers[i]; 
                                    }
                                }
                            }
                        } else { // handling of normal customer in queue
                            for (int i = 0; i < servers.length; i++) {
                                if (servers[i].canWait()) { 
                                    selectedServer = servers[i];
                                    waits = true;
                                    break;
                                }
                            }
                        }
                        if (waits) { // customer joins the queue and processes the waiting customer
                            Customer waitingCustomer = customer.changeState(State.WAITS);
                            CustomerSchedule waitingSchedule = new CustomerSchedule(
                                waitingCustomer.getEventTime(), waitingCustomer, selectedServer);
                            finalizedSchedule.add(waitingSchedule);
                            servers[selectedServer.getId() - 1] = selectedServer.updateWaitQueue();
                            if (selectedServer.getId() <= noOfServers) {
                                waitList.add(waitingSchedule);
                            } else {
                                selfCheckoutWaitList.add(waitingCustomer);
                            }

                        }
                    }

                    if (!served && !waits) { // customer leaves 
                        Customer leavingCustomer = customer.changeState(State.LEAVES);
                        CustomerSchedule leavingSchedule = new CustomerSchedule(
                            leavingCustomer.getEventTime(), leavingCustomer, null);
                        finalizedSchedule.add(leavingSchedule);
                        stats = stats.updateCustomersLeft();
                    }
                } else { // customer is done
                    int serverId = schedule.getServer().getId();
                    Server currentServer = servers[serverId - 1];
                    Customer waitingCustomer = null; 
                    Server updatedServer = null;
                    if (serverId <= noOfServers) { // normal server 
                        boolean serverBreak = generator.genRandomRest() < restingProbability
                            ? true : false; 
                        if (serverBreak) { // server takes a break
                            updatedServer = currentServer.changeState(ServerState.SERVER_REST);
                            double breakTime = generator.genRestPeriod();
                            ServerSchedule serverSchedule = 
                                new ServerSchedule(schedule.getTime() + breakTime, updatedServer);
                            initialSchedule.add(serverSchedule);
                        } else { // there is no serverbreak
                            if (currentServer.hasCustomerWaiting()) {
                                updatedServer = currentServer.promoteWaitingCustomer();
                                double serviceTime = generator.genServiceTime(); 
                                for (int i = 0; i < waitList.size(); i++) {
                                    if (waitList.get(i).getServer().getId() == serverId) {
                                        waitingCustomer = waitList.get(i).getCustomer();
                                        waitList.remove(i);
                                        break;
                                    }
                                }
                                Customer servedCustomer = waitingCustomer
                                    .changeState(State.SERVED, customer.getEventTime());
                                CustomerSchedule servedSchedule = 
                                    new CustomerSchedule(servedCustomer.getEventTime(), 
                                    servedCustomer, updatedServer);
                                finalizedSchedule.add(servedSchedule);
                                Customer doneCustomer = 
                                    servedCustomer.changeState(State.DONE, 
                                    servedCustomer.getEventTime() + serviceTime);
                                CustomerSchedule doneSchedule = 
                                    new CustomerSchedule(doneCustomer.getEventTime(), 
                                    doneCustomer, updatedServer);
                                initialSchedule.add(doneSchedule);
                                stats = stats.updateWaitingTime(customer.getEventTime() 
                                        - waitingCustomer.getEventTime());
                            } else { // there is no customer in the queue of the server
                                updatedServer = currentServer.clearCustomer(); 
                            }
                        }
                    } else { // selfcheckout server 
                        // there are customers waiting in the queue
                        if (currentServer.hasCustomerWaiting()) { 
                            updatedServer = currentServer.promoteWaitingCustomer();
                            waitingCustomer = selfCheckoutWaitList.get(0);
                            selfCheckoutWaitList.remove(0);
                            double serviceTime = generator.genServiceTime();
                            Customer servedCustomer = 
                                waitingCustomer.changeState(State.SERVED, customer.getEventTime());
                            CustomerSchedule servedSchedule = 
                                new CustomerSchedule(servedCustomer.getEventTime(), 
                                servedCustomer, updatedServer);
                            finalizedSchedule.add(servedSchedule);
                            Customer doneCustomer = 
                                servedCustomer.changeState(State.DONE, 
                                servedCustomer.getEventTime() + serviceTime);
                            CustomerSchedule doneSchedule = 
                                new CustomerSchedule(doneCustomer.getEventTime(), 
                                doneCustomer, updatedServer);
                            initialSchedule.add(doneSchedule);
                            stats = stats.updateWaitingTime(customer.getEventTime() 
                                    - waitingCustomer.getEventTime());

                        } else { // no queue in the selfCheckout counter 
                            updatedServer = currentServer.clearCustomer();
                        }
                    }
                    stats = stats.updateCustomersServed();
                    servers[serverId - 1] = updatedServer;
                }
            }
        }
        return finalizedSchedule;
    }

    /**
     * prints out the finalised schedule which occured given a queue of finalised schedules.
     */
    public void printSchedule(PriorityQueue<Schedule> scheduleQueue) {
        while (scheduleQueue.size() != 0) {
            Schedule schedule = scheduleQueue.poll();
            System.out.println(schedule.schedule());
        }
        System.out.println(stats.toString());
    }
}
