package warehouse;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class ReleasedAWT {

	private JFrame frame;
	JPanel p1;
	JPanel p2;
	private JLabel lblNewLabel_1;
	private JTextField searchTextField;
	private JTable table;
	MyTableCellRenderer tcr;
	ReleasedMgr rsl;
	ReleaseAWT2 awt;
	private Object[][] tableContent=new Object[0][6];
	String[] tableCol= {"물품코드","카테고리","물품명","사이즈","색상","재고량"};
	DefaultTableModel dtm; 
	JScrollPane tableScroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReleasedAWT window = new ReleasedAWT();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReleasedAWT() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void openAWT2(String s)
	{
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rsl=new ReleasedMgr();
		dtm=new DefaultTableModel(tableContent, tableCol);
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(44,122,147));
		p1.setBounds(0, 0, 133, 461);
		frame.getContentPane().add(p1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ReleasedAWT.class.getResource("/warehouse/images/releaseTitle.png")));
		lblNewLabel.setBounds(20, 10, 85, 75);
		p1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(new Color(44, 127, 147));
		btnNewButton.setBackground(new Color(44,122,147));
		btnNewButton.setIcon(new ImageIcon(ReleasedAWT.class.getResource("/warehouse/images/homeBtn.png")));
		btnNewButton.setBounds(20, 338, 97, 113);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		p1.add(btnNewButton);
		p2=new JPanel();
		p2.setLayout(null);
		p2.setBackground(new Color(0,32,96));
		p2.setBounds(132, 0, 552, 461);
		frame.getContentPane().add(p2);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ReleasedAWT.class.getResource("/warehouse/images/release.png")));
		lblNewLabel_1.setBounds(145, 10, 153, 75);
		p2.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 32, 96));
		panel.setBounds(145, 95, 527, 356);
		p2.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 32, 96));
		panel_1.setBounds(12, 10, 503, 53);
		panel.add(panel_1);
		
		JLabel searchLabel = new JLabel("");
		searchLabel.setIcon(new ImageIcon(ReleasedAWT.class.getResource("/warehouse/images/search.png")));
		panel_1.add(searchLabel);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Dialog", Font.PLAIN, 19));
		panel_1.add(searchTextField);
		searchTextField.setColumns(10);
		Vector<ProductBean> allResult=rsl.loadWhenOpened();
		for(int i=0;i<allResult.size();i++)
		{
			ProductBean bean=allResult.elementAt(i);
			Vector<Object> vlist=new Vector<Object>();
			vlist.addElement(bean.getProdCode());
			vlist.addElement(bean.getCategory());
			vlist.addElement(bean.getProdName());
			vlist.addElement(bean.getProdSize());
			vlist.addElement(bean.getProdColor());
			vlist.addElement(bean.getProdStock());
			dtm.addRow(vlist);
		}
		
		JButton searchButton = new JButton("");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword=searchTextField.getText();
				if(keyword.length()==0)
				{
					JOptionPane.showMessageDialog(null,"검색할 키워드를 입력하시오.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				Vector<ProductBean> searchResult=rsl.loadWarehouseOut(keyword);
				int l=dtm.getRowCount();
				for(int i=l-1;i>=0;i--)
					dtm.removeRow(i);
				for(int i=0;i<searchResult.size();i++)
				{
					ProductBean bean=searchResult.elementAt(i);
					Vector<Object> vlist=new Vector<Object>();
					vlist.addElement(bean.getProdCode());
					vlist.addElement(bean.getCategory());
					vlist.addElement(bean.getProdName());
					vlist.addElement(bean.getProdSize());
					vlist.addElement(bean.getProdColor());
					vlist.addElement(bean.getProdStock());
					dtm.addRow(vlist);
				}
				//String[][] tableContents=new String[][6]();
				//while() {}
			}
		});
		searchButton.setIcon(new ImageIcon(ReleasedAWT.class.getResource("/warehouse/images/searchBtn.png")));
		panel_1.add(searchButton);
		searchButton.setBorderPainted(false);
		searchButton.setFocusPainted(false);
		searchButton.setContentAreaFilled(false);
		
		table = new JTable(dtm);
		table.setRowSelectionAllowed(true);
		tableScroll=new JScrollPane(table);
		tableScroll.setBounds(12, 73, 503, 233);
		panel.add(tableScroll);
		
		JButton releaseButton = new JButton("");
		releaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0)
				{
					JOptionPane.showMessageDialog(null,"출고를 진행할 물품을 선택하시오.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				String s=dtm.getDataVector().elementAt(table.getSelectedRow()).elementAt(0).toString();
				int n=Integer.parseInt(dtm.getDataVector().elementAt(table.getSelectedRow()).elementAt(5).toString());
				if(awt==null)
					awt=new ReleaseAWT2(s,n);
				else
					awt.resetCode(s, n);
				//int l=dtm.getRowCount();
				//for(int i=l-1;i>=0;i--)
				//	dtm.removeRow(i);
			}
		});
		releaseButton.setIcon(new ImageIcon(ReleasedAWT.class.getResource("/warehouse/images/releaseBtn.png")));
		releaseButton.setBounds(410, 316, 105, 30);
		panel.add(releaseButton);
		releaseButton.setBorderPainted(false);
		releaseButton.setFocusPainted(false);
		releaseButton.setContentAreaFilled(false);
	}
}