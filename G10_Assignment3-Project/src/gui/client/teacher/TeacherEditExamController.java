package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TeacherEditExamController implements Initializable{

	// JAVAFX INSTNCES ******************************************************
    @FXML
    private AnchorPane sbBotPanelAp;

    @FXML
    private Button sbEditSelectedExamBtn;

    @FXML
    private Button sbChangeCourseBtn;

    @FXML
    private TableView<String> sbExamsTv;

    @FXML
    private TableColumn<?, String> sbExamIDTc;

    @FXML
    private Button sbPreviewExamBtn;

    @FXML
    private AnchorPane sbMidPanelAp;

    @FXML
    private ComboBox<String> sbChooseCourseCb;

    @FXML
    private Button sbContinue2Btn;

    @FXML
    private Button sbChangeBankBtn;

    @FXML
    private AnchorPane sbTopPanelAp;

    @FXML
    private ComboBox<String> sbChooseBankCb;

    @FXML
    private Button sbContinue1Btn;
    
    // STATIC JAVAFX INSTANCES **********************************************
    private static AnchorPane botPanelAp;
    private static Button editSelectedExamBtn;
    private static Button changeCourseBtn;
    private static TableView<String> examsTv;
    private static TableColumn<?, String> examIDTc;
    private static Button previewExamBtn;
    private static AnchorPane midPanelAp;
    private static ComboBox<String> chooseCourseCb;
    private static Button continue2Btn;
    private static Button changeBankBtn;
    private static AnchorPane topPanelAp;
    private static ComboBox<String> chooseBankCb;
    private static Button continue1Btn;

    // INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
	    editSelectedExamBtn = sbEditSelectedExamBtn;
	    changeCourseBtn = sbChangeCourseBtn;
	    examsTv = sbExamsTv;
	    examIDTc = sbExamIDTc;
	    previewExamBtn = sbPreviewExamBtn;
	    midPanelAp = sbMidPanelAp;
	    midPanelAp.setDisable(true);
	    chooseCourseCb = sbChooseCourseCb;
	    continue2Btn = sbContinue2Btn;
	    changeBankBtn = sbChangeBankBtn;
	    topPanelAp = sbTopPanelAp;
	    chooseBankCb = sbChooseBankCb;
	    continue1Btn = sbContinue1Btn;
	}

    @FXML
    void cbPressChooseBank(ActionEvent event) {
    	System.out.println("TeacherEditExam::cbPressChooseBank");

    }

    @FXML
    void btnPressContinue1(ActionEvent event) {
    	System.out.println("TeacherEditExam::btnPressContinue1");
    	topPanelAp.setDisable(true);
    	midPanelAp.setDisable(false);
    }

    @FXML
    void btnPressChangeBank(ActionEvent event) {
    	System.out.println("TeacherEditExam::btnPressChangeBank");
    	topPanelAp.setDisable(false);
    	midPanelAp.setDisable(true);
    }

    @FXML
    void btnPressContinue2(ActionEvent event) {
    	System.out.println("TeacherEditExam::btnPressContinue2");
    	midPanelAp.setDisable(true);
    	botPanelAp.setDisable(false);
    }

    @FXML
    void btnPressChangeCourse(ActionEvent event) {
    	System.out.println("TeacherEditExam::btnPressChangeCourse");
    	midPanelAp.setDisable(false);
    	botPanelAp.setDisable(true);
    }

    @FXML
    void btnPressEditSelectedExam(ActionEvent event) {
    	System.out.println("TeacherEditExam::btnPressEditSelectedExam");
    }

    @FXML
    void btnPressPreviewExam(ActionEvent event) {
    	System.out.println("TeacherEditExam::btnPressPreviewExam");
    }

    @FXML
    void cbPressChooseCourse(ActionEvent event) {
    	System.out.println("TeacherEditExam::cbPressChooseCourse");
    }

}
