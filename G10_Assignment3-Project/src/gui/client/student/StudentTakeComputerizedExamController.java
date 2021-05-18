package gui.client.student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class StudentTakeComputerizedExamController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
    private Label sbExamOfCourseLbl;

    @FXML
    private TextArea sbGeneralInfoTa;

    @FXML
    private Label sbScorelbl;

    @FXML
    private ScrollPane sbQuestionSp;

    @FXML
    private ButtonBar sbQuestionBarBb;

    @FXML
    private ToggleGroup sbQuestionTg;

    @FXML
    private ScrollPane sbAnswersSp;

    @FXML
    private Label sbQuestionLbl;

    @FXML
    private RadioButton sbAnswer1Rb;

    @FXML
    private ToggleGroup sbAnswerTg;

    @FXML
    private RadioButton sbAnswer2Rb;

    @FXML
    private RadioButton sbAnswer3Rb;

    @FXML
    private RadioButton sbAnswer4Rb;

    @FXML
    private Button sbSubmitBtn;

    // STATIC JAVAFX INSTANCES **********************************************
	private static Label examOfCourseLbl;
	private static TextArea generalInfoTa;
	private static Label scorelbl;
	private static ButtonBar questionBarBb;
	private static Label questionLbl;
	private static ToggleGroup answerTg;
	private static RadioButton answer1Rb;
	private static RadioButton answer2Rb;
	private static RadioButton answer3Rb;
	private static RadioButton answer4Rb;
	private static Button submitBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		examOfCourseLbl = sbExamOfCourseLbl;
		generalInfoTa = sbGeneralInfoTa;
		scorelbl = sbScorelbl;
		questionBarBb = sbQuestionBarBb;
		questionLbl = sbQuestionLbl;
		answerTg = sbAnswerTg;
		answer1Rb = sbAnswer1Rb;
		answer2Rb = sbAnswer2Rb;
		answer3Rb = sbAnswer3Rb;
		answer4Rb = sbAnswer4Rb;
		submitBtn = sbSubmitBtn;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void rbPressAnswer1(ActionEvent event) {
		// TODO
	}

	@FXML
	void rbPressAnswer2(ActionEvent event) {
		// TODO
	}

	@FXML
	void rbPressAnswer3(ActionEvent event) {
		// TODO
	}

	@FXML
	void rbPressAnswer4(ActionEvent event) {
		// TODO
	}
	
	@FXML
	void btnPressSubmit(ActionEvent event) throws IOException {
		// TODO
		//successful submit example ***********************************
		ClientUI.mainStage.setWidth(600);
		ClientUI.mainStage.setHeight(400);
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentExamSubmitted.fxml")));
		//*************************************************************
	}
}
