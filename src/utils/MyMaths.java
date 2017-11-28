package utils;

import java.awt.Point;

public class MyMaths {

	public static double distance(Point a, Point b)
	{
		return Math.sqrt(Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y, 2));
	}
	
	public static double[] normaliseVector(int x, int y){
		double[] normalisedVector = new double[2];
		double magnitude = Math.sqrt(x*x + y*y);
		normalisedVector[0] = x/magnitude;
		normalisedVector[1] = y/magnitude;
		
		return normalisedVector;
	}
}
