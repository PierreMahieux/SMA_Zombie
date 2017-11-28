package model;

import utils.MyMaths;

public class Zombie extends Agent{
	
	protected boolean miracleHealing = false;
	protected int moveSpeed = 2;

	public Zombie(int x, int y, Perception perception) 
	{
		super(x,y,perception);
	}

	public Zombie(Perception perception)
	{
		super(perception);
	}

	@Override
	public void live()
	{
		Human nearestHuman = this.perception.getNearestHumanFrom(this.getX(), this.getY());
		double[] direction = MyMaths.normaliseVector(nearestHuman.getX() - this.getX(), nearestHuman.getY() - this.getY());
		this.move((int)(direction[0]*moveSpeed), (int)(direction[1]*moveSpeed));
	}
	
	@Override
	public Agent getNextState()
	{
		if(miracleHealing) return new Human(this.getX(), this.getY(), this.perception);
		return null;
	}

}
