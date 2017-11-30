package model;

import java.awt.Point;
import java.util.ArrayList;

import life.LifeCtrlServices;
import model.agents.Human;
import model.agents.Zombie;
import utils.MyMaths;

public class Perception {
	
	public static final int MAX_PERCEPTION_DISTANCE = 150;

	private LifeCtrlServices services;
	
	public Perception(LifeCtrlServices services) {
		this.services = services;
	}
	
	public Human getNearestHumanFrom(int x, int y)
	{
		double distance = -1;
		Human nearestHuman = null;
		for(Agent a : services.getAllAgents())
		{
			if(a instanceof Human)
			{
				double newDistance = MyMaths.distance(new Point(x,y), new Point(a.getX(), a.getY()));
				if(distance == -1) //Initialization
				{
					nearestHuman = (Human)a;
					distance = newDistance;
				}
				else
				{
					if(newDistance < distance)
					{
						distance = newDistance;
						nearestHuman = (Human)a;
					}
				}
			}
		}	
		if(distance > MAX_PERCEPTION_DISTANCE)return null;
		return nearestHuman;
	}
	
	public Zombie getNearestZombieFrom(int x, int y)
	{
		double distance = -1;
		Zombie nearestZombie = null;
		for(Agent a : services.getAllAgents())
		{
			if(a instanceof Zombie)
			{
				double newDistance = MyMaths.distance(new Point(x,y), new Point(a.getX(), a.getY()));
				if(distance == -1) //Initialization
				{
					nearestZombie = (Zombie)a;
					distance = newDistance;
				}
				else
				{
					if(newDistance < distance)
					{
						distance = newDistance;
						nearestZombie = (Zombie)a;
					}
				}
			}
		}	

		if(distance > MAX_PERCEPTION_DISTANCE)return null;
		return nearestZombie;
	}
	
	public ArrayList<Agent> getNearbyZombies(int x, int y, int areaRadius){
		ArrayList<Agent> nearbyZombies = new ArrayList<>();
		
		for(Agent a : services.getAllAgents()) {
			if(a instanceof Zombie) {
				if(MyMaths.distance(new Point(x,y), new Point(a.getX(), a.getY())) < areaRadius) {
					nearbyZombies.add((Zombie)a);
				}
			}
		}
		
		return nearbyZombies;
	}
	
	public Point getCentroidOfAgents(ArrayList<Agent> agents) {
		Point centroid = new Point();
		
		for (Agent agent : agents) {
			centroid.x += agent.getX();
			centroid.y += agent.getY();
		}
		
		centroid.x = centroid.x / agents.size();
		centroid.y = centroid.y / agents.size();
		
		return centroid;
	}
}
