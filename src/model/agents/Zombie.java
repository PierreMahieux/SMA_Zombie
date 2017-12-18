package model.agents;

import java.awt.Point;

import model.Agent;
import model.Perception;
import utils.MyMaths;

public class Zombie extends Agent{
	
	protected boolean miracleHealing = false;
	protected int miracleHealingProbability = 100000;
	protected int moveSpeed = 2;
	
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
		
		if(Math.random() * miracleHealingProbability < 1)
		{
			miracleHealing = true;
			alive = false;
			return;
		}
		
		int[] deltaMove = new int[2];
		
		if(nearestHuman == null)
		{
			//No human in sight
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
				
				deltaMove = getUsableDirection(direction);
			}
		}
		
		if(perception.isInMap(new Point(this.getX() + deltaMove[0], this.getY() + deltaMove[1])))
		{
			this.move(deltaMove[0],deltaMove[1]);
		}
		else
		{
			for(int[] move : getDirectionsNear(deltaMove))
			{
				if(perception.isInMap(new Point(this.getX() + move[0], this.getY() + move[1])))
				{
					this.move(move[0], move[1]);
					return;
				}
			}
		}
		
		
		
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
		return null;
	}

	@Override
	public int getMoveSpeed() {
		return moveSpeed;
	}

}
