package life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Agent;
import model.Perception;
import model.agents.Human;
import model.agents.Zombie;

@SuppressWarnings("serial")
public class LifeView extends JPanel{
	
	protected final boolean DEBUG = true; //True to draw more for better understanding
	
	protected final int DIAMETER = 2;
	
	protected LifeCtrlServices services;
	
	public LifeView(LifeCtrlServices services)
	{
		super();
		this.services = services;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(Agent a : services.getAllAgents())
		{
			if(a instanceof Human)			//Don't wanna add Color data in Agent class. Agents don't have to know they have a color. 
				g.setColor(Color.BLUE);
			else //ZOMBIE
				g.setColor(Color.RED);
			
			g.fillOval(a.getX() - DIAMETER/2, a.getY() - DIAMETER/2, DIAMETER, DIAMETER);
			
			if(DEBUG && g.getColor() == Color.RED)g.drawOval(a.getX() - Zombie.killDistance, a.getY() - Zombie.killDistance, Zombie.killDistance*2, Zombie.killDistance*2);
			
			if(DEBUG)g.drawOval(a.getX() - Perception.MAX_PERCEPTION_DISTANCE, a.getY() - Perception.MAX_PERCEPTION_DISTANCE, Perception.MAX_PERCEPTION_DISTANCE*2, Perception.MAX_PERCEPTION_DISTANCE*2);
		}
	}

}
