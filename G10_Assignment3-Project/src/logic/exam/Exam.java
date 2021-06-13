package logic.exam;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Exam implements Serializable,IExam{
	private String examID;
	private String author;
	private String bankID;
	private String courseID;
	private String allocatedTime;
	private String type;
	private String numtotalStudents;
	private String numSubmitted_1; // submitted student
	private String numSubmitted_0; // non submitted students
	private String duration;
	private String date;

	public Exam(String examID, String bankID, String courseID, String allocatedTime, String author,String type) {
		this.examID = examID;
		this.author = author;
		this.type = type;
		this.bankID = bankID;
		this.courseID = courseID;
		this.allocatedTime = allocatedTime;

	}

	@Override
	public String getDuration() {
		return duration;
	}

	@Override
	public void setDuration(String duration) {
		this.duration = duration;
	}


	@Override
	public String getAllocatedTime() {
		return allocatedTime;
	}

	@Override
	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	@Override
	public String getExamID() {
		return examID;
	}

	@Override
	public String getCourseID() {
		return courseID;
	}

	@Override
	public String getBankID() {
		return bankID;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getNumtotalStudents() {
		return numtotalStudents;
	}

	@Override
	public void setNumtotalStudents(String numtotalStudents) {
		this.numtotalStudents = numtotalStudents;
	}

	@Override
	public String getNumSubmitted_1() {
		return numSubmitted_1;
	}

	@Override
	public void setNumSubmitted_1(String numSubmitted_1) {
		this.numSubmitted_1 = numSubmitted_1;
	}

	@Override
	public String getNumSubmitted_0() {
		return numSubmitted_0;
	}

	@Override
	public void setNumSubmitted_0(String numSubmitted_0) {
		this.numSubmitted_0 = numSubmitted_0;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public void setDate(String date) {
		// TODO Auto-generated method stub
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("examID=%s\n bankID=%s\n courseID=%s\n allocatedTime=%s\n author=%s\n type=%s\n numtotalStudents=%s\n numSubmitted_1 =%s\n numSubmitted_0 =%s\n ",examID,bankID,courseID,allocatedTime,author,type,date,duration,numtotalStudents,numSubmitted_1,numSubmitted_0);
	}
}
