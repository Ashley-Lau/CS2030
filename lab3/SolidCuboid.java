class SolidCuboid extends Cuboid implements SolidShape	{ 
	private double height; 
	private double width; 
	private double length;
	private double density;  
	
	public SolidCuboid(double height, double width, double length, double density)	{
		super(height, width, length); 
		this.height = height; 
		this.width = width;
		this.length = length;  
		this.density = density; 
	}
	
	public double getDensity()	{
		return density; 
	}
	
	@Override 
	public SolidCuboid setHeight(double height) 	{
		return new SolidCuboid(height, this.width, this.length, this.density);
	}

	@Override 
	public SolidCuboid setWidth(double width) 	{
		return new SolidCuboid(this.height, width, this.length, this.density); 
 	}
	
	@Override
	public SolidCuboid setLength(double length)	{
		return new SolidCuboid(this.height, this.width, length, this.density);
	}

	public double getMass()	{ 
		return getVolume() * density; 
	} 
	
	@Override
	public String toString()	{
		return "Solid" + super.toString() + " with a mass of " + String.format("%.2f", getMass()); 
	} 

}
