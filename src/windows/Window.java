package windows;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import life.LifeCtrl;

@SuppressWarnings("serial")
public class Window extends JFrame {

	protected LifeCtrl ctrl;
	
	public Window()
	{
		ctrl = new LifeCtrl();	
		setVisible(true);		
		setTitle("Zombies");
		
		setLocationRelativeTo(null);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel container = new JPanel();
		
		container.setLayout(new BorderLayout());
		container.add(ctrl.getView(), BorderLayout.CENTER);
		
		setSize(1000,600);

		this.setContentPane(container);
		
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		         System.out.println("exited");
		    }
		});
		
		setVisible(true);
		this.setLocationRelativeTo(null);		
		
		System.out.println("lancement !");
		go();
	}
	
	private void go()
	{
		int index = 0;
		while(true)
		{
			System.out.println(index++);
			
			try{
				Thread.sleep(5);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
