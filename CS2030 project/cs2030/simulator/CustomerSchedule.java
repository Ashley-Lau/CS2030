package cs2030.simulator; 

/** 
 * class to create CustomerSchedule object, which contains a customer and the timing of a event
 * and the server of a customer if the customer is served..
 */ 
public class CustomerSchedule implements Schedule {
    private final double time; 
    private final Customer customer; 
    private final Server server; 

    /** 
     * constructor to initialise the CustomerSchedule.
     */
    public CustomerSchedule(double time, Customer customer, Server server) { 
        this.time = time; 
        this.customer = customer; 
        this.server = server; 
    }

    /** 
     * constructor to initialise the CustomerSchedule.
     */ 
    public CustomerSchedule(Customer customer, Server server) {
        this.customer = customer;
        this.server = server; 
        this.time = 0;
    }

    public double getTime() {
        return time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Server getServer() {
        return server;
    }
    
    /** 
     * prints out the schedule of the customer.
     */
    public String schedule() {
        if (server == null) {
            return String.format("%.3f", time) + " " + customer.state(); 
        } else {
            return String.format("%.3f", time) + " " + customer.state() + server.toString();
        }
    }
}
