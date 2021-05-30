package gui.client.principle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrincipleReportsController implements Initializable {

	@FXML
	private BarChart<?, ?> sbHistogramBc;

	@FXML
	private CategoryAxis sbCategoryAxis;

	@FXML
	private NumberAxis sbNumberAxis;

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

	private BarChart<?, ?> histogramBc;
	private CategoryAxis categoryAxis;
	private NumberAxis numberAxis;
	private Label reportsByLbl;
	private Label examIDLbl;
	private Label medianLbl;
	private Label averageLbl;
	private Button nextRepBtn;
	private Button previousRepBtn;
	

	public void start() throws IOException {
		// TODO Auto-generated method stub
		//example **************************************
		PrincipleMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleReports"));
		//**********************************************
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		histogramBc = sbHistogramBc;
		categoryAxis = sbCategoryAxis;
		numberAxis = sbNumberAxis;
		reportsByLbl = sbReportsByLbl;
		examIDLbl = sbExamIDLbl;
		medianLbl = sbMedianLbl;
		averageLbl = sbAverageLbl;
		nextRepBtn = sbNextRepBtn;
		previousRepBtn = sbPreviousRepBtn;
	}

	@FXML
	void BtnPressPrevousRep(ActionEvent event) {
		// TODO show the next report
		System.out.println("PrincipleReports::BtnPressPrevousRep");
	}

	@FXML
	void btnPressNextRep(ActionEvent event) {
		// TODO show the previous report
		System.out.println("PrincipleReports::btnPressNextRep");
	}

}