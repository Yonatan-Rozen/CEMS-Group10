package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.IClientController;
import gui.client.teacher.TeacherReportsController;
import logic.exam.ExamResults;


class ClientGetExamResultsTest {
	
	
	private ClientControllerStub clientController;
	private TeacherReportsController teacherReportsController;
	private List<ExamResults> examResults = new ArrayList<>();
	
	private class ClientControllerStub implements IClientController{

		@Override
		public void accept(Object obj) {
			teacherReportsController.setExamResultsDetails(examResults);
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		examResults.add(new ExamResults("010101", "90"));
		teacherReportsController = new TeacherReportsController();
		clientController = new ClientControllerStub();
		teacherReportsController.setClientController(clientController);
	}

	/**
	 * try loading details when course is not choosen
	 * input : courseName = null, username = "4"
	 * expected : false
	 */
	@Test
	void testNoCourseIsChoosen() {
		boolean result = teacherReportsController.loadChosenCourseExamReports(null, "4");
		assertFalse(result);
	}
	
	/**
	 * try loading details when a course is choosen
	 * input : courseName = "Math", username = "4"
	 * expected : true
	 */
	@Test
	void testChoorseMathIsChoosen() {
		boolean result = teacherReportsController.loadChosenCourseExamReports("Math", "4");
		assertTrue(result);
	}

}
