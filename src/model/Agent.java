package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import utils.MyMaths;

public abstract class Agent {

	private int x = 0;
	private int y = 0;
	
	protected double strength;
	
	protected boolean alive = true;
	
	public boolean isAlive() {
		return alive;
	}

	protected Perception perception;
	
	public void setPos(int x, int y)
	{
		this.setX(x);
		this.setY(y);
	}
	
	public Point getPos()
	{
		return new Point(getX(), getY());
	}
	
	public void move(int dx, int dy)
	{
		this.setX(x + dx);
		this.setY(y + dy);
	}
	
	/**
	 * 
	 * @param computedDirection : Normalized vector
	 * @return A new vector scaled.
	 */
	public int[] getUsableDirection(double[] computedDirection)
	{
		int[] newDirection = new int[2];
		
		newDirection[0] = (int)(computedDirection[0] * getMoveSpeed());
		newDirection[1] = (int)((getMoveSpeed() - Math.abs(newDirection[0])) * Math.signum(computedDirection[1]));	
		
		return newDirection;
	}
	
	/**
	 * 
	 * @param direction : usable movement vector
	 * @return
	 */
	protected List<int[]> getDirectionsNear(int[] direction)
	{
		ArrayList<int[]> otherDirections = new ArrayList<>();
		
		/** FINDING ALL THE POSSIBILITIES **/
		for(int xi = -getMoveSpeed(); xi <= getMoveSpeed(); xi++)
		{
			int[] tmp = {xi, getMoveSpeed() - Math.abs(xi)};
			otherDirections.add(tmp);
			
			int[] tmpNegative = new int[2];
			
			if(tmp[1] != 0)
			{
				tmpNegative[0] = xi;
				tmpNegative[1] = - (getMoveSpeed() - Math.abs(xi));	
				otherDirections.add(tmpNegative);
			}
		}
		
		int maxMovementPossible = 4*getMoveSpeed();
		
		/** Sorting them by distance **/
		ArrayList<int[]> sortedDirections = new ArrayList<>();
		
		Point oldDirection = new Point(direction[0], direction[1]);
		
		for(int xi = 0; xi < otherDirections.size(); xi++)
		{
			int[] point = otherDirections.get(xi);
			boolean found = false;
			for(int sortedi = 0; sortedi < sortedDirections.size() && !found; sortedi++)
			{
				int[] sortedPoint = sortedDirections.get(sortedi);
				if(MyMaths.distance(oldDirection, new Point(point[0], point[1])) < MyMaths.distance(oldDirection, new Point(sortedPoint[0], sortedPoint[1])))
				{
					sortedDirections.add(sortedi, point);
					found = true;
				}
			}
			
			if(!found)
			{
				sortedDirections.add(point);
			}
		}
		
		/** Dumping the unwanted values **/
		sortedDirections.remove(0);
		while(sortedDirections.size() > maxMovementPossible*2/3)
		{
			sortedDirections.remove(sortedDirections.size()-1);
		}
		
		
		return sortedDirections;
	}

	public double getStrength()
	{
		return this.strength;
	}
	public abstract int getMoveSpeed();
	public abstract void live();
	public abstract Agent getNextState();
	
	public Agent(Perception perception) {
		this(0,0, perception);
	}
	
	public Agent(int x, int y, Perception perception) {
		this.setX(x);
		this.setY(y);
		
		this.perception = perception;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
