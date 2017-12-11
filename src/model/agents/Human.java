package model.agents;

import java.awt.Point;
import java.util.ArrayList;

import model.Agent;
import model.Perception;
import utils.MyMaths;

public class Human extends Agent{
	
	protected double moveSpeed = 3;
	
	public Human(int x, int y, Perception perception) {
		super(x,y, perception);
	}

	public Human(Perception perception) {
		super(perception);
	}

	@Override
	public void live() {
//		this.move((int)((Math.random()-0.5)*moveSpeed), (int)((Math.random()-0.5)*moveSpeed));
//		this.perception.getNearestZombieFrom(this.getX(), this.getY());
		
		int areaRadius = 100;
		ArrayList<Agent> nearbyZombies = this.perception.getNearbyZombies(this.getX(), this.getY(), areaRadius);
		
		int[] deltaMove = new int[2];
		
		if (nearbyZombies.isEmpty()) {
			//this.move((int)((Math.random()-0.5) * 4),(int)((Math.random()-0.5) * 4));
			deltaMove[0] = (int)((Math.random()-0.5) * 4);
			deltaMove[1] = (int)((Math.random()-0.5) * 4);			
		}
		else
		{
			Point zombiesCentroid = new Point(perception.getCentroidOfAgents(nearbyZombies)); //Center of gravity of all zombies
			
			
			double[] direction = MyMaths.normaliseVector(this.getX() - (int)zombiesCentroid.getX(), this.getY() - (int)zombiesCentroid.getY());
			
			//if(perception.isInMap(new Point(this.getX() + (int)(direction[0]*moveSpeed), this.getY() + (int)(direction[1]*moveSpeed))))
			//this.move((int)(direction[0]*moveSpeed), (int)(direction[1]*moveSpeed));
			deltaMove[0] = (int)(direction[0]*moveSpeed);
			deltaMove[1] = (int)(direction[1]*moveSpeed);	
		}
		
		if(perception.isInMap(new Point(this.getX() + deltaMove[0], this.getY() + deltaMove[1])))
		{
			this.move(deltaMove[0], deltaMove[1]);
		}
		
	}
	
	public void kill()
	{
		alive = false;
	}
	
	@Override
	public Agent getNextState()
	{
		//System.out.println("Human Killed");
		return new Zombie(this.getX(), this.getY(), this.perception);
	}

}
