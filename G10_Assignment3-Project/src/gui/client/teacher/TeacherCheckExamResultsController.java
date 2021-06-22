package gui.client.teacher;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.exam.ComputerizedResults;
import logic.exam.ExamResultOfStudent;
import logic.exam.ManualResults;

/**
 * A controller that controls a tableview that contains 
 * all the completed exams that the teacher didn't check yet.
 * The exams shown are only exams made by the currently connected teacher
 * @author Yonatan Rozen
 */
public class TeacherCheckExamResultsController implements Initializable {
	public static TeacherCheckExamResultsController tcrController;
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private TableView<ExamResultOfStudent> sbComputerizedResultsTv;

	@FXML
	private TableColumn<ExamResultOfStudent, String> sbExamIDTc;

	@FXML
	private TableColumn<ExamResultOfStudent, String> sbStudentIDTc;

	@FXML
	private TableColumn<ExamResultOfStudent, String> sbTypeTc;

	@FXML
	private Button sdCheckAnswersBtn;

	@FXML
	private AnchorPane sbUploadCopyAp;

	@FXML
	private TextField sbPathTf;

	@FXML
	private Button sbSearchBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView<ExamResultOfStudent> computerizedResultsTv;
	private static TableColumn<ExamResultOfStudent, String> examIDTc;
	private static TableColumn<ExamResultOfStudent, String> studentIDTc;
	private static TableColumn<ExamResultOfStudent, String> typeTc;
	private static AnchorPane uploadCopyAp;
	private static TextField pathTf;
	private static Button searchBtn;

	// STATIC INSTANCES *****************************************************
	private static CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();
	private static ObservableList<ExamResultOfStudent> computerizedExamResults = FXCollections.observableArrayList();;
	private static String FileName;
	private static String FilePath;
	private static String ExamID , StudentID;
	private static FileChooser fileChooser = new FileChooser();


	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcrController = new TeacherCheckExamResultsController();
		computerizedResultsTv = sbComputerizedResultsTv;
		examIDTc = sbExamIDTc;
		studentIDTc = sbStudentIDTc;
		typeTc = sbTypeTc;
		uploadCopyAp = sbUploadCopyAp;
		uploadCopyAp.setVisible(false);
		pathTf = sbPathTf;
		searchBtn = sbSearchBtn;
		TeacherMenuBarController.menuBarAp.setDisable(false);
		pathTf.setText("Enter path");
		cmh.disableTableColumnSwap(computerizedResultsTv);
		examIDTc.setCellValueFactory(new PropertyValueFactory<ExamResultOfStudent, String>("examID"));
		studentIDTc.setCellValueFactory(new PropertyValueFactory<ExamResultOfStudent, String>("studentID"));
		typeTc.setCellValueFactory(new PropertyValueFactory<ExamResultOfStudent, String>("type"));
		ClientUI.chat.accept(new String[] { "GetExamResultsByTeacherUsername", ChatClient.user.getUsername() });
		
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressCheckAnswers(ActionEvent event) {

		System.out.println("TeacherCheckComputerizedResultsController::btnPressCheckAnswers");
		ExamResultOfStudent selectedResult = computerizedResultsTv.getSelectionModel().getSelectedItem();
		if (selectedResult instanceof ComputerizedResults) {
			TeacherMenuBarController.mainPaneBp.setCenter(cmh.getPane("teacher", "TeacherCheckAnswers"));
			TeacherCheckAnswersController.tcaController.setExamOfStudentDetails((ComputerizedResults)selectedResult);
		} else if (selectedResult instanceof ManualResults) {
			ExamID = selectedResult.getExamID();
			StudentID = selectedResult.getStudentID();
			computerizedResultsTv.setDisable(true);
			uploadCopyAp.setVisible(true);
			
			
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Save file");
			File defaultDirectory = new File("D:");
			chooser.setInitialDirectory(defaultDirectory); // set default
			try {
				// get folder path
				File selectedDirectory = chooser.showDialog(new Stage());
				if (selectedDirectory != null) {
					FileName = selectedDirectory.getName();
					FilePath = selectedDirectory.getPath(); // path
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			
			System.out.println("path = " + FilePath + ChatClient.user.getUsername());
			ClientUI.chat.accept(new String[] { "DownloadFileExamResult", ExamID , FilePath ,StudentID});
		}
	}

	@FXML
	void btnPressSearch(ActionEvent event) throws IOException {
		System.out.println("TeacherCheckComputerizedResultsController::btnPressSearch");

		fileChooser.setTitle("Choose Exam File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("docx Files", "*.docx"),
				new FileChooser.ExtensionFilter("doc Files", "*.doc"));
		try {
			File selectedFile = fileChooser.showOpenDialog(new Stage());
			if (selectedFile != null) {

				FileName = selectedFile.getName();
				FilePath = selectedFile.getPath();
				pathTf.setText(FilePath);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		if(!pathTf.getText().equals("Enter path")) {
			ClientUI.chat.accept(new String[] { "TeacherUploadManualCheckExamFile", ExamID, FilePath,StudentID});
			
			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");
			Optional<ButtonType> request = CommonMethodsHandler
					.getInstance().getNewAlert(AlertType.INFORMATION, "Checked copy",
							"File was uploaded successfuly!", "Upload another check file?", buttonYes, buttonNo)
					.showAndWait();

			if (request.get() == buttonYes) {
				TeacherMenuBarController.mainPaneBp
						.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCheckExamResults"));
			} else {
				ClientUI.mainScene
						.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
			}
			
		}
		else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing File",
					"Please choose file to upload").showAndWait();
		}
	}
	
	// EXTERNAL USE METHODS *************************************************
	/**
	 * Sets the tableview with all the completed exams which were not checked yet
	 * @param results The list of completed exams
	 */
	public void setResults(List<ExamResultOfStudent> results) {

		if (results == null || results.contains(null))
			System.err.println("bad info!");
		else {
			computerizedExamResults.clear();
			computerizedExamResults.addAll(results);
			computerizedResultsTv.setItems(computerizedExamResults);
		}

	}
}
