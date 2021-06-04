package gui.client.principle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.User;

public class PrincipleViewUsersInfoScreenController implements Initializable{

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

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView <User> tblE;
	public static PrincipleViewUsersInfoScreenController pvuisController;
	private static ObservableList<User> usersDetails;// = new ArrayList<>();


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

	// EXTERNAL USE METHODS *************************************************

	/*
	 *
	 * @param rowInExamTable
	 */
	//public void addExamToObservableList(List<String> rowInUsersTable)
	public void setUsersInfoList(List<User> usersList) {
		{
			//	ObservableList<User> users = FXCollections.observableArrayList();
			for (User row : usersList)
				usersDetails.add(row);
			tblE.setItems(usersDetails);
		}


		/**
		 *
		 * @param usersList
		 */
		//	public void setUsersInfoList(List<User> usersList) {
		//		usersDetails=usersList;
		//	}

	}
}
