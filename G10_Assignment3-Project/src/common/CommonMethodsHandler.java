package common;

import java.io.FileNotFoundException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class CommonMethodsHandler 
{
	private static CommonMethodsHandler commonMethodsHandler;
	
	private CommonMethodsHandler() {
	}
	
	public static CommonMethodsHandler getInstance() {
		if (commonMethodsHandler == null)
			commonMethodsHandler = new CommonMethodsHandler();
		return commonMethodsHandler;
	}
	
	public Pane getPane(String fxmlName) {
		Pane pane = null;
		try {
			URL fileUrl = getClass().getResource("/gui/client/principle/" + fxmlName + ".fxml");
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
