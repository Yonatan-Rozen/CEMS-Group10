package gui.client.teacher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import client.IClientController;
import common.IntegerStringConverter;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import logic.exam.ExamResults;

/**
 * A controller that controls all the functionalites of viewing reports of exams, including:
 * <br>* viewing report under chosen course<br>* 
 * @author Yonatan Rozen & Danielle Sarusi
 */
public class TeacherReportsController implements Initializable {
	public static TeacherReportsController trController;

	public static IClientController chat;

	public void setClientController(IClientController chat) {
		TeacherReportsController.chat = chat;
	}

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private ChoiceBox<String> sbCourcesCb;

	@FXML
	private BarChart<String, Integer> sbHistogramBc;

	@FXML
	private NumberAxis sbAmountAxisNa;

	@FXML
	private Label sbExamIDLbl;

	@FXML
	private Label sbMedianLbl;

	@FXML
	private Label sbAverageLbl;

	@FXML
	private Button sbPreviousRepBtn;

	@FXML
	private Button sbNextRepBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static ChoiceBox<String> courcesCb;
	private static BarChart<String, Integer> histogramBc;
	private static Label examIDLbl;
	private static Label medianLbl;
	private static Label averageLbl;
	private static Button previousRepBtn;
	private static Button nextRepBtn;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> coursesList = FXCollections.observableArrayList();
	public static IntegerStringConverter integerStringConverter = new IntegerStringConverter();
	private static List<ExamResults> examResultsList = new ArrayList<>();
	private static Series<String, Integer> series;
	private int index;

	// INITIALIZE METHOD ****************************************************

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trController = new TeacherReportsController();
		trController.setClientController(ClientUI.chat);
		histogramBc = sbHistogramBc;
		sbAmountAxisNa.setTickLabelFormatter(integerStringConverter);
		examIDLbl = sbExamIDLbl;
		medianLbl = sbMedianLbl;
		averageLbl = sbAverageLbl;
		nextRepBtn = sbNextRepBtn;
		nextRepBtn.setDisable(true);
		previousRepBtn = sbPreviousRepBtn;
		previousRepBtn.setDisable(true);
		courcesCb = sbCourcesCb;
		courcesCb.setItems(coursesList);

		courcesCb.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> coursesList, String oldCourse, String newCourse) -> {
					histogramBc.getData().removeAll(series);
					loadChosenCourseExamReports(newCourse, ChatClient.user.getUsername());
				});
	}

	/**
	 * Load the detials of the exams under the chosen course
	 * @param courseName The chosen course
	 * @param username The username of the teacher
	 * @return true if the details were loaded; false otherwise
	 */
	public boolean loadChosenCourseExamReports(String courseName, String username) {
		if (courseName != null) {

			/// for examResultsList;
			if (chat != null)
				chat.accept(new String[] { "GetExamDetails", courseName, username, "T" });
			else return false;
			// reset next button
			try {
				if (examResultsList.size() > 1)
					nextRepBtn.setDisable(false);
				else
					nextRepBtn.setDisable(true);

				// reset previous button
				previousRepBtn.setDisable(true);

				// reset index
				index = 0;

				// show the exam results of the first exam on the list
				setExamResultData();
			} catch (NullPointerException e) { }
			return true;
		}
		return false;
	}

	// ACTION METHODS *******************************************************

	@FXML
	@SuppressWarnings("unchecked")
	void BtnPressPreviousRep(ActionEvent event) {
		index--;
		if (index == examResultsList.size() - 2)
			nextRepBtn.setDisable(false);
		if (index == 0)
			previousRepBtn.setDisable(true);
		histogramBc.getData().removeAll(series);
		setExamResultData();

	}
	
	@FXML
	@SuppressWarnings("unchecked")
	void btnPressNextRep(ActionEvent event) {
		histogramBc.getData().removeAll(series);
		index++;
		if (index == 1)
			previousRepBtn.setDisable(false);
		if (index == examResultsList.size() - 1)
			nextRepBtn.setDisable(true);
		setExamResultData();
	}

	// INTERNAL USE METHODS *************************************************

	/**
	 * Set up the barchart with the current exam result details
	 */
	private void setExamResultData() {
		ExamResults er = examResultsList.get(index);
		String examID = "ExamID: #" + er.getExamID();
		String median = "Median: " + String.format("%.2f", er.getMedian());
		String average = "Average: " + String.format("%.2f", er.getAverage());
		series = er.getGraph();

		setLabelsAndBarChart(examID, median, average);
	}

	/**
	 * Sets all the details of the current exam report
	 * @param examID The exam ID 
	 * @param median The median of th exam results
	 * @param average The average of the exam results
	 */
	public void setLabelsAndBarChart(String examID, String median, String average) {
		examIDLbl.setText(examID);
		medianLbl.setText(median);
		averageLbl.setText(average);
		histogramBc.getData().add(series);
	}

	// EXTERNAL USE METHODS *************************************************
	/**
	 * Set coursesList, and initializes courcesCb with the first course name in the list
	 * @param list The list of courses with exam results
	 */
	public void setCoursesChoiseBox(List<String> list) {
		coursesList.clear();
		coursesList.addAll(list);
		courcesCb.setValue(coursesList.get(0));
	}

	/**
	 * Set up exam results list
	 * @param examResultsList The exam results list
	 */
	public void setExamResultsDetails(List<ExamResults> examResults) {
		examResultsList.clear();
		examResultsList.addAll(examResults);
	}

}
