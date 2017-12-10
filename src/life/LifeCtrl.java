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

	protected LifeView view;
	protected LifeModel model;
	
	public LifeCtrl()
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

		double MAX_X = 1000;
		double MAX_Y = 600;

		view = new LifeView(services);
		model = new LifeModel();
		
		model.map = new FieldMap((int)MAX_X*2,(int) MAX_Y*2);
		model.map.initCircleMap();
		
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

		model.agents.add(new Human(300,300, new Perception(services)));
		model.agents.add(new Zombie(280,250, new Perception(services)));
		model.agents.add(new Zombie(250,300, new Perception(services)));
		model.agents.add(new Zombie(280,350, new Perception(services)));
		
		for(int i = 0; i < 200; i++)
		{
			int x = 100 + (int)(Math.random() * MAX_X);
			int y = 100 + (int)(Math.random() * MAX_Y);
			
			if(Math.random() > 0.5)
				model.agents.add(new Zombie(x, y, new Perception(services)));
			else
				model.agents.add(new Human(x,y, new Perception(services)));
		}
	}
	
	public JPanel getView()
	{
		return this.view;
	}

	public void oneStep()
	{
		Collections.shuffle(model.agents);
		
		ArrayList<Agent> deads = new ArrayList<>();
		
		for(Agent a : model.agents)
		{
			a.live();
			
			if(!a.isAlive()) //if Dead
			{
				deads.add(a);
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
