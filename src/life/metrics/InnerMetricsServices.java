package life.metrics;

import java.util.List;

public interface InnerMetricsServices {
	public void registerZombiesNumber(int number);
	public void registerHumansNumber(int number);

	public List<Integer> getHumansHistogram();
	public List<Integer> getZombiesHistogram();
}
