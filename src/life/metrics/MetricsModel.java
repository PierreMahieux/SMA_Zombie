package life.metrics;

import java.util.ArrayList;
import java.util.List;

public class MetricsModel {
	
	protected ArrayList<Integer> zombiesNumber = new ArrayList<>();
	protected ArrayList<Integer> humansNumber = new ArrayList<>();
	
	protected void limitArraysSize(int sizeMax)
	{
		azerty(zombiesNumber, sizeMax);
		azerty(humansNumber, sizeMax);		
	}
		
	protected void azerty(List<Integer> list, int sizeMax)
	{
		if(list.size() > sizeMax)
		{
			for(int i = 1; i < (list.size() - 1 )/2;  i++)
			{
				list.remove(i);
			}
		}
	}
}
