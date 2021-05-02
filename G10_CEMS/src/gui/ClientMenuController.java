package gui;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 
 * @author Yonatan Rozen
 *
 */
public class ClientMenuController {
	public static ExamDataInfoController edic;
	@FXML
	private Button btnGetExamInfo;
	
	private static Stage currentStage;
	public void start(Stage primaryStage) {
		try {
			currentStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ClientMenu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/gui/ClientMenu.css").toExternalForm());
			primaryStage.setTitle("CEMS - Client");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				ClientUI.chat.accept("client disconnected");
				primaryStage.hide();
				System.exit(0);
			});
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBtn(ActionEvent event) throws Exception {
		Point windowPosition = new Point(currentStage.getX(), currentStage.getY());
		((Node)event.getSource()).getScene().getWindow().hide();
		edic = new ExamDataInfoController();
		edic.start(new Stage(), windowPosition);
	}

}
