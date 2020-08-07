class Sphere implements Shape {
	private double radius;

	public Sphere(double radius)	{
		this.radius = radius; 
	}
	
	public double getSurfaceArea() {
		return Math.PI * 4 * radius * radius; 
	}
	
	public double getVolume()	{
		return (4 * Math.PI * radius * radius * radius) /  3; 
	}

	public Sphere setRadius(double radius) 	{
		return new Sphere(radius); 
	}
 
	public String toString()	{
		return "Sphere [" + String.format("%.2f", radius) + "]";
	}      
}
