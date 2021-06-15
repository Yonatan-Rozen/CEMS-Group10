package gui.client.principle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.exam.IExam;
import logic.exam.Request;

public class PrincipleViewRequestsController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
	@FXML
	private TableView<Request> sbRequestsTv;

	@FXML
	private TableColumn<Request, String> sbTeacherTc;

	@FXML
	private TableColumn<Request, String> sbExamIdTc;

	@FXML
	private TableColumn<Request, Integer> sbOriginalAllocatedTimeTc;

	@FXML
	private TableColumn<Request, Integer> sbNewAllocatedTimeTc;

	@FXML
	private Button sbAcceptRequestBtn;

	@FXML
	private Button sbDeclineRequestBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private TableView<Request> requestsTv;
	private TableColumn<Request, String> teacherTc;
	private TableColumn<Request, String> examIdTc;
	private TableColumn<Request, Integer> origTimeTc;
	private TableColumn<Request, Integer> newTimeTc;
	private Button acceptRequestBtn;
	private Button declineRequestBtn;
	private static ObservableList<Request> requestList;
	public static PrincipleViewRequestsController pvrController;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pvrController = new PrincipleViewRequestsController();
		setUpTableProperties();
		setUpButtonsProperties();
		ClientUI.chat.accept(new String[] { "sbViewRequests",ChatClient.user.getUsername() });
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressAcceptRequest(ActionEvent event) 
	{
		// TODO send change allocated time request to server
		System.out.println("PrincipleViewRequests::btnPressAcceptRequest");
	}

	@FXML
	void btnPressDeclineRequest(ActionEvent event) {
		// TODO send message "request declined" to teacher
		System.out.println("PrincipleViewRequests::btnPressAcceptRequest");
	}
	
	//Set The Table Properties
	void setUpTableProperties()
	{
		requestsTv = sbRequestsTv;
		sbTeacherTc.setCellValueFactory(new PropertyValueFactory<Request, String>("Teacher"));
		sbExamIdTc.setCellValueFactory(new PropertyValueFactory<Request, String>("ExamID"));
		sbOriginalAllocatedTimeTc.setCellValueFactory(new PropertyValueFactory<Request, Integer>("Original Allocated Time"));
		sbNewAllocatedTimeTc.setCellValueFactory(new PropertyValueFactory<Request, Integer>("New Allocated Time"));
	}
	//======================================================================================================================
	//Set The Buttons Properties
	void setUpButtonsProperties()
	{
		acceptRequestBtn = sbAcceptRequestBtn;
		declineRequestBtn = sbDeclineRequestBtn;
	}
	//======================================================================================================================
	/**
	 * This method puts the tuples of the requests from the DB into the tableView
	 * @param  List<Request> of the request in the table from DB
	 */
	public void fillRequstTable(Request requestFromDataBase)
	{
		requestList.clear();
		requestList.addAll(requestFromDataBase);
		try {
			requestsTv.setItems(requestList);
		} catch (IllegalStateException e) {}
	}
	
	
}
