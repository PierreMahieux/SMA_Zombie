package model;

import java.awt.Point;
import java.util.Random;

import utils.ImprovedNoise;
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
	
	public void initPerlinMap(double seed)
	{		
		for(int yi = 0; yi < size[1];yi++)
		{
			for(int xi = 0; xi < size[0];xi++)
			{
				double noise = 0;
				
				if(xi > 0.850*size[0] || xi < 0.15*size[0])
				{
					if(xi>0.850*size[0])noise-= (0.15*size[0] - (size[0]-xi));
					else noise-=(0.15*size[0]-xi);
				}
				if( yi > 0.850*size[1] || yi<0.15*size[1])
				{
					if(yi>0.850*size[1])noise-= (0.15*size[1] - (size[1]-yi));
					else noise-=(0.15*size[1]-yi);
				}
				
				double x = (double)xi;x/=200;
				double y = (double)yi;y/=200;
				
				noise += (int)(300*ImprovedNoise.noise(x/3,y/3,seed));
				noise += (int)(100*ImprovedNoise.noise(x+10,y+10,seed));
				noise += (int)(50*ImprovedNoise.noise(x*2,y*2,seed));
				noise += (int)(5*ImprovedNoise.noise(x*10,y*10,seed));
				noise += (int)(5*ImprovedNoise.noise(x*100,y*100,seed));
				
				if(noise>0)
					map[yi][xi] = 1;
				else
					map[yi][xi] = 0;
				
			}
		}
	}
	
	public boolean isInMap(Point agent)
	{
		if(agent.x > size[0] || agent.y > size[1] || agent.x < 0 || agent.y < 0) return false;
		return map[agent.y][agent.x] == 1;
	}
}
