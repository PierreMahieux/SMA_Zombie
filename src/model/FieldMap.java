package model;

import java.awt.Point;
import java.util.Random;

import utils.MyMaths;

public class FieldMap {
	
	protected int[][] map;
	protected int[] size = new int[2];
	
	public int[][] getTerrain()
	{
		return map;
	}
	
	public FieldMap(int xSize, int ySize)
	{
		if(xSize < 0 || ySize < 0)
		{
			System.err.println("Error in map dimension, x and y must be positive integers");
		}
		
		map = new int[ySize][xSize];
		
		size[0] = xSize;
		size[1] = ySize;
	}
	
	public void initCircleMap()
	{
		Point center = new Point(size[0]/2, size[1]/2);
		int radius = (int)(Math.min(size[0]/2, size[1])/2);
		
		for(int yi = 0; yi < map.length; yi++)
		{
			for(int xi = 0; xi < map[yi].length; xi++)
			{
				map[yi][xi] = MyMaths.distance(new Point(xi,yi), center) <= radius ? 1 : 0;
			}
		}
	}
	
	public Point getRandomPointInMap()
	{
		Random generator = new Random();
		Point p = new Point(generator.nextInt(size[0]) , generator.nextInt(size[1]));
		
		while(!isInMap(p))
		{
			p = new Point(generator.nextInt(size[0]) , generator.nextInt(size[1]));
		}		
		
		return p;
	}
	
	public boolean isInMap(Point agent)
	{
		if(agent.x > size[0] || agent.y > size[1] || agent.x < 0 || agent.y < 0) return false;
		return map[agent.y][agent.x] == 1;
	}
}
