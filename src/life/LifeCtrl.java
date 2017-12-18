package life;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import model.Agent;
import model.FieldMap;
import model.Perception;
import model.agents.Human;
import model.agents.Zombie;

public class LifeCtrl {
	
	protected LifeCtrlServices services;

	protected FieldMapView mapview;
	protected LifeView view;
	protected LifeModel model;
	
	protected int numberOfAgents;
	
	public LifeCtrl(float ratio, float density)
	{
		services = new LifeCtrlServices() {		
			@Override
			public ArrayList<Agent> getAllAgents() {
				return model.agents;
			}

			@Override
			public Point getScreenPos() {
				return new Point(model.xScreen, model.yScreen);
			}

			@Override
			public int[][] getTerrain() {
				return model.map.getTerrain();
			}
		};

		int MAX_X = 1000;
		int MAX_Y = 600;
		
		view = new LifeView(services);
		model = new LifeModel();
		
		model.map = new FieldMap(MAX_X*2, MAX_Y*2);
		model.map.initCircleMap();
//		model.map.initPerlinMap(0);

		mapview = new FieldMapView(services);
		
		view.addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {}			
			@Override
			public void mousePressed(MouseEvent e) {
				model.pressedX = e.getX(); model.pressedY = e.getY();				
			}			
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		view.addMouseMotionListener(new MouseMotionListener() {			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				mooveScreen(model.pressedX-e.getX(), model.pressedY-e.getY());
				model.pressedX = e.getX(); model.pressedY = e.getY();				
			}
		});
		
		numberOfAgents = (int)(density * model.map.getSurface());
		
		for(int i = 0; i < numberOfAgents; i++)
		{
			Point spawn = model.map.getRandomPointInMap();
			
			if (i < numberOfAgents*ratio) {
				model.agents.add(new Human(spawn.x, spawn.y, new Perception(services, model.map)));
			}
			else {
				model.agents.add(new Zombie(spawn.x, spawn.y, new Perception(services, model.map)));
			}
		}
	}
	
	public JPanel getView()
	{
		return this.view;
	}
	
	public JPanel getMapView()
	{
		return this.mapview;
	}

	public void oneStep()
	{
		Collections.shuffle(model.agents);
		
		ArrayList<Agent> deads = new ArrayList<>();
		
		for(Agent a : model.agents)
		{
			if(!model.map.isInMap(a.getPos()))
				System.out.println("died");
			
			if(!a.isAlive() //if Dead
					|| !model.map.isInMap(a.getPos())) //Or outside the map
			{
				deads.add(a);
			}
			else
			{
				a.live();
			
				if(!a.isAlive()) //if Dead
				{
					deads.add(a);
				}
			}
		}
		
		for(Agent a : deads)
		{
			model.agents.remove(a);
			if(a.getNextState() != null) model.agents.add(a.getNextState());
		}
		
		view.repaint();
	}
	
	private void mooveScreen(int dx, int dy)
	{
		model.xScreen += dx;
		model.yScreen += dy;
	}
	
}
