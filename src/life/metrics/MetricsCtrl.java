package life.metrics;

import java.util.List;

import javax.swing.JPanel;

import life.LifeCtrlServices;

public class MetricsCtrl {

	protected MetricsView view;
	protected MetricsModel model;
	
	protected final static int MAX_DATA_SIZE = 600;
	
	public MetricsCtrl(LifeCtrlServices services)
	{
		InnerMetricsServices callback = new InnerMetricsServices() {

			@Override
			public void registerZombiesNumber(int number) {
				model.zombiesNumber.add(number);
				model.limitArraysSize(MAX_DATA_SIZE);
			}

			@Override
			public void registerHumansNumber(int number) {
				model.humansNumber.add(number);		
				model.limitArraysSize(MAX_DATA_SIZE);			
			}

			@Override
			public List<Integer> getHumansHistogram() {
				return model.humansNumber;
			}

			@Override
			public List<Integer> getZombiesHistogram() {
				return model.zombiesNumber;
			}
		};

		view = new MetricsView(MAX_DATA_SIZE, services, callback);
		model = new MetricsModel();
	}
	
	public JPanel getView()
	{
		return view;
	}

}
