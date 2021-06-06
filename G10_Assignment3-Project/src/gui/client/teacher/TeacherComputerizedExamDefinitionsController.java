package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.question.Question;

public class TeacherComputerizedExamDefinitionsController implements Initializable {
	public static TeacherComputerizedExamDefinitionsController tcedController;

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private Button sbContinue2Btn;

	@FXML
	private Button sbChangeBankBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;

	// STATIC INSTANCES *****************************************************

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcedController = new TeacherComputerizedExamDefinitionsController();

		/**** First panel ****/
		botPanelAp = sbBotPanelAp;

	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {

	}

	@FXML
	void btnPressContinue2(ActionEvent event) {

	}

}
