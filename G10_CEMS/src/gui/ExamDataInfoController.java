package gui;

import java.util.ArrayList;
import java.util.List;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import logic.Exam;

public class ExamDataInfoController {
	
	@FXML
	private TableView tblVExamDetails;
	
	private static TableView tblE;
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ExamDataInfo.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("CEMS - Client");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void initialize()
	{
		tblE = tblVExamDetails;
		ClientUI.chat.accept("Request Test Table");
	}
	
	public void addExam(List<String> details)
	{
		/*
		 * private ObservableList<Design> data = FXCollections.observableArrayList();
		 * 
		 * 
		 * 
		 * 
		 * data.addAll(designManagement.getDesignArray()); //all you items
		 * 
		 * TableView<Design> table = new TableView<>(); TableColumn<Design, String>
		 * column1 = new TableColumn<>(); column1.setCellValueFactory(new
		 * PropertyValueFactory<Design, String>("id")); TableColumn<Design, String>
		 * column2 = new TableColumn<>(); column2.setCellValueFactory(new
		 * PropertyValueFactory<Design, String>("part_name"));
		 * table.getColumns().add(column1); table.getColumns().add(column2);
		 * 
		 * table.setItems(data);
		 */
		
		/*ObservableList<Exam> data = FXCollections.observableArrayList();
		
		List<Exam> exams = new ArrayList<>();
		for (String row : details)
		{
			String[] s = row.split("\\,");
			Exam e = new Exam(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
			exams.add(e);
		}
		tblE.getItems().add(exams);*/
	}
}
