package cs2030.simulator;

/** 
 * class to create customers object.
 */
public class Customer { 
    protected final int id; 
    protected final double eventTime;
    protected final State state; 

    /**
     * Constructors to instantiate a customer.
     */
    public Customer(int id, double eventTime, State state) {
        this.id = id;  
        this.eventTime = eventTime;
        this.state = state;  
    }

    /**
     * gets the event time of customer. 
     * @return event time of the customer
     */
    public double getEventTime() {
        return this.eventTime; 
    }

    /**
     * gets the string equivalent of the state of the customer.
     * @return string which describes the state of the customer
     */ 
    public String state() { 
        if (state == State.ARRIVES) { 
            return this.toString() + " arrives";
        } else if (state == State.SERVED) {
            return this.toString() + " served by "; 
        } else if (state == State.WAITS) { 
            return this.toString() + " waits to be served by ";  
        } else if (state == State.LEAVES) {
            return this.toString() + " leaves"; 
        } else { 
            return this.toString() + " done serving by "; 
        }
    } 

    /** 
     * gets the state of the customer.
     * @return state of the customer
     */ 
    public State getState() {
        return state; 
    }

    /** 
     * changes the state of a customer.
     * @param state pdated state of the customer
     * @return customer with the updated state
     **/
    public Customer changeState(State state) {
        return new Customer(id, eventTime, state);
    }

    /**
     * changes the state of a customer.
     * @param state updates state of customer 
     * @param eventTime updates time associated with state of the customer
     * @return customer with the new time and state
     */ 
    public Customer changeState(State state, double eventTime) {
        return new Customer(id, eventTime, state);
    }

    /**
     * gets the id of the customer. 
     * @return id of the customer
     */ 
    public int getId() {
        return id;
    }

    /**
     * returns the string equivalent of the id of the custoemr.
     * @return string equivalent of the id of the customer
     */
    public String toString() {
        return Integer.toString(id);
    }
}
