package life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Agent;
import model.Human;

@SuppressWarnings("serial")
public class LifeView extends JPanel{
	
	protected final int RADIUS = 3;
	
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
				g.setColor(Color.GREEN);
			else //ZOMBIE
				g.setColor(Color.RED);
			
			g.fillOval(a.getX() - RADIUS/2, a.getY() - RADIUS/2, RADIUS, RADIUS);
		}
	}

}
