package windows;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import life.LifeCtrl;

@SuppressWarnings("serial")
public class Window extends JFrame implements ItemListener{

	protected LifeCtrl ctrl;
	protected boolean playing = true;
	
	public Window()
	{
		
		System.out.println("Entrez le pourcentage d'Humain ]0;1]");
		Scanner sc = new Scanner(System.in);
		float ratio = Float.parseFloat(sc.nextLine());
		System.out.println("Densité d'agent (agent/pixel) sur la carte : ");
		float density = Float.parseFloat(sc.nextLine());
		sc.close();
				
		ctrl = new LifeCtrl(ratio, density);	
		setVisible(true);		
		setTitle("Zombies");
		
		setLocationRelativeTo(null);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JLayeredPane container = new JLayeredPane();
		
		container.setBounds(0,0,5000,5000);

		ctrl.getView().setBounds(0,0,5000,5000);
		ctrl.getMapView().setBounds(0,0,5000,5000);
		ctrl.getMetricsView().setBounds(0,0,5000,5000);

		container.add(ctrl.getMetricsView(), new Integer(2));	
		container.add(ctrl.getView(), new Integer(1));	
		container.add(ctrl.getMapView(), new Integer(0));
		
		setSize(1300,800);

		add(container);
		
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		         System.out.println("exited");
		    }
		});
		
		createMenuBar();
				
		setVisible(true);
		this.setLocationRelativeTo(null);		
		
		System.out.println("lancement !");
		go();
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription(
		        "Menu");
		menuBar.add(menu);
		
		JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("Play simulation");
		cbMenuItem.setSelected(playing);
		cbMenuItem.addItemListener(this);
		menu.add(cbMenuItem);

		
		this.setJMenuBar(menuBar);
	}

	private void go()
	{
		while(true)
		{
			if (playing) {
				ctrl.oneStep();
			}
			else {
				this.repaint();
			}
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		this.playing = !playing;
	}
}
