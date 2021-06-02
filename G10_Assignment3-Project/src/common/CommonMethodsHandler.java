package common;

import java.io.FileNotFoundException;
import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class CommonMethodsHandler {
	public static final Image CEMS_LOGO = new Image("/logo.png");
	public static final Image CEMS_ICON = new Image("/icon_black.png");
	
	private static CommonMethodsHandler commonMethodsHandler;

	private CommonMethodsHandler() {
	}

	public static CommonMethodsHandler getInstance() {
		if (commonMethodsHandler == null)
			commonMethodsHandler = new CommonMethodsHandler();
		return commonMethodsHandler;
	}

	/**
	 * loads inner fxml file
	 * 
	 * @param packageName the package name
	 * @param fxmlName the fxml file name
	 * @return the requested fxml file as a Pane
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
			//System.out.println(path.toString());
			fileUrl = getClass().getClassLoader().getResource(path.toString());
			if (fileUrl == null) {
				throw new FileNotFoundException(fxmlName + ".fxml could not be found!");
			}
			pane = FXMLLoader.load(fileUrl);
		} catch (Exception e) {
			System.out.println("could not load " + fxmlName);
		}
		return pane;
	}
	
	/**
	 * limits a text field component to a specified maximum amount of character
	 * @param tf the text field component
	 * @param maxLength the maximum length of characters
	 */
	public void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
}
