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
	String cbText;
	StatisticsAWT statisticsAWT;

	public GanttChartMgr(StatisticsAWT statisticsAWT, String cbText) {
		this.statisticsAWT = statisticsAWT;
		this.cbText = cbText;
		setLayout(null);
		setBackground(Color.white);
		setBounds(25, 15, 505, 340);

		if (cbText.equals("ÀÔ°í")) { // ÀÔ°í Ä«Å×°í¸® 
			JFreeChart chart = ChartFactory.createBarChart("¹°Ç°º° ÃÑ ÀÔ°íÂ÷Æ®", "", "", createDataset());
			ChartPanel chartPanel = new ChartPanel(chart);

			// Å¸ÀÌÆ²
			chart.getTitle().setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
			chart.getTitle().setPadding(15, 0, 0, 0);
			// ¼­ºê Å¸ÀÌÆ²
			TextTitle copyright = new TextTitle("2023³â", new Font("¸¼Àº°íµñ", Font.PLAIN, 12));
			copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			chart.addSubtitle(copyright); // Â÷Æ® ¼­ºê Å¸ÀÌÆ²
			// ¹ü·Ê
			chart.getLegend().setItemFont(new Font("¸¼Àº°íµñ", Font.BOLD, 10));
			// plot »ý¼º
			CategoryPlot plot = chart.getCategoryPlot();
			Font font = plot.getDomainAxis().getLabelFont();
			// XÃà ¶óº§
			plot.getDomainAxis().setLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), font.getSize()));
			// XÃà µµ¸ÞÀÎ
			plot.getDomainAxis().setTickLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), 10));
			font = plot.getRangeAxis().getLabelFont();
			// YÃà ¶óº§
			plot.getRangeAxis().setLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), font.getSize()));
			// YÃà ¹üÀ§
			plot.getRangeAxis().setTickLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), 10));

			chartPanel.setBounds(0, 0, 505, 330);

			add(chartPanel);
		} else if (cbText.equals("Ãâ°í")) { // Ãâ°í Ä«Å×°í¸® 
			JFreeChart chart2 = ChartFactory.createBarChart("¹°Ç°º° ÃÑ Ãâ°íÂ÷Æ®", "", "", createDataset2());
			ChartPanel chartPanel2 = new ChartPanel(chart2);

			// Å¸ÀÌÆ²
			chart2.getTitle().setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
			chart2.getTitle().setPadding(15, 0, 0, 0);
			// ¼­ºê Å¸ÀÌÆ²
			TextTitle copyright2 = new TextTitle("2023³â", new Font("¸¼Àº°íµñ", Font.PLAIN, 12));
			copyright2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			chart2.addSubtitle(copyright2); // Â÷Æ® ¼­ºê Å¸ÀÌÆ²
			// ¹ü·Ê
			chart2.getLegend().setItemFont(new Font("¸¼Àº°íµñ", Font.BOLD, 10));
			// plot »ý¼º
			CategoryPlot plot2 = chart2.getCategoryPlot();
			Font font = plot2.getDomainAxis().getLabelFont();
			// XÃà ¶óº§
			plot2.getDomainAxis().setLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), font.getSize()));
			// XÃà µµ¸ÞÀÎ
			plot2.getDomainAxis().setTickLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), 10));
			font = plot2.getRangeAxis().getLabelFont();
			// YÃà ¶óº§
			plot2.getRangeAxis().setLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), font.getSize()));
			// YÃà ¹üÀ§
			plot2.getRangeAxis().setTickLabelFont(new Font("¸¼Àº°íµñ", font.getStyle(), 10));

			chartPanel2.setBounds(0, 0, 505, 330);

			add(chartPanel2);

		}
		statisticsAWT.p7.add(this);
	}

	public CategoryDataset createDataset() { // ÀÔ·Â Â÷Æ® µ¥ÀÌÅÍ
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
	
	public CategoryDataset createDataset2() { // Ãâ·Â Â÷Æ® µ¥ÀÌÅÍ
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		String sql = null;
		pool = DBConnectionMgr.getInstance();
		try {
			con = pool.getConnection();
			sql = "select *\r\n" + "from takeout_log\r\n" + "where TAKEOUT_AMOUNT>0";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dataset2.addValue(rs.getInt("TAKEOUT_AMOUNT"), rs.getString("PROD_CODE"), rs.getString("PROD_CODE"));
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
		return dataset2;
	}
}
