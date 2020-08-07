class SolidShape3D implements SolidShape	{
	private Shape shape;  
	private Material material; 
	public SolidShape3D(Shape shape, Material material) {
		this.shape = shape; 
		this.material = material; 
	}
	
	public double getDensity()	{
		return material.getDensity();
	}	

	public double getMass()	{
		return shape.getVolume() * getDensity(); 
	}
	
	public String toString()	{
		return "Solid" + shape + " with a mass of " + String.format("%.2f", getMass()); 
	}
}
