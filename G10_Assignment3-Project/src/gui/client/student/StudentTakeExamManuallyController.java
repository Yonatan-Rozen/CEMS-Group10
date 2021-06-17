package gui.client.student;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.exam.ManualExam;

public class StudentTakeExamManuallyController implements Initializable {
	public static StudentTakeExamManuallyController stemController = new StudentTakeExamManuallyController();;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Label sbTimerLbl;
	
	@FXML
	private Hyperlink sbDownloadExamFileLnk;

	@FXML
	private Button sbSubmitExamBtn;

	@FXML
	private Button sbSearchBtn;

	@FXML
	private TextField sbUploadFileTf;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Label timerLbl;
	private static Hyperlink downloadExamFileLnk;
	private static Button submitExamBtn;
	private static TextField uploadFileTf;
	private static Button searchBtn;

	// STATIC INSTANCES *****************************************************
	private static String examID;
	// private static Blob manualExamRecived;
	// maybe need get the file into this param?
	private static ManualExam exam;
	FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	private static String FileName;
	private static String FilePath;
	private static long startTime;
	private static long estimatedTime;
	
	public static int sec, min, hour;
	public String ddMin, ddHour;
	public DecimalFormat dFormat = new DecimalFormat("00");
	public Timer timer;
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		downloadExamFileLnk = sbDownloadExamFileLnk;
		submitExamBtn = sbSubmitExamBtn;
		// stemController
		searchBtn = sbSearchBtn;
		uploadFileTf = sbUploadFileTf;
		ClientUI.chat.accept(new String[] { "btnPressStartExam", examID,"M" });
		startTime = System.nanoTime();
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressSubmitExam(ActionEvent event) throws IOException {
		stopExam("successful", FilePath);
	}


	@FXML
	void lnkPressDownloadExamFile(ActionEvent event) throws RuntimeException{
		System.out.println("StudentTakeExamManually::lnkPressDownloadExamFile");
		// TODO BLOB stuff

		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Save file");
		//File defaultDirectory = new File("D:");
		File defaultDirectory = new File("C:");
		chooser.setInitialDirectory(defaultDirectory); // set default
		try {
			// get folder path
			File selectedDirectory = chooser.showDialog(new Stage());
			if (selectedDirectory != null) {
				FileName = selectedDirectory.getName();
				FilePath = selectedDirectory.getPath(); // path
				uploadFileTf.setText(FilePath);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		System.out.println("path = " + FilePath);
		ClientUI.chat.accept(new String[] { "lnkPressDownloadExamFile", examID , FilePath , "submit", ""});
		// TODO add method of getFile from DB
		// added table of manual exams
	}

	@FXML
	void btnPressSearch(ActionEvent event) throws RuntimeException{
		System.out.println("StudentTakeExamManually::btnPressSearch");
		fileChooser.setTitle("Choose Exam File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("docx Files", "*.docx"),
				new FileChooser.ExtensionFilter("doc Files", "*.doc"));
		try {
			File selectedFile = fileChooser.showOpenDialog(new Stage());
			if (selectedFile != null) {

				FileName = selectedFile.getName();
				FilePath = selectedFile.getPath();
				uploadFileTf.setText(FilePath);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	// EXTERNAL USE METHODS *************************************************

	/**
	 * *
	 *
	 * @param examIDFromTeacher the running exam ID sent from the teacher
	 */
	public void setExamID(String examIDFromTeacher) {
		if (examIDFromTeacher != null && !examIDFromTeacher.equals("")) {
			// TODO get examID from teacher to all connected students
			examID = examIDFromTeacher;
			System.out.println("StudentTakeExamManually - setExamID :: examID = "+examID);
		}
		else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Code inserting failed",
					"There was no examID chosen by a teacher").showAndWait();
		}
	}

	/**
	 *
	 * @param examTupple one exam from the exams table, with all it's fields
	 */
	public void setExam(ManualExam examTupple) {
		exam = examTupple;
		System.out.println("THE EXAM : "+exam);
	}

	// TODO check if works after LOCK EXAM is implemented in Teacher
	public void setSubmitButtonWhenLockInvoked() throws IOException {
		stopExam("Not successful", null);

	}

	public void stopExam(String submited, String FilePath) throws IOException {
		System.out.println("studentTakeManualExam - stopExam :: exam: "+exam);
		estimatedTime = System.nanoTime() - startTime; // elapsed time in nanoseconds
		// convert to minutes
		// There are 60,000,000,000 nanosecond in 1 minute.
		estimatedTime = estimatedTime / 600000;
		estimatedTime = estimatedTime / 100000;

		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentExamSubmitted.fxml")));
		StudentExamSubmittedController.sesController.setExamDetailsManual(String.format("%d", estimatedTime), examID, exam.getAllocatedTime(), submited, FilePath);
	}
	
	private void initializeTimer(String time) {
		hour = Integer.parseInt(time) / 60;
		min = Integer.parseInt(time) % 60;
		sec = 0;
		ddHour = dFormat.format(hour);
		ddMin = dFormat.format(min);
		timerLbl.setText(ddHour + ":" + ddMin);
	}

	private void startTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						sec--;
						ddHour = dFormat.format(hour);
						ddMin = dFormat.format(min);
						timerLbl.setText(ddHour + ":" + ddMin);
						if (sec == -1) {
							sec = 59;
							min--;
							ddMin = dFormat.format(min);
							timerLbl.setText(ddHour + ":" + ddMin);
						}
						if (min == -1) {
							hour--;
							sec = 59;
							min = 59;
							ddMin = dFormat.format(min);
							ddHour = dFormat.format(hour);
							timerLbl.setText(ddHour + ":" + ddMin);
						}
						if (sec == 0 && min == 0 & hour == 0) {
							timer.cancel();
							cancel();
							if (StudentEnterCodeController.additionalTime != null) {
								initializeTimer(StudentEnterCodeController.additionalTime);
								Timer extraTime = new Timer();
								extraTime.schedule(new TimerTask() {
									@Override
									public void run() {
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
												sec--;
												ddHour = dFormat.format(hour);
												ddMin = dFormat.format(min);
												timerLbl.setText(ddHour + ":" + ddMin);
												if (sec == -1) {
													sec = 59;
													min--;
													ddMin = dFormat.format(min);
													timerLbl.setText(ddHour + ":" + ddMin);
												}
												if (min == -1) {
													hour--;
													sec = 59;
													min = 59;
													ddMin = dFormat.format(min);
													ddHour = dFormat.format(hour);
													timerLbl.setText(ddHour + ":" + ddMin);
												}
												if (sec == 0 && min == 0 & hour == 0) {
													timerLbl.setText("Time Is Finished");
													extraTime.cancel();
													cancel();
													try { stopExam("Not successful", null);
													} catch (IOException e) { e.printStackTrace(); }
												}
											}
										});

									}
								}, 0, 1000); // TODO change to 1000!
							}
							else 
								try { stopExam("Not successful", null);
								} catch (IOException e) { e.printStackTrace(); }
						}
					}
				});

			}
		}, 0, 1000); // TODO change to 1000!

		
	}
}
