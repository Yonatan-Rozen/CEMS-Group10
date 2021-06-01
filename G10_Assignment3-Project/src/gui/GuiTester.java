package gui;

import gui.client.teacher.TeacherPreviewQuestionController;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiTester extends Application{
	public static void main(String args[]) throws Exception {
		launch(args);
		//GREATER than the "greatest" Comment
	}

	@Override
	public void start(Stage stage) throws Exception {
		new TeacherPreviewQuestionController().start(stage);
	}
}
