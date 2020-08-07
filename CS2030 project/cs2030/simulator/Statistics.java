package cs2030.simulator;

/**
 * class to store the total waiting time, number of customers served 
 * and number of customers who left the queue.
 */
public class Statistics {
    private final double waitingTime;
    private final int noOfCustomersServed;
    private final int noOfCustomersLeft;

    /**
     * constructor to initialise the statistics class.
     */
    public Statistics() {
        this.waitingTime = 0;
        this.noOfCustomersServed = 0; 
        this.noOfCustomersLeft = 0;
    }

    /** 
     * constructor to initialise the statistics class.
     */ 
    public Statistics(double waitingTime, int noOfCustomersServed, int noOfCustomersLeft) {
        this.waitingTime = waitingTime;
        this.noOfCustomersServed = noOfCustomersServed; 
        this.noOfCustomersLeft = noOfCustomersLeft;

    }

    /**
     * updates the waiting time of a customer.
     * @param waitingTime of a customer
     * @return updated statistics object
     */ 
    public Statistics updateWaitingTime(double waitingTime) {
        return new Statistics(waitingTime + this.waitingTime,
                noOfCustomersServed, noOfCustomersLeft);
    }

    /**
     * updates the number of customers served..
     * @return updated statistics object
     */ 
    public Statistics updateCustomersServed() {
        return new Statistics(waitingTime, 
                noOfCustomersServed + 1, noOfCustomersLeft);
    }

    /**
     * updates the number of customers which left the queue without being served.
     * @return updated statistics object
     */ 
    public Statistics updateCustomersLeft() {
        return new Statistics(waitingTime,
                noOfCustomersServed, noOfCustomersLeft + 1);
    }

    /**
     * returns the statistics of the current statistics class in as a string.
     */ 
    public String toString() {
        if (noOfCustomersServed == 0) {
            return "[" + String.format("%.3f", waitingTime) + " " + noOfCustomersServed 
                + " " + noOfCustomersLeft + "]"; 
        } else {
            return "[" + String.format("%.3f", waitingTime / noOfCustomersServed) 
                + " " + noOfCustomersServed + " " + noOfCustomersLeft + "]"; 
        }
    }
}

