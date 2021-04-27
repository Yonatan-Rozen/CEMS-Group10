package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Exam;

public class ExamDataInfoController implements Initializable{
	
	@FXML
	private BorderPane borderPane;
	
	@FXML
	private TableView<Exam> tblVExamDetails;
	
	@FXML
	private TableColumn<Exam,String> tblCExamID;
	
	@FXML
	private TableColumn<Exam,String> tblCProfession;
	
	@FXML
	private TableColumn<Exam,String> tblCCourse;
	
	@FXML
	private TableColumn<Exam,Integer> tblCAllocatedTime;
	
	@FXML
	private TableColumn<Exam,String> tblCScores;
	
	@FXML
	private TableColumn tblCUpdateBtns;
	
	/*
	private final ObservableList<Exam> data
    = FXCollections.observableArrayList(
            new Exam("123456", "Math", "Mlm", 120, "50|20|30")
    );*/
	
	private static TableView<Exam> tblE;
	private static BorderPane bp;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// set up columns
		tblCExamID.setCellValueFactory(new PropertyValueFactory<Exam, String>("eID"));
		tblCProfession.setCellValueFactory(new PropertyValueFactory<Exam, String>("eProfession"));
		tblCCourse.setCellValueFactory(new PropertyValueFactory<Exam, String>("eCourse"));
		tblCAllocatedTime.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("eAllocatedTime"));
		tblCScores.setCellValueFactory(new PropertyValueFactory<Exam, String>("eScores"));
		
		// 
		Callback<TableColumn<Exam, String>, TableCell<Exam, String>> btnCellFactory
        = new Callback<TableColumn<Exam, String>, TableCell<Exam, String>>() {
		    @Override
		    public TableCell call(final TableColumn<Exam, String> param) {
		        final TableCell<Exam, String> cell = new TableCell<Exam, String>() {
		
		            final Button btn = new Button("update");
		
		            @Override
		            public void updateItem(String item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty) {
		                    setGraphic(null);
		                    setText(null);
		                } else {
		                    btn.setOnAction(event -> {
                                Exam exam = getTableView().getItems().get(getIndex());
                                System.out.println(exam);
                                tblVExamDetails.setVisible(false);
                                updateSelectedExamAllocatedTime(exam);
                                
		                    });
		                    setGraphic(btn);
		                    setText(null);
		                }
		            }
		        };
		        return cell;
		    }
		};
		tblCUpdateBtns.setCellFactory(btnCellFactory);
		
		tblE = tblVExamDetails;
		bp = borderPane;
		ClientUI.chat.accept("Request Test Table");
	}
	
	public void addExam(List<String> details)
	{
		ObservableList<Exam> exams = FXCollections.observableArrayList();
		for (String row : details)
		{
			String[] s = row.split("\\,");
			Exam e = new Exam(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
			exams.add(e);
		}
		tblE.setItems(exams);
	}
	
	public void updateSelectedExamAllocatedTime(Exam exam) {
		
		GridPane gp = new GridPane();
		bp.setCenter(gp);
		Label headline = new Label("Exam "+ exam.getEID() + " Details:");
		headline.setUnderline(true);
		headline.setFont(new Font("Arial", 20));
		
		Label lblProfession = new Label("Profession: " + exam.getEProfession());
		
		Label lblCourse = new Label("Profession: " + exam.getECourse());
		
		Label lblAllocatedTime = new Label("Allocated Time: ");
		
		TextField txtAllocatedTime = new TextField();
		txtAllocatedTime.setText(String.valueOf(exam.getEAllocatedTime()));
		
		Label lblScores = new Label("Scores: " + exam.getEScores());
		
		Button btnCancelRequest = new Button("cancel request");
		
		Button btnUpdateTime = new Button("update time");
		
		gp.add(headline, 0, 0);
		gp.add(lblProfession, 0, 1);
		gp.add(lblCourse, 0, 2);
		gp.add(lblAllocatedTime, 0, 3);
		gp.add(txtAllocatedTime, 1, 3);
		gp.add(lblScores, 0, 4);
		gp.add(btnCancelRequest, 0, 5);
		gp.add(btnUpdateTime, 1, 5);
		
		
		btnCancelRequest.setOnAction(event ->{
			tblE.setVisible(true);
			bp.getChildren().remove(gp);
		});
		
		/*btnUpdateTime.setOnAction(event ->{
			ClientUI.chat.accept("Update " + exam.getEID() + " " + txtAllocatedTime.getText());
		});*/
	}
}
