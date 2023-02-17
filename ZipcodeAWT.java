package warehouse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ZipcodeAWT {

	private JFrame frame;
	private JTextField textField;
	ZipcodeMgr mgr;
	private JList searchedList;
	ReleaseAWT2 awt;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZipcodeAWT window = new ZipcodeAWT();
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
	public ZipcodeAWT(ReleaseAWT2 awt) {
		this.awt=awt;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mgr=new ZipcodeMgr();
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 500);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ZipcodeAWT.class.getResource("/warehouse/images/searchAddress.png")));
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchedList.isSelectionEmpty())
				{
					JOptionPane.showMessageDialog(null,"주소를 선택하십시오.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				awt.setAddress(searchedList.getSelectedValue().toString());
			}
		});
		btnNewButton.setIcon(new ImageIcon(ZipcodeAWT.class.getResource("/warehouse/images/selectBtn.png")));
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("도로명: ");
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword=textField.getText();
				if(keyword.length()==0)
				{
					JOptionPane.showMessageDialog(null,"검색할 키워드를 입력하십시오.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				searchedList.removeAll();
				Vector<String> serachResult=mgr.getSearchedResult(keyword);
				if(serachResult.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.\n정확한 도로명을 입력하였는지 확인해 주십시오.","알림",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				/*
				for(int i=0; i<serachResult.size();i++) {
					ZipcodeBean bean = serachResult.get(i);
					String str = bean.getZipcode()+" ";
					str += bean.getArea1()+" ";
					str += bean.getArea2()+" ";
					str += bean.getArea3()+" ";
					searchedList.add;
				}*/
				searchedList.setListData(serachResult);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ZipcodeAWT.class.getResource("/warehouse/images/addressSearchBtn.png")));
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		
		searchedList = new JList();
		searchedList.setPreferredSize(new Dimension(270,250));
		panel.add(searchedList);
		searchedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

}
