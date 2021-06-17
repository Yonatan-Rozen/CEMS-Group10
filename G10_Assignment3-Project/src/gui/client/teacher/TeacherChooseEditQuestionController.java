package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private Button sbDeleteSelectedBtn;

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
	
	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> subjectList = FXCollections.observableArrayList();
	public static String chosenSubject;
	public static boolean pressedDiscard;
	private static boolean deletable;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tceqController = new TeacherChooseEditQuestionController();
		
		/**** First panel ****/
		topPanelAp = sbTopPanelAp;
		questionSubjectCb = sbQuestionSubjectCb;
		// set "----------" as the first value of the choice box

		if (chosenSubject == null) {
			subjectList.clear();
			subjectList.add("----------");
			questionSubjectCb.setValue("----------");
		}else questionSubjectCb.setValue(chosenSubject);
		
		// set the choice box to get it's items from 'subjectList'
		questionSubjectCb.setItems(subjectList);
		
		// set up a listener that sets the disable value of 
		// 'createQuestionBtn' according to the selected value
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
		showQuestionsBySubjectBtn = sbShowQuestionsBySubjectBtn;
		showQuestionsBySubjectBtn.setDisable(true);
		
		/**** Second panel ****/
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		availableQuestionsTv = sbAvailableQuestionsTv;
		questionIDTc = sbQuestionIDTc;
		previewTc = sbPreviewTc;
		
		CommonMethodsHandler.getInstance().disableTableColumnSwap(availableQuestionsTv);
		
		
		if (pressedDiscard) {
			btnPressShowQuestionsBySubject(new ActionEvent());
			pressedDiscard = false;
		}
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressShowQuestionsBySubject(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressShowQuestionsByBank");
		topPanelAp.setDisable(true);
		botPanelAp.setDisable(false);
		//--------------------------------
		chosenSubject = questionSubjectCb.getValue();
		System.out.println("chosenSubject : " + chosenSubject);
		ClientUI.chat.accept(new String[] {"btnPressShowQuestionsBySubject",chosenSubject,"1", ChatClient.user.getUsername()});
		// set up table view
		questionIDTc.setCellValueFactory(new PropertyValueFactory<Question, String>("questionID"));
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
	public void btnPressChangeSubject(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressChangeBank");
		topPanelAp.setDisable(false);
		botPanelAp.setDisable(true);
		//--------------------------------
		availableQuestionsTv.getItems().clear();
		questionSubjectCb.setValue("----------");
	}
	
	@FXML
	public void btnPressDeleteSelected(ActionEvent event) {
		Question question = availableQuestionsTv.getSelectionModel().getSelectedItem();
		CommonMethodsHandler methodsHandler = CommonMethodsHandler.getInstance();

		if (!showAlertIfNotSelected()) {
			ClientUI.chat.accept(new String[] {"CheckQuestionExistsInExam", question.getQuestionID()});
			if (deletable) {
				ButtonType buttonYes = new ButtonType("Yes");
				ButtonType buttonCancel = new ButtonType("Cancel");
				Optional <ButtonType> result = methodsHandler.getNewAlert(AlertType.CONFIRMATION,"Question Deletion",
						"Are you sure you want to delete the selected question?", buttonYes, buttonCancel).showAndWait();
				if (result.get() == buttonYes) {
					// remove the question from the database & tableView
					ClientUI.chat.accept(new String[] {"RemoveQuestionFromDatabase", question.getQuestionID(), questionSubjectCb.getValue(), ChatClient.user.getUsername()});
					availableQuestionsTv.getItems().remove(availableQuestionsTv.getSelectionModel().getSelectedItem());
					if (availableQuestionsTv.getItems().size() == 0) {
						methodsHandler.getNewAlert(AlertType.INFORMATION,"Question Removed" ,"Subject " + questionSubjectCb.getValue() + " is now empty from questions", "press ok to continue.").showAndWait();
						TeacherMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherChooseEditQuestion"));
					}
				}
			}
			else methodsHandler.getNewAlert(AlertType.INFORMATION,"Deletion Canceled",
					"Sorry, but the chosen question cannot be deleted!",
					"(This question is part of one or more exams)").showAndWait();
		}
	}

	@FXML
	public void btnPressEditQuestion(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressEditQuestion");
		if (!showAlertIfNotSelected()) {
			TeacherMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditQuestion"));
			TeacherEditQuestionController.teqController.setQuestion(availableQuestionsTv.getSelectionModel().getSelectedItem());
		}
	}
	// INTERNAL USE METHODS **************************************************
	private boolean showAlertIfNotSelected() {
		if (availableQuestionsTv.getSelectionModel().getSelectedItem() != null)
			return false;
		CommonMethodsHandler.getInstance().getNewAlert(AlertType.WARNING, "Select Question" ,"Please select a row from the table above!").showAndWait();
		return true;
	}
	
	// EXTERNAL USE METHODS **************************************************
	public void setSubjectChoiceBox(List<String> subjects) {
		System.out.println("current:"+subjectList.toString());
		System.out.println("subjects to add: " + subjects);
		subjectList.clear();
		chosenSubject = "----------";
		subjectList.add(chosenSubject);
		subjectList.addAll(subjects);
		questionSubjectCb.setValue(chosenSubject);
	}
	
	public void setQuestionTableView(List<Question> questions) {
		ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
		questionObservableList.addAll(questions);
		availableQuestionsTv.setItems(questionObservableList);
	}
	
	public void setQuestionDeletable(String existsInExam) {
		deletable = (existsInExam.equals("0"));
	}
	
	public void chooseQuestionToPreview(Question question) {
		try { new TeacherPreviewQuestionController().start(new Stage());
		} catch (IOException e) {e.printStackTrace();}
		System.out.println(question);
		TeacherPreviewQuestionController.tpqController.setQuestion(question);
	}

	public void badGetSubjectsWithBank(String Msg) {
		System.out.println("badGetSubjectsWithBank");
	}

}
