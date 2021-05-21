package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherStartExamController implements Initializable {

	// JAVAFX INSTANCES *****************************************************
	@FXML
	private ComboBox<?> sbChoseExamCb;

	@FXML
	private TextField sbCodeTf;

	@FXML
	private Button sbStartBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private ComboBox<?> choseExamCb;
	private TextField codeTf;
	private Button startBtn;

	public void start(Stage mainStage) throws IOException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choseExamCb = sbChoseExamCb;
		codeTf = sbCodeTf;
		CommonMethodsHandler.getInstance().addTextLimiter(codeTf, 4);
		startBtn = sbStartBtn;
	}

	@FXML
	void btnPressStart(ActionEvent event) {
	}

	@FXML
	void cbPressChoseExam(ActionEvent event) {
	}
}
