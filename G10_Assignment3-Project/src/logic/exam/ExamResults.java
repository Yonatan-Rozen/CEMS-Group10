package logic.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.XYChart;
/**
 * a class to describe exams done by students for the reports of teacher
 * @author Danielle Sarusi and Yonatan Rozen
 */
@SuppressWarnings("serial")
public class ExamResults implements Serializable {
	// FIELDS ****************************************************************************************
	private String examID;
	private List<Integer> gradesList = new ArrayList<>();

	// CONSTRUCTOR ***********************************************************************************
	public ExamResults(String examID, String grade) {
		this.examID = examID;
		this.gradesList.add(Integer.parseInt(grade));
	}

	// METHODS ***********************************************************************************
	/**
	 * a method to get the examID of the exam the student has done
	 * @return the examID of the exam done by the student (String)
	 */
	public String getExamID() {
		return examID;
	}

	/**
	 * a method to get the GradesList of the students who had done the exam
	 * @return List<Integer> GradesList of the students who had done the  exam
	 */
	public List<Integer> getGradesList() {
		return gradesList;
	}

	// for the other grades with examID already exist
	/**
	 * method that adds a grade of student who had done the exam to
	 * the list of grades of the exam
	 * @param grade the student's grade to be added to the list
	 * @return boolean value : 	true = the grade added successfully,
	 * 							false = else
	 */
	public boolean addGrade(String grade) {
		int gradeInt = Integer.parseInt(grade);
		if (gradeInt < 0 || gradeInt > 100)
			return false;
		return gradesList.add(gradeInt);
	}

	/**
	 * a method to calculate and get the exam's statistic median
	 * @return double the median of the exam's grade
	 */
	public double getMedian() {

		int lenghtArray = gradesList.size();

		// First we sort the array
		Collections.sort(gradesList);

		// check for even case
		if (lenghtArray % 2 != 0)
			return gradesList.get(lenghtArray / 2);

		return (gradesList.get((lenghtArray - 1) / 2) + gradesList.get(lenghtArray / 2)) / 2.0;
	}

	/**
	 * a method to calculate and get the exam's statistic Average
	 * @return double the Average of the exam's grade
	 */
	public double getAverage() {
		double sum = 0.0;
		for (Integer grade : gradesList)
			sum += (double) grade;
		return sum / gradesList.size();
	}

	/**
	 * Override method of Object's ToString
	 */
	@Override
	public String toString() {
		return examID+" : "+gradesList;

	}

	public XYChart.Series<String, Integer> getGraph() {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		List<String> gradesFromItoJlist = new ArrayList<>();
		int studentGradeBins[];

		initGradesFromItoJlist(gradesFromItoJlist);

		// init studentGradeBins
		studentGradeBins = getStudentsGradeBins();

		// init series
		for (int i = 0; i < 10; i++) {
			series.getData().add(new XYChart.Data<String, Integer>(gradesFromItoJlist.get(i), studentGradeBins[i]));
		}
		return series;
	}

	public int[] getStudentsGradeBins() {
		int studentGradeBins[] = new int[10];
		Arrays.fill(studentGradeBins, 0);
		for (int grade : gradesList) {
			if (grade >= 0 && grade <= 10)
				studentGradeBins[0]++;
			else if (grade >= 11 && grade <= 20)
				studentGradeBins[1]++;
			else if (grade >= 21 && grade <= 30)
				studentGradeBins[2]++;
			else if (grade >= 31 && grade <= 40)
				studentGradeBins[3]++;
			else if (grade >= 41 && grade <= 50)
				studentGradeBins[4]++;
			else if (grade >= 51 && grade <= 60)
				studentGradeBins[5]++;
			else if (grade >= 61 && grade <= 70)
				studentGradeBins[6]++;
			else if (grade >= 71 && grade <= 80)
				studentGradeBins[7]++;
			else if (grade >= 81 && grade <= 90)
				studentGradeBins[8]++;
			else if (grade >= 91 && grade <= 100)
				studentGradeBins[9]++;
		}
		return studentGradeBins;
	}

	public void initGradesFromItoJlist(List<String> gradesFromItoJlist) {
		// init gradesFromItoJlist
		gradesFromItoJlist.add("0-10");
		gradesFromItoJlist.add("11-20");
		gradesFromItoJlist.add("21-30");
		gradesFromItoJlist.add("31-40");
		gradesFromItoJlist.add("41-50");
		gradesFromItoJlist.add("51-60");
		gradesFromItoJlist.add("61-70");
		gradesFromItoJlist.add("71-80");
		gradesFromItoJlist.add("81-90");
		gradesFromItoJlist.add("91-100");
	}


	/**
	 * Override method of Object's equals
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ExamResults))
			return false;
		ExamResults otherExamResults = (ExamResults) other;

		if (!this.getExamID().equals(otherExamResults.getExamID()))
			return false;

		List<Integer> otherGrades = otherExamResults.getGradesList();
		return gradesList.equals(otherGrades);
	}
}
