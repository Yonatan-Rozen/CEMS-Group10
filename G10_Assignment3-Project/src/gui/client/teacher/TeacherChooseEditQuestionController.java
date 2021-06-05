package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import logic.question.Question;

public class TeacherChooseEditQuestionController implements Initializable {
	public static TeacherChooseEditQuestionController tceqController;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbTopPanelAp;
	
	@FXML
	private ChoiceBox<String> sbQuestionSubjectCb;

	@FXML
	private Button sbShowQuestionsBySubjectBtn;
	
	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private TableView<Question> sbAvailableQuestionsTv;

	@FXML
	private TableColumn<Question, String> sbQuestionIDTc;
	
	@FXML
	private TableColumn<Question, Void> sbPreviewTc;

	@FXML
	private Button sbChangeSubjectBtn;

	@FXML
	private Button sbEditQuestionBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> questionSubjectCb;
	private static Button showQuestionsBySubjectBtn;
	protected static AnchorPane botPanelAp;
	private static TableView<Question> availableQuestionsTv;
	private static TableColumn<Question, String> questionIDTc;
	private static TableColumn<Question, Void> previewTc;
	private static Button changeSubjectBtn;
	private static Button editQuestionBtn;
	
	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> subjectList = FXCollections.observableArrayList("----------");
	private static List<Question> questionList;
	private static RadioButton selected;
	private static String msg;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tceqController = new TeacherChooseEditQuestionController();
		/**** First panel ****/
		topPanelAp = sbTopPanelAp;
		
		//////////////////////////////////////////////////////
		questionSubjectCb = sbQuestionSubjectCb;
		
		// set "----------" as the first value of the choice box
		questionSubjectCb.setValue("----------");
		
		// set the choice box to get it's items from 'subjectList'
		questionSubjectCb.setItems(subjectList);
		
		// set up a listener that sets the disable value of 
		// 'createQuestionBtn' acurding to the selected value
		questionSubjectCb.getSelectionModel().selectedItemProperty().addListener(
			(ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
			{
				if (newValue != null) {
					if (newValue.equals("----------"))
						showQuestionsBySubjectBtn.setDisable(true);
					else
						showQuestionsBySubjectBtn.setDisable(false);
				}
			});
		//////////////////////////////////////////////////////
		showQuestionsBySubjectBtn = sbShowQuestionsBySubjectBtn;
		showQuestionsBySubjectBtn.setDisable(true);
		
		/**** Second panel ****/
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		availableQuestionsTv = sbAvailableQuestionsTv;
		questionIDTc = sbQuestionIDTc;
		previewTc = sbPreviewTc;
		changeSubjectBtn = sbChangeSubjectBtn;
		editQuestionBtn = sbEditQuestionBtn;
		//----------------------------------
		if (subjectList.size() == 1) // add subjects only once
		{
			ClientUI.chat.accept(new String[]{"GetExistingBanks", ChatClient.user.getUsername()});
			
			if (subjectList.size() == 1) { // if still empty after query show an alert
				Alert alert = new Alert(AlertType.WARNING);
		    	alert.initStyle(StageStyle.UTILITY);
				alert.setTitle("Missing questions");
				alert.setHeaderText(null);
				alert.setContentText(msg);
				alert.show();
			}
		}
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressShowQuestionsBySubject(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressShowQuestionsByBank");
		topPanelAp.setDisable(true);
		botPanelAp.setDisable(false);
		//--------------------------------
		ClientUI.chat.accept(new String[] {"btnPressShowQuestionsBySubject",questionSubjectCb.getValue(),"1", ChatClient.user.getUsername()});
		// set up table view
		questionIDTc.setCellValueFactory(new PropertyValueFactory<Question, String>("questionID"));
		questionIDTc.setStyle("-fx-alignment: CENTER; -fx-font-weight: Bold;");
		Callback<TableColumn<Question, Void>, TableCell<Question, Void>> btnCellFactory
        = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
		    @Override
		    public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
		        final TableCell<Question, Void> cell = new TableCell<Question, Void>() {
		        	
		            private final Button btn = new Button();
		            private final ImageView previewEye = new ImageView(new Image("/previewEye.png"));
		            
		            @Override
		            public void updateItem(Void item, boolean empty) {
		                super.updateItem(item, empty);
		                btn.setStyle("-fx-background-color: transparent;");
		                btn.setPrefSize(40, 25);
		                previewEye.setPreserveRatio(true);
		                previewEye.setFitHeight(40);
		                previewEye.setFitWidth(25);
		                btn.setGraphic(previewEye);
		                if (empty) {
		                    setGraphic(null);
		                } else {
		                	btn.setOnAction(e -> {
		                		Question question = getTableRow().getItem();
		                		TeacherMenuBarController.mainPaneBp.setDisable(true);
		                		TeacherMenuBarController.menuBarAp.setDisable(true);
		                		chooseQuestionToPreview(question);
	                        });
		                    setGraphic(btn);
		                }
		            }
		        };
		        cell.setAlignment(Pos.CENTER);
		        return cell;
		    }
		};
		previewTc.setCellFactory(btnCellFactory);
	}

	@FXML
	void btnPressChangeSubject(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressChangeBank");
		topPanelAp.setDisable(false);
		botPanelAp.setDisable(true);
		//--------------------------------
		availableQuestionsTv.getItems().clear();
		questionSubjectCb.setValue("----------");
	}

	@FXML
	void btnPressEditQuestion(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressEditQuestion");
	}
	
	// EXTERNAL USE METHODS **************************************************
	public void setSubjectChoiceBox(List<String> subjects) {
		subjectList.addAll(subjects);
	}
	
	public void setQuestionTableView(List<Question> questions) {
		questionList = questions;
		ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
		questionObservableList.addAll(questions);
		availableQuestionsTv.setItems(questionObservableList);
	}
	
	public void chooseQuestionToPreview(Question question) {
		try { new TeacherPreviewQuestionController().start(new Stage());
		} catch (IOException e) {e.printStackTrace();}
		System.out.println(question);
		TeacherPreviewQuestionController.tpqController.setQuestion(question);
	}

	public void badGetSubjectsWithBank(String Msg) {
		System.out.println("badGetSubjectsWithBank");
    	msg = Msg;
	}

}
