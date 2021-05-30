package gui;

import gui.client.teacher.TeacherPreviewQuestionController;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiTester extends Application{
	public static Stage primaryStage;
	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		new TeacherPreviewQuestionController().start();
	}
}
