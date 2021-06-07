package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import logic.exam.ExamResults;

public class TeacherReportsController implements Initializable {

	public static TeacherReportsController trController;

	@FXML
	private BarChart<?, ?> sbHistogramBc;

	@FXML
	private CategoryAxis sbCategoryAxis;

	@FXML
	private NumberAxis sbNumberAxis;

	@FXML
	private Label sbExamIDLbl;

	@FXML
	private Label sbMedianLbl;

	@FXML
	private Label sbAverageLbl;

	@FXML
	private Button sbNextRepBtn;

	@FXML
	private Button sbPreviousRepBtn;

	@FXML
	private ChoiceBox<String> sbCourcesCb;

	@FXML
	private Button sbShowReportsByCourseBtn;

	private static BarChart<?, ?> histogramBc;
	private static CategoryAxis categoryAxis;
	private static NumberAxis numberAxis;
	private static Label reportsByLbl;
	private static Label examIDLbl;
	private static Label medianLbl;
	private static Label averageLbl;
	private static Button nextRepBtn;
	private static Button previousRepBtn;
	private static Button showReportsByCourseBtn;
	private static ChoiceBox<String> courcesCb;

	public static ObservableList<String> coursesList = FXCollections.observableArrayList("----------");
	public static List <ExamResults> examResultsList;
	private int index=0;
	private static Series series;
	
	public void start() throws IOException {
		// example **************************************
		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherReports"));
		// **********************************************
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trController = new TeacherReportsController();
		histogramBc = sbHistogramBc;
		categoryAxis = sbCategoryAxis;
		numberAxis = sbNumberAxis;
		examIDLbl = sbExamIDLbl;
		medianLbl = sbMedianLbl;
		averageLbl = sbAverageLbl;
		nextRepBtn = sbNextRepBtn;
		previousRepBtn = sbPreviousRepBtn;
		previousRepBtn.setDisable(true);
		courcesCb = sbCourcesCb;
		showReportsByCourseBtn = sbShowReportsByCourseBtn;
	
		// set "----------" as the first value of the choice box
		courcesCb.setValue("----------");

		// set the choice box to get it's items from 'coursesList'
		courcesCb.setItems(coursesList);

		// set up a listener that sets the disable value of
		// 'showReportsByCourseBtn' according to the selected value
		courcesCb.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (newValue != null) {
						if (newValue.equals("----------"))
							showReportsByCourseBtn.setDisable(true);
						else
							showReportsByCourseBtn.setDisable(false);
					}
				});
		showReportsByCourseBtn.setDisable(true);

		if (coursesList.size() == 1) // add courses only once
			ClientUI.chat.accept(new String[] { "GetCourses", ChatClient.user.getUsername() });
	}

	// ACTION METHODS *******************************************************
	

	@FXML
	void BtnPressPreviousRep(ActionEvent event) {
		index--;
		if(index==examResultsList.size()-2)
			nextRepBtn.setDisable(false);
		if(index==0)
			previousRepBtn.setDisable(true);
		histogramBc.getData().removeAll(series);
		setExamResultData();

	}

	@FXML
	void btnPressNextRep(ActionEvent event) {
		index++;
		if(index==1)
			previousRepBtn.setDisable(false);
		if(index==examResultsList.size()-1) 
			nextRepBtn.setDisable(true);
		histogramBc.getData().removeAll(series);
		setExamResultData();

	}

	@FXML
	void btnPressShowReportsByCourse(ActionEvent event) {
		histogramBc.getData().removeAll(series);
		ClientUI.chat.accept(new String[] { "GetExamDetails",courcesCb.getValue(), ChatClient.user.getUsername() });
		if(examResultsList.size()==1)
			nextRepBtn.setDisable(true);
		setExamResultData();
	}

	public void setCoursesCoiseBox(List<String> list) {
		coursesList.addAll(list);
	}

	public void setExamResultsDetails(List<ExamResults> examResultsList) {
		TeacherReportsController.examResultsList=examResultsList;
		
	}
	
	public void setExamResultData() {
		ExamResults er=examResultsList.get(index);
		examIDLbl.setText("ExamID: #" + er.getExamID());
		medianLbl.setText("Median: "+er.getMedian());
		averageLbl.setText("Average: " + er.getAverage());
		series= er.getGraph();
		histogramBc.getData().add(series);
	}

}
