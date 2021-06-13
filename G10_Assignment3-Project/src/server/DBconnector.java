package server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import common.MyFile;
import logic.User;
import logic.exam.ComputerizedExam;
import logic.exam.Exam;
import logic.exam.ExamResults;
import logic.exam.IExam;
import logic.exam.ManualExam;
import logic.question.Question;
import logic.question.QuestionInExam;
import ocsf.server.ConnectionToClient;

// singleton class
public class DBconnector {
	public static Connection con;
	private static DBconnector dbCinstance;

	// ***********************************************************************************************
	private DBconnector() {
	}

	public static DBconnector getInstance() {
		if (dbCinstance == null)
			dbCinstance = new DBconnector();
		return dbCinstance;
	}

	// ***********************************************************************************************
	/**
	 * Handles with the connection to the database
	 */
	public void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ServerUI.serverConsole.println("mysql driver definition succeed");
		} catch (Exception ex) {
			ServerUI.serverConsole.println("mysql driver definition failed");
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root", "Group10*");
			ServerUI.serverConsole.println("SQL connection succeed.");
		} catch (SQLException ex) {
			ServerUI.serverConsole.println("SQLException: " + ex.getMessage());
			ServerUI.serverConsole.println("SQLState: " + ex.getSQLState());
			ServerUI.serverConsole.println("VendorError: " + ex.getErrorCode());
		}

	}

	// ***********************************************************************************************
	// SERVER DATABASE RESETS
	public void resetUserConnections() {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE users SET Connected = '0'");
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Parses any object that can be deserialized
	 *
	 * @param data   The data to parse
	 * @param client The user who sent the message to the server
	 * @throws IOException if an I/O error occur when sending the message.
	 */
	public void parseData(Object data, ConnectionToClient client) throws IOException {
		// System.out.println(data);
		if (data instanceof String)
			client.sendToClient(data);
		// parse clients requests
		else if (data instanceof String[]) {

			String[] request = (String[]) data;
			System.out.println(request[0]);
			switch (request[0]) {
			case "Disconnect": // disconnectClient(username, client)
				disconnectClient(request[1], client);
				break;
			case "btnPressSignIn": // getUserInfoByUsernameAndPassword(username, password, client)
				getUserInfoByUsernameAndPassword(request[1], request[2], client);
				break;
			case "btnPressConfirmChange": // setNewPasswordByusername(username, actaulPass, tryPass, newPass, reNewPass,
				// client)
				setNewPasswordByusername(request[1], request[2], request[3], request[4], request[5], client);
				break;
			case "GetSubjects":
				getSubjectsByUsername(request[1], client);
				break;
			case "GetBanks":
				getBanksByUsername(request[1], request[2], client);// 1->username , 2->num
				break;
			case "GetCourseBySubject":
				getCourseBySubject(request[1], request[2], request[3], client); // 1->subject
				break;
			case "btnPressStartExam":
				getExamByExamID(request[1], client); // getExamByExamID(examID, client)
				getExamsQuestionsByExamID(request[1], client); // getExamsQuestionsByExamID(examID, client)
				break;
			case "btnPressSaveQuestion": // insertNewQuestionToDB(subjectName, questionBody, answer1, answer2, answer3,
				// answer4, correctAnswer, username, author, client)
				insertNewQuestionToDB(request[1], request[2], request[3], request[4], request[5], request[6],
						request[7], request[8], request[9], client);
				break;
			case "btnPressShowQuestionsBySubject": // getQuestionsBySubjectAndUsername(subjectName, username, client)
				getQuestionsBySubjectAndUsername(request[1], request[2], request[3], client);
				break;
			case "btnPressShowExamsBySubject": // getQuestionsBySubjectAndUsername(subjectName, username, client)
				getExamsBySubjectAndUsername(request[1], request[2], client);
				break;
			case "btnPressContinue2CreateExam":
				insertNewExamToDB(request[1], request[2], request[3], client);
				break;
			case "btnPressFinishCreateComputerizedExam":
				UpdateTimeAndCommentsForExam(request[1], request[2], request[3], request[4], request[5], client);
				break;
			case "btnPressFinishCreateManualExam":
				insertNewManualExamToDB(request[1], request[2], request[3], request[4], client);
				break;
			case "GetExistingBanks":
				getSubjectWithExistingBanks(request[1], client);
				break;
			case "lnkPressDownloadExamFile":
				getManualExamFileByExamID(request[1], client);
				break;
			case "CheckQuestionExistsInExam": // checkQuestionExistsInExam(questionID, client)
				checkQuestionExistsInExam(request[1], client);
				break;
			case "RemoveQuestionFromDatabase": // removeQuestion(questionID,subjectName,username,client)
				removeQuestion(request[1], request[2], request[3], client);
				break;
			case "sbViewUsersBtn":
				getUsersTableViewInfo(client);
				break;
			case "sbViewQuestionsBtn":
				getQuestionsTableViewInfo(client);
				break;
			case "sbViewExamsBtn":
				getExamsTableViewInfo(client);
				break;
			case "GetCourses":
				getCoursesByUserName(request[1], request[2], client);
				break;
			case "GetTeachers":
				getTeacherNamesByCourseID(request[1], client);
				break;
			case "GetExamDetails":
				getExamsIDAndGradesByUsernameAndCourseName(request[1], request[2], request[3], client);
				break;
			case "GetExamDetailsReportByCourse":
				getExamsIDAndGradesByCourseIDandTeacherName(request[1], request[2], client);
				break;
			case "GetSubjectCourseIDofExam":
				getSubjectCourseIDofExam(client);
				break;
				//			case "GetExamByID":
				//				getExamInfoByID(request[1], client); // UNUSED
				//				break;
			case "GetQuestionsInExam":
				getQuestionInExamByID(request[1], client);
			case "GetTypeOfExamAndOptionalComments":
				getTypeOfExamAndOptionalComments(request[1], client);
				break;
			case "GetFullComputerizedExamInfoByExamID": // TODO this query isn't used !
				getFullComputerizedExamDetailsByID(request[1], client);
				break;
			case "RemoveExamFromDatabase":
				removeExam(request[1], request[2], client);
				break;
			case "checkIfSearchedIDExists":
				checkIfSearchedIDExists(request[1], request[2], client);
				break;
			case "UpdateQuestionAndScoreToExam":
				UpdateQuestionAndScoreToExam(request[1], request[2], request[3], client);
				break;
			case "btnPressSubmitComputerized":
				// ClientUI.chat.accept(new String[] { "btnPressSubmit","successful",
				// String.format("%ld", estimatedTime),
				// ChatClient.user.getUsername(), examID, grade});
				UpdateCopmuterizedSubmittedExamInfoByExamIDandStudentID(request[1], request[2], request[3],
						request[4], request[5], client);
				break;
			case "btnPressSubmitManual":
				// ClientUI.chat.accept(new String[] { "btnPressSubmit","successful",
				// String.format("%ld", estimatedTime),
				// ChatClient.user.getUsername(), examID, grade});
				UpdateManualSubmittedExamInfoByExamIDandStudentID(request[1], request[2], request[3],
						request[4], client);
				break;
			default:
				ServerUI.serverConsole.println(request[0] + " is not a valid case! (String[] DBconnector)");
				client.sendToClient(request[0] + " is not a valid case! (String[] DBconnector)");
				break;
			}
		}

		else if (data instanceof Object[]) {
			Object[] request = (Object[]) data;
			System.out.println(request[0].toString());
			switch (request[0].toString()) {
			case "UpdateQuestion":
				updateQuestion((Question) request[1], client);
				break;
			case "StudentUploadFile":
				//studentTestUpload((String) request[1], (MyFile) request[2], client);
				//break;
			case "TeacherUploadFile": // req1 -examID , req2 -filepath
				ExamFileUpload((String) request[1], (MyFile) request[2],(String) request[3],(String)request[4], client);
				break;
			case "btnPressFinishEditManualExam": // req1-ExamID , req2=filepath , req3- time for manual exam
				System.out.println("finish edit exam case");
				btnPressFinishEditManualExam((String) request[1], (MyFile) request[2], (String) request[3], client);
				break;
			default:
				ServerUI.serverConsole.println(request[0] + " is not a valid case! (Object[] DBconnector)");
				client.sendToClient(request[0] + " is not a valid case! (Object[] DBconnector)");
				break;
			}
		}
	}


	private void btnPressFinishEditManualExam(String examID, MyFile myFile, String time, ConnectionToClient client)
			throws IOException {

		File outputFile = new File(myFile.getFileName());
		FileOutputStream fos = new FileOutputStream(outputFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		try {
			Blob blob = con.createBlob();
			blob.setBytes(1, myFile.getMybytearray()); // convert to byte[]
			PreparedStatement stmt = con
					.prepareStatement("UPDATE exams SET File = ? , AllocatedTime = ? WHERE ExamID = ?");
			stmt.setBlob(1, blob);
			stmt.setString(2, time);
			stmt.setString(3, examID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		bos.write(myFile.getMybytearray(), 0, myFile.getSize());
		bos.flush();
		fos.flush();

		client.sendToClient("");

	}

	private void ExamFileUpload(String examID, MyFile myFile,String whoCalled,String studentID, ConnectionToClient client) throws IOException {
		File outputFile = new File(myFile.getFileName());
		FileOutputStream fos = new FileOutputStream(outputFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		try {
			Blob blob = con.createBlob();
			PreparedStatement stmt=null;
			blob.setBytes(1, myFile.getMybytearray()); // convert to byte[]
			if(whoCalled.equals("T"))
				stmt = con.prepareStatement("UPDATE exams SET File = ? WHERE ExamID = ?");
			else// if(whoCalled.equals("S"))
				stmt = con.prepareStatement("UPDATE exams_results_manual SET FileSubmit = ? WHERE ExamID = ? and UsernameS = ?");

			stmt.setBlob(1, blob);
			stmt.setString(2, examID);
			stmt.setString(3, studentID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		bos.write(myFile.getMybytearray(), 0, myFile.getSize());
		bos.flush();
		fos.flush();
		System.out.println("UPLOAD FILE ---------------> END");
		client.sendToClient("");

		//			TODO this will be used to take a blob out of the database
		//			Statement st = conn.createStatement();
		//			ResultSet rs = st.executeQuery("select c1 from t1");
		//			Blob b2 = rs.getBlob(1);
	}

	/**
	 * Sends message of success to to user
	 *
	 * @param ExamID     String ##@@$$$
	 * @param questionID string &&%%%
	 * @param client     the teacher
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev
	 */
	private void UpdateQuestionAndScoreToExam(String ExamID, String ScoreVal, String QuestionID,
			ConnectionToClient client) throws IOException {
		// check if we need to update or insert new
		String hasQuestion = "0";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ExamID From questions_in_exam " + "WHERE ExamID = '" + ExamID
					+ "' AND QuestionID = '" + QuestionID + "'");
			if (rs.next())
				hasQuestion = "1";
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
		// if we need to insert the question for the first time
		if (hasQuestion.equals("0")) {
			// insert new exam into exams and generate a new 'examID'
			try {
				PreparedStatement stmt = con.prepareStatement(
						"INSERT INTO questions_in_exam (ExamID, QuestionID,Score)" + "VALUES (?,?,?)");
				stmt.setString(1, ExamID);
				stmt.setString(2, QuestionID);
				stmt.setString(3, ScoreVal);
				stmt.executeUpdate();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}
		}
		// if we need to update score for existing question in exam
		else if (hasQuestion.equals("1")) {
			try {
				PreparedStatement stmt = con
						.prepareStatement("UPDATE questions_in_exam SET Score = ? WHERE QuestionID = ? AND ExamID = ?");
				stmt.setString(1, ScoreVal);
				stmt.setString(2, QuestionID);
				stmt.setString(3, ExamID);
				stmt.executeUpdate();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}

		} else
			System.out.println("Error with hasQuestion");

		client.sendToClient(
				"Update Question #" + QuestionID + "in exam #" + ExamID + " has been updated in the database!");

	}

	/**
	 * Sends array with examID(computerized) and success msg
	 *
	 * @param CourseName  String
	 * @param subjectName string
	 * @param author      - full name client
	 * @param client      the teacher
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev
	 */
	private void insertNewExamToDB(String CourseName, String subjectName, String author, ConnectionToClient client)
			throws IOException {

		// get CourseID by CourseName
		String CourseID = null;
		String SubjectID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT CourseID,SubjectID From courses WHERE CourseName = '" + CourseName + "'");

			if (rs.next()) {
				CourseID = rs.getString(1);
				SubjectID = rs.getString(2);
			}
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// get bankID by subjectid
		String BankID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BankID From banks WHERE SubjectID = \"" + SubjectID + "\"");
			if (rs.next())
				BankID = rs.getString(1);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// create questionID by subjectID(%%) and courseID(@@) -- %%@@##
		String examID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ExtractedExamID) as ID FROM ("
					+ "SELECT SUBSTRING(ExamID, 5) as ExtractedExamID FROM exams " + "WHERE ExamID LIKE '" + SubjectID
					+ "" + CourseID + "%') as MaxID");
			rs.next();
			int currentMaxID = rs.getInt(1);
			rs.close();
			examID = String.format("%s%s%02d", SubjectID, CourseID, currentMaxID + 1);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		String type = "C"; // computer - M-> Manually
		// insert new exam into exams and generate a new 'examID'
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO exams (ExamID, BankID,CourseID,AllocatedTime,Author,Type) VALUES (?,?,?,?,?,?)");
			stmt.setString(1, examID);
			stmt.setString(2, BankID);
			stmt.setString(3, CourseID);
			stmt.setString(4, "000"); // not null
			stmt.setString(5, author);
			stmt.setString(6, type);
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		client.sendToClient(new String[] { "GetExamIDForComputerizedExam", examID });
	}

	/**
	 * Sends message success
	 *
	 * @param ExamID          String - ##@@$$$
	 * @param studentComments comments to student (Optional)
	 * @param teacherComments - comments to teacher (Optional)
	 * @param time            - update allocated time of exam
	 * @param num             different controllers
	 * @param client          the teacher
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev
	 */
	private void UpdateTimeAndCommentsForExam(String ExamID, String studentComments, String teacherComments,
			String time, String num, ConnectionToClient client) throws IOException {

		try {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE exams SET AllocatedTime = '" + time + "',StudentComments = '" + studentComments
					+ "',TeacherComments = '" + teacherComments + "' WHERE ExamID = '" + ExamID + "';");
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
		if (num.equals("1"))
			client.sendToClient(
					"CreateExam SUCCESS - Exam " + String.format("#%s", ExamID) + " was updated successfully!");
		else if (num.equals("2")) {
			client.sendToClient(
					"EditExam SUCCESS - Exam " + String.format("#%s", ExamID) + " was updated successfully!");
		}
	}

	/**
	 * Sends array with examID(manual) and success msg
	 *
	 * @param CourseName  String
	 * @param subjectName string
	 * @param author      - full name client
	 * @param time        - allocated time for exam
	 * @param client      the teacher
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev
	 */
	private void insertNewManualExamToDB(String CourseName, String subjectName, String author, String time,
			ConnectionToClient client) throws IOException {

		// get CourseID by CourseName
		String CourseID = null;
		String SubjectID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT CourseID,SubjectID From courses WHERE CourseName = '" + CourseName + "'");
			if (rs.next()) {
				CourseID = rs.getString(1);
				SubjectID = rs.getString(2);
			}
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// get bankID by subjectid
		String BankID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BankID From banks WHERE SubjectID = '" + SubjectID + "'");
			if (rs.next())
				BankID = rs.getString(1);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// create questionID by subjectID(%%) and courseID(@@) -- %%@@##
		String examID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ExtractedExamID) as ID FROM ("
					+ "SELECT SUBSTRING(ExamID, 5) as ExtractedExamID FROM exams " + "WHERE ExamID LIKE '" + SubjectID
					+ CourseID + "%') as MaxID");
			rs.next();
			int currentMaxID = rs.getInt(1);
			rs.close();
			examID = String.format("%s%s%02d", SubjectID, CourseID, currentMaxID + 1);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		String type = "M"; // computer - M-> Manually
		// insert new exam into exams and generate a new 'examID'
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO exams (ExamID, BankID,CourseID,AllocatedTime,Author,Type)" + "VALUES (?,?,?,?,?,?)");
			stmt.setString(1, examID);
			stmt.setString(2, BankID);
			stmt.setString(3, CourseID);
			stmt.setString(4, time); // not null
			stmt.setString(5, author);
			stmt.setString(6, type);
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		client.sendToClient(new String[] { "CreateManualDetails", examID });
	}

	// ***********************************************************************************************
	// QUERY METHODS
	// ***********************************************************************************************

	/**
	 * Disconnect the current client from the server
	 *
	 * @param username the username of the current user
	 * @param client   the connected user
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void disconnectClient(String username, ConnectionToClient client) throws IOException {
		try {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE users SET Connected = '0' WHERE Username = '" + username + "'");
			stmt.executeUpdate();
			client.sendToClient("Disconnect");
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends the student the questions (an ArrayList) of the exam he is taking
	 *
	 * @param examID identifier for the exam, gotten from teacher
	 * @param client the student
	 * @throws IOException
	 *
	 * @author Meitar El-Ezra
	 */
	private void getExamsQuestionsByExamID(String examID, ConnectionToClient client) throws IOException {
		List<Question> questionsOfExam = new ArrayList<>();
		List<String> questionsScores = new ArrayList<>();
		questionsOfExam.add(new Question("getExamsQuestionsByExamID", "", "", "", "", "", "", ""));
		questionsScores.add("getExamsQuestionsByExamID");
		// get all the exam's questions into the arrayList according to the examID
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Q.*,QOE.Score " + "FROM questions_in_exam QOE, questions Q "
					+ "WHERE QOE.QuestionID = Q.QuestionID AND QOE.ExamID = '" + examID + "'");

			while (rs.next()) {
				questionsOfExam.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9))); // the question itself
				questionsScores.add(rs.getString(11));// the question's score
			}
			client.sendToClient(questionsOfExam);
			client.sendToClient(questionsScores);
			rs.close();
		} catch (SQLException e) {
			// * This method should always work!!! ; Add Missing information if it doesn't*
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the student the exam he is taking and the course of the exam
	 *
	 * @param examID identifier for the exam, gotten from teacher
	 * @param client the student
	 * @throws IOException
	 *
	 * @author Meitar El-Ezra
	 */
	private void getExamByExamID(String examID, ConnectionToClient client) throws IOException {
		// get all the exam's data according to the examID
		IExam exam = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM exams E WHERE E.ExamID = '" + examID + "'");

			while (rs.next()) {
				if (rs.getString(9).equals("C"))
					// public ComputerizedExam(String examID, String bankID, String courseID, String
					// allocatedTime, String scores,
					// String studentComments, String teacherComments, String author, String type) {
					exam = new ComputerizedExam(rs.getString(1), "", rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), "C");
				else if (rs.getString(9).equals("M")) {
					// public ManualExam(String examID, String bankID, String courseID, String
					// allocatedTime, String author, String type) {
					exam = new ManualExam(rs.getString(1), "", rs.getString(3), rs.getString(4), rs.getString(8),
							"M"/* , rs.getBlob(10) */);
				}
			}
			client.sendToClient(exam);
			rs.close();

		} catch (SQLException e) {
			// * This method should always work!!! ; Add Missing information if it doesn't*
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// get course name
		try {
			String courseName = "";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT C.courseName FROM courses C WHERE C.courseID = '" + exam.getCourseID() + "'");

			if (rs.next()) {
				courseName = rs.getString(1);
			}
			client.sendToClient("courseName:" + courseName);
			rs.close();
		} catch (SQLException e) {
			// * This method should always work!!! ; Add Missing information if it doesn't*
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the teacher an (ArrayList) of her subjects of study
	 *
	 * @param username The username of the teacher
	 * @param client   The teacher
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void getSubjectsByUsername(String username, ConnectionToClient client) throws IOException {
		List<String> bankList = new ArrayList<>();
		bankList.add("getSubjectsByUsername");
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT S.SubjectName FROM cems.subjects S, cems.subjects_of_teacher SOT "
					+ "WHERE S.SubjectID = SOT.SubjectID AND SOT.UsernameT = '" + username + "'");

			while (rs.next())
				bankList.add(rs.getString(1));
			client.sendToClient(bankList);
			rs.close();
		} catch (SQLException e) {
			// * This method should always work!!! ; Add Missing information in
			// sbujects_of_teacher if it doesn't*
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the teacher an (ArrayList) of her banks
	 *
	 * @param username The username of the teacher
	 * @param num      - to use in this query in several controllers
	 * @param client   The teacher
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev
	 */
	private void getBanksByUsername(String username, String num, ConnectionToClient client) throws IOException {
		List<String> bankList = new ArrayList<>();

		if (num.equals("1")) {
			bankList.add("getBanksByUsername1");
		} else if (num.equals("2")) {
			bankList.add("getBanksByUsername2");
		} else {
			bankList.add("getBanksByUsername3");
		}

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT S.SubjectName FROM cems.subjects S, cems.subjects_of_teacher SOT "
					+ "WHERE S.SubjectID = SOT.SubjectID AND SOT.UsernameT = '" + username + "'");
			while (rs.next())
				bankList.add(rs.getString(1));
			client.sendToClient(bankList);

			rs.close();

		} catch (SQLException e) {
			// * This method should always work!!! ; Add Missing information if it doesn't*
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the teacher an (ArrayList) of her courses
	 *
	 * @param username    The username of the teacher
	 * @param SubjectName - subject string
	 * @param num         - to use in this query in several controllers
	 * @param client      The teacher
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev
	 */
	private void getCourseBySubject(String SubjectName, String username, String num, ConnectionToClient client)
			throws IOException {
		List<String> CourseList = new ArrayList<>();

		if (num.equals("1")) {
			CourseList.add("getCourseBySubject1");
		} else if (num.equals("2")) {
			CourseList.add("getCourseBySubject2");
		} else {
			CourseList.add("getCourseBySubject3");
		}

		try {
			// get courses with bankid
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(
					"SELECT C.CourseName FROM cems.courses C WHERE SubjectID = (SELECT S.SubjectID FROM cems.subjects S WHERE SubjectName = \""
							+ SubjectName + "\");");

			while (rs2.next())
				CourseList.add(rs2.getString(1));

			client.sendToClient(CourseList);

			// rs.close();
			rs2.close();

		} catch (SQLException e) {
			// * This method should always work!!! ; Add Missing information if it doesn't*
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Sets a new password for the user by his choice
	 *
	 * @param username   The username of the user
	 * @param actaulPass The actaul password; retrived earlier from the DB
	 * @param tryPass    The current password; inputed by the user
	 * @param newPass    The new password; inputed by the user
	 * @param reNewPass  The retyped new password; inputed by the user
	 * @param client     The user which is requesting to change his password
	 * @throws IOException if an I/O error occur when sending the message.
	 *
	 * @author Yonatan Rozen
	 */
	private void setNewPasswordByusername(String username, String actaulPass, String tryPass, String newPass,
			String reNewPass, ConnectionToClient client) throws IOException {

		if (tryPass.equals("") || newPass.equals("") || reNewPass.equals("")) {
			client.sendToClient("ChangePassword ERROR - All fields are required!");
			return;
		}
		if (!actaulPass.equals(tryPass)) {
			client.sendToClient("ChangePassword ERROR - Wrong current password input!");
			return;
		}
		if (!newPass.equals(reNewPass)) {
			client.sendToClient("ChangePassword ERROR - RePassword must be the same as NewPassword!");
			return;
		}

		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE users SET Password =? WHERE Username =?");
			stmt.setString(1, newPass);
			stmt.setString(2, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		client.sendToClient("ChangePassword SUCCESS - Your password was changed successfully!");
	}

	// ***********************************************************************************************
	/**
	 * @param string
	 * @param client
	 * @throws IOException
	 *
	 * @author Michael
	 */
	private void getManualExamFileByExamID(String examID, ConnectionToClient client) throws IOException {
		// TODO Auto-generated method stub
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT File FROM exams WHERE ExamID = '" + examID + "'");
			con.createBlob();
			client.sendToClient(rs.getBlob(4));
			rs.close();
			// maybe send as a stream?
			// https://coderanch.com/t/305876/databases/convert-Blob-Type-File

		} catch (SQLException e) {
			// TODO: handle exception
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Sends to the user all the details about his user (get stored in user thread)
	 *
	 * @param username The username; inputed by the user
	 * @param password The new password; inputed by the user
	 * @param client   A user of one of the following types : Student / Teacher /
	 *                 Principle
	 * @throws IOException if an I/O error occur when sending the message.
	 *
	 * @author Yonatan Rozen
	 */
	public void getUserInfoByUsernameAndPassword(String username, String password, ConnectionToClient client)
			throws IOException {

		User user = null;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * From users WHERE Username = '" + username + "' AND Password = '" + password + "'");

			if (rs.next()) {
				if (rs.getString(8).equals("1")) {
					client.sendToClient("SignIn ERROR - This user is already connected!");
					return;
				} else
					user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7));
			} else
				client.sendToClient("SignIn ERROR - Wrong username or password!");
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// update that this user is connected to the server
		try {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE users SET Connected = '1' WHERE Username = '" + username + "'");
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		client.sendToClient(user);
		client.setName(username);
		ServerUI.serverConsole.println(("user [" + client.getName() + "] has connected successfully!"));
		client.setInfo(client.getName(), user.getType());
	}

	// ***********************************************************************************************
	/**
	 * Inserts a new question into the database
	 *
	 * @param subjectName   The study of subject of the question
	 * @param questionBody  The body of the question
	 * @param answer1       1st answer
	 * @param answer2       2nd answer
	 * @param answer3       3rd answer
	 * @param answer4       4th answer
	 * @param correctAnswer The correct answer
	 * @param username      The username of the teacher
	 * @param author        The full name of the teacher
	 * @param client        The clienty
	 * @throws IOException if an I/O error occur when sending the message.
	 *
	 * @author Yonatan Rozen
	 */
	private void insertNewQuestionToDB(String subjectName, String questionBody, String answer1, String answer2,
			String answer3, String answer4, String correctAnswer, String username, String author,
			ConnectionToClient client) throws IOException {

		// get subjectID by subjectName
		String subjectID = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT SubjectID From subjects WHERE SubjectName = '" + subjectName + "'");
			if (rs.next())
				subjectID = rs.getString(1);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// check if HasBank is set to 'TRUE' in subjects_of_teacher by (SubjectID &
		// Username)
		String hasBank = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT HasBank From subjects_of_teacher " + "WHERE SubjectID = '"
					+ subjectID + "' AND UsernameT = '" + username + "'");
			if (rs.next())
				hasBank = rs.getString(1);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// if bank was not yet created for (SubjectID & Username)
		if (hasBank.equals("0")) {

			// insert (SubjectID & Username) into Banks and generate a new 'bankID'
			try {
				PreparedStatement stmt = con.prepareStatement("INSERT INTO Banks (SubjectID, UsernameT) VALUES (?,?)");
				stmt.setString(1, subjectID);
				stmt.setString(2, username);
				stmt.executeUpdate();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}

			// update HasBank to '1' in 'subjects_of_teacher' table
			try {
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE subjects_of_teacher SET HasBank = '1' WHERE SubjectID = ? AND UsernameT = ?");
				stmt.setString(1, subjectID);
				stmt.setString(2, username);
				stmt.executeUpdate();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}
		}

		String bankID = null;
		// get bankID from 'banks' by (SubjectID & Username)
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BankID From Banks WHERE SubjectID = '" + subjectID
					+ "' AND UsernameT = '" + username + "'");
			if (rs.next())
				bankID = String.format("%02d", rs.getInt(1));
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// create questionID by subjectID
		String questionID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(ExtractedQuestionID) as ID FROM ("
					+ "SELECT SUBSTRING(QuestionID, 3) as ExtractedQuestionID FROM questions "
					+ "WHERE QuestionID LIKE '" + subjectID + "%') as MaxID");
			rs.next();
			int currentMaxID = rs.getInt(1);
			rs.close();
			questionID = String.format("%s%03d", subjectID, currentMaxID + 1);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// insert the new question into the database
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO questions (QuestionID,BankID,Body,Answer1,Answer2,Answer3,Answer4,CorrectAnswer,Author,ExistsInExam) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,'0')");
			stmt.setString(1, questionID);
			stmt.setString(2, bankID);
			stmt.setString(3, questionBody);
			stmt.setString(4, answer1);
			stmt.setString(5, answer2);
			stmt.setString(6, answer3);
			stmt.setString(7, answer4);
			stmt.setString(8, correctAnswer);
			stmt.setString(9, author);
			stmt.executeUpdate();

		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		client.sendToClient(
				"CreateQuestion SUCCESS - Question " + String.format("#%s", questionID) + " was created successfully!");
	}

	// ***********************************************************************************************
	/**
	 * Sends to the teacher an ArrayList of subjects with existing bank
	 *
	 * @param subjectID The choosen subject
	 * @param username  The username of the teacher
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void getSubjectWithExistingBanks(String username, ConnectionToClient client) throws IOException {
		List<String> bankList = new ArrayList<>();
		bankList.add("getSubjectWithExistingBanks");
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT S.SubjectName FROM subjects S, banks B " + "WHERE B.UsernameT = '"
					+ username + "' AND S.SubjectID = B.SubjectID");
			while (rs.next()) {
				bankList.add(rs.getString(1));
			}
			rs.close();
			//			if (bankList.size() > 1)
			client.sendToClient(bankList);
			//			else
			//				client.sendToClient("GetSubjectsWithBank ERROR - ");
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Sends to the teacher an ArrayList of questions under the same subject
	 *
	 * @param subjectID The choosen subject
	 * @param username  The username of the teacher
	 * @throws IOException
	 *
	 * @author Yonatan Rozen , edited by Eliran Amerzoyev
	 */

	private void getQuestionsBySubjectAndUsername(String subjectName, String num, String username,
			ConnectionToClient client) throws IOException { // num for using in create exam
		List<Question> questionList = new ArrayList<>();
		List<QuestionInExam> questionInExamList = new ArrayList<>();

		if (num.equals("2")) { // num==2 --> teacher create exam controller
			questionInExamList
			.add(new QuestionInExam("getQuestionsBySubjectAndUsername2", "", "", "", "", "", "", "", "", ""));

		} else { // num==1 --> teacher choose edit question controller
			questionList.add(new Question("getQuestionsBySubjectAndUsername", "", "", "", "", "", "", ""));
		}

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Q.* FROM questions Q WHERE Q.bankID = "
					+ "	(SELECT B.BankID FROM banks B WHERE B.UsernameT = '" + username + "' AND B.SubjectID = "
					+ "	(SELECT S.SubjectID FROM subjects S WHERE S.SubjectName = '" + subjectName + "'))");
			while (rs.next()) {
				if (num.equals("2")) {
					questionInExamList.add(new QuestionInExam(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), "0", "0"));
				} else {
					questionList.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
				}
			}

			rs.close();
			if (num.equals("2")) {
				client.sendToClient(questionInExamList);
			} else
				client.sendToClient(questionList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Sends to the teacher an ArrayList of exams
	 *
	 * @param subjectID
	 * @param username
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev (Edited by Yonatn Rozen)
	 */
	private void getExamsBySubjectAndUsername(String subjectName, String username, ConnectionToClient client)
			throws IOException {
		List<IExam> examList = new ArrayList<>();

		examList.add(new Exam("getExamsBySubjectAndUsername", "", "", "", "", ""));

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT E.* FROM exams E WHERE E.bankID = "
					+ "	(SELECT B.BankID FROM banks B WHERE B.UsernameT = '" + username + "' AND B.SubjectID = "
					+ "	(SELECT S.SubjectID FROM subjects S WHERE S.SubjectName = '" + subjectName + "'))");
			while (rs.next()) {
				if (rs.getString(9).equals("C")) {
					ComputerizedExam ce = new ComputerizedExam(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9));
					examList.add(ce);
				} else {
					ManualExam me = new ManualExam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(8), rs.getString(9)/* , rs.getBlob(10) */);
					examList.add(me);

				}
				//				examList.add(new Exam(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
				//						rs.getString(6), rs.getString(7)));
			}
			rs.close();
			client.sendToClient(examList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the client '0' or '1' to indicated if a question exists in any exam
	 *
	 * @param questionID The question ID
	 * @param client     The teacher
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void checkQuestionExistsInExam(String questionID, ConnectionToClient client) throws IOException {
		String[] existsInExam = { "checkQuestionExistsInExam", "" };
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT ExistsInExam FROM questions WHERE QuestionID = '" + questionID + "'");
			if (rs.next())
				existsInExam[1] = rs.getString(1);
			rs.close();
			System.out.println(Arrays.toString(existsInExam));
			client.sendToClient(existsInExam);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

	}

	/**
	 * Sends to the client success remove
	 *
	 * @param examID
	 * @param username
	 * @throws IOException
	 *
	 * @author Eliran Amerzoyev
	 */
	private void removeExam(String examID, String username, ConnectionToClient client) throws IOException {
		// need to remove from exams,exam_result,exams_result_computerized,and all the
		// question_in_exam

		// remove the exam from database(exams table)
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM exams WHERE ExamID = '" + examID + "'");
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		//		// remove the exam from database(exams_results table)
		//		try {
		//			PreparedStatement stmt = con.prepareStatement("DELETE FROM exams_results WHERE ExamID = '" + examID + "'");
		//			stmt.executeUpdate();
		//		} catch (SQLException e) {
		//			client.sendToClient("sql exception");
		//			e.printStackTrace();
		//			return;
		//		}

		//		// remove the exam from database(exams_results_computerized table)
		//		try {
		//			PreparedStatement stmt = con
		//					.prepareStatement("DELETE FROM exams_results_computerized WHERE ExamID = '" + examID + "'");
		//			stmt.executeUpdate();
		//		} catch (SQLException e) {
		//			client.sendToClient("sql exception");
		//			e.printStackTrace();
		//			return;
		//		}

		// remove the question in exam from database(questions_in_exam table)
		try {
			PreparedStatement stmt = con
					.prepareStatement("DELETE FROM questions_in_exam WHERE ExamID = '" + examID + "'");
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		client.sendToClient("Exam #" + examID + " has been removed from the database!");

	}

	// ***********************************************************************************************
	/**
	 * Removes a question from the database
	 *
	 * @param questionID  The questionID
	 * @param subjectName The subject of the question
	 * @param username    the username of the teacher
	 * @param client      the teacher that is requestion the question removal
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void removeQuestion(String questionID, String subjectName, String username, ConnectionToClient client)
			throws IOException {
		String bankID = null;

		// get the bankID by question ID
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BankID FROM questions WHERE QuestionID = '" + questionID + "'");
			if (rs.next())
				bankID = rs.getString(1);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// remove the question from database
		try {
			PreparedStatement stmt = con
					.prepareStatement("DELETE FROM questions WHERE QuestionID = '" + questionID + "'");
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// check if the bank reperesented by the bankID has been emptied
		boolean isEmpty = false;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT (EXISTS (SELECT 1 FROM questions WHERE bankID = '" + bankID + "'))");
			if (rs.next())
				if (rs.getString(1).equals("0")) {
					isEmpty = true;
				}
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		if (isEmpty) { // if the bank is now empty...

			// get subjectID by subjectName
			String subjectID = "";
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT SubjectID FROM subjects WHERE SubjectName = '" + subjectName + "'");
				if (rs.next())
					subjectID = rs.getString(1);
				rs.close();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}

			// set HasBank to '0' by username and subjectID
			try {
				PreparedStatement stmt = con.prepareStatement("UPDATE subjects_of_teacher SET HasBank = '0' "
						+ "WHERE SubjectID = '" + subjectID + "' AND UsernameT = '" + username + "'");
				stmt.executeUpdate();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}

			// remove the bank from banks table
			try {
				PreparedStatement stmt = con.prepareStatement("DELETE FROM banks WHERE BankID = '" + bankID + "'");
				stmt.executeUpdate();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}
		}

		client.sendToClient("Question #" + questionID + " has been removed from the database!");
	}

	// ***********************************************************************************************
	/**
	 * Updates the details of a specific question
	 *
	 * @param question The question
	 * @param client   The teacher requesting the change
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void updateQuestion(Question question, ConnectionToClient client) throws IOException {
		try {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE questions SET Body = ?, Answer1 = ?, Answer2 = ?, Answer3 = ?,"
							+ "Answer4 = ?, CorrectAnswer = ? WHERE QuestionID = ?");
			stmt.setString(1, question.getQuestionBody());
			stmt.setString(2, question.getAnswer1());
			stmt.setString(3, question.getAnswer2());
			stmt.setString(4, question.getAnswer3());
			stmt.setString(5, question.getAnswer4());
			stmt.setString(6, question.getCorrectAnswer());
			stmt.setString(7, question.getQuestionID());
			stmt.executeUpdate();
			client.sendToClient("UpdatedQuestion");
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Sends to the teacher all exam IDs
	 *
	 * @param client The teacher requesting the exam IDs
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void getSubjectCourseIDofExam(ConnectionToClient client) throws IOException {
		List<String> examTextIDList = new ArrayList<>();
		examTextIDList.add("SetAllExamIDs");
		String eID = "0";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT S.SubjectName, C.CourseName, E.ExamID " + "FROM courses C, subjects S, exams E, banks B "
							+ "WHERE E.CourseID = C.CourseID AND C.SubjectID = S.SubjectID AND "
							+ "	B.BankID = E.BankID AND B.SubjectID = S.SubjectID ORDER BY E.ExamID");
			while (rs.next())

				examTextIDList.add(rs.getString(1) + " - " + rs.getString(2) + " - #" + rs.getString(3));
			rs.close();
			client.sendToClient(examTextIDList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * <<<<<<< HEAD Sends the teacher the exam by the exam ID
	 *
	 * ======= Sends the teacher the comments made by the creator and the type of
	 * the exam
	 *
	 * >>>>>>> branch 'master' of https://github.com/DeathSource/Group10.git
	 *
	 * @param examID The exam ID
	 * @param client The supervising teacher
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void getTypeOfExamAndOptionalComments(String examID, ConnectionToClient client) throws IOException {
		String[] typeAndOptionalComments = new String[] { "setTypeAndOptionalTeacherComments", "", "" };
		//		IExam exam=null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM exams WHERE ExamID = '" + examID + "'");
			if (rs.next()) {
				//				if (rs.getString(9).equals("C")) {
				//					exam = new ComputerizedExam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
				//							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
				//				} else {
				//					exam = new ManualExam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
				//							rs.getString(8), rs.getString(9)); // TODO add rs.getString(10) [the actaul file]
				//				}
				typeAndOptionalComments[1] = rs.getString(9);
				typeAndOptionalComments[2] = rs.getString(7);
			}
			rs.close();
			// client.sendToClient(new Object[] { "setRequestedExamInfo", exam });
			client.sendToClient(typeAndOptionalComments);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Sends the teacher an ArrayList of the question in an exam which is specified
	 * by the exam ID
	 *
	 * @param examID The exam ID
	 * @param client The teacher
	 *
	 * @author Yonatan Rozen
	 * @throws IOException
	 */
	private void getQuestionInExamByID(String examID, ConnectionToClient client) throws IOException {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question("setQuestionInExam", "", "", "", "", "", "", ""));
		Question question = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Q.* ,QIE.Score " + "FROM questions Q, questions_in_exam QIE "
					+ "WHERE Q.QuestionID = QIE.QuestionID AND QIE.ExamID = '" + examID + "'");
			while (rs.next()) {
				question = new Question(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)); // TODO [rs.getString(10)
				// has the score]
				questionList.add(question);
			}
			rs.close();
			client.sendToClient(questionList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Send computerized exam details to the teacher
	 *
	 * @param examID The exam's ID
	 * @param client The teacher
	 * @throws IOException
	 *
	 * @author Yonatan Rozen
	 */
	private void getFullComputerizedExamDetailsByID(String examID, ConnectionToClient client) throws IOException {
		List<String> examIDs = new ArrayList<>();
		examIDs.add("SetAllExamIDs");
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ExamID FROM exams ORDER BY ExamID");
			while (rs.next())
				examIDs.add(rs.getString(1));
			rs.close();
			client.sendToClient(examIDs);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends Arraylist of courses back to the teacher
	 *
	 * @param userName user name of the teacher
	 * @param client   the teacher
	 * @throws IOException
	 *
	 * @author Danielle Sarusi, edited by Meitar El Ezra
	 */
	private void getCoursesByUserName(String userName, String type, ConnectionToClient client) throws IOException {
		System.out.println(userName);
		System.out.println(type);
		List<String> coursesList = new ArrayList<>();
		// coursesList.add("getCoursesByUserName");
		if (type.equals("T"))
			coursesList.add("getCoursesByUserNameForTeacher");
		else if (type.equals("P"))
			coursesList.add("getCoursesByUserNameForPrincipleTeacher");
		else if (type.equals("S"))
			coursesList.add("getCoursesByUserNameForPrincipleStudent");
		if (type.equals("T") || type.equals("P")) // Returns the list of course names taught by the teacher that had
			// exams
		{
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT C.CourseName FROM courses C, "
						+ " (SELECT E.CourseID, B.SubjectID FROM banks B, exams E, exams_results_computerized ER WHERE B.UsernameT = '"
						+ userName + "' AND B.BankID = E.BankID AND E.ExamID=ER.ExamID) as CS "
						+ " WHERE C.CourseID = CS.CourseID AND C.SubjectID = CS.SubjectID");
				while (rs.next()) {
					coursesList.add(rs.getString(1));
				}
				rs.close();
				client.sendToClient(coursesList);
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}
		} else if (type.equals("S")) { // return a list of the courses that a student has taken their exams
			try {
				System.out.println("got to courses query for student " + userName);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT DISTINCT C.CourseName FROM courses C, exams_results_computerized ER, exams E, banks B "
								+ "WHERE ER.UsernameS='" + userName
								+ "' AND E.ExamID=ER.ExamID AND C.CourseID=E.CourseID "
								+ "AND B.BankID=E.BankID AND B.SubjectID=C.SubjectID");
				while (rs.next()) {
					System.out.println("there are courses with exams for student " + userName);
					coursesList.add(rs.getString(1));
				}
				rs.close();
				client.sendToClient(coursesList);

			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * Sends to the teacher Arraylist of examID with grades
	 *
	 * @param userName course name that the teacher teaches
	 * @param userName user name of the teacher
	 * @param client   the teacher
	 * @throws IOException
	 *
	 * @author Danielle sarusi, edited by Meitar El Ezra
	 */
	private void getExamsIDAndGradesByUsernameAndCourseName(String courseName, String userName, String type,
			ConnectionToClient client) throws IOException {
		System.out.println("type=" + type + "\nuser=" + userName);
		List<ExamResults> examResultsList = new ArrayList<>();
		if (type.equals("T"))
			examResultsList.add(new ExamResults("getExamDetailsForTeacher", "0"));
		else if (type.equals("P"))
			examResultsList.add(new ExamResults("getExamDetailsForPrincipleTeacher", "0"));
		else if (type.equals("S"))
			examResultsList.add(new ExamResults("getExamDetailsForPrincipleStudent", "0"));
		String lastExamID = "";
		ExamResults er = null;
		// TODO take care of student report query
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			if (type.equals("T") || type.equals("P")) {
				rs = stmt.executeQuery("SELECT E.ExamID, GradeByTeacher "
						+ "FROM exams E, courses C , banks B, exams_results_computerized ERC "
						+ "WHERE C.CourseID=E.CourseID and C.CourseName= '" + courseName + "'" + " and B.UsernameT= '"
						+ userName + "' and B.BankID=E.BankID and ERC.ExamID=E.ExamID and C.SubjectID=B.SubjectID"
						+ " ORDER BY E.ExamID");
			} else if (type.equals("S")) {
				rs = stmt.executeQuery("SELECT E.ExamID, GradeByTeacher "
						+ "FROM exams E, courses C , exams_results_computerized ERC, banks B "
						+ "where ERC.ExamID=E.ExamID and E.CourseID=C.CourseID and C.CourseName='" + courseName
						+ "' and ERC.UsernameS='" + userName + "'"
						+ " and B.BankID=E.BankID and C.SubjectID=B.SubjectID " + "ORDER BY E.ExamID");
			}
			while (rs.next()) {
				if (!lastExamID.equals(rs.getString(1))) {
					er = new ExamResults(rs.getString(1), rs.getString(2));
					examResultsList.add(er);
					lastExamID = rs.getString(1);
				} else
					er.addGrade(rs.getString(2));
			}

			rs.close();
			System.out.println("examResultsList : " + examResultsList);
			System.out.println("FINISHED query to get exam ID and grade");
			client.sendToClient(examResultsList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the teacher Arraylist of examID with grades
	 *
	 * @param userName course name that the teacher teaches
	 * @param userName user name of the teacher
	 * @param client   the teacher
	 * @throws IOException
	 *
	 * @author Meitar El Ezra
	 */

	private void getExamsIDAndGradesByCourseIDandTeacherName(String TeacherNameAndID, String courseID,
			ConnectionToClient client) throws IOException {
		List<ExamResults> examResultsList = new ArrayList<>();
		examResultsList.add(new ExamResults("getExamDetailsForPrincipleCourse", "0"));
		String[] teacherDetailes = TeacherNameAndID.split(" ID:"); // "Danielle Sarusi ID:3" ---> ["Danielle
		// Sarusi"],["3"]
		String subjectID = courseID.substring(2);
		String courseIDafterSplit = courseID.substring(0, 2);
		String lastExamID = "";
		ExamResults er = null;
		// find each teacher who teaches course the userID
		// fix query
		// chatClient
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT E.ExamID, GradeByTeacher "
					+ "FROM exams E, courses C , banks B, exams_results_computerized RC "
					+ "WHERE C.CourseID=E.CourseID and C.CourseID='" + courseIDafterSplit + "'" + " and B.UsernameT='"
					+ teacherDetailes[1] + "' and B.SubjectID='" + subjectID
					+ "' and B.BankID=E.BankID and RC.ExamID=E.ExamID ORDER BY E.ExamID");
			while (rs.next()) {
				if (!lastExamID.equals(rs.getString(1))) {
					er = new ExamResults(rs.getString(1), rs.getString(2));
					examResultsList.add(er);
					lastExamID = rs.getString(1);
				} else
					er.addGrade(rs.getString(2));
			}

			rs.close();
			System.out.println("examResultsList : " + examResultsList);
			client.sendToClient(examResultsList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	// ***********************************************************************************************
	/**
	 * Sends to the principle all the users' details for View Info option
	 *
	 * @param client principle
	 * @throws IOException if an I/O error occur when sending the message.
	 *
	 * @author Meitar El-Ezra
	 */
	public void getUsersTableViewInfo(ConnectionToClient client) throws IOException {

		List<User> userDetails = new ArrayList<>();
		userDetails.add(new User("getUsersTableViewInfo", "", "", "", "", "", ""));
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Username, Lastname, Firstname, Phonenumber, Email, AccountType From users");

			while (rs.next()) {
				// public User(String username, String password, String firstname, String
				// lastname, String phonenumber, String email, String string) {
				userDetails.add(new User(rs.getString(1), "", rs.getString(3), rs.getString(2), rs.getString(4),
						rs.getString(5), rs.getString(6)));
			}
			client.sendToClient(userDetails);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the principle all the questions' details for View Info option
	 *
	 * @param client principle
	 * @throws IOException if an I/O error occur when sending the message.
	 *
	 * @author Meitar El-Ezra
	 */
	public void getQuestionsTableViewInfo(ConnectionToClient client) throws IOException {
		List<Question> questionsDetails = new ArrayList<>();
		questionsDetails.add(new Question("getQuestionsTableViewInfo", "", "", "", "", "", "", ""));
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * From questions");

			while (rs.next()) {
				// public Question(String questionID, String questionBody, String answer1,
				// String answer2, String answer3, String answer4, String correctAnswer,String
				// author) {
				questionsDetails.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
			}
			System.out.println(questionsDetails);
			client.sendToClient(questionsDetails);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends to the principle all the exams' details for View Info option
	 *
	 * @param client principle
	 * @throws IOException if an I/O error occur when sending the message.
	 *
	 * @author Meitar El-Ezra
	 */
	public void getExamsTableViewInfo(ConnectionToClient client) throws IOException {
		List<IExam> examsDetails = new ArrayList<>();
		// public Exam(String examID, String bankID, String courseID, String
		// allocatedTime, String author,String type) {
		examsDetails.add(new Exam("getExamsTableViewInfo", "", "", "", "", ""));
		ComputerizedExam ce;
		ManualExam me;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * From exams");
			while (rs.next()) {
				// FIX!!
				System.out.println(rs.getString(9));
				if (rs.getString(9).equals("C")) {
					// public ComputerizedExam(String examID, String bankID, String courseID, String
					// allocatedTime, String scores,
					// String studentComments, String teacherComments, String author, String type) {
					ce = new ComputerizedExam(rs.getString(1), "", rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), "Computerized");
					examsDetails.add(ce);
				}
				// examsDetails.add(new ComputerizedExam(rs.getString(1), "", rs.getString(3),
				// rs.getString(4),
				// rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
				// rs.getString(9)));
				else if (rs.getString(9).equals("M")) {
					// public ManualExam(String examID, String bankID, String courseID, String
					// allocatedTime, String author, String type) {
					me = new ManualExam(rs.getString(1), "", rs.getString(3), rs.getString(4), rs.getString(8),
							"Manual"/* , rs.getBlob(10) */);
					examsDetails.add(me);
				}
			}
			System.out.println(examsDetails);
			client.sendToClient(examsDetails);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * The functions determines whether the ID gotten from the principle reports'
	 * textField exists in the DB and us of the correct type
	 *
	 * @param username String of the ID that's being searched in the DB
	 * @param type     a letter describing the type of account that being checked
	 *                 (teacher -> "T" , student -> "S") only if the ID exists in
	 *                 the DB AND the account type is the same as we are searching
	 *                 for does the function return TRUE
	 * @param client   principle
	 * @throws IOException
	 *
	 * @author Meitar El-Ezra
	 */
	public void checkIfSearchedIDExists(String ID, String type, ConnectionToClient client) throws IOException {

		Boolean doesExist = false;
		if (type.equals("S") || type.equals("T")) {
			// checks if there is a tuple in the users list of the matching type with the
			// same userName (ID)
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * From users WHERE Username = '" + ID + "'");

				if (rs.next()) {
					System.out.println("RS NOT EMPTY");
					System.out.println(type + " " + rs.getString(7));
					if ((type.equals("T") && rs.getString(7).equals("Teacher"))
							|| (type.equals("S") && rs.getString(7).equals("Student"))) {
						doesExist = true;
						System.out.println("AFTER IF -> the user exists");
					}
				}
				System.out.println("ABOUT TO FINISH");
				client.sendToClient(doesExist);
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}
		} else if (type.equals("C")) {
			// checks if there is a tuple in the courses list of the matching courseID
			try {
				String subjectID = ID.substring(2);
				String courseIDafterSplit = ID.substring(0, 2);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * From courses WHERE CourseID = '" + courseIDafterSplit
						+ "' and SubjectID='" + subjectID + "'");

				if (rs.next()) {
					System.out.println("RS NOT EMPTY");
					doesExist = true;
					System.out.println("AFTER IF -> the course exists");
				}
				System.out.println("ABOUT TO FINISH");
				client.sendToClient(doesExist);
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * sends to the principle the list of teachers' names that teach a certain
	 * course
	 *
	 * @param courseID the course identifier : courseID + SubjectID
	 * @param client   principle
	 * @throws IOException
	 *
	 * @author Meitar El-Ezra
	 */
	// TODO just a signal
	public void getTeacherNamesByCourseID(String courseID, ConnectionToClient client) throws IOException {
		List<String> TeachrsNamesList = new ArrayList<>();
		TeachrsNamesList.add("TeachrsNamesListForPrincipleReportByCourse");
		try {
			// if the principle inserted the course ID : 0201
			// then : courseIDafterSplit = 02
			// subjectID = 01
			// the course's name is : 'Algebra 2'
			// the teachers who teaches this subject are : 2 ( Eliran Amerzoyev ) , 3 (
			// Danielle Sarusi ) , 4 ( Yonatan Rozen )
			// the teachers who had an exam done in the course : Danielle Sarusi
			String subjectID = courseID.substring(2);
			String courseIDafterSplit = courseID.substring(0, 2);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT DISTINCT E.Author, B.UsernameT FROM exams E, courses C , banks B, exams_results_computerized RC "
							+ "WHERE C.CourseID=E.CourseID and E.ExamID=RC.ExamID and C.CourseID= '"
							+ courseIDafterSplit + "' and C.SubjectID='" + subjectID
							+ "' and B.BankID=E.BankID and B.SubjectID=C.SubjectID ORDER BY E.ExamID");
			while (rs.next()) {
				TeachrsNamesList.add(rs.getString(1) + " ID:" + rs.getString(2)); // Danielle Sarusi ID:3
				// TeachrsIDsList.add(rs.getString(2));
			}
			rs.close();
			System.out.println("TeachrsNamesList : " + TeachrsNamesList);
			client.sendToClient(TeachrsNamesList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * sets the TimeOfExecution and the submitted columns according to the
	 * information from the student who pressed submit into the exam_results table
	 * FOR COMPUTERIZED EXAM
	 *
	 * @param estimatedTime the time length it took the student to complete the exam
	 *                      in minutes
	 * @param studentID     the ID of the student taking the exam
	 * @param examID        the ID of the exam that the student was taking
	 * @param client        student
	 * @param grade the grade calculate automatically for the student's exam
	 *
	 * @author Michael Malka and Meitar EL-Ezra
	 * @throws IOException
	 */
	private void UpdateCopmuterizedSubmittedExamInfoByExamIDandStudentID(String status, String estimatedTime,
			String studentID, String examID, String grade, ConnectionToClient client) throws IOException {
		// TODO insert INTO exams_results_computerized :examID, studentID, gradeBySystem(calculate) ,
		// ConfirmedByTeacher = 0 (for now)

		try {
			// ----------- query to add the tuple to exam_results table in case it doesn't
			// exist allready
			PreparedStatement stmt1 = con.prepareStatement(
					"INSERT INTO exams_results (TimeOfExecution, Submited, ExamID, UsernameS, Started) "
							+ "VALUES (?, ?, ?, ?, ?)");

			stmt1.setString(1, estimatedTime);
			if (status.equals("successful")) {
				stmt1.setString(2, "1");
			} else {// if(status.equals("NOT successful"))
				stmt1.setString(2, "0");
			}
			stmt1.setString(3, examID);
			stmt1.setString(4, studentID);
			stmt1.setString(5, "1");
			stmt1.executeUpdate();

			// ----------- query to add the tuple to exams_results_computerized table in
			// case it doesn't exist allready (accordingly)
			PreparedStatement stmt2 = con.prepareStatement(
					"INSERT INTO exams_results_computerized (ExamID, UsernameS, GradeBySystem, ConfirmedByTeacher) "
							+ "VALUES (?, ?, ?, ?)");
			stmt2.setString(1, examID);
			stmt2.setString(2, studentID);
			stmt2.setString(3, grade);
			stmt2.setString(4, "0");
			stmt2.executeUpdate();

		} catch (SQLException e) {
		}

		try {
			System.out.println("status = " + status + "\testimatedTime = " + estimatedTime + "\tstudentID = "
					+ studentID + "\texamID = " + examID);

			// ----------- updating the tuple in exam_result table
			PreparedStatement stmt = con.prepareStatement("UPDATE exams_results SET TimeOfExecution = '" + estimatedTime
					+ "', Submited = ?, Date = ?" + " WHERE ExamID =? AND UsernameS =?");
			if (status.equals("successful")) {
				stmt.setString(1, "1");
			} else {// if(status.equals("NOT successful"))
				stmt.setString(1, "0");
			}

			GregorianCalendar calendar = new GregorianCalendar();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String string = format.format(calendar.getTime());
			stmt.setString(2, string);

			stmt.setString(3, examID);
			stmt.setString(4, studentID);

			stmt.executeUpdate();

			System.out.println("studentID = " + studentID + "\texamID = " + examID + "\tGradeBySystem = " + grade
					+ "\tConfirmedByTeacher = '0'");

			// ----------- updating the tuple in exam_result table
			PreparedStatement stmt3 = con.prepareStatement("UPDATE exams_results_computerized SET GradeBySystem = '"
					+ grade + "', ConfirmedByTeacher = '0', GradeByTeacher = ?,"
					+ " ReasonForGradeChange = ? WHERE ExamID =? AND UsernameS =?");
			stmt3.setString(1, null);
			stmt3.setString(2, null);
			stmt3.setString(3, examID);
			stmt3.setString(4, studentID);

			stmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			client.sendToClient("");
			return;
		}
		client.sendToClient("");
	}


	/**
	 * sets the TimeOfExecution and the submitted columns according to the
	 * information from the student who pressed submit into the exam_results table
	 * FOR MANUAL EXAM

	 * @param estimatedTime the time length it took the student to complete the exam
	 *                      in minutes
	 * @param studentID     the ID of the student taking the exam
	 * @param examID        the ID of the exam that the student was taking
	 * @param client        student
	 *
	 * @author Michael Malka and Meitar EL-Ezra
	 * @throws IOException
	 */
	private void UpdateManualSubmittedExamInfoByExamIDandStudentID(String status, String estimatedTime,
			String studentID, String examID, ConnectionToClient client) throws IOException {
		// TODO insert INTO exams_results_computerized :examID, studentID, gradeBySystem
		// (calculate) ,
		// ConfirmedByTeacher = 0 (for now)

		try {
			// ----------- query to add the tuple to exam_results table in case it doesn't
			// exist allready
			PreparedStatement stmt1 = con.prepareStatement(
					"INSERT INTO exams_results (TimeOfExecution, Submited, ExamID, UsernameS, Started) "
							+ "VALUES (?, ?, ?, ?, ?)");

			stmt1.setString(1, estimatedTime);
			if (status.equals("successful")) {
				stmt1.setString(2, "1");
			} else {// if(status.equals("NOT successful"))
				stmt1.setString(2, "0");
			}
			stmt1.setString(3, examID);
			stmt1.setString(4, studentID);
			stmt1.setString(5, "1");
			stmt1.executeUpdate();

			// ----------- query to add the tuple to exams_results_manual table in
			// 						case it doesn't exist allready (accordingly)
			PreparedStatement stmt2 = con.prepareStatement(
					"INSERT INTO exams_results_manual (ExamID, UsernameS) "
							+ "VALUES (?, ?)");
			stmt2.setString(1, examID);
			stmt2.setString(2, studentID);
			stmt2.executeUpdate();

		} catch (SQLException e) {
		}

		try {
			System.out.println("status = " + status + "\testimatedTime = " + estimatedTime + "\tstudentID = "
					+ studentID + "\texamID = " + examID);

			// ----------- updating the tuple in exam_result table
			PreparedStatement stmt = con.prepareStatement("UPDATE exams_results SET TimeOfExecution = '" + estimatedTime
					+ "', Submited = ?, Date = ?" + " WHERE ExamID =? AND UsernameS =?");
			if (status.equals("successful")) {
				stmt.setString(1, "1");
			} else {// if(status.equals("NOT successful"))
				stmt.setString(1, "0");
			}

			GregorianCalendar calendar = new GregorianCalendar();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String string = format.format(calendar.getTime());
			stmt.setString(2, string);

			stmt.setString(3, examID);
			stmt.setString(4, studentID);

			stmt.executeUpdate();

			//System.out.println("studentID = " + studentID + "\texamID = " + examID + "\tGradeBySystem = " + grade
			//	+ "\tConfirmedByTeacher = '0'");

		} catch (SQLException e) {
			e.printStackTrace();
			client.sendToClient("");
			return;
		}
		System.out.println("QUERY FOR MANUAL EXAM----------> END");
		client.sendToClient("");
	}

}
