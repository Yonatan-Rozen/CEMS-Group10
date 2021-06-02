package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.User;
import logic.exam.Exam;
import logic.question.Question;
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
	/**
	 * Parses any object that can be deserialized
	 *
	 * @param data   The data to parse
	 * @param client The user who sent the message to the server
	 * @throws IOException if an I/O error occur when sending the message.
	 */
	public void parseData(Object data, ConnectionToClient client) throws IOException {
		if (data instanceof String)
			client.sendToClient(data);
		else if (data instanceof String[]) {

			String[] request = (String[]) data;

			switch (request[0]) {
			case "btnPressSignIn":
				getUserInfoByUsernameAndPassword(request[1], request[2], client);
				break;
			case "btnPressConfirmChange":
				setNewPasswordByusername(request[1], request[2], request[3], request[4], request[5], client);
				break;
			case "GetSubjects":
				getSubjectsByUsername(request[1], client);
				break;
			case "GetBanks":
				getBanksByUsername(request[1], client);
				break;
			case "GetCourseBySubject":
				getCourseBySubject(request[1], request[2], client); // 2->subject
				break;
//			case "GetQuestionsByBank":
//				getQuestionsByBank(request[1] , client);
//				break;
			case "btnPressStartExam":
				getExamByExamID(request[1], client);
				getExamsQuestionsByExamID(request[1], client);
				break;
			case "btnPressSaveQuestion":
				insertNewQuestionToDB(request[1], request[2], request[3], request[4], request[5], request[6],
						request[7], request[8], request[9], client);
				break;
			case "btnPressShowQuestionsBySubject":
				getQuestionsBySubjectAndUsername(request[1], request[2], client);
				break;
			case "GetExistingBanks":
				getSubjectWithExistingBanks(request[1],client);
			case "lnkPressDownloadExamFile":
				getManualExamFileByExamID(request[1], client);
				break;
			default:
				ServerUI.serverConsole.println(request[0] + " is not a valid case!");
				client.sendToClient(request[0] + " is not a valid case!");
				break;
			}
		}
	}

	// ***********************************************************************************************
	// QUERY METHODS
	// ***********************************************************************************************
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
		questionsOfExam.add(new Question("getExamsQuestionsByExamID", "", "", "", "", "", ""));
		// questionsOfExam.add("getSubjectsByUsername");
		// get all the exam's questions into the arrayList according to the examID
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT Q.* "
												+ "FROM questions_in_exam QOE, question Q "
												+ "WHERE QOE.QuestionID = Q.QuestionID AND QOE.ExamID = \"" + examID + "\"");

			while (rs.next()) {
				questionsOfExam.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			client.sendToClient(questionsOfExam);
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
		Exam exam = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM exam E WHERE E.ExamID = \"" + examID + "\"");

			while (rs.next()) {
				exam = new Exam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
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
			ResultSet rs = stmt.executeQuery(
					"SELECT C.courseName FROM courses C WHERE C.courseID = \"" + exam.getCourseID() + "\"");

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
					+ "WHERE S.SubjectID = SOT.SubjectID AND SOT.Username = \"" + username + "\";");

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
	
