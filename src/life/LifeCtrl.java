package life;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import model.Agent;
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
		};

		view = new LifeView(services);
		model = new LifeModel();		

		model.agents.add(new Human(300,300, new Perception(services)));
		model.agents.add(new Zombie(280,250, new Perception(services)));
		model.agents.add(new Zombie(250,300, new Perception(services)));
		model.agents.add(new Zombie(280,350, new Perception(services)));
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
}
