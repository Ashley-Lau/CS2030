package cs2030.simulator;

/**
 * class that creates a server object.
 */
public class Server {
    protected final int id;
    protected final int maxQueueLength;
    protected final int currentQueueLength; 
    protected boolean isServing;
    protected final ServerState state;

    /** 
     * constructor to instantiate the server class.
     */ 
    public Server(int id, int maxQueueLength, int currentQueueLength, 
            boolean isServing, ServerState state) {
        this.id = id; 
        this.maxQueueLength = maxQueueLength;
        this.currentQueueLength = currentQueueLength;
        this.isServing = isServing; 
        this.state = state;
    }

    /** 
     * constructor to instantiate the server class.
     */ 
    public Server(int id, int maxQueueLength) { 
        this.id = id; 
        this.maxQueueLength = maxQueueLength;
        this.currentQueueLength = 0;
        this.isServing = false;
        this.state = ServerState.SERVER_BACK;
    }


    /** 
     * Server serves the customer.
     * @return server updated with a serving customer 
     */ 
    public Server serve() {
        return new Server(id, maxQueueLength, 0, true, state);
    }

    /** 
     * checks if the server can serve the customer.
     * @return whether the customer can be served
     */
    public boolean canServe() {
        return !isServing && state != ServerState.SERVER_REST;
    }

    /** 
     * server finished serving the customer, hence removing the customer from the server.
     */
    public Server clearCustomer() {
        return new Server(id, maxQueueLength);
    }

    /**
     * checkers if the waitlist of the server is empty.
     * @return whether the customer can be in the waitlist of the server
     */ 
    public boolean canWait() { 
        return currentQueueLength < maxQueueLength;
    }

    public boolean hasCustomerWaiting() {
        return currentQueueLength > 0;
    }

    /**
     * server servers the first customer in the queue.
     */ 
    public Server promoteWaitingCustomer() {
        return new Server(id, maxQueueLength, currentQueueLength - 1, true, state);
    }

    /**
     * updates the number of customers in the queue of the server.
     */
    public Server updateWaitQueue() {
        return new Server(id, maxQueueLength, currentQueueLength + 1, isServing, state);
    }


    /**
     * changes the state of the server to SERVER_BACK or SERVER_REST.
     */ 
    public Server changeState(ServerState state) {
        return new Server(id, maxQueueLength, currentQueueLength, false, state);
    }

    public ServerState getState() {
        return state;
    }

    public int getId() {
        return id; 
    } 

    public int queueLength() {
        return currentQueueLength; 
    }

    public String toString() {
        return "server " + id;
    }
}
