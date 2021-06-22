package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.exam.ExamResults;

class ExamResultsTest {
	private ExamResults examResults;
	List<String> gradesFromItoJlist;
	int studentGradeBins[];
	private String examID;
	private String firstGradeResult;

	@BeforeEach
	void setUp() throws Exception {
		examID = "010101";		
		examResults = new ExamResults(examID, "0");
		examResults.addGrade("100");
		examResults.addGrade("96");
		examResults.addGrade("80");
		examResults.addGrade("57");
		examResults.addGrade("53");
		examResults.addGrade("48");
		
		gradesFromItoJlist = new ArrayList<>();
		examResults.initGradesFromItoJlist(gradesFromItoJlist);
		
		studentGradeBins = new int[10];
		Arrays.fill(studentGradeBins, 0);
	}

	/**
	 * any added grade with a 'NULL' value from the database
	 * 
	 * input : new added grade value is null
	 * expected : NumberFormatException being thrown
	 */
	@Test
	public void testExamResultsWithAddNullGrade() {
		try {
			examResults.addGrade(null);
			fail();
		} catch (NumberFormatException e) { }
		assertTrue(true);
	}
	
	/**
	 * get first grade with a 'NULL' value from the database
	 * 
	 * input : firstGradeResult = null
	 * expected : NumberFormatException being thrown
	 */
	@Test
	public void testExamResultsWithFirstGradeAsNull() {
		firstGradeResult = null;
		try {
			new ExamResults(examID, firstGradeResult);
			fail();
		} catch (NumberFormatException e) { }
		assertTrue(true);
	}
	
	/**
	 * try adding negative grade value to the exam results
	 * input : negative grade value
	 * expected : false
	 */
	@Test
	public void testInsertedGradeIsLessThanZero() {
		boolean actual = examResults.addGrade("-1");
		assertFalse(actual);
	}
	
	/**
	 * try adding grade with a value more than 100 to the exam results
	 * input : 101
	 * expected : false
	 */
	@Test
	public void testInsertedGradeIsMoreThan100() {
		boolean actual =  examResults.addGrade("101");
		assertFalse(actual);
	}
	
	/**
	 * getMedian calculates and returns the correct 
	 * median value of the grades in the exam results
	 * 
	 * input : grades defined in setUp() method
	 * expected : 57.0
	 */
	@Test
	public void testMedianReturnsRightValueOfExamResults() {
		double expected = 57.0;
		double actual = examResults.getMedian();
		assertEquals(expected, actual);
	}
	
	
	/**
	 * getAverage calculates and returns the correct 
	 * average value of the grades in the exam results
	 * 
	 * input : grades defined in setUp() method
	 * expected : 62.0
	 */
	@Test
	public void testAverageReturnsRightValueOfExamResults() {
		double expected = 62.0;
		double actual = examResults.getAverage();
		assertEquals(expected, actual);
	}
	
	
	/**
	 * check if the distribution of grades returns correctly
	 * input : grades defined in setUp() method
	 * expected : {1, 0, 0, 0, 1, 2, 0, 1, 0, 2} (amount of grades in each bin)
	 */
	@Test
	public void testCorrectStudentsGradeBins() {
		int[] expected = {1,0,0,0,1,2,0,1,0,2};
		int[] actual = examResults.getStudentsGradeBins();
		for (int i = 0 ; i < 10; i ++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
}
