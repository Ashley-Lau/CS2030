package cs2030.simulator;

/**
 * class to create GreedyCustomer, which is a subclass of Customer.
 *
 */
public class GreedyCustomer extends Customer {
    /**
     * constructor to instialise GreedyCustomer. 
     */
    public GreedyCustomer(int id, double eventTime, State state) {
        super(id, eventTime, state);
    }

    /**
     * changes the state of the GreedyCustomer.
     * @param state new updated state of the GreedyCustomer
     * @return GreedyCustomer with its updated state
     */
    public GreedyCustomer changeState(State state) {
        return new GreedyCustomer(id, eventTime, state);
    }

    /**
     * changes the state and the event time of the GreedyCustomer associated to the state.
     * @param state new updated state of the GreedyCustomer
     * @param eventTime updated event time of GreedyCustomer
     * @return GreedyCustomer with updated state and eventTime
     */ 
    public GreedyCustomer changeState(State state, double eventTime) {
        return new GreedyCustomer(id, eventTime, state);
    }

    public String toString() {
        return id + "(greedy)";
    }

}
