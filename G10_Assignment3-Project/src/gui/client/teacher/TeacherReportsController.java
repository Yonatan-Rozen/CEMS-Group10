package gui.client.teacher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
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

public class TeacherReportsController implements Initializable {
	public static TeacherReportsController trController;

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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trController = new TeacherReportsController();
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
		// reset courses list
		// coursesList.clear();
		// set the choice box to get it's items from 'coursesList'
		courcesCb.setItems(coursesList);
		choiceBoxEvents();
	}

	// ACTION METHODS *******************************************************

	/**
	 * Displays the previous exam report under the same course
	 * 
	 * @param event
	 */
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

	/**
	 * Displays the next exam report under the same course
	 * 
	 * @param event
	 */
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
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void choiceBoxEvents() {
		courcesCb.getSelectionModel().selectedItemProperty()
			.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
				// show the first exam result of the first
				histogramBc.getData().removeAll(series);
				examResultsList.clear();
				ClientUI.chat.accept(new String[] { "GetExamDetails", courcesCb.getValue(),ChatClient.user.getUsername(), "T" });
				// reset next button
				if (examResultsList.size() > 1)
					nextRepBtn.setDisable(false);
				else nextRepBtn.setDisable(true);

				// reset previous button
				previousRepBtn.setDisable(true);

				// reset index
				index = 0;

				setExamResultData();
			});
	}
	

	/**
	 * Set up the barchart with the current exam result details
	 */
	private void setExamResultData() {
		ExamResults er = examResultsList.get(index);
		examIDLbl.setText("ExamID: #" + er.getExamID());
		medianLbl.setText("Median: " + String.format("%.2f", er.getMedian()));
		averageLbl.setText("Average: " + String.format("%.2f", er.getAverage()));
		series = er.getGraph();
		histogramBc.getData().add(series);
	}

	// EXTERNAL USE METHODS *************************************************
	/**
	 * Set coursesList, and initializes courcesCb with the first course name in the list
	 * @param list The list of courses with exam results
	 */
	public void setCoursesCoiseBox(List<String> list) {
		if (coursesList.isEmpty())
			coursesList.addAll(list);
		courcesCb.setValue(coursesList.get(0));
	}

	/**
	 * Set up exam results list
	 * @param examResultsList The exam results list
	 */
	public void setExamResultsDetails(List<ExamResults> examResults) {
		TeacherReportsController.examResultsList.addAll(examResults);
	}
}
