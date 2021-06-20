package gui.client.principle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.User;
/**
 * A controller class which controls the "View Users" screen from the "View Info" option
 *  of the Principle user.
 * @author Michael Malka, Tuval Zitelbach & Meitar El Ezra
 */
public class PrincipleViewUsersInfoScreenController implements Initializable {
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TableView<User> tblVusersDetails;

	@FXML
	private TableColumn<User, String> sbUsernameClm;

	@FXML
	private TableColumn<User, String> sbLastNameClm;

	@FXML
	private TableColumn<User, String> sbFirstNameClm;

	@FXML
	private TableColumn<User, String> sbPhoneNumberClm;

	@FXML
	private TableColumn<User, String> sbEmailAddClm;

	@FXML
	private TableColumn<User, String> sbUserTypeClm;

	@FXML
	private Button sbBackToViewInfoBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView <User> tblE;
	public static PrincipleViewUsersInfoScreenController pvuisController;
	private static ObservableList<User> usersDetails;// = new ArrayList<>();
	private static Button backToViewInfoBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblE = tblVusersDetails;
		usersDetails= FXCollections.observableArrayList();
		pvuisController=new PrincipleViewUsersInfoScreenController();
		// set up columns
		sbUsernameClm.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		sbLastNameClm.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		sbFirstNameClm.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
		sbPhoneNumberClm.setCellValueFactory(new PropertyValueFactory<User, String>("phonenumber"));
		sbEmailAddClm.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		sbUserTypeClm.setCellValueFactory(new PropertyValueFactory<User, String>("type"));

		ClientUI.chat.accept(new String[] { "sbViewUsersBtn" });

	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressBackToViewInfo(ActionEvent event) throws IOException {
		PrincipleMenuBarController.	mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewInfo"));
	}

	// EXTERNAL USE METHODS *************************************************

	/**
	 * This method puts the tuples of the users from the DB into the tableView
	 * @param usersList List<User> of the users in the table from DB
	 */
	//public void addExamToObservableList(List<String> rowInUsersTable)
	public void setUsersInfoList(List<User> usersList) {
		{
			//	ObservableList<User> users = FXCollections.observableArrayList();
			for (User row : usersList)
				usersDetails.add(row);
			tblE.setItems(usersDetails);
		}
	}
}
