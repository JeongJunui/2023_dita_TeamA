package warehouse;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class GanttChartMgr extends JPanel{
	StatisticsAWT statisticsAWT;
	public GanttChartMgr(StatisticsAWT statisticsAWT) {
		this.statisticsAWT =statisticsAWT;
		setLayout(null);
		//setBackground(Color.white);
		setBounds(25, 5, 505, 340);

		JFreeChart chart = ChartFactory.createGanttChart("2022년 입고 차트", "x Label", "y Label", null);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0,0,505,340);
		add(chartPanel);
		statisticsAWT.p7.add(this);
	}
	
	
}
