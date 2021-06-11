package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.jshell.spi.ExecutionControl.ExecutionControlException;
import logic.question.Question;

public class TeacherExamTypeController implements Initializable {
	public static TeacherExamTypeController tetController;

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private Button sbComputerizedBtn;

	@FXML
	private Button sbManualBtn;

	@FXML
	private ImageView sbManualImg;

	@FXML
	private ImageView sbComputerizedImg;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static Button computerizedBtn;
	private static Button manualBtn;
	private static ImageView manualImg;
	private static ImageView computerizedImg;

	// STATIC INSTANCES *****************************************************
//	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");
//	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
//	private static List<Question> questionList;
//	ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
//	private static String msg;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tetController = new TeacherExamTypeController();

		/**** First panel ****/
		botPanelAp = sbBotPanelAp;
		computerizedBtn = sbComputerizedBtn;
		manualBtn = sbManualBtn;
		manualImg = sbManualImg;
		computerizedImg = sbComputerizedImg;
		manualImg.setImage(new Image("/manualExam_icon.jpg"));
		computerizedImg.setImage(new Image("/automaticExam_icon.jpg"));
		manualImg.setFitWidth(300);
		computerizedImg.setFitWidth(300);
		manualImg.setFitHeight(141);
		computerizedImg.setFitHeight(141);
		manualImg.setCache(true);
		computerizedImg.setSmooth(true);
		manualImg.setPreserveRatio(false);
		computerizedImg.setPreserveRatio(false);
	}

	// ACTION METHODS *******************************************************

	@FXML
	void btnPressComputerized(ActionEvent event) throws Exception {
		System.out.println("TeacherExamType::btnPressComputerized");
		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateExam"));
	}

	@FXML
	void btnPressManual(ActionEvent event) throws Exception {
		System.out.println("TeacherExamType::btnPressManual");
		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateManualExam"));
	}
}