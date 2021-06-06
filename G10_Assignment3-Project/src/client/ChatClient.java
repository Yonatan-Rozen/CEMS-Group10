package client;

import java.io.IOException;
import java.util.List;

import common.CommonMethodsHandler;
import gui.client.ChangePasswordController;
import gui.client.SignInController;
import gui.client.principle.PrincipleViewExamsInfoScreenController;
import gui.client.principle.PrincipleViewQuestionsInfoScreenController;
import gui.client.principle.PrincipleViewUsersInfoScreenController;
import gui.client.student.StudentTakeComputerizedExamController;
import gui.client.teacher.TeacherChooseEditQuestionController;
import gui.client.teacher.TeacherCreateExamController;
import gui.client.teacher.TeacherCreateQuestionController;
import gui.client.teacher.TeacherEditQuestionController;
import gui.client.teacher.TeacherReportsController;
import javafx.scene.control.Alert.AlertType;
import logic.User;
import logic.exam.Exam;
import logic.exam.ExamResults;
import logic.question.Question;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF ClientController;
	public static boolean awaitResponse = false;
	public static User user;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 * 
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.ClientController = clientUI;
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		if (msg == null) {
			ClientController.display("fatal error");
		}
		/**** short execution checks ***/
		else if (msg instanceof User) { // SignIn Success
			user = (User) msg;
			ClientController.display("user [" + user.getUsername() + "] has connected successfully!");
		}

		/**** method execution handling ****/
		else if (msg instanceof String) {
			handleStringMessagesFromServer((String) msg);
		} else if (msg instanceof List) {
			handleListMessagesFromserver((List<?>) msg);
		} else if (msg instanceof Object[])
			handleArraysMessagesFromServer((Object[]) msg);

		else if (msg instanceof Exam) {
			StudentTakeComputerizedExamController.stceController.setExam((Exam) msg);

		}

		// releases 'handleMessageFromClientUI' to continue getting new input
		awaitResponse = false;
	}

	/**
	 * Handles with (Object[]) type messages.
	 * 
	 * @param msg The (Object[]) object
	 */
	private void handleArraysMessagesFromServer(Object[] msg) {
		Object obj = msg[0];

		if (obj instanceof String) {
			String[] stringArray = (String[]) msg;
			switch (stringArray[0]) {
			case "checkQuestionExistsInExam":
				TeacherChooseEditQuestionController.tceqController.setQuestionDeletable(stringArray[1]);
				break;
			default:
				ClientController.display(stringArray[0] + " is missing!");
				break;
			}
		}

	}

	/**
	 * Handle with (String) type messages.
	 * 
	 * @param msg The (String) object.
	 */
	private void handleStringMessagesFromServer(String msg) {
		/**** handle connection ****/
		if (msg.equals("Disconnect"))
			return; // Client is disconnecting
		if (msg.equals("TerminateClient"))
			quit(); // Terminates the current client

		else if (msg.contains("UpdatedQuestion")) { // Question has been updated
			System.out.println("UpdatedQuestion");
			TeacherEditQuestionController.teqController
					.successfulEditQuestion("The question has been edited successfully!");
		}
		/**** handle return message to client ****/
		else if (msg.contains("SignIn ERROR - ")) // SignIn Errors
			SignInController.siController.setErrorMsg(msg.substring("SignIn ERROR - ".length()));

		else if (msg.contains("ChangePassword ERROR - ")) { // ChangePassword Errors
			ChangePasswordController.cpController.badChangePassword(msg.substring("ChangePassword ERROR - ".length()));
		} else if (msg.contains("ChangePassword SUCCESS - "))

		{ // ChangePassword Success
			ChangePasswordController.cpController
					.successfulChangePassword(msg.substring("ChangePassword SUCCESS - ".length()));
		} else if (msg.contains("courseName:")) { // TakeComputerizedExam Error
			StudentTakeComputerizedExamController.stceController.setCourseName(msg.substring("courseName:".length()));
		} else if (msg.contains("CreateQuestion SUCCESS - ")) { // CreateQuestion Success
			TeacherCreateQuestionController.tcqController
					.successfulCreateQuestion(msg.substring("CreateQuestion SUCCESS - ".length()));
		} else if (msg.contains("CreateExam SUCCESS - ")) { // createExam Success
			TeacherCreateExamController.tceController
					.successfulCreateExam(msg.substring("CreateExam SUCCESS - ".length()));
		} else if (msg.contains("GetSubjectsWithBank ERROR - ")) { // ChooseEditQuestion Error
			TeacherChooseEditQuestionController.tceqController
					.badGetSubjectsWithBank(msg.substring("GetSubjectsWithBank ERROR - ".length()));
		} else
			ClientController.display(msg);
	}

	/**
	 * Handles with (List<?>) type messages.
	 * 
	 * @param msg The (List<?>) object
	 */
	@SuppressWarnings("unchecked")
	private void handleListMessagesFromserver(List<?> msg) {
		Object obj = msg.get(0);
		msg.remove(0);
		if (obj instanceof String) { // list of String
			List<String> list = ((List<String>) msg);
			switch (obj.toString()) {
			case "getSubjectsByUsername":
				TeacherCreateQuestionController.tcqController.setSubjectChoiceBox(list);
				return;
			case "getSubjectWithExistingBanks":
				TeacherChooseEditQuestionController.tceqController.setSubjectChoiceBox(list);
				return;
			case "getCoursesByUserName":
				TeacherReportsController.trController.setCoursesCoiseBox(list);
				return;
			case "getBanksByUsername":
				TeacherCreateExamController.tceController.setBankChoiceBox(list);
				return;
			case "getCourseBySubject":
				TeacherCreateExamController.tceController.setCourseChoiceBox(list);
				return;
			default:
				ClientController.display(obj.toString() + " is missing!");
				break;
			}

		} else if (obj instanceof Question) { // list of questions
			List<Question> questionList = (List<Question>) msg;
			System.out.println(questionList);
			switch (((Question) obj).getQuestionID()) {
			case "getExamsQuestionsByExamID":
				StudentTakeComputerizedExamController.stceController.setQuestionsOfExam(questionList);
				return;
			case "getQuestionsBySubjectAndUsername":
				TeacherChooseEditQuestionController.tceqController.setQuestionTableView(questionList);
				return;
			case "getQuestionsBySubjectAndUsername2":
				TeacherCreateExamController.tceController.setQuestionTableView(questionList);
				return;
			case "getQuestionsTableViewInfo":
				PrincipleViewQuestionsInfoScreenController.pvqisController.setQuestionsInfoList(questionList);
				return;
			default:
				ClientController.display(((Question) obj).getQuestionID() + " is missing!");
				break;
			}
		} else if (obj instanceof ExamResults) {
			List<ExamResults> examResultsList = (List<ExamResults>) msg;
			System.out.println(examResultsList);
			switch (((ExamResults) obj).getExamID()) {
			case "getExamDetails":
				TeacherReportsController.trController.setExamResultsDetails(examResultsList);
				break;
			default:
				ClientController.display(((ExamResults) obj).getExamID() + " is missing!");
				break;
			}
		} else if (obj instanceof User) { // List of users
			List<User> usersList = (List<User>) msg;
			System.out.println(usersList);
			switch (((User) obj).getUsername()) {
			case "getUsersTableViewInfo":
				PrincipleViewUsersInfoScreenController.pvuisController.setUsersInfoList(usersList);
				return;
			default:
				ClientController.display(((User) obj).getUsername() + " is missing!");
				break;
			}
		} else if (obj instanceof Exam) { // List of exams
			List<Exam> examsList = (List<Exam>) msg;
			System.out.println(examsList);
			switch (((Exam) obj).getExamID()) {
			case "getExamsTableViewInfo":
				PrincipleViewExamsInfoScreenController.pveisController.setExamsInfoList(examsList);
				return;
			default:
				ClientController.display(((Exam) obj).getExamID() + " is missing!");
				break;
			}
		}
	}

	/**
	 * Handles with messages that the client sends to the server
	 * 
	 * @param obj The message to send
	 */
	public void handleMessageFromClientUI(Object obj) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(obj);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			ClientController.display("Could not send message to server: Terminating client." + e);
			CommonMethodsHandler.getInstance()
					.getNewAlert(AlertType.WARNING, "Connection Issues",
							"It seems like you have connection issues with the server!",
							"Sorry for the inconvenience. Please try agian at a later time...")
					.showAndWait();

			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

}
