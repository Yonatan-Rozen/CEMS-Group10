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

	public Exam(String examID, String bankID, String courseID, String allocatedTime, String author,String type) {
		this.examID = examID;
		this.author = author;
		this.type = type;
		this.bankID = bankID;
		this.courseID = courseID;
		this.allocatedTime = allocatedTime;

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
	public String toString() {
		return String.format("%s %s %s %s %s %s ",examID,bankID,courseID,allocatedTime,author,type);
	}
}
