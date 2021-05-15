package server;

import gui.server.ServerConsoleController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServerUI extends Application{
	
	public final static int DEFAULT_PORT = 5555;
	public static ServerConsoleController serverConsole;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		serverConsole = new ServerConsoleController();
		serverConsole.start(mainStage);
	}

	/**
	 * Starts listening for client connections by using 'EchoServer' which is
	 * a subclass of 'AbstractServer'.<br>Also starts the connection with the database
	 * @param portNumber the port to listen to connections from
	 */
	public static void startServer(int portNumber) {
		
		EchoServer sv = new EchoServer(portNumber);
		
		try {
			sv.listen();
		} catch(Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Server Error");
			alert.setHeaderText("Could not listent for clients!");
			alert.setContentText("Make sure there isn't an open server instance on the same port!");
			alert.showAndWait();
			serverConsole.setDisable(false);
			return;
		}
		
		DBconnector.getInstance().connectToDB();
	}
}
