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
	
	protected final boolean DEBUG = false; //True to draw more for better understanding
	
	protected final int DIAMETER = 4;
	
	protected LifeCtrlServices services;
	
	public LifeView(LifeCtrlServices services)
	{
		super();
		this.services = services;
		
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/** Drawing Agents **/
		
		for(Agent a : services.getAllAgents())
		{
			if(a instanceof Human)			//Don't wanna add Color data in Agent class. Agents don't have to know they have a color. 
				g.setColor(Color.BLUE);
			else //ZOMBIE
				g.setColor(Color.RED);

			int xPos = - services.getScreenPos().x + a.getX();
			int yPos = - services.getScreenPos().y + a.getY();
			
			g.fillOval(xPos - DIAMETER/2, yPos - DIAMETER/2, DIAMETER, DIAMETER);
			
			if(DEBUG && g.getColor() == Color.RED)g.drawOval(xPos - Zombie.killDistance, yPos - Zombie.killDistance, Zombie.killDistance*2, Zombie.killDistance*2);
			
			if(DEBUG)g.drawOval(xPos - Perception.MAX_PERCEPTION_DISTANCE, yPos - Perception.MAX_PERCEPTION_DISTANCE, Perception.MAX_PERCEPTION_DISTANCE*2, Perception.MAX_PERCEPTION_DISTANCE*2);
		}
	}

}
