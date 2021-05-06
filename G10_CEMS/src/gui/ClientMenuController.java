package gui;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ClientMenuController implements Initializable{
	public static ExamDataInfoController edic;
	
	@FXML
	private ImageView imgView;
	
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
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Client disconnected");
				alert.setHeaderText("You have been disconnected from the server");
				alert.setContentText("Press ok to continue...");
				alert.showAndWait();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imgView.setImage(new Image (getClass().getResourceAsStream("/logo.png")));
	}

}
