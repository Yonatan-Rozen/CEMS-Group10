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
import logic.exam.ExamResults;
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
	// SERVER DATABASE RESETS
	public void resetUserConnections() {
		try { 
			PreparedStatement stmt = con.prepareStatement("UPDATE users SET Connected = '0'");
			stmt.executeUpdate();
		}catch (SQLException e) {
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
		if (data instanceof String)
			client.sendToClient(data);
		else if (data instanceof String[]) {

			String[] request = (String[]) data;

			switch (request[0]) {
			case "Disconnect": // disconnectClient(username, client)
				disconnectClient(request[1], client);
				break;
			case "btnPressSignIn": // getUserInfoByUsernameAndPassword(username, password, client)
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
				getCourseBySubject(request[1], request[2], client); // 1->subject
				break;
			case "btnPressStartExam":
				getExamByExamID(request[1], client);
				getExamsQuestionsByExamID(request[1], client);
				break;
			case "btnPressSaveQuestion": // insertNewQuestionToDB(subjectName, questionBody, answer1, answer2, answer3, answer4, correctAnswer, username, author, client)
				insertNewQuestionToDB(request[1], request[2], request[3], request[4], 
						request[5], request[6], request[7], request[8], request[9], client);
				break;
			case "btnPressContinue2CreateExam":
				insertNewExamToDB(request[1], request[2], request[3], client);
				break;
			case "btnPressShowQuestionsBySubject":
				getQuestionsBySubjectAndUsername(request[1], request[2], request[3], client);
				break;
			case "GetExistingBanks":
				getSubjectWithExistingBanks(request[1], client);
				break;
			case "lnkPressDownloadExamFile":
				getManualExamFileByExamID(request[1], client);
				break;
			case "CheckQuestionExistsInExam": // checkQuestionExistsInExam(questionID, client)
				checkQuestionExistsInExam(request[1],client);
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
				getCoursesByUserName(request[1], client);
				break;
			case "GetExamDetails":
				getExamsIDAndGradesByUsernameAndCourseName(request[1], request[2], client);
				break;
			default:
				ServerUI.serverConsole.println(request[0] + " is not a valid case!");
				client.sendToClient(request[0] + " is not a valid case!");
				break;
			}
		}
		
		
		else if (data instanceof Object[]) {
			Object[] request = (Object[]) data;
			
			switch(request[0].toString()) {
			case "UpdateQuestion":
				updateQuestion((Question)request[1], client);
				break;
			default:
				ServerUI.serverConsole.println(request[0] + " is not a valid case!");
				client.sendToClient(request[0] + " is not a valid case!");
				break;
			}
		}
	}

	private void insertNewExamToDB(String CourseName, String subjectName, String author, ConnectionToClient client)
			throws IOException {

		// get CourseID by CourseName
		String CourseID = null;
		String SubjectID = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT CourseID,SubjectID From courses WHERE CourseName = \"" + CourseName + "\"");
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

		String type ="C"; //computer - M-> Manually
		String examID = "01"; //need to generate numbers
		// insert new exam into exams and generate a new 'examID'
		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO exams (ExamID, BankID,CourseID,AllocatedTime,Author,Type)" + "VALUES (?,?,?,?,?,?)");
			stmt.setString(1, examID);
			stmt.setString(2, BankID);
			stmt.setString(3, CourseID);
			stmt.setString(4, "000"); //not null
			stmt.setString(5, author);
			stmt.setString(6, type);
			stmt.executeUpdate();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

	client.sendToClient("CreateExam SUCCESS - Exam "+String.format("#%s",examID)+" was created successfully!");
	}

	
	
	// ***********************************************************************************************
	// QUERY METHODS
	// ***********************************************************************************************


	/**
	 * Disconnect the current client from the server
	 * @param username the username of the current user
	 * @param client the connected user
	 * @throws IOException 
	 */
	private void disconnectClient(String username, ConnectionToClient client) throws IOException {
		// update that this user is connected to the server
		try { 
			PreparedStatement stmt = con.prepareStatement("UPDATE users SET Connected = '0' WHERE Username = '"+ username +"'");
			stmt.executeUpdate();
			client.sendToClient("Disconnect");
		}catch (SQLException e) {
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
		questionsOfExam.add(new Question("getExamsQuestionsByExamID", "", "", "", "", "", "", ""));
		// questionsOfExam.add("getSubjectsByUsername");
		// get all the exam's questions into the arrayList according to the examID
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Q.* " + "FROM questions_in_exam QOE, questions Q "
					+ "WHERE QOE.QuestionID = Q.QuestionID AND QOE.ExamID = '" + examID + "'");

			while (rs.next()) {
				questionsOfExam.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM exams E WHERE E.ExamID = '" + examID + "'");

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
					"SELECT C.courseName FROM courses C WHERE C.courseID = '" + exam.getCourseID() + "'");

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
			// * This method should always work!!! ; Add Missing information in sbujects_of_teacher if it doesn't*
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	private void getBanksByUsername(String username, ConnectionToClient client) throws IOException {
		List<String> bankList = new ArrayList<>();

		bankList.add("getBanksByUsername");
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

	////////////////////////
	private void getCourseBySubject(String SubjectName, String username, ConnectionToClient client) throws IOException {
		List<String> CourseList = new ArrayList<>();
		String fixBankID = SubjectName;

		if (!fixBankID.startsWith("0"))
			fixBankID = "0" + fixBankID;

		CourseList.add("getCourseBySubject");
		try {
			// get courses with bankid
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery( "SELECT C.CourseName FROM cems.courses C WHERE SubjectID = "
					+ "(SELECT S.SubjectID FROM cems.subjects S WHERE SubjectName = '"+ SubjectName + "'");

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
	public void getUserInfoByUsernameAndPassword(String username, String password, ConnectionToClient client) throws IOException {

		User user = null;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * From users WHERE Username = '" + username + "' AND Password = '" + password + "'");
			
			if (rs.next()) {
				if (rs.getString(8).equals("1")) {
					client.sendToClient("SignIn Error - This user is already connected!");
					return;
				}
				else user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
			} else
				client.sendToClient("SignIn ERROR - Wrong username or password!");
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
		
		// update that this user is connected to the server
		try { 
			PreparedStatement stmt = con.prepareStatement("UPDATE users SET Connected = '1' WHERE Username = '"+ username +"'");
			stmt.executeUpdate();
		}catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}

		client.sendToClient(user);
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
			ResultSet rs = stmt.executeQuery("SELECT SubjectID From subjects WHERE SubjectName = '" + subjectName + "'");
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
			ResultSet rs = stmt.executeQuery("SELECT HasBank From subjects_of_teacher "
						+ "WHERE SubjectID = '" + subjectID + "' AND UsernameT = '" + username + "'");
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
				PreparedStatement stmt = con.prepareStatement("UPDATE subjects_of_teacher SET HasBank = '1' WHERE SubjectID = ? AND UsernameT = ?");
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
			ResultSet rs = stmt.executeQuery("SELECT BankID From Banks WHERE SubjectID = '" + subjectID + "' AND UsernameT = '" + username + "'");
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
			PreparedStatement stmt = con.prepareStatement("INSERT INTO questions (QuestionID,BankID,Body,Answer1,Answer2,Answer3,Answer4,CorrectAnswer,Author,ExistsInExam) "
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
			ResultSet rs = stmt.executeQuery("SELECT S.SubjectName FROM subjects S, banks B "
					+ "WHERE B.UsernameT = '" + username + "' AND S.SubjectID = B.SubjectID");
			while (rs.next()) {
				bankList.add(rs.getString(1));
			}
			rs.close();
			if (bankList.size() > 1)
				client.sendToClient(bankList);
			else
				client.sendToClient("GetSubjectsWithBank ERROR - Please note that you should create a question first!");
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
	 * @author Yonatan Rozen
	 */

	private void getQuestionsBySubjectAndUsername(String subjectName, String num, String username,
			ConnectionToClient client) throws IOException { // num for using in create exam
		List<Question> questionList = new ArrayList<>();
		System.out.println(num);

		if (num.equals("2")) {
			questionList.add(new Question("getQuestionsBySubjectAndUsername2", "", "", "", "", "", "", ""));

		} else {
			questionList.add(new Question("getQuestionsBySubjectAndUsername", "", "", "", "", "", "", ""));

		}
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Q.* FROM questions Q WHERE Q.bankID = "
					+ "	(SELECT B.BankID FROM banks B WHERE B.UsernameT = '" + username + "' AND B.SubjectID = "
					+ "	(SELECT S.SubjectID FROM subjects S WHERE S.SubjectName = '" + subjectName + "'))");
			while (rs.next()) {
				questionList.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
			}
			rs.close();
			System.out.println("checkList = " + questionList);
			client.sendToClient(questionList);
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
	 * @param client The teacher
	 * @throws IOException
	 * 
	 * @author Yonatan Rozen
	 */
	private void checkQuestionExistsInExam(String questionID, ConnectionToClient client) throws IOException {
		String[] existsInExam = {"checkQuestionExistsInExam",""};
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ExistsInExam FROM questions WHERE QuestionID = '" + questionID + "'");
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
	
	// ***********************************************************************************************
	/**
	 * Removes a question from the database
	 * 
	 * @param questionID The questionID
	 * @param subjectName The subject of the question
	 * @param username the username of the teacher
	 * @param client the teacher that is requestion the question removal
	 * @throws IOException
	 * 
	 * @author Yonatan Rozen
	 */
	private void removeQuestion(String questionID, String subjectName ,String username, ConnectionToClient client) throws IOException {
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
			PreparedStatement stmt = con.prepareStatement("DELETE FROM questions WHERE QuestionID = '" + questionID + "'");
			stmt.executeUpdate();
		}catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
		
		// check if the bank reperesented by the bankID has been emptied
		boolean isEmpty = false;
		try {	
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT (EXISTS (SELECT 1 FROM questions WHERE bankID = '" + bankID + "'))");
			if (rs.next())
				if(rs.getString(1).equals("0")) {
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
				ResultSet rs = stmt.executeQuery("SELECT SubjectID FROM subjects WHERE SubjectName = '" + subjectName + "'");
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
	 * @param client The teacher requestion the change
	 * @throws IOException 
	 * 
	 * @author Yonatan Rozen
	 */
	private void updateQuestion(Question question, ConnectionToClient client) throws IOException {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE questions SET Body = ?, Answer1 = ?, Answer2 = ?, Answer3 = ?,"
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

	/**
	 * Sends Arraylist of courses back to the teacher
	 * 
	 * @param userName user name of the teacher
	 * @param client   the teacher
	 * @throws IOException
	 * 
	 * @author Danielle sarusi
	 */
	private void getCoursesByUserName(String userName, ConnectionToClient client) throws IOException {
		List<String> coursesList = new ArrayList<>();
		coursesList.add("getCoursesByUserName");

		// Returns the list of course names taught by the teacher
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT C.courseName FROM courses C,"
					+ " (SELECT E.CourseID, B.SubjectID FROM banks B, exam E" + "	WHERE B.username = \"" + userName
					+ "\" AND B.BankID = E.BankID) as CS "
					+ "WHERE C.CourseID = CS.CourseID AND C.SubjectID = CS.SubjectID");
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
	}

	/**
	 * Sends to the teacher Arraylist of examID with grades
	 * 
	 * @param userName course name that the teacher teaches
	 * @param userName user name of the teacher
	 * @param client   the teacher
	 * @throws IOException
	 * 
	 * @author Danielle sarusi
	 */

	private void getExamsIDAndGradesByUsernameAndCourseName(String courseName, String userName,
			ConnectionToClient client) throws IOException {
		List<ExamResults> examResultsList = new ArrayList<>();
		examResultsList.add(new ExamResults("getExamDetails", "0"));
		String lastExamID = "";
		ExamResults er = null;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT E.ExamID,grade "
					+ "FROM exam E, courses C , banks B, grades_of_exam GOE "
					+ "WHERE C.CourseID=E.CourseID and C.courseName= \"" + courseName + "\" " + "and B.Username= \""
					+ userName + "\"and B.BankID=E.BankID and GOE.ExamID=E.ExamID " + "ORDER BY E.ExamID");
			while (rs.next()) {
				if (!lastExamID.equals(rs.getString(1))) {
					er = new ExamResults(rs.getString(1), rs.getString(2));
					examResultsList.add(er);
					lastExamID = rs.getString(1);
				} else
					er.addGrade(rs.getString(2));
			}

			rs.close();
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

	public void getQuestionsTableViewInfo(ConnectionToClient client) throws IOException {
		List<Question> questionsDetails = new ArrayList<>();
		questionsDetails.add(new Question("getQuestionsTableViewInfo", "", "", "", "", "", "", ""));
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * From question");

			while (rs.next()) {
				// public Question(String questionID, String questionBody, String answer1,
				// String answer2, String answer3, String answer4, String correctAnswer,String
				// author) {
				questionsDetails.add(new Question(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
			}
			client.sendToClient(questionsDetails);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}

	public void getExamsTableViewInfo(ConnectionToClient client) throws IOException {
		List<Exam> examsDetails = new ArrayList<>();
		examsDetails.add(new Exam("getExamsTableViewInfo", "", "", "", "", "", "", ""));
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * From exam");

			while (rs.next()) {
				// public Exam(String examID, String username, String bankID, String courseID,
				// String allocatedTime, String scores, String studentComments, String
				// teacherComments) {
				examsDetails.add(new Exam(rs.getString(1), rs.getString(2), "", rs.getString(4), rs.getString(5), "",
						rs.getString(7), rs.getString(8)));
			}
			client.sendToClient(examsDetails);
			rs.close();
		} catch (SQLException e) {
			client.sendToClient("sql exception");
			e.printStackTrace();
			return;
		}
	}
}
