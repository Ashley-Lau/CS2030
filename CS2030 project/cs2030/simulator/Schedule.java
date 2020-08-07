package cs2030.simulator;

/**
 * interface for a schedule. 
 */ 
public interface Schedule {
    public double getTime(); 

    public Customer getCustomer();
    
    public Server getServer();
    
    public String schedule();
}
