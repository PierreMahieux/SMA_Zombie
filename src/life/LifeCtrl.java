package life;

import java.util.ArrayList;

import javax.swing.JPanel;

import model.Agent;
import model.Human;
import model.Zombie;

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
		};

		view = new LifeView(services);
		model = new LifeModel();
		
		Human h = new Human(100,100);
		Zombie z = new Zombie(340,340);
		

		model.agents.add(h);
		model.agents.add(z);
	}
	
	public JPanel getView()
	{
		return this.view;
	}

	public void oneStep()
	{
		for(Agent a : model.agents)
		{
			a.live();
		}
		
		view.repaint();
	}
}
