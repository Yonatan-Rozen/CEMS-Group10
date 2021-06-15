package client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import common.CommonMethodsHandler;
import common.MyFile;
import gui.client.ChangePasswordController;
import gui.client.SignInController;
import gui.client.principle.PrincipleReportsByCourseController;
import gui.client.principle.PrincipleReportsByStudentController;
import gui.client.principle.PrincipleReportsByTeacherController;
import gui.client.principle.PrincipleViewExamsInfoScreenController;
import gui.client.principle.PrincipleViewQuestionsInfoScreenController;
import gui.client.principle.PrincipleViewReportsController;
import gui.client.principle.PrincipleViewUsersInfoScreenController;
import gui.client.student.StudentMenuController;
import gui.client.student.StudentTakeComputerizedExamController;
import gui.client.teacher.TeacherChooseEditQuestionController;
import gui.client.teacher.TeacherComputerizedExamDefinitionsController;
import gui.client.teacher.TeacherCreateExamController;
import gui.client.teacher.TeacherCreateManualExamController;
import gui.client.teacher.TeacherCreateQuestionController;
import gui.client.teacher.TeacherEditExamController;
import gui.client.teacher.TeacherEditQuestionController;
import gui.client.teacher.TeacherReportsController;
import gui.client.teacher.TeacherStartExamController;
import javafx.scene.control.Alert.AlertType;
import logic.User;
import logic.exam.ComputerizedExam;
import logic.exam.Exam;
import logic.exam.ExamResults;
import logic.exam.IExam;
import logic.question.Question;
import logic.question.QuestionInExam;
import ocsf.client.AbstractClient;
import server.ServerUI;

public class ChatClient extends AbstractClient {

	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF ClientController;
	public static boolean awaitResponse = false;
	public static User user;
	// public static String msg;
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
	 * @throws IOException
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println("===================handleMessageFromServer :: "+msg+"===================");
		if (msg == null) {
			ClientController.display("fatal error (null object has been passed)");
		} else if (msg instanceof User) { // SignIn Success
			user = (User) msg;
			ClientController.display("user [" + user.getUsername() + "] has connected successfully!");
		}

		/**** method execution handling ****/
		else if (msg instanceof String) {
			handleStringMessagesFromServer((String) msg);
		} else if (msg instanceof List)
			handleListMessagesFromserver((List<?>) msg);
		else if (msg instanceof Object[]) {
			// System.out.println("check instaceOF object[]");
			handleArraysMessagesFromServer((Object[]) msg);
		} else if (msg instanceof ComputerizedExam)
			StudentTakeComputerizedExamController.stceController.setExam((ComputerizedExam) msg);
		// else if (msg instanceof ManualExam)
		// StudentTakeExamManuallyController.stemController.setExam((ManualExam) msg);
		else if (msg instanceof Boolean)
			PrincipleViewReportsController.pvrController.setDoesExit((boolean) msg);

