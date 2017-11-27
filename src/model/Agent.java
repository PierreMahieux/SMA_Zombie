package model;

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
	
	public void move(int dx, int dy)
	{
		this.setX(x + dx);
		this.setY(y + dy);
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
