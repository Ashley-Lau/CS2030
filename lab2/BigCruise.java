class BigCruise extends Cruise	{
    private double length; 
    private double noOfPassengers;
    private final static int lengthDivider = 40;
    private final static int passengersDivider = 50; 
    public BigCruise(String id, int arrivalTime, double length, double noOfPassengers)	{
        super(id, arrivalTime, (int) Math.ceil(length/ lengthDivider), 
                (int) Math.ceil(noOfPassengers / passengersDivider));
        this.length = length; 
        this.noOfPassengers = noOfPassengers;

    }
}
