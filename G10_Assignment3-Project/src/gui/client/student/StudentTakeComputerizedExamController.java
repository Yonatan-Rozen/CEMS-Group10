package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class StudentTakeComputerizedExamController implements Initializable {

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

	@FXML
	void rbPressAnswer1(ActionEvent event) {
		
	}

	@FXML
	void rbPressAnswer2(ActionEvent event) {

	}

	@FXML
	void rbPressAnswer3(ActionEvent event) {

	}

	@FXML
	void rbPressAnswer4(ActionEvent event) {

	}
}
