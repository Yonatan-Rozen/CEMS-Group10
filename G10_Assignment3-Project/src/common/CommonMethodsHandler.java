package common;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import client.ClientUI;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CommonMethodsHandler {
	public static final Image CEMS_LOGO = new Image("/logo.png");
	public static final Image CEMS_ICON = new Image("/icon_black.png");
	public static final Image ICON_CORRECT = new Image("/icon_correct.png");
	public static final Image ICON_WRONG = new Image("/icon_wrong.png");

	private static CommonMethodsHandler commonMethodsHandler;
	private static List<String> choiceBoxesList = new ArrayList<>();

	private CommonMethodsHandler() {
	}

	public static CommonMethodsHandler getInstance() {
		if (commonMethodsHandler == null)
			commonMethodsHandler = new CommonMethodsHandler();
		return commonMethodsHandler;
	}

	// ******************************************************************************************

	/**
	 * Loads a specified fxml file
	 *
	 * @param packageName the package name
	 * @param fxmlName    the fxml file name
	 * @return the requested fxml file as a Pane
	 *
	 * @author Yonatan Rozen
	 */
	public Pane getPane(String packageName, String fxmlName) {
		Pane pane = null;
		StringBuilder path = new StringBuilder("gui/");
		URL fileUrl = null;
		try {
			switch (packageName) {
			case "student":
			case "principle":
			case "teacher":
				path.append("client/").append(packageName).append("/").append(fxmlName).append(".fxml");
				break;
			case "client":
			case "server":
				path.append(packageName).append("/").append(fxmlName).append(".fxml");
				break;
			default:
				break;
			}
			// System.out.println(path.toString());
			try {
				fileUrl = getClass().getClassLoader().getResource(path.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (fileUrl == null) {
				throw new FileNotFoundException(fxmlName + ".fxml could not be found!");
			}
			pane = FXMLLoader.load(fileUrl);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("could not load " + fxmlName);
		}
		return pane;
	}

	// ******************************************************************************************

	/**
	 * limits a text field component to a specified maximum amount of character
	 * 
	 * @param tf        The text field component
	 * @param maxLength The maximum length of characters
	 *
	 * @author Yonatan Rozen
	 */
	public void setTextLimiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			if (tf.getText().length() > maxLength) {
				String s = tf.getText().substring(0, maxLength);
				tf.setText(s);
			}
		});
	}

	// ******************************************************************************************

	/**
	 * allow only integer input
	 * 
	 * @param tf The text field component
	 * 
	 * @author Yonatan Rozen
	 */
	public void setIntegersOnlyText(TextField tf) {

		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*"))
				((StringProperty) observable).set(oldValue);
		});
	}

	// ******************************************************************************************

	/**
	 * Combination of {@link setIntegersOnlyText} and {@link setTextLimiter}
	 * 
	 * @param tf        The text field component
	 * @param maxLength The maximum length of characters
	 * 
	 * @author Yonatan Rozen
	 */
	public void setIntegersOnlyTextLimiter(TextField tf, final int maxLength) {

		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*"))
				((StringProperty) observable).set(oldValue);

			if (tf.getText().length() > maxLength) {
				String s = tf.getText().substring(0, maxLength);
				tf.setText(s);
			}
		});
	}

	// ALERT METHODS
	// ***************************************************************************
	/**
	 * Set up a simple alert message (Full)
	 *
	 * @param alertType The type of the alert [see {@link AlertType}]
	 * @param title     The The title of the alert
	 * @param header    The message in the header area
	 * @param context   The message in the context area
	 * @return A newly created alert
	 *
	 * @author Yonatan Rozen
	 */
	public Alert getNewAlert(AlertType alertType, String title, String header, String context) {
		Alert alert = new Alert(alertType);
		alert.initStyle(StageStyle.DECORATED);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(context);
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(CEMS_ICON);
		return alert;
	}

	/**
	 * Set up a simple alert message (Without header)
	 *
	 * @param alertType The type of the alert [see {@link AlertType}]
	 * @param title     The title of the alert
	 * @param context   The message in the context area
	 * @return A newly created alert
	 *
	 * @author Yonatan Rozen
	 */
	public Alert getNewAlert(AlertType alertType, String title, String context) {
		Alert alert = new Alert(alertType);
		alert.initStyle(StageStyle.DECORATED);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(context);
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(CEMS_ICON);
		return alert;
	}

	/**
	 * Set up an alert message with buttons (Full)
	 *
	 * @param alertType The type of the alert [see {@link AlertType}]
	 * @param title     The The title of the alert
	 * @param header    The message in the header area
	 * @param context   The message in the context area
	 * @param buttons   The ButtonType to set
	 * @return A newly created alert
	 *
	 * @author Yonatan Rozen
	 */
	public Alert getNewAlert(AlertType alertType, String title, String header, String context, ButtonType... buttons) {
		Alert alert = new Alert(alertType, context, buttons);
		alert.initStyle(StageStyle.DECORATED);
		alert.setTitle(title);
		alert.setHeaderText(header);
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(CEMS_ICON);
		return alert;
	}

	/**
	 * Set up an alert message with buttons (Without header)
	 *
	 * @param alertType The type of the alert [see {@link AlertType}]
	 * @param title     The The title of the alert
	 * @param context   The message in the context area
	 * @param buttons   The ButtonType to set
	 * @return A newly created alert
	 *
	 * @author Yonatan Rozen
	 */
	public Alert getNewAlert(AlertType alertType, String title, String context, ButtonType... buttons) {
		Alert alert = new Alert(alertType, context, buttons);
		alert.initStyle(StageStyle.DECORATED);
		alert.setTitle(title);
		alert.setHeaderText(null);
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(CEMS_ICON);
		return alert;
	}
	// *****************************************************************************************

	/**
	 * Gets the number of the answer acoording to the selected RadioButton
	 *
	 * @param selected The RadioButton that reperesents the chosen answer
	 * @return The number of the answer [1/2/3/4]
	 *
	 * @author Yonatan Rozen
	 */
	public String getSelectedAnswer(RadioButton selected) {
		switch (selected.getText()) {
		case "A":
			return "1";
		case "B":
			return "2";
		case "C":
			return "3";
		case "D":
			return "4";
		default:
			System.out.println("Please fix this radio button!");
			return "";
		}
	}

	// *****************************************************************************************

	/**
	 * Disable the newly pressed button instead the currentBtn one
	 * 
	 * @param currentBtn   The disabled button before
	 * @param newlyPressed The 'to-be' disabled button after
	 */
	public Button disablePropertySwapper(Button currentBtn, Button newBtn) {
		if (currentBtn != null)
			currentBtn.setDisable(false);
		newBtn.setDisable(true);
		return newBtn;
	}

	// *****************************************************************************************

	/**
	 * Disables swaping of the table columns
	 * 
	 * @param tableView
	 */
	public void disableTableColumnSwap(TableView<?> tableView) {
		tableView.widthProperty().addListener((source, oldWidth, newWidth) -> {
			TableHeaderRow header = (TableHeaderRow) tableView.lookup("TableHeaderRow");
			header.reorderingProperty().addListener((observable, oldValue, newValue) -> {
				header.setReordering(false);
			});
		});
	}

	// *****************************************************************************************

	/**
	 * Send the server a request for a specific List of Strings
	 * 
	 * @param request The request
	 * @param alert   shows up if the List returned was empty
	 * @return true if the list isn't empy, false otherwise
	 */
	public List<String> getListRequest(String[] request, Alert alert) {
		ClientUI.chat.accept(request);
		if (choiceBoxesList.isEmpty()) {
			alert.showAndWait();
			System.out.println("populateList");
			return null;
		}
		return choiceBoxesList;
	}

	// ChatClient METHODS
	// ***********************************************************************

	public void setChoiceBoxList(List<String> list) {
		choiceBoxesList.clear();
		choiceBoxesList.addAll(list);
	}
}
