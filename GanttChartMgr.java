package warehouse;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;

import net.DBConnectionMgr;

public class GanttChartMgr extends JPanel {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	StatisticsAWT statisticsAWT;

	public GanttChartMgr(StatisticsAWT statisticsAWT) {
		this.statisticsAWT = statisticsAWT;
		setLayout(null);
		setBackground(Color.white);
		setBounds(25, 15, 505, 340);

		JFreeChart chart = ChartFactory.createBarChart("��ǰ�� �� �԰���Ʈ", "", "", createDataset());
		ChartPanel chartPanel = new ChartPanel(chart);
		// Ÿ��Ʋ
		chart.getTitle().setFont(new Font("�������", Font.BOLD, 20));
		chart.getTitle().setPadding(15, 0, 0, 0);
		// ���� Ÿ��Ʋ
		TextTitle copyright = new TextTitle("2023��", new Font("�������", Font.PLAIN, 12));
		copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		chart.addSubtitle(copyright); // ��Ʈ ���� Ÿ��Ʋ
		// ����
		chart.getLegend().setItemFont(new Font("�������", Font.BOLD, 10));
		// plot ����
		CategoryPlot plot = chart.getCategoryPlot();
		Font font = plot.getDomainAxis().getLabelFont();
		// X�� ��
		plot.getDomainAxis().setLabelFont(new Font("�������", font.getStyle(), font.getSize()));
		// X�� ������
		plot.getDomainAxis().setTickLabelFont(new Font("�������", font.getStyle(), 10));
		font = plot.getRangeAxis().getLabelFont();
		// Y�� ��
		plot.getRangeAxis().setLabelFont(new Font("�������", font.getStyle(), font.getSize()));
		// Y�� ����
		plot.getRangeAxis().setTickLabelFont(new Font("�������", font.getStyle(), 10));

		chartPanel.setBounds(0, 0, 505, 330);

		add(chartPanel);
		statisticsAWT.p7.add(this);
	}

	public CategoryDataset createDataset() { // ��Ʈ ������
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql = null;
		pool = DBConnectionMgr.getInstance();
		try {
			con = pool.getConnection();
			sql = "select *\r\n" + "from product\r\n" + "where PROD_STOCK>0";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dataset.addValue(rs.getInt("PROD_STOCK"), rs.getString("PROD_NAME"), rs.getString("PROD_NAME"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
		return dataset;
	}
}
