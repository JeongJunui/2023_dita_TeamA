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

		if (cbText.equals("입고")) { // 입고 카테고리 
			JFreeChart chart = ChartFactory.createBarChart("물품별 총 입고차트", "", "", createDataset());
			ChartPanel chartPanel = new ChartPanel(chart);

			// 타이틀
			chart.getTitle().setFont(new Font("맑은고딕", Font.BOLD, 20));
			chart.getTitle().setPadding(15, 0, 0, 0);
			// 서브 타이틀
			TextTitle copyright = new TextTitle("2023년", new Font("맑은고딕", Font.PLAIN, 12));
			copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			chart.addSubtitle(copyright); // 차트 서브 타이틀
			// 범례
			chart.getLegend().setItemFont(new Font("맑은고딕", Font.BOLD, 10));
			// plot 생성
			CategoryPlot plot = chart.getCategoryPlot();
			Font font = plot.getDomainAxis().getLabelFont();
			// X축 라벨
			plot.getDomainAxis().setLabelFont(new Font("맑은고딕", font.getStyle(), font.getSize()));
			// X축 도메인
			plot.getDomainAxis().setTickLabelFont(new Font("맑은고딕", font.getStyle(), 10));
			font = plot.getRangeAxis().getLabelFont();
			// Y축 라벨
			plot.getRangeAxis().setLabelFont(new Font("맑은고딕", font.getStyle(), font.getSize()));
			// Y축 범위
			plot.getRangeAxis().setTickLabelFont(new Font("맑은고딕", font.getStyle(), 10));

			chartPanel.setBounds(0, 0, 505, 330);

			add(chartPanel);
		} else if (cbText.equals("출고")) { // 출고 카테고리 
			JFreeChart chart2 = ChartFactory.createBarChart("물품별 총 출고차트", "", "", createDataset2());
			ChartPanel chartPanel2 = new ChartPanel(chart2);

			// 타이틀
			chart2.getTitle().setFont(new Font("맑은고딕", Font.BOLD, 20));
			chart2.getTitle().setPadding(15, 0, 0, 0);
			// 서브 타이틀
			TextTitle copyright2 = new TextTitle("2023년", new Font("맑은고딕", Font.PLAIN, 12));
			copyright2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			chart2.addSubtitle(copyright2); // 차트 서브 타이틀
			// 범례
			chart2.getLegend().setItemFont(new Font("맑은고딕", Font.BOLD, 10));
			// plot 생성
			CategoryPlot plot2 = chart2.getCategoryPlot();
			Font font = plot2.getDomainAxis().getLabelFont();
			// X축 라벨
			plot2.getDomainAxis().setLabelFont(new Font("맑은고딕", font.getStyle(), font.getSize()));
			// X축 도메인
			plot2.getDomainAxis().setTickLabelFont(new Font("맑은고딕", font.getStyle(), 10));
			font = plot2.getRangeAxis().getLabelFont();
			// Y축 라벨
			plot2.getRangeAxis().setLabelFont(new Font("맑은고딕", font.getStyle(), font.getSize()));
			// Y축 범위
			plot2.getRangeAxis().setTickLabelFont(new Font("맑은고딕", font.getStyle(), 10));

			chartPanel2.setBounds(0, 0, 505, 330);

			add(chartPanel2);

		}
		statisticsAWT.p7.add(this);
	}

	public CategoryDataset createDataset() { // 입력 차트 데이터
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
	
	public CategoryDataset createDataset2() { // 출력 차트 데이터
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		String sql = null;
		pool = DBConnectionMgr.getInstance();
		try {
			con = pool.getConnection();
			sql = "select p.PROD_NAME, sum(t.TAKEOUT_AMOUNT)\r\n"
					+ "from takeout_log t, product p \r\n"
					+ "where p.PROD_CODE = t.PROD_CODE \r\n"
					+ "group by t.PROD_CODE";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dataset2.addValue(rs.getInt("sum(t.TAKEOUT_AMOUNT)"), rs.getString("p.PROD_NAME"), rs.getString("p.PROD_NAME"));
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
