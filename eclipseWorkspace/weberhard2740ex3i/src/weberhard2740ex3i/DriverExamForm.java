package weberhard2740ex3i;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DriverExamForm extends JFrame {

	private JPanel contentPane;
	private JLabel questNumLabel;
	private JTextField inputAnswerTextField;
	private JList responsesList;
	private JLabel resultLabel;
	private JButton prevButton;
	private JButton nextButton;
	private DefaultListModel responsesListModel;
	private DriverExam exam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverExamForm frame = new DriverExamForm();
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
	public DriverExamForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBackground(SystemColor.menu);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setEnabled(false);
		list.setBounds(10, 33, 38, 191);
		contentPane.add(list);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

		        prevButton.setEnabled(true);
		        nextButton.setEnabled(true);
		        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
		            nextButton.setEnabled(false);
		        if (responsesList.getSelectedIndex() == 0) 
		            prevButton.setEnabled(false);
		        inputAnswerTextField.requestFocus(); 
			}
		});
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setBounds(58, 33, 38, 191);
		contentPane.add(responsesList);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(10, 8, 75, 14);
		contentPane.add(lblResponses);
		
		questNumLabel = new JLabel("0");
		questNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		questNumLabel.setBounds(106, 116, 29, 14);
		contentPane.add(questNumLabel);
		
		inputAnswerTextField = new JTextField();
		inputAnswerTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		inputAnswerTextField.setBounds(106, 141, 29, 20);
		contentPane.add(inputAnswerTextField);
		inputAnswerTextField.setColumns(10);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(174, 8, 46, 14);
		contentPane.add(lblResult);
		
		resultLabel = new JLabel("New label");
		resultLabel.setBounds(174, 34, 46, 14);
		contentPane.add(resultLabel);
		
		JButton btnPass = new JButton("Pass");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exam.setResponses((DefaultListModel) responsesList.getModel());
				int invalid = exam.validate();
				if(invalid >= 0){
					resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
					responsesList.setSelectedIndex(invalid);
				}
				else{
					if(exam.passed()) resultLabel.setText("You passed");
					else resultLabel.setText("You Failed");
				}
			}
		});
		btnPass.setBounds(156, 59, 102, 23);
		contentPane.add(btnPass);
		
		JButton btnCorrect = new JButton("Correct");
		btnCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exam.setResponses((DefaultListModel) responsesList.getModel());
				int invalid = exam.validate();
				if(invalid >= 0){
					resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
					responsesList.setSelectedIndex(invalid);
				}
				else{
					resultLabel.setText(Integer.toString(exam.totalCorrect()));
				}
			}
		});
		btnCorrect.setBounds(156, 93, 102, 23);
		contentPane.add(btnCorrect);
		
		JButton btnIncorrect = new JButton("Incorrect");
		btnIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exam.setResponses((DefaultListModel) responsesList.getModel());
				int invalid = exam.validate();
				if(invalid >= 0){
					resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
					responsesList.setSelectedIndex(invalid);
				}
				else{
					resultLabel.setText(Integer.toString(exam.totalIncorrect()));
				}
			}
		});
		btnIncorrect.setBounds(156, 127, 102, 23);
		contentPane.add(btnIncorrect);
		
		JButton btnListIncorrect = new JButton("List Incorrect");
		btnListIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exam.setResponses((DefaultListModel) responsesList.getModel());
				int invalid = exam.validate();
				if(invalid >= 0){
					resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
					responsesList.setSelectedIndex(invalid);
				}
				else{
					resultLabel.setText(Integer.toString(exam.questionsMissed()));
				}
			}
		});
		btnListIncorrect.setBounds(156, 161, 102, 23);
		contentPane.add(btnListIncorrect);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				responsesListModel.setElementAt(
		                inputAnswerTextField.getText().toUpperCase(), 
		                responsesList.getSelectedIndex());
		        responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
		        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		        inputAnswerTextField.setText((String)responsesList.getSelectedValue());
		        
		        prevButton.setEnabled(true);
		        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
		            nextButton.setEnabled(false);
		        inputAnswerTextField.requestFocus();
			}
		});
		nextButton.setBounds(174, 195, 55, 23);
		contentPane.add(nextButton);
		
		prevButton = new JButton("Prev");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        responsesListModel.setElementAt(
		                inputAnswerTextField.getText().toUpperCase(), 
		                responsesList.getSelectedIndex());
		        responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
		        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

		        nextButton.setEnabled(true);
		        if (responsesList.getSelectedIndex() == 0) 
		            prevButton.setEnabled(false);
		        inputAnswerTextField.requestFocus();
			}
		});
		prevButton.setEnabled(false);
		prevButton.setBounds(174, 228, 55, 23);
		contentPane.add(prevButton);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.");
		this.exam = mapper.getDriverExam();
		this.responsesListModel = exam.getAnswers();
		responsesList.setModel(this.responsesListModel);
	}
}
