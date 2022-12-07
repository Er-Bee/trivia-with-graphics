import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class TriviaLogic {
	// when starting a new game, reset points and need to ask for a topic.
	// read questions file using java.util.Scanner and java.io.File
	// made a file with questions for each topic.
	// after every answer, notify whether it was correct or not through a popup using joptionpane.
	
	private Scanner input;
	private String question;
	private String answer;
	private String topic = null;
	private int score = 0;
	private ArrayList <String> possible_answers;
	
	// when topic = null, a topic hasn't been chosen yet, this indicated to TriviaController that the game 
	// has yet to start and the buttons need to represent a topic to be chosen.
	public TriviaLogic() {
		topic = null;
	}
	
	public TriviaLogic(String topic) {
		score = 0;
		this.topic = topic;
		possible_answers = new ArrayList<String>();
		try {
			input = new Scanner(new File(topic + ".txt"));
		} 
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public boolean isTopicNull() {
		return topic == null;
	}
	
	// in file, the lines are made like this:
	// line 1: question, line 2: correct answer, line 3: wrong answer, line 4: wrong answer, line 5: wrong answer.
	// we hold the question in the variable 'question' and the correct answer in the variable 'answer'.
	public String getQuestion() {
		if(!input.hasNext() || (question = input.nextLine()) == null) 
			return "end";
		else {
			answer = input.nextLine();
			possible_answers.add(new String(answer));
			possible_answers.add(input.nextLine());
			possible_answers.add(input.nextLine());
			possible_answers.add(input.nextLine());
			return question;
		}
	}
	
	public String getPossibleAnswer(int index) {
		return possible_answers.remove(index);
	}
	
	public void checkAnswer(String ans) {
		if (answer.equals(ans)) {
			JOptionPane.showMessageDialog(null, "correct! the answer was: " + this.answer + ".");
			score += 10;
		}
		else {
			JOptionPane.showMessageDialog(null, "incorrect :(  the answer was: " + this.answer + ".");
			score -=5;
		}
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getListLength() {
		return possible_answers.size();
	}
	
	public void endGame() {
		try {
			input.close();
		}
		catch (NullPointerException e){
			;
		}
		JOptionPane.showMessageDialog(null, "You finished the trivia quiz with " + score + " points!!");
	}
}
