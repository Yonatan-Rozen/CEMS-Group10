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
import javafx.scene.layout.AnchorPane;
import logic.exam.ExamResults;

public class PrincipleReportsByStudentController implements Initializable {
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private BarChart<String, Integer> sbHistogramBc;

	@FXML
	private Label sbReportsByLbl;

	@FXML
	private NumberAxis sbAmountAxisNa;

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
	private ChoiceBox<String> sbCourcesCb;

//	@FXML
//	private AnchorPane sbBarChartContainerPn;

	@FXML
	private Button sbShowReportsByCourseBtn;

	@FXML
	private Label sbStudentIDLbl;

	// STATIC JAVAFX INSTANCES **********************************************
	private static BarChart<String, Integer> histogramBc;
	private static Label reportsByLbl;
	private static Label examIDLbl;
	private static Label medianLbl;
	private static Label averageLbl;
	private static Button nextRepBtn;
	private static Button previousRepBtn;
	private static ChoiceBox<String> courcesCb;
	private static Button showReportsByCourseBtn;
	private static Button backToViewReportsBtn;
	private static Label studentIDLbl;
//	private static AnchorPane barChartContainerPn;

	// STATIC INSTANCES ****************************************************
	public static ObservableList<String> coursesList = FXCollections.observableArrayList();
	public static List<ExamResults> examResultsList;
	private int index = 0;
	private static Series series;
	private static IntegerStringConverter isc=new IntegerStringConverter();

	// STATIC CONTROLLER INSTANCES ******************************************
	public static PrincipleReportsByStudentController prbsController;
	private CommonMethodsHandler methodsHandler = CommonMethodsHandler.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			sbAmountAxisNa.setTickLabelFormatter(isc);
//			barChartContainerPn = sbBarChartContainerPn;
			prbsController = new PrincipleReportsByStudentController();
			histogramBc = sbHistogramBc;
			reportsByLbl = sbReportsByLbl;
			examIDLbl = sbExamIDLbl;
			medianLbl = sbMedianLbl;
			averageLbl = sbAverageLbl;
			nextRepBtn = sbNextRepBtn;
			previousRepBtn = sbPreviousRepBtn;
			backToViewReportsBtn = sbBackToViewReportsBtn;
			studentIDLbl = sbStudentIDLbl;
			previousRepBtn.setDisable(true);
			nextRepBtn.setDisable(true);

			courcesCb = sbCourcesCb;
			showReportsByCourseBtn = sbShowReportsByCourseBtn;
			studentIDLbl.setText(" " + PrincipleViewReportsController.insertedValue);

			coursesList.clear();
			coursesList.add("----------");

			// set "----------" as the first value of the choice box
			courcesCb.setValue("----------");

			// set the choice box to get it's items from 'coursesList'
			courcesCb.setItems(coursesList);

			// hide barchart until after pressing SHOW
//			barChartContainerPn.setVisible(false);

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
			System.out.println(PrincipleViewReportsController.insertedValue);
			coursesList.addAll(PrincipleViewReportsController.list);
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
	void btnPressShowReportsByCourse(ActionEvent event) {
		System.out.println("PrincipleReports::btnPressShowReportsByCourse");

		index = 0;
		ClientUI.chat.accept(new String[] { "GetExamDetails", courcesCb.getValue(),
				PrincipleViewReportsController.insertedValue, "S" });
		if (examResultsList.size() == 1) 
			nextRepBtn.setDisable(true);
		 else 
			nextRepBtn.setDisable(false);
		

		previousRepBtn.setDisable(true);
		histogramBc.getData().removeAll(series);
		setExamResultData();
//		barChartContainerPn.setVisible(true);

	}

	// EXTERNAL METHODS *******************************************************

	/**
	 * sets the exams of the current student's course to be displayed
	 *
	 * @param examResultsList a list of the exams of a certain course of the teacher
	 */
	public void setExamResultsDetails(List<ExamResults> examResultsList) {
		PrincipleReportsByStudentController.examResultsList = examResultsList;
	}

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

}
