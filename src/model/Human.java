package model;

import java.awt.Point;
import java.util.ArrayList;

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
//		this.move((int)((Math.random()-0.5)*moveSpeed), (int)((Math.random()-0.5)*moveSpeed));
//		this.perception.getNearestZombieFrom(this.getX(), this.getY());
		
		int areaRadius = 100;
		ArrayList<Agent> nearbyZombies = this.perception.getNearbyZombies(this.getX(), this.getY(), areaRadius);
		
		Point centroid;
		if (nearbyZombies.isEmpty()) {
			centroid = new Point(this.getX() + (int)((Math.random() - 0.5) * 2), this.getY() + (int)((Math.random() - 0.5) * 2));
		}
		else {
			centroid = new Point(perception.getCentroidOfAgents(nearbyZombies));
		}
	
		double[] direction = MyMaths.normaliseVector(this.getX() - (int)centroid.getX(), this.getY() - (int)centroid.getY());
		this.move((int)(direction[0]*moveSpeed), (int)(direction[1]*moveSpeed));
		
	}
	
	@Override
	public Agent getNextState()
	{
		return new Zombie(this.getX(), this.getY(), this.perception);
	}

}
