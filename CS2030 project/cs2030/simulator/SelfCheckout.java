package cs2030.simulator;

/**
 * class to create SelfCheckout machines, a subclass of server class.
 */ 
public class SelfCheckout extends Server {
    public static int totalQueue = 0;
    
    public SelfCheckout(int id, int maxQueueLength, boolean isServing, ServerState state) {
        super(id, maxQueueLength, totalQueue, isServing, state); 
    }

    public SelfCheckout(int id, int maxQueueLength) {
        super(id, maxQueueLength, totalQueue, false, ServerState.SERVER_BACK);
    }

    public SelfCheckout serve() {
        return new SelfCheckout(id, maxQueueLength, true, state);
    }

    /**
     * finished serving the current customer, hence removing the customer from the machine.
     */ 
    public SelfCheckout clearCustomer() {
        return new SelfCheckout(id, maxQueueLength);
    }

    /**
     * checks if a customer can join the queue of the SelfCheckout machines.
     */ 
    public boolean canWait() {
        return totalQueue < maxQueueLength;
    }

    public boolean hasCustomerWaiting() {
        return totalQueue > 0; 
    }

    /**
     * servers the first waiting customer and reduces the queue size by 1.
     */ 
    public SelfCheckout promoteWaitingCustomer() {
        totalQueue -= 1;
        return new SelfCheckout(id, maxQueueLength, true, state); 
    }

    /**
     * increases the queue size of the SelfCheckout counters by 1.
     */ 
    public SelfCheckout updateWaitQueue() {
        totalQueue += 1;
        return new SelfCheckout(id, maxQueueLength, isServing, state);
    }

    public SelfCheckout changeState(ServerState state) {
        return new SelfCheckout(id, maxQueueLength, false, state);
    }

    public int queueLength() {
        return totalQueue;
    }

    public String toString() {
        return "self-check " + id;
    }
}
