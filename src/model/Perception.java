package model;

import java.awt.Point;

import life.LifeCtrlServices;
import utils.MyMaths;

public class Perception {

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
		
		return nearestZombie;
	}
}
