class Cuboid implements Shape	{ 
	private double height; 
	private double width; 
	private double length; 
	public Cuboid(double height, double width, double length)	{
		this.height = height;
		this.width = width; 
		this.length = length; 
	}
	
	public double getSurfaceArea()	{
		return 2 * width * length + 2 * width * height + 2 * length * height; 
	}
	
	public double getVolume()	{
		return height * width * length; 
	}

	public Cuboid setHeight(double height)	{
		return new Cuboid(height, this.width, this.length);  
	}

	public Cuboid setWidth(double width)	{
		return new Cuboid(this.height, width, this.length);
	}
	
	public Cuboid setLength(double length)	{
		return new Cuboid(this.height, this.width, length); 
	}
	
	@Override
	public String toString()	{
		return "Cuboid [" + String.format("%.2f", height) + " x " + String.format("%.2f", width)
		+ " x " + String.format("%.2f", length) + "]";  
	}
}







