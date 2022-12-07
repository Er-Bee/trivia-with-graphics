import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TriviaController {
	
	private TriviaLogic tl;

    @FXML
    private Button button_a;

    @FXML
    private Button button_b;

    @FXML
    private Button button_c;

    @FXML
    private Button button_d;

    @FXML
    private Label main_label;

    @FXML
    private Label score;

    // buttons representing choices (topics at first, then answers for questions).
    // whenever a button is pressed, send it's label (the possible answer) and check if it's the correct one.
    @FXML
    void aPressed(ActionEvent event) {
    	if (tl.isTopicNull()) {
    		tl = new TriviaLogic(button_a.getText());
    		update();
    	}
    	else {
    		tl.checkAnswer(button_a.getText());
    		update();
    	}
    		
    }

    @FXML
    void bPressed(ActionEvent event) {
    	if (tl.isTopicNull()) {
    		tl = new TriviaLogic(button_b.getText());
    		update();
    	}
    	else {
    		tl.checkAnswer(button_b.getText());
    		update();
    	}
    }

    @FXML
    void cPressed(ActionEvent event) {
    	if (tl.isTopicNull()) {
    		tl = new TriviaLogic(button_c.getText());
    		update();
    	}
    	else {
    		tl.checkAnswer(button_c.getText());
    		update();
    	}
    }

    @FXML
    void dPressed(ActionEvent event) {
    	if (tl.isTopicNull()) {
    		tl = new TriviaLogic(button_d.getText());
    		update();
    	}
    	else {
    		tl.checkAnswer(button_d.getText());
    		update();
    	}
    }

    @FXML
    void endPressed(ActionEvent event) {
    	endGame();
    }
    
    void endGame() {
    	tl.endGame();
		Stage stage = (Stage)button_a.getScene().getWindow();
		stage.close();
    }
    
    // start the game, change labels accordingly
    public void startGame() {
    	tl = new TriviaLogic();
    	main_label.setText("Please pick a topic for the questions!");
    	button_a.setText("geography");
    	button_b.setText("movies");
    	button_c.setText("music");
    	button_d.setText("science");
    	score.setText(String.valueOf(tl.getScore()));
    }
    
    private Random rand = new Random();
    private int order;
    private String next_question;
    public void update() {
    	// will happen after every answer button press, update question and button labels with possible answers, update score according to previous answer.
    	if((next_question = tl.getQuestion()) == "end") {
    		endGame();
    	}
    	else {
    		score.setText(String.valueOf(tl.getScore()));
    		main_label.setText(next_question);
    		order = rand.nextInt(tl.getListLength());
    		button_a.setText(tl.getPossibleAnswer(order));
    		order = rand.nextInt(tl.getListLength());
    		button_b.setText(tl.getPossibleAnswer(order));
    		order = rand.nextInt(tl.getListLength());
    		button_c.setText(tl.getPossibleAnswer(order));
    		order = rand.nextInt(tl.getListLength());
    		button_d.setText(tl.getPossibleAnswer(order));
    	}
    	
    }
    
    public void initialize() {
    	startGame();
    }

}
