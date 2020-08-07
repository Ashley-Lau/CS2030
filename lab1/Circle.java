class Circle {
    private static Point centre; 
    private static double radius;

    private Circle(Point centre, double radius) {
        this.centre = centre; 
        this.radius = radius; 
    }

    public static Circle getCircle(Point p, double r)	{
	if ( r <= 0) 	{
		return null; 
	} else {
		return new Circle(p, r);
	} 
    }

    public static boolean contains(Circle circle, Point point)      {
        double distanceto = point.distanceTo(circle.centre);
        if (distanceto <= circle.radius)        {
                return true;
        } else {
                return false;
        }
    }

    @Override 
    public String toString()	{
	return "circle of radius " + radius + " centered at " + centre.toString();
    }
}

