package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import gui.server.IServerConsoleController;
import logic.exam.ExamResults;
import server.DBconnector;

public class ServerGetExamResultsTest {

	private DBconnector dbconnector;
	String courseName;
	String userName;
	String type;
	
	ExamResults examResults010101;
	ExamResults examResults010102;
	
	private class ServerConsoleControllerStub implements IServerConsoleController{

		/* stubing to avoid NullPointerException at 'connectoToDB()'*/
		@Override
		public void println(String info) {
			System.out.println(info);
		}
	}

	@Before
	public void setUp() throws Exception {
		dbconnector = DBconnector.getInstance();
		dbconnector.setServerConsole(new ServerConsoleControllerStub());
		dbconnector.connectToDB();
		
		examResults010101 = new ExamResults("010101", "100");
		examResults010102 = new ExamResults("010102", "75");
		examResults010102.addGrade("50");
		examResults010102.addGrade("0");
	}

	/**
	 * check 'getExamsIDAndGradesByUsernameAndCourseName' returns correct info from database
	 * input : coursename = Algebra 1, userName = "4", type = "T"
	 * expected : correct list of exam results
	 */
	@Test
	public void testGetExamResultsForExistingExamsInDBbyCourseNameAndUsername() {
		courseName = "Algebra 1";
		userName = "4";
		type = "T";

		List<ExamResults> expected = new ArrayList<>();
		expected.add(new ExamResults("getExamDetailsForTeacher", "0"));
		expected.add(examResults010101);
		expected.add(examResults010102);
		
		Object res;
		if (!((res = dbconnector.getExamsIDAndGradesByUsernameAndCourseName(courseName, userName, type)) instanceof List<?>)){
			fail();
			return;
		}
		
		List<ExamResults> actual = (List<ExamResults>) res;
		assertEquals(expected, actual);
	}

}
