package life;

import javax.swing.JPanel;

public class LifeCtrl {
	
	protected LifeView view = new LifeView();
	
	public JPanel getView()
	{
		return this.view;
	}
}
