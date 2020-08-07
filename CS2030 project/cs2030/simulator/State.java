package cs2030.simulator;

/** 
* enum to store the different states of the customers.
*/
public enum State {
    ARRIVES(1), SERVED(2), WAITS(5), LEAVES(3), DONE(4);
    
    private int num;

    public int getIndex() {
        return num;
    }
    
    private State(int num) {
        this.num = num;
    }
}
