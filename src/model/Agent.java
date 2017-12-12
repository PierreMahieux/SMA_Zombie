package model;

import java.awt.Point;

public abstract class Agent {

	private int x = 0;
	private int y = 0;
	
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
	 * @param moveSpeed : Quantity of movement allowed
	 * @return A new vector scaled.
	 */
	protected static int[] getUtilisableDirection(double[] computedDirection, double moveSpeed)
	{
		int[] newDirection = new int[2];
		
		newDirection[0] = (int)(computedDirection[0] * moveSpeed);
		newDirection[1] = (int)(moveSpeed - Math.abs(newDirection[0]));	
		
		return newDirection;
	}
	
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
