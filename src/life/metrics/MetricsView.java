package life.metrics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import life.LifeCtrlServices;
import model.Agent;
import model.agents.Zombie;

@SuppressWarnings("serial")
public class MetricsView extends JPanel{
	
	protected Point startDraw = new Point(30,30);
	protected Point endDraw = new Point(650, 300);

	protected int graphHeight;
	protected int graphWidth;
	protected int maxNumber;

	protected LifeCtrlServices services;
	protected InnerMetricsServices cb;

	public MetricsView(int maxDataSize, LifeCtrlServices services, InnerMetricsServices cb)
	{
		this.services = services;
		this.cb = cb;
		setOpaque(false);
		
		endDraw.x = startDraw.x + maxDataSize;
		
		graphHeight = endDraw.y - startDraw.y;
		graphWidth = endDraw.x - startDraw.x;
	}

	@Override
	protected void paintComponent(Graphics g)
	{		
		int zombiesNumber = 0;
		int humansNumber = 0;
		
		for(Agent a : services.getAllAgents())
		{
			if(a instanceof Zombie)
			{
				zombiesNumber++;
			}
			else
			{
				humansNumber++;
			}
		}

		cb.registerHumansNumber(humansNumber);
		cb.registerZombiesNumber(zombiesNumber);
		
		maxNumber = 0;
		
		for(int i : cb.getHumansHistogram())
		{
			if(i > maxNumber) maxNumber = i;
		}
		
		for(int i : cb.getZombiesHistogram())
		{
			if(i > maxNumber) maxNumber = i;
		}
		
		if(maxNumber == 0)return;
		
		g.setColor(Color.BLACK);
		g.drawRect(startDraw.x, startDraw.x, graphWidth, graphHeight);
		

		g.setColor(Color.BLUE);
		plotCurve(g, cb.getHumansHistogram());
		
		g.setColor(Color.RED);
		plotCurve(g, cb.getZombiesHistogram());
		
		
	}
	
	protected void plotCurve(Graphics g, List<Integer> histogram)
	{
		Point memory = null;
		for(int i = 0; i < histogram.size(); i++)
		{
			//int offset = i * 50;
			
			Point p = new Point(startDraw.x + i, startDraw.y + graphHeight - 2 - ((graphHeight-3) * histogram.get(i) / maxNumber) );
			
			if(memory != null)
			{
				g.drawLine(memory.x, memory.y, p.x, p.y);
			}
			
			memory = p;			
		}
	}
}
