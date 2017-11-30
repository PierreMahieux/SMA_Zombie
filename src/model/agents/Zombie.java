package model.agents;

import model.Agent;
import model.Perception;
import utils.MyMaths;

public class Zombie extends Agent{
	
	protected boolean miracleHealing = false;
	protected double moveSpeed = 2;

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
		
		if(nearestHuman == null)
		{
			//No human in sight
			this.move((int)((Math.random()-0.5) * 4),(int)((Math.random()-0.5) * 4));
			return;
		}
		
		
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
