package cs2030.simulator;

/**
 * class to create ServerSchedule specifically for SERVER_REST and SERVER_BACK events.
 */
public class ServerSchedule implements Schedule {
    private final double time;
    private final Server server;

    public ServerSchedule(double time, Server server) {
        this.time = time; 
        this.server = server;
    }

    public double getTime() {
        return time; 
    }

    public Customer getCustomer() {
        return new Customer(-1, -1, State.LEAVES); 
    }

    public Server getServer() {
        return server;
    }

    public String schedule() {
        return time + " server " + server.getId(); 
    }
}
