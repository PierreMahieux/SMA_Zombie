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

		model.agents.add(new Human(100,100));
		model.agents.add(new Zombie(140,340));
		model.agents.add(new Human(200,200));
		model.agents.add(new Zombie(340,240));
		model.agents.add(new Human(300,100));
		model.agents.add(new Zombie(240,240));
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
