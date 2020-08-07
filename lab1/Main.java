import java.util.Scanner;
class Main {
	public static Circle createCircle(Point point1, Point point2, double radius) 	{
		double distancetopoint2 = point1.distanceTo(point2);
		if (distancetopoint2 > radius * 2 || radius == 0 || distancetopoint2 == 0)	{
			return null; 
		} else {
			Point midpoint = point1.midPoint(point2);
			double halfdistance = distancetopoint2 / 2;  
			double distancetocentre = Math.sqrt(radius * radius - halfdistance * halfdistance);
			double angle = point1.angleTo(point2) + Math.PI / 2; 
 			Point newcentre = midpoint.moveTo(angle, distancetocentre); 
			return Circle.getCircle(newcentre, radius); 
		}
 	}
	public static void main(String args[])	{
		Scanner sc = new Scanner(System.in); 
		int numberofpoint = sc.nextInt();
		Point[] points = new Point[numberofpoint];  
		int maximum = 0; 
		for(int i = 0; i < points.length; i++) 	{
			double x = sc.nextDouble(); 
			double y = sc.nextDouble(); 
			points[i] = new Point(x, y); 
		}
		for (int i = 0; i < points.length; i++)	{
			for(int j = i + 1; j < points.length; j++)	{
					Circle circle = createCircle(points[i], points[j], 1);
					if (circle != null) 	{
						int count = 0;
						for(int k = 0; k < points.length; k++)	{
							if (circle.contains(circle, points[k])) {
								count = count + 1;
							} 
						}	
						if (count > maximum) 	{
							maximum = count; 
						}
					}  
			}
		} 
		System.out.println("Maximum Disc Coverage: " + maximum); 
	}
}