		// releases 'handleMessageFromClientUI' to continue getting new input
		awaitResponse = false;
	}

	/**
	 * Handles with (Object[]) type messages.
	 *
	 * @param msg The (Object[]) object
	 * @throws IOException
	 */
	private void handleArraysMessagesFromServer(Object[] msg) {
		System.out.println("in handle array mesages :: "+msg[0]);
		switch (msg[0].toString()) {
		case "checkQuestionExistsInExam":
			TeacherChooseEditQuestionController.tceqController.setQuestionDeletable(msg[1].toString());
			break;
		case "setTypeAndOptionalTeacherComments":
			TeacherStartExamController.tseController.setTypeAndOptionalComments((String[]) msg);
			// TeacherStartExamController.tseController.setTypeAndOptionalComments(msg[1].toString()
			// + "|" + msg[2].toString());
			break;
		case "GetExamIDForComputerizedExam":
			TeacherComputerizedExamDefinitionsController.tcedController.setExamID(msg[1].toString());
			break;
		case "CreateManualDetails":
			TeacherCreateManualExamController.tcmeController.successfulCreateDetailsAndSetExamID(msg[1].toString(),
					"CreateManualExam SUCCESS" + msg[1].toString());
			break;
		case "SendMessageExamIDExamTypeAndExamCode":
			System.out.println("BEFORE SETREADY ------------<<<<<<");
			System.out.println("the array that's being passed to StudentMenuController :\n"+Arrays.toString(msg));
			StudentMenuController.smController.setReadyExam((String[]) msg);
			System.out.println("AFTER SETREADY ------------<<<<<<");
			break;
		case "MessageSentExamIDExamTypeAndExamCode":
			TeacherStartExamController.tseController.checkStartExam(msg);
			break;
		case "SendMessageLockExam":
			StudentMenuController.smController.lockExam((String[]) msg);
			break;
		case "SendMessageIncNumStudentsInExam":
			TeacherStartExamController.tseController.IncStudentsInExam();
			break;
		case "SendMessageDecNumStudentsInExam":
			System.out.println("entered case SendMessageDecNumStudentsInExam <<<<<<<<<<<");
			TeacherStartExamController.tseController.DecStudentsInExam();
			break;
		default:
			ClientController.display(msg[0].toString() + " is missing!");
			break;
		}
	}

	/**
	 * Handle with (String) type messages.
	 *
	 * @param msg The (String) object.
	 */
	private void handleStringMessagesFromServer(String msg) {
		System.out.println("msg is : "+msg);
		/**** handle connection ****/
		if (msg.equals("Disconnect"))
			return; // Client is disconnecting
		if (msg.equals("TerminateClient"))
			quit(); // Terminates the current client
		else if (msg.contains("UpdatedQuestion")) // Question has been updated
			TeacherEditQuestionController.teqController
			.successfulEditQuestion("The question has been edited successfully!");
		/**** handle return message to client ****/
		else if (msg.contains("SignIn ERROR - ")) // SignIn Errors
			SignInController.siController.setErrorMsg(msg.substring("SignIn ERROR - ".length()));
		else if (msg.contains("ChangePassword ERROR - ")) // ChangePassword Errors
			ChangePasswordController.cpController.badChangePassword(msg.substring("ChangePassword ERROR - ".length()));
		else if (msg.contains("ChangePassword SUCCESS - ")) { // ChangePassword Success
			ChangePasswordController.cpController
			.successfulChangePassword(msg.substring("ChangePassword SUCCESS - ".length()));
			//			ChangePasswordController.cpController.badChangePassword(msg.substring("ChangePassword ERROR - ".length()));
		} else if (msg.contains("ChangePassword SUCCESS - ")) { // ChangePassword Success
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
		} else if (msg.contains("EditExam SUCCESS - ")) {
			TeacherEditExamController.teeController.successfulEditExam(msg.substring("EditExam SUCCESS - ".length()));
		} else if (msg.contains("Update Question")) {
			TeacherComputerizedExamDefinitionsController.tcedController.successfulUpdateQuestionInExam(msg);
		} else if (msg.contains("GetSubjectsWithBank ERROR - ")) { // ChooseEditQuestion Error
			TeacherChooseEditQuestionController.tceqController
			.badGetSubjectsWithBank(msg.substring("GetSubjectsWithBank ERROR - ".length()));
		} else {
			System.out.println("BEFORE DISPLAY");
			ClientController.display(msg+" is the message sent :(");
			System.out.println("AFTER DISPLAY");
		}

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
			List<String> stringList = ((List<String>) msg);
			switch (obj.toString()) {
			case "getSubjectsByUsername":
				TeacherCreateQuestionController.tcqController.setSubjectChoiceBox(stringList);
				return;
			case "getSubjectWithExistingBanks":
			case "getCoursesByUserNameForTeacher":
				CommonMethodsHandler.getInstance().setChoiceBoxList(stringList);
				return;
			case "getCoursesByUserName":
				TeacherReportsController.trController.setCoursesChoiseBox(stringList);
				return;
			case "getCoursesByUserNameForPrincipleTeacher":
				PrincipleViewReportsController.pvrController.setChoiseBoxList(stringList);
				return;
			case "getCoursesByUserNameForPrincipleStudent":
				PrincipleViewReportsController.pvrController.setChoiseBoxList(stringList);
				return;
			case "getBanksByUsername1":
				TeacherCreateExamController.tceController.setBankChoiceBox(stringList);
				return;
			case "getBanksByUsername2":
				TeacherCreateManualExamController.tcmeController.setBankChoiceBox(stringList);
				return;
			case "getBanksByUsername3":
				TeacherEditExamController.teeController.setBankChoiceBox(stringList);
				return;
			case "getCourseBySubject1":
				TeacherCreateExamController.tceController.setCourseChoiceBox(stringList);
				return;
			case "getCourseBySubject2":
				TeacherCreateManualExamController.tcmeController.setCourseChoiceBox(stringList);
				return;
			case "getCourseBySubject3":
				TeacherEditExamController.teeController.setCourseChoiceBox(stringList);
				return;
			case "SetAllExamIDs":
				TeacherStartExamController.tseController.setExamIDs(stringList);
				return;
			case "TeachrsNamesListForPrincipleReportByCourse":
				PrincipleViewReportsController.pvrController.setChoiseBoxList(stringList);
				return;
			case "getExamsQuestionsByExamID":
				StudentTakeComputerizedExamController.stceController.setQuestionsScoresOfExam(stringList);
				return;
				// case "TeachrsIDsListForPrincipleReportByCourse":
				// PrincipleReportsByCourseController.prbcController.setTeachersIDsList(stringList);
				// return;
			default:
				ClientController.display(obj.toString() + " is missing!");
				break;
			}
			/// questionin before question!!
		} else if (obj instanceof QuestionInExam) { // list of QuestionInExam
			System.out.println("enter to instanceof question in exam");
			List<QuestionInExam> questionList = (List<QuestionInExam>) msg;
			System.out.println(questionList);
			switch (((QuestionInExam) obj).getQuestionID()) {
			case "getQuestionsBySubjectAndUsername2":
				TeacherCreateExamController.tceController.setQuestionTableView(questionList);
				return;
			default:
				ClientController.display(((QuestionInExam) obj).getQuestionID() + " is missing2!");
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

			case "getQuestionsTableViewInfo":
				PrincipleViewQuestionsInfoScreenController.pvqisController.setQuestionsInfoList(questionList);
				return;
			default:
				ClientController.display(((Question) obj).getQuestionID() + " is missing1!");
				break;
			}

		} else if (obj instanceof ExamResults) {
			List<ExamResults> examResultsList = (List<ExamResults>) msg;
			System.out.println(examResultsList);
			switch (((ExamResults) obj).getExamID()) {
			case "getExamDetailsForTeacher":
				TeacherReportsController.trController.setExamResultsDetails(examResultsList);
				return;
			case "getExamDetailsForPrincipleTeacher":
				PrincipleReportsByTeacherController.prbtController.setExamResultsDetails(examResultsList);
				return;
			case "getExamDetailsForPrincipleStudent":
				PrincipleReportsByStudentController.prbsController.setExamResultsDetails(examResultsList);
				return;
			case "getExamDetailsForPrincipleCourse":
				PrincipleReportsByCourseController.prbcController.setExamResultsDetails(examResultsList);
				return;
			default:
				ClientController.display(((ExamResults) obj).getExamID() + " is missing!");
				break;
			}
			//		} else if (obj instanceof Exam) {
			//			List<Exam> examList = (List<Exam>) msg;
			//			System.out.println(examList);
			//			switch (((Exam) obj).getExamID()) {
			//			case "getExamsBySubjectAndUsername":
			//				TeacherEditExamController.teeController.setExamTableView(examList);
			//				return;  -- [Commented by Yonatn]
			//			}
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
		} else if (obj instanceof IExam) { // List of exams
			List<IExam> examsList = (List<IExam>) msg;
			System.out.println(examsList);
			switch (((Exam) obj).getExamID()) {
			case "getExamsTableViewInfo":
				PrincipleViewExamsInfoScreenController.pveisController.setExamsInfoList(examsList);
				return;
			case "getExamsBySubjectAndUsername":
				TeacherEditExamController.teeController.setExamTableView(examsList);
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
	 *
	 * @author Yonatan Rozen
	 */
	public void handleMessageFromClientUI(Object obj) {
		try {
			openConnection();// in order to send more than one message

			// used for sending files to the server
			if ((obj instanceof String[]) && ((String[]) obj)[0].contains("UploadFile")) {
				String examID = ((String[]) obj)[1];
				String message = ((String[]) obj)[2];
				File wordDocument = new File(message);
				String whoCalled=((String[]) obj)[3];
				String studentID;
				// check if 'message' is a pathname (for example:
				// "C:\Users\Jon\Desktop\test.docx")
				String[] s1 = message.split("\\\\");
				String fileName = s1[s1.length - 1]; // "test.txt"
				MyFile testFile = new MyFile(fileName);

				if(whoCalled.equals("S"))
					studentID=((String[]) obj)[4];
				else studentID=null;

				try {
					byte[] mybytearray = new byte[(int) wordDocument.length()];
					FileInputStream fis = new FileInputStream(wordDocument);
					BufferedInputStream bis = new BufferedInputStream(fis);

					testFile.initArray(mybytearray.length);
					testFile.setSize(mybytearray.length);

					bis.read(testFile.getMybytearray(), 0, mybytearray.length);
					bis.close();
					sendToServer(new Object[] { ((String[]) obj)[0], examID, testFile,whoCalled,studentID});
				} catch (Exception e) {
					ServerUI.serverConsole.println("<<<<<<<Error send (Files)msg) to Server>>>>>>>");
				}

			} else if ((obj instanceof String[]) && ((String[]) obj)[0].contains("FinishEditManualExam")) {

				String examID = ((String[]) obj)[1];
				String message = ((String[]) obj)[2];
				String time = ((String[]) obj)[3];
				File wordDocument = new File(message);

				// check if 'message' is a pathname (for example:
				// "C:\Users\Jon\Desktop\test.docx")
				String[] s1 = message.split("\\\\");
				String fileName = s1[s1.length - 1]; // "test.txt"
				MyFile testFile = new MyFile(fileName);

				try {
					byte[] mybytearray = new byte[(int) wordDocument.length()];
					FileInputStream fis = new FileInputStream(wordDocument);
					BufferedInputStream bis = new BufferedInputStream(fis);

					testFile.initArray(mybytearray.length);
					testFile.setSize(mybytearray.length);

					bis.read(testFile.getMybytearray(), 0, mybytearray.length);
					bis.close();
					sendToServer(new Object[] { ((String[]) obj)[0], examID, testFile, time });
				} catch (Exception e) {
					ServerUI.serverConsole.println("<<<<<<<Error send (Files)msg) to Server>>>>>>>");
				}

			}

			else
				sendToServer(obj); // <-- message being sent to the server

			awaitResponse = true;

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
