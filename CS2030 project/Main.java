import cs2030.simulator.EventSimulator;
import java.util.Scanner;

/**
 * Main class which does data processing and calls for the simulation of the event.
 */ 
class Main { 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 

        // data processing
        int seed = sc.nextInt();
        int noOfServers = sc.nextInt();
        int noOfSelfCheckout = sc.nextInt(); 
        int maxQueueLength = sc.nextInt();
        int noOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble(); 
        double restingProbability = sc.nextDouble();
        double greedyCustomerProbability = sc.nextDouble(); 
        sc.close();

        EventSimulator events = new EventSimulator(seed, arrivalRate, serviceRate, restingRate,
                noOfServers, noOfSelfCheckout, maxQueueLength, noOfCustomers, restingProbability, 
                greedyCustomerProbability);
        events.printSchedule(events.makeSchedule());
    }
} 

