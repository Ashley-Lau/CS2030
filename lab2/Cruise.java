class Cruise    {
    private final String identifier; 
    private final int arrivalTime;
    private final int loader; 
    private final int serviceTime;
    public Cruise(String identifier, int arrivalTime, int loader, int serviceTime) { 
        this.identifier = identifier;
        this.arrivalTime = arrivalTime; 
        this.loader = loader;
        this.serviceTime = serviceTime;
    }
    public int getArrivalTime() { 
        int minutes = arrivalTime % 100; 
        int hours = arrivalTime / 100;
        return hours * 60 + minutes; 
    }
    public int getServiceCompletionTime() {
       return getArrivalTime() + serviceTime; 
    } 
    
    public int getNumOfLoadersRequired()    {
        return loader;
    }
    
    @Override 
    public String toString()    {
        return identifier + "@" + String.format("%04d", arrivalTime); 
    }
}
