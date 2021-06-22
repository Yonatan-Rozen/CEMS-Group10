package server;

import common.CommonMethodsHandler;
import gui.server.ServerConsoleController;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 *
 * a class that opens ServerConsole
 */
public class ServerUI extends Application{

	public final static int DEFAULT_PORT = 5555;
	public static ServerConsoleController serverConsole;
	private static boolean alreadyRunning;

	public static void main(String[] args) {

		try {
			JUnique.acquireLock("Cems-Server");
			alreadyRunning = false;
		} catch (AlreadyLockedException e) {
			alreadyRunning = true; }

		launch(args); // <-- default JavaFX start
	}

	@Override
	public void start(Stage mainStage) throws Exception {

		if (alreadyRunning) {
			System.out.println("server instance already exists!");
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION,
					"Server already exists","Only one instance of the server is allowed!").showAndWait();
			System.exit(1);
		}

		serverConsole = new ServerConsoleController();
		serverConsole.start(mainStage);
	}

	/**
	 * Starts listening for client connections by using 'EchoServer' which is
	 * a subclass of 'AbstractServer'.
	 *
	 * <br> Also starts the connection with the database
	 * @param portNumber the port to listen to connections from
	 */
	public static void startServer(int portNumber) {

		EchoServer sv = new EchoServer(portNumber);

		try {
			sv.listen();
		} catch(Exception ex) {
			try { DBconnector.getInstance().resetUserConnections();
			}catch(Exception e) { System.out.println("connection isn't working!"); }

			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR,"Server Error", "Could not listent for clients!",
					"Make sure there isn't an open server instance on the same port!" ).showAndWait();
			serverConsole.setDisable(false);
			return;
		}

		DBconnector.getInstance().setServerConsole(ServerUI.serverConsole);
		DBconnector.getInstance().connectToDB();
		DBconnector.getInstance().resetUserConnections();
	}
}
