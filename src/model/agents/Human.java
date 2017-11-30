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
		
		if (nearbyZombies.isEmpty()) {
			this.move((int)((Math.random()-0.5) * 4),(int)((Math.random()-0.5) * 4));
			return;
		}
			
		
		Point zombiesCentroid = new Point(perception.getCentroidOfAgents(nearbyZombies)); //Center of gravity of all zombies
		
	
		double[] direction = MyMaths.normaliseVector(this.getX() - (int)zombiesCentroid.getX(), this.getY() - (int)zombiesCentroid.getY());
		this.move((int)(direction[0]*moveSpeed), (int)(direction[1]*moveSpeed));
		
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
