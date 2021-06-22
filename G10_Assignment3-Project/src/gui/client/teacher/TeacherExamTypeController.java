package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * A controller that leads to either creating a manual exam or a computerized exam
 * @author Eliran Amerzoyev
 */
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
	private ImageView sbTypeImg;


	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static Button computerizedBtn;
	private static Button manualBtn;
	private static ImageView typeImg;


	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tetController = new TeacherExamTypeController();

		/**** First panel ****/
		botPanelAp = sbBotPanelAp;
		computerizedBtn = sbComputerizedBtn;
		manualBtn = sbManualBtn;
		typeImg = sbTypeImg;
		typeImg.setImage(new Image("/img_examType.png"));
		typeImg.setFitWidth(925);
		typeImg.setFitHeight(230);
		typeImg.setCache(true);
		typeImg.setPreserveRatio(false);
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