//	private void getQuestionsByBank(String username, ConnectionToClient client) throws IOException {
//		List<String> bankList = new ArrayList<>();
//		bankList.add("getQuestionsByBank");
//		try {
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT S.SubjectName FROM cems.subjects S, cems.subjects_of_teacher SOT "
//					+ "WHERE S.SubjectID = SOT.SubjectID AND SOT.Username = \"" + username + "\";");
//
//			while (rs.next())
//				bankList.add(rs.getString(1));
//			client.sendToClient(bankList);
//
//			rs.close();
//		} catch (SQLException e) {
//			// * This method should always work!!! ; Add Missing information if it doesn't*
//			client.sendToClient("sql exception");
//			e.printStackTrace();
//			return;
//		}
//
//	}

	/**
	 * Sends to the teacher an (ArrayList) of her subjects of study
	 * 
	 * @param username The username of the teacher
	 * @param client   The teacher
	 * @throws IOException
	 */
	private void getBanksByUsername(String username, ConnectionToClient client) throws IOException {
		List<String> bankList = new ArrayList<>();
		bankList.add("getBanksByUsername");
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT B.SubjectID FROM cems.banks B " + "WHERE B.Username = \"" + username + "\";");

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

	private void getCourseBySubject(String username, String subjectid, ConnectionToClient client) throws IOException {
		List<String> CourseList = new ArrayList<>();
		CourseList.add("getCourseBySubject");
		System.out.format("inserted question : %s \n", subjectid);
		System.out.println("with zero: " + subjectid);
		String SubjectIDwithZero = "0" + subjectid;
		System.out.println(SubjectIDwithZero);

		try {
			// get courses with subjectid
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(
					"SELECT courseName FROM cems.courses C " + "WHERE C.SubjectID = \"" + SubjectIDwithZero + "\";");

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

		client.sendToClient("ChangePassword SUCCESS - Your password was set successfully!");
	}
	
	// ***********************************************************************************************
	/**
	 * 
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
			ResultSet rs = stmt.executeQuery("SELECT examFile FROM manual_exams WHERE examID = \"" + examID + "\"");
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
	 * @param client   A user of one of the following types : Student / Teacher / Principle
	 * @throws IOException if an I/O error occur when sending the message.
	 * 
	 * @author Yonatan Rozen
	 */
	public void getUserInfoByUsernameAndPassword(String username, String password, ConnectionToClient client)
			throws IOException {

		if (username.equals("") || password.equals("")) {
			client.sendToClient("SignIn ERROR - All fields are required!");
			return;
		}

		int NumberOfColumns = 7;
		List<String> userDetails = new ArrayList<>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * From users WHERE Username = \"" + username + "\" AND Password = \"" + password + "\"");

			if (rs.next()) {
				for (int i = 1; i <= NumberOfColumns; i++) {
					userDetails.add(rs.getString(i));
				}
				User user = new User(userDetails.get(0), userDetails.get(1), userDetails.get(2), userDetails.get(3),
						userDetails.get(4), userDetails.get(5), userDetails.get(6));

				client.sendToClient(user);
			} else
				client.sendToClient("SignIn ERROR - Wrong username or password!");
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
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
	 * @param username		The username of the teacher
	 * @param author		The full name of the teacher
	 * @param client 		The clienty
	 * @throws IOException if an I/O error occur when sending the message.
	 * 
	 * @author Yonatan Rozen 
	 */
	private void insertNewQuestionToDB(String subjectName, String questionBody, String answer1, String answer2,
			String answer3, String answer4, String correctAnswer, String username, String author,
			ConnectionToClient client) throws IOException {

		// get subjectID by subjectName
		String subjectID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT SubjectID From subjects WHERE SubjectName = \"" + subjectName + "\"");
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
		String hasBank = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT HasBank From subjects_of_teacher " + "WHERE SubjectID = \""
					+ subjectID + "\" AND Username = \"" + username + "\"");
			if (rs.next())
				hasBank = rs.getString(1);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		// if bank was not yet created for (SubjectID & Username)
		if (hasBank.equals("FALSE")) {

			// insert (SubjectID & Username) into Banks and generate a new 'bankID'
			try {
				PreparedStatement stmt = con
						.prepareStatement("INSERT INTO Banks (SubjectID, Username)" + "VALUES (?,?)");
				stmt.setString(1, subjectID);
				stmt.setString(2, username);
				stmt.executeUpdate();
			} catch (SQLException e) {
				client.sendToClient("sql exception");
				e.printStackTrace();
				return;
			}

			// update HasBank to TRUE in 'subjects_of_teacher' table
			try {
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE subjects_of_teacher SET HasBank = 'TRUE' " + "WHERE SubjectID = ? AND Username = ?");
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
			ResultSet rs = stmt.executeQuery("SELECT BankID From Banks " + "WHERE SubjectID = \"" + subjectID
					+ "\" AND Username = \"" + username + "\"");
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
			ResultSet rs = stmt.executeQuery("SELECT MAX(ExtractedQuestionID) as ID "
					+ "FROM (SELECT SUBSTRING(QuestionID, 3) as ExtractedQuestionID " + "FROM question "
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
					"INSERT INTO question (QuestionID,BankID,Body,Answer1,Answer2,Answer3,Answer4,CorrectAnswer,Author) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)");
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
	 * @param subjectID		  The choosen subject
	 * @param username		  The username of the teacher
	 * @throws IOException 
	 * 
	 * @author Yonatan Rozen
	 */
	private void getSubjectWithExistingBanks(String username, ConnectionToClient client) throws IOException {
		List<String> bankList = new ArrayList<>();
		bankList.add("getSubjectWithExistingBanks");
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT S.SubjectName "
											+ "FROM subjects S, banks B "
											+ "WHERE B.Username = \"" + username + "\" AND S.SubjectID = B.SubjectID");
			while (rs.next()) {
				bankList.add(rs.getString(1));
			}
			rs.close();
			if (bankList.size() > 1)
				client.sendToClient(bankList);
			else client.sendToClient("GetSubjectsWithBank ERROR - Please note that you should create a question first!");
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
	 * @param subjectID			The choosen subject
	 * @param username			The username of the teacher
	 * @throws IOException 
	 * 
	 * @author Yonatan Rozen
	 */
	private void getQuestionsBySubjectAndUsername(String subjectName, String username, ConnectionToClient client) throws IOException {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question("getQuestionsBySubjectAndUsername", "", "", "", "", "", ""));
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Q.* FROM question Q WHERE Q.bankID = "
					+ "	(SELECT B.BankID FROM banks B WHERE B.Username = \"" + username + "\" AND B.SubjectID = "
					+ "	(SELECT S.SubjectID FROM subjects S  WHERE S.SubjectName = \"" + subjectName + "\"))");
			while (rs.next()) {
				questionList.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			rs.close();
			client.sendToClient(questionList);
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}
}