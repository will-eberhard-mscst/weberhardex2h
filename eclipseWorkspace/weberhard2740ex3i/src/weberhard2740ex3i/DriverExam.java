package weberhard2740ex3i;

import javax.swing.DefaultListModel;

public class DriverExam {
	private char[] answers = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D'};
	private char[] responses;
	private final double requiredPct = 0.7;
	
	public DriverExam(char[] answers){
		this.answers = new char[answers.length];
		
		for(int i = 0; i < answers.length; i++)
			answers[i] = answers[i];
	}
	
	public DriverExam(DefaultListModel answers){
		this.answers = new char[answers.getSize()];
		for(int i = 0; i < answers.getSize(); i++){
			String r = (String) answers.get(i);
			this.answers[i] = r.charAt(0);
		}
	}
	
	public void setResponses(DefaultListModel responses){
		this.responses = new char[responses.getSize()];
		for(int i = 0; i < responses.getSize(); i++){
			String r = (String) responses.get(i);
			this.responses[i] = r.charAt(0);
		}
		
	}
	
	public DefaultListModel getAnswers(){
		DefaultListModel answersListModel = new DefaultListModel();
		
		return answersListModel;
	}
	
	public int validate(){
		int i = 0;
		while(i < responses.length){
			if(i != 'A' || i != 'B' || i != 'C' || i != 'D'){
				return i;
			}
			i++;
		}
		return i;
	}
	
	public boolean passed(){
		boolean passed = false;
		if(totalCorrect() >= requiredPct * 10)
			passed = true;
		
		return passed;
		
	}
	
	public int totalCorrect(){
		int i = 0;
		int correct = 0;
		while(i < responses.length){
			i++;
			if(answers[i] == responses[i])
				correct += 1;
		}
		return correct;
	}
	
	public int totalIncorrect(){
		int i = 0;
		int incorrect = 0;
		while(i < responses.length){
			i++;
			if(answers[i] != responses[i])
				incorrect += 1;
		}
		return incorrect;
		
	}
	
	public int[] questionsMissed(){
		int[] missed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int m = 0;
		for(int i = 0; i < responses.length; i++){
			if(answers[i] != responses[i]){
				missed[m] = i + 1;
				m++;
			}
		}
		return missed;
	}

}
