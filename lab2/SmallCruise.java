class SmallCruise extends Cruise { 
	private final static int loadersRequired = 1; 
    private final static int timeRequired = 30; 
    public SmallCruise(String id, int arrivalTime)	{
		super(id, arrivalTime, loadersRequired, timeRequired);  
	} 
}
