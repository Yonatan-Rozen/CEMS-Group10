package logic.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class ExamResults implements Serializable {

	private String examID ;
	private List <Integer> gradesList=new ArrayList <>();
	
	public ExamResults(String examID, String grade ) {
		this.examID=examID;
		this.gradesList.add(Integer.parseInt(grade));
	}

	public String getExamID() {
		return examID;
	}

	public List <Integer> getGradesList() {
		return gradesList;
	}
	
	//for the other grades with examID already exist
	public void addGrade(String grade) {
		gradesList.add(Integer.parseInt(grade));
	}
	
	
	public double getMedian()
	{
	
		int lenghtArray=gradesList.size();
        
		// First we sort the array
		Collections.sort(gradesList);
  
        // check for even case
        if (lenghtArray % 2 != 0)
            return (double) gradesList.get(lenghtArray/ 2);
  
        return (double)(gradesList.get((lenghtArray-1)/2)+ 
        		gradesList.get(lenghtArray/ 2))/ 2.0;    
	}
	
	public double getAverage() {
		double sum=0.0;
		for(Integer grade :gradesList)
			sum+=(double)grade;
		return sum/gradesList.size();
	}

	public String toString() {
		return examID;
	}
	
	public Series getGraph() {
		final String gradesBetween_0_To_10= "0-10";
		final String gradesBetween_11_To_20= "11-20";
		final String gradesBetween_21_To_30= "21-30";
		final String gradesBetween_31_To_40= "31-40";
		final String gradesBetween_41_To_50= "41-50";
		final String gradesBetween_51_To_60= "51-60";
		final String gradesBetween_61_To_70= "61-70";
		final String gradesBetween_71_To_80= "71-80";
		final String gradesBetween_81_To_90= "81-90";
		final String gradesBetween_91_To_100= "91-100";
		
		XYChart.Series series = new XYChart.Series();
		int counterStudentGrades[]= new int[10];
		Arrays.fill(counterStudentGrades, 0);
		
		for(int grade: gradesList) {
			if(grade>=0 && grade<=10)
				counterStudentGrades[0]++;
			if(grade>=11 && grade<=20)
				counterStudentGrades[1]++;
			if(grade>=21 && grade<=30)
				counterStudentGrades[2]++;
			if(grade>=31 && grade<=40)
				counterStudentGrades[3]++;
			if(grade>=41 && grade<=50)
				counterStudentGrades[4]++;
			if(grade>=51 && grade<=60)
				counterStudentGrades[5]++;
			if(grade>=61 && grade<=70)
				counterStudentGrades[6]++;
			if(grade>=71 && grade<=80)
				counterStudentGrades[7]++;
			if(grade>=81 && grade<=90)
				counterStudentGrades[8]++;
			if(grade>=91 && grade<=100)
				counterStudentGrades[9]++;
			}
		
		series.getData().add(new XYChart.Data(gradesBetween_0_To_10,counterStudentGrades[0]));
		series.getData().add(new XYChart.Data(gradesBetween_11_To_20,counterStudentGrades[1]));
		series.getData().add(new XYChart.Data(gradesBetween_21_To_30,counterStudentGrades[2]));
		series.getData().add(new XYChart.Data(gradesBetween_31_To_40,counterStudentGrades[3]));
		series.getData().add(new XYChart.Data(gradesBetween_41_To_50,counterStudentGrades[4]));
		series.getData().add(new XYChart.Data(gradesBetween_51_To_60,counterStudentGrades[5]));
		series.getData().add(new XYChart.Data(gradesBetween_61_To_70,counterStudentGrades[6]));
		series.getData().add(new XYChart.Data(gradesBetween_71_To_80,counterStudentGrades[7]));
		series.getData().add(new XYChart.Data(gradesBetween_81_To_90,counterStudentGrades[8]));
		series.getData().add(new XYChart.Data(gradesBetween_91_To_100,counterStudentGrades[9]));

		return series;


		

	}
}
	
	

