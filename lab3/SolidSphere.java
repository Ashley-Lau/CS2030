class SolidSphere extends Sphere implements SolidShape	{
	private double radius; 
	private double density;
 
	public SolidSphere(double radius, double density)	{
		super(radius); 
		this.radius = radius;
		this.density = density; 
	} 
 	
	public double getDensity()	{
		return density; 
	} 
	
	@Override 
	public double getMass()	{
		return getVolume() * density; 
	}
	
	@Override 
	public SolidSphere setRadius(double radius)	{
		return new SolidSphere(radius, this.density);
	}
	
	@Override
	public String toString()	{
		return "SolidSphere [" + String.format("%.2f", radius) + "] with a mass of "
				 + String.format("%.2f", getMass());
	}

}
