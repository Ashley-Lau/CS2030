package cs2030.simulator;

/**
 * enum which captures the two states of the server, SERVER_REST and SERVER_BACK.
 */ 
public enum ServerState {
    SERVER_REST(1), SERVER_BACK(2);

    private final int num; 
    
    private ServerState(int num) {
        this.num = num; 
    }

    public int getIndex() {
        return num; 
    }
}
