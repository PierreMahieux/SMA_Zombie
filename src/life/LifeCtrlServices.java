package life;

import java.awt.Point;
import java.util.ArrayList;

import model.Agent;

public interface LifeCtrlServices {
	public ArrayList<Agent> getAllAgents();
	
	public Point getScreenPos();
	
	public int[][] getTerrain();
}
