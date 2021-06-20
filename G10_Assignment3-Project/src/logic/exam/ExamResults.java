package logic.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.XYChart;

@SuppressWarnings("serial")
public class ExamResults implements Serializable {

	private String examID;
	private List<Integer> gradesList = new ArrayList<>();

	public ExamResults(String examID, String grade) {
		this.examID = examID;
		this.gradesList.add(Integer.parseInt(grade));
	}

	public String getExamID() {
		return examID;
	}

	public List<Integer> getGradesList() {
		return gradesList;
	}

	// for the other grades with examID already exist
	public boolean addGrade(String grade) {
		int gradeInt = Integer.parseInt(grade);
		if (gradeInt < 0 || gradeInt > 100)
			return false;
		return gradesList.add(gradeInt);
	}

	public double getMedian() {

		int lenghtArray = gradesList.size();

		// First we sort the array
		Collections.sort(gradesList);

		// check for even case
		if (lenghtArray % 2 != 0)
			return gradesList.get(lenghtArray / 2);

		return (gradesList.get((lenghtArray - 1) / 2) + gradesList.get(lenghtArray / 2)) / 2.0;
	}

	public double getAverage() {
		double sum = 0.0;
		for (Integer grade : gradesList)
			sum += (double) grade;
		return sum / gradesList.size();
	}

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
