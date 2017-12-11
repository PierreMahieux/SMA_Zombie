package life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FieldMapView extends JPanel{
	
	protected LifeCtrlServices services;
	protected BufferedImage actualMap = null;
	
	public FieldMapView(LifeCtrlServices services) {
		super();
		this.services = services;
		setOpaque(false);
		
		setBackground(Color.BLACK);
		
		createBufferedImage(services.getTerrain());
	}
	
	

	/**
	 * Prebuilding the map to avoid computing everything at each repaint
	 * @param terrain
	 */
	protected void createBufferedImage(int[][] terrain)
	{
		actualMap = new BufferedImage(terrain[0].length, terrain.length, BufferedImage.TYPE_INT_ARGB);
		
		Color transparent = new Color(0, 0, 0, 0);
		
		for(int yi = 0; yi < terrain.length; yi++)
		{
			for(int xi = 0; xi < terrain[yi].length; xi++)
			{
				if(services.getTerrain()[yi][xi] != 0)
				{
					//g.setColor(Color.GREEN);
					//g.fillRect(xi - services.getScreenPos().x, yi - services.getScreenPos().y, 1, 1);
					actualMap.setRGB(xi, yi, Color.GREEN.getRGB());
				}
				else
				{
					actualMap.setRGB(xi, yi, transparent.getRGB());
				}
			}
		}
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		
		if(actualMap == null)return;
		
		// Drawing Map //
		g.drawImage(actualMap, - services.getScreenPos().x, - services.getScreenPos().y, null);
	}

	
}
