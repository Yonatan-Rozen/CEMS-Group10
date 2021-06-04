package gui.client.principle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.question.CorrectAnswerOfQuestion;
import logic.question.Question;

public class PrincipleViewQuestionsInfoScreenController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TableView<Question> tblVQuestionsDetails;

	@FXML
	private TableColumn<Question, String> sbQuestionIDClm;

	@FXML
	private TableColumn<Question, String> sbQuestionsBodyClm;

	@FXML
	private TableColumn<Question, String> sbAns1Clm;

	@FXML
	private TableColumn<Question, String> sbAns2Clm;

	@FXML
	private TableColumn<Question, String> sbAns3Clm;

	@FXML
	private TableColumn<Question, String> sbAns4Clm;

	@FXML
	private TableColumn<Question, CorrectAnswerOfQuestion> sbCorrectAnswerClm;

	@FXML
	private TableColumn<Question, String> sbAuthorClm;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView <Question> tblE;
	public static PrincipleViewQuestionsInfoScreenController pvqisController;
	private static ObservableList<Question> questionsDetails;// = new ArrayList<>();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblE = tblVQuestionsDetails;
		questionsDetails= FXCollections.observableArrayList();
		pvqisController=new PrincipleViewQuestionsInfoScreenController();
		// set up columns
		sbQuestionIDClm.setCellValueFactory(new PropertyValueFactory<Question, String>("questionID"));
		sbQuestionsBodyClm.setCellValueFactory(new PropertyValueFactory<Question, String>("questionBody"));
		sbAns1Clm.setCellValueFactory(new PropertyValueFactory<Question, String>("answer1"));
		sbAns2Clm.setCellValueFactory(new PropertyValueFactory<Question, String>("answer2"));
		sbAns3Clm.setCellValueFactory(new PropertyValueFactory<Question, String>("answer3"));
		sbAns4Clm.setCellValueFactory(new PropertyValueFactory<Question, String>("answer4"));
		sbCorrectAnswerClm.setCellValueFactory(new PropertyValueFactory<Question, CorrectAnswerOfQuestion>("correctAnswer"));
		sbAuthorClm.setCellValueFactory(new PropertyValueFactory<Question, String>("author"));

		ClientUI.chat.accept(new String[] { "sbViewQuestionsBtn" });
	}

	// EXTERNAL USE METHODS *************************************************

	/**
	 * This method puts the tuples of the questions from the DB into the tableView
	 * @param questionsList List<Question> of the questions in the table from DB
	 */
	//public void addExamToObservableList(List<String> rowInUsersTable)
	public void setQuestionsInfoList(List<Question> questionsList) {
		{
			//	ObservableList<User> users = FXCollections.observableArrayList();
			for (Question row : questionsList)
				questionsDetails.add(row);
			tblE.setItems(questionsDetails);
		}
	}


}
