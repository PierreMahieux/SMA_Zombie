package model.agents;

import java.awt.Point;
import java.util.ArrayList;

import model.Agent;
import model.Perception;
import utils.MyMaths;

public class Human extends Agent{
	
	protected int moveSpeed = 3;
	
	public Human(int x, int y, Perception perception) {
		super(x,y, perception);
	}

	public Human(Perception perception) {
		super(perception);
	}

	@Override
	public void live() {
		
		int areaRadius = 100;
		ArrayList<Agent> nearbyZombies = this.perception.getNearbyZombies(this.getX(), this.getY(), areaRadius);
		
		int[] deltaMove = new int[2];
		
		if (nearbyZombies.isEmpty()) {
			deltaMove[0] = (int)((Math.random()-0.5) * 4);
			deltaMove[1] = (int)((Math.random()-0.5) * 4);			
		}
		else
		{
			Point zombiesCentroid = new Point(perception.getCentroidOfAgents(nearbyZombies)); //Center of gravity of all zombies
			
			
			double[] direction = MyMaths.normaliseVector(this.getX() - (int)zombiesCentroid.getX(), this.getY() - (int)zombiesCentroid.getY());
			
			deltaMove = getUsableDirection(direction);
		}
		
		if(perception.isInMap(new Point(this.getX() + deltaMove[0], this.getY() + deltaMove[1])))
		{
			this.move(deltaMove[0], deltaMove[1]);
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
	
	public void kill()
	{
		alive = false;
	}
	
	@Override
	public Agent getNextState()
	{
		return new Zombie(this.getX(), this.getY(), this.perception);
	}

	@Override
	public int getMoveSpeed() {
		return moveSpeed;
	}

}
