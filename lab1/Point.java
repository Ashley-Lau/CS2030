class Point { 
   
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x; 
        this.y = y; 
    }

    public double distanceTo(Point otherPoint) {
        double dx = otherPoint.x - this.x; 
        double dy = otherPoint.y - this.y; 
        double distance = Math.sqrt( dx * dx + dy * dy); 
        return distance;
    
    }
    public Point midPoint(Point otherPoint) {
        double dx = (this.x + otherPoint.x) / 2;
        double dy = (this.y + otherPoint.y) / 2;
        Point midPoint = new Point(dx, dy);
        return midPoint; 
    }
    
    public double angleTo(Point otherPoint) {
        double dx = (otherPoint.x - this.x) / 2; 
        double dy = (otherPoint.y - this.y) / 2;
        return Math.atan2(dy, dx);
    }
    
    public Point moveTo(double angle, double d) {
        double newX = this.x + d * Math.cos(angle); 
        double newY = this.y + d * Math.sin(angle);
	return new Point(newX, newY); 

    }
    @Override 
    public String toString() {
        return "point (" + (String.format("%.3f", this.x)) + ", " + (String.format("%.3f", this.y)) + ")" ; 
    }

}



