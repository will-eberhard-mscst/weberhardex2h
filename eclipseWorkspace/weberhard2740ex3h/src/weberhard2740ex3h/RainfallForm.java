package weberhard2740ex3h;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class RainfallForm extends JFrame {

	private JPanel contentPane;
	private JTextField inputTextField;
	private JList list;
	private JLabel totalLabel;
	private JLabel averageLabel;
	private JLabel highestLabel;
	private JLabel lowestLabel;
	private String[] strRainfall = {
			"1.2", "2.7", "2.2", "3.1", "2.9", "5.1", 
			"3.2", "2.7", "3.6", "1.8", "2.2", "1.7"
	};
	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RainfallForm frame = new RainfallForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RainfallForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMonthlyRainfall = new JLabel("Monthly Rainfall:");
		lblMonthlyRainfall.setBounds(10, 11, 92, 14);
		contentPane.add(lblMonthlyRainfall);
		
		JList disList = new JList();
		disList.setEnabled(false);
		disList.setBackground(SystemColor.menu);
		disList.setForeground(Color.BLACK);
		disList.setModel(new AbstractListModel() {
			String[] values = new String[] {"01 Jan", "02 Feb", "03 Mar", "04 Apr", "05 May", "06 Jun", "07 Jul", "08 Aug", "09 Sep", "10 Oct", "11 Nov", "12 Dec"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		disList.setBounds(10, 36, 43, 233);
		contentPane.add(disList);
		
		list = new JList(strRainfall);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				btnUpdate.setEnabled(true);
				inputTextField.setText((String) list.getSelectedValue());
				inputTextField.requestFocus();
				inputTextField.selectAll();
			}
		});
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setForeground(Color.BLACK);
		list.setBackground(Color.WHITE);
		list.setBounds(63, 36, 43, 233);
		contentPane.add(list);
		
		inputTextField = new JTextField();
		inputTextField.setText("0.0");
		inputTextField.setBounds(113, 173, 43, 20);
		contentPane.add(inputTextField);
		inputTextField.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(127, 37, 46, 14);
		contentPane.add(lblTotal);
		
		JLabel lblAverage = new JLabel("Average:");
		lblAverage.setBounds(127, 62, 67, 14);
		contentPane.add(lblAverage);
		
		JLabel lblHighest = new JLabel("Highest:");
		lblHighest.setBounds(127, 87, 46, 14);
		contentPane.add(lblHighest);
		
		JLabel lblLowest = new JLabel("Lowest:");
		lblLowest.setBounds(127, 113, 46, 14);
		contentPane.add(lblLowest);
		
		totalLabel = new JLabel("0.0");
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBounds(221, 37, 46, 14);
		contentPane.add(totalLabel);
		
		averageLabel = new JLabel("0.0");
		averageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		averageLabel.setBounds(221, 62, 46, 14);
		contentPane.add(averageLabel);
		
		highestLabel = new JLabel("0.0");
		highestLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		highestLabel.setBounds(221, 87, 46, 14);
		contentPane.add(highestLabel);
		
		lowestLabel = new JLabel("0.0");
		lowestLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lowestLabel.setBounds(221, 113, 46, 14);
		contentPane.add(lowestLabel);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rainfall rainfall = new Rainfall(strRainfall);
				
				DecimalFormat fmt = new DecimalFormat("0.0");
				totalLabel.setText(fmt.format(rainfall.getTotal()));
				averageLabel.setText(fmt.format(rainfall.getAverage()));
				highestLabel.setText(fmt.format(rainfall.getHighest()));
				lowestLabel.setText(fmt.format(rainfall.getLowest()));
			}
		});
		btnCalculate.setBounds(152, 139, 104, 23);
		contentPane.add(btnCalculate);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = list.getSelectedIndex();
				double r = Double.parseDouble(inputTextField.getText());
				strRainfall[selectedIndex] = Double.toString(r);
				list.repaint();
				
				inputTextField.setText("0.0");
				btnUpdate.setEnabled(false);
				totalLabel.setText("");
				averageLabel.setText("");
				highestLabel.setText("");
				lowestLabel.setText("");
			}
		});
		btnUpdate.setBounds(166, 172, 84, 23);
		contentPane.add(btnUpdate);
	}
}
