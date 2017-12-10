package life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FieldMapView extends JPanel{
	
	protected LifeCtrlServices services;
	
	public FieldMapView(LifeCtrlServices services) {
		super();
		this.services = services;
		setOpaque(false);
	}
	
	/*

	@Override
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		
		System.out.println("Map Repaint");
		
		// Drawing Map //
		for(int yi = 0; yi < services.getTerrain().length; yi++)
		{
			for(int xi = 0; xi < services.getTerrain()[yi].length; xi++)
			{
				if(services.getTerrain()[yi][xi] != 0)
				{
					g.setColor(Color.GREEN);
					g.fillRect(xi - services.getScreenPos().x, yi - services.getScreenPos().y, 1, 1);
				}				
			}
		}
	}
*/
	
}
