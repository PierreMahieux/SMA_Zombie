package model.agents;

import java.awt.Point;

import model.Agent;
import model.Perception;
import utils.MyMaths;

public class Zombie extends Agent{
	
	protected boolean miracleHealing = false;
	protected double moveSpeed = 2;
	
	public static final int killDistance = 10;

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
		
		if(Math.random() * 10000 < 1)
		{
			miracleHealing = true;
			alive = false;
			return;
		}
		
		int[] deltaMove = new int[2];
		
		if(nearestHuman == null)
		{
			//No human in sight
			//this.move((int)((Math.random()-0.5) * 4),(int)((Math.random()-0.5) * 4));
			deltaMove[0] = (int)((Math.random()-0.5) * 4);
			deltaMove[1] = (int)((Math.random()-0.5) * 4);
		}
		else
		{
			if(MyMaths.distance(nearestHuman.getPos(), this.getPos()) < killDistance)
			{
				attack(nearestHuman);
				return;
			}
			else
			{
				double[] direction = MyMaths.normaliseVector(nearestHuman.getX() - this.getX(), nearestHuman.getY() - this.getY());
				

				//if(perception.isInMap(new Point(this.getX() + (int)(direction[0]*moveSpeed), this.getY() + (int)(direction[1]*moveSpeed))))
				//	this.move((int)(direction[0]*moveSpeed), (int)(direction[1]*moveSpeed));
				
				deltaMove[0] = (int)(direction[0]*moveSpeed);
				deltaMove[1] = (int)(direction[1]*moveSpeed);
			}
		}
		
		if(perception.isInMap(new Point(this.getX() + deltaMove[0], this.getY() + deltaMove[1])))
			this.move(deltaMove[0],deltaMove[1]);
		
		
		
	}
	
	protected void attack(Human h)
	{
		if(Math.random() > 0.75)
		{
			//Human wins
			alive = false;
		}
		else
		{
			//Zombie wins
			h.kill();
		}
	}
	
	@Override
	public Agent getNextState()
	{
		if(miracleHealing) return new Human(this.getX(), this.getY(), this.perception);
		//System.out.println("Zombie Killed");
		return null;
	}

}
