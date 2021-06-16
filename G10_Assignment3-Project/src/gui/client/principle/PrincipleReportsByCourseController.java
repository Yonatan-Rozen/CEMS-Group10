package gui.client.principle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
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

public class PrincipleReportsByCourseController implements Initializable {
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private BarChart<String, Integer> sbHistogramBc;

	//	@FXML
	//	private AnchorPane sbBarChartContainerPn;

	@FXML
	private NumberAxis sbAmountAxisNa;

	@FXML
	private Label sbReportsByLbl;

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
	private Button sbBackToViewReportsBtn;

	@FXML
	private ChoiceBox<String> sbTeachersCb;

	@FXML
	private Button sbShowReportsByTeacherBtn;

	@FXML
	private Label sbCourseIDLbl;

	// STATIC JAVAFX INSTANCES **********************************************
	private static BarChart<String, Integer> histogramBc;
	private static Label reportsByLbl;
	private static Label examIDLbl;
	private static Label medianLbl;
	private static Label averageLbl;
	private static Button nextRepBtn;
	private static Button previousRepBtn;
	private static ChoiceBox<String> teachersCb;
	private static Button showReportsByTeacherBtn;
	private static Button backToViewReportsBtn;
	private static Label courseIDLbl;
	//	private static AnchorPane barChartContainerPn;

	// STATIC INSTANCES ****************************************************
	public static ObservableList<String> teachersList = FXCollections.observableArrayList();
	public static List<ExamResults> examResultsList;
	private int index = 0;
	private static Series series;
	private static IntegerStringConverter isc=new IntegerStringConverter();

	// STATIC CONTROLLER INSTANCES ******************************************
	public static PrincipleReportsByCourseController prbcController;
	private CommonMethodsHandler methodsHandler = CommonMethodsHandler.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			//			barChartContainerPn = sbBarChartContainerPn;
			prbcController = new PrincipleReportsByCourseController();
			histogramBc = sbHistogramBc;
			reportsByLbl = sbReportsByLbl;
			examIDLbl = sbExamIDLbl;
			medianLbl = sbMedianLbl;
			averageLbl = sbAverageLbl;
			nextRepBtn = sbNextRepBtn;
			previousRepBtn = sbPreviousRepBtn;
			backToViewReportsBtn = sbBackToViewReportsBtn;
			courseIDLbl = sbCourseIDLbl;
			sbAmountAxisNa.setTickLabelFormatter(isc);
			previousRepBtn.setDisable(true);
			nextRepBtn.setDisable(true);

			teachersCb = sbTeachersCb;
			showReportsByTeacherBtn = sbShowReportsByTeacherBtn;
			courseIDLbl.setText(" " + PrincipleViewReportsController.insertedValue);
			teachersList.clear();
			teachersList.add("----------");

			// set "----------" as the first value of the choice box
			teachersCb.setValue("----------");

			// set the choice box to get it's items from 'coursesList'
			teachersCb.setItems(teachersList);

			// hide barchart until after pressing SHOW
			//			barChartContainerPn.setVisible(false);

			// set up a listener that sets the disable value of
			// 'showReportsByCourseBtn' according to the selected value
			teachersCb.getSelectionModel().selectedItemProperty()
			.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
				if (newValue != null) {
					if (newValue.equals("----------"))
						showReportsByTeacherBtn.setDisable(true);
					else
						showReportsByTeacherBtn.setDisable(false);
				}
			});

			showReportsByTeacherBtn.setDisable(true);
			System.out.println(PrincipleViewReportsController.insertedValue);
			teachersList.addAll(PrincipleViewReportsController.list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void BtnPressPrevousRep(ActionEvent event) {
		System.out.println("PrincipleReports::BtnPressPrevousRep");
		index--;
		if (index == examResultsList.size() - 2)
			nextRepBtn.setDisable(false);
		if (index == 0)
			previousRepBtn.setDisable(true);
		histogramBc.getData().removeAll(series);
		setExamResultData();
	}

	@FXML
	void btnPressBackToViewReports(ActionEvent event) {
		System.out.println("PrincipleReports::btnPressBackToViewReports");
		PrincipleMenuBarController.mainPaneBp
		.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewReports"));
	}

	@FXML
	void btnPressNextRep(ActionEvent event) {
		System.out.println("PrincipleReports::btnPressNextRep");
		index++;
		if (index == 1)
			previousRepBtn.setDisable(false);
		if (index == examResultsList.size() - 1)
			nextRepBtn.setDisable(true);
		histogramBc.getData().removeAll(series);
		setExamResultData();
	}

	@FXML
	void btnPressShowReportsByTeacher(ActionEvent event) {
		System.out.println("PrincipleReports::btnPressShowReportsByTeacher");

		index = 0;
		System.out.println(PrincipleViewReportsController.insertedValue);
		ClientUI.chat.accept(new String[] { "GetExamDetailsReportByCourse", teachersCb.getValue(),
				PrincipleViewReportsController.insertedValue });
		if (examResultsList.size() == 1) {
			System.out.println("list length for teacherID " + teachersCb.getValue() + "has only one exam");
			nextRepBtn.setDisable(true);
		}
		else if(examResultsList.size() == 0) {

		}
		else {
			System.out.println(
					"list length for teacherID " + teachersCb.getValue() + " has " + examResultsList.size() + " exams");
			nextRepBtn.setDisable(false);
		}

		previousRepBtn.setDisable(true);
		histogramBc.getData().removeAll(series);
		setExamResultData();
		//		barChartContainerPn.setVisible(true);
	}

	// EXTERNAL METHODS *******************************************************

	/**
	 * sets the exams statistics and ID
	 */
	public void setExamResultData() {
		ExamResults er = examResultsList.get(index);
		examIDLbl.setText("ExamID: #" + er.getExamID());
		medianLbl.setText("Median: " + String.format("%.2f", er.getMedian()));
		averageLbl.setText("Average: " + String.format("%.2f", er.getAverage()));
		series = er.getGraph();
		histogramBc.getData().add(series);
	}

	/**
	 * sets the exams of the current teacher in the course to be displayed
	 *
	 * @param examResultsList a list of the exams of a the course that were done by
	 *                        the current teacher
	 */
	public void setExamResultsDetails(List<ExamResults> examResultsList) {
		PrincipleReportsByCourseController.examResultsList = examResultsList;
	}

}
