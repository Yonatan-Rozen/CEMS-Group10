package common;

import java.io.FileNotFoundException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class CommonMethodsHandler {
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
		URL fileUrl = null;
		try {
			switch (packageName) {
				case "student":
				case "principle":
				case "teacher":
					fileUrl = getClass().getResource("/gui/client/" + packageName + "/" + fxmlName + ".fxml");
					break;
				case "client":
				case "server":
					fileUrl = getClass().getResource("/gui/"+ packageName +"/" + fxmlName + ".fxml");
					break;
				default:
					break;
			}
			if (fileUrl == null) {
				throw new FileNotFoundException(fxmlName + ".fxml could not be found!");
			}
			pane = FXMLLoader.load(fileUrl);
		} catch (Exception e) {
			System.out.println("could not load " + fxmlName);
		}
		return pane;
	}
}
