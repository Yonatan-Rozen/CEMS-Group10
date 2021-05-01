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
import javafx.scene.Node;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Exam;

/**
 * 
 * @author Yonatan Rozen
 *
 */
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
	private TableColumn<Exam, Void> tblCUpdateBtns;
	
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
		tblE = tblVExamDetails;
		bp = borderPane;
		
		// set up columns
		tblCExamID.setCellValueFactory(new PropertyValueFactory<Exam, String>("eID"));
		tblCProfession.setCellValueFactory(new PropertyValueFactory<Exam, String>("eProfession"));
		tblCCourse.setCellValueFactory(new PropertyValueFactory<Exam, String>("eCourse"));
		tblCAllocatedTime.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("eAllocatedTime"));
		tblCScores.setCellValueFactory(new PropertyValueFactory<Exam, String>("eScores"));
		
		// set button cells for the 'Update Time' Column
		Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>> btnCellFactory
        = new Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>>() {
		    @Override
		    public TableCell<Exam, Void> call(final TableColumn<Exam, Void> param) {
		        final TableCell<Exam, Void> cell = new TableCell<Exam, Void>() {
		        	
		            private final Button btn = new Button("update");
		            
		            @Override
		            public void updateItem(Void item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty) {
		                    setGraphic(null);
		                } else {
		                	btn.setOnAction(e -> {
	                            Exam exam = getTableView().getItems().get(getIndex());
	                            updateSelectedExamAllocatedTime(exam);
	                        });
		                    setGraphic(btn);
		                }
		            }
		        };
		        return cell;
		    }
		};
		tblCUpdateBtns.setCellFactory(btnCellFactory);

		ClientUI.chat.accept("Request Test Table");
		
	}
	
	public void addExams(List<String> details)
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
		headline.setFont(Font.font("Verdana", FontWeight.BOLD,20));
		
		Label lblProfession = new Label("Profession: " + exam.getEProfession());
		lblProfession.setFont(Font.font("Verdana", 15));
		
		Label lblCourse = new Label("Course: " + exam.getECourse());
		lblCourse.setFont(Font.font("Verdana", 15));
		
		Label lblAllocatedTime = new Label("Allocated Time: ");
		lblAllocatedTime.setFont(Font.font("Verdana", 15));
		
		TextField txtAllocatedTime = new TextField();
		txtAllocatedTime.setPrefWidth(10);
		txtAllocatedTime.setText(String.valueOf(exam.getEAllocatedTime()));
		txtAllocatedTime.setFont(Font.font("Verdana", 15));
		
		Label lblScores = new Label("Scores: " + exam.getEScores());
		lblScores.setFont(Font.font("Verdana", 15));
		
		Button btnCancelRequest = new Button("cancel request");
		btnCancelRequest.setFont(Font.font("Verdana", 15));
		
		Button btnUpdateTime = new Button("update time");
		btnUpdateTime.setFont(Font.font("Verdana", 15));
		
		gp.add(headline, 0, 0);
		gp.add(lblProfession, 0, 1);
		gp.add(lblCourse, 0, 2);
		gp.add(lblAllocatedTime, 0, 3);
		gp.add(txtAllocatedTime, 1, 3);
		gp.add(lblScores, 0, 4);
		gp.add(btnCancelRequest, 0, 5);
		gp.add(btnUpdateTime, 1, 5);
		
		
		btnCancelRequest.setOnAction(event ->{
			bp.setCenter(tblE);
			System.out.println("tblE should be shown");
		});
		
		btnUpdateTime.setOnAction(event ->{ 
			ClientUI.chat.accept("Update Test AllocatedTime " + txtAllocatedTime.getText() + " ID " + exam.getEID());
			((Node)event.getSource()).getScene().getWindow().hide();
			ClientMenuController.edic.start(new Stage());
		});
	}
}
