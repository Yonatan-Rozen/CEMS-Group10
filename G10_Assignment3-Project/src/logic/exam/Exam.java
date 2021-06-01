package logic.exam;

import java.io.Serializable;

public class Exam implements Serializable{
	private String examID;
	private String username;
	private String bankID;
	private String courseID;
	private String allocatedTime;
	//is it necessary?
	private String scores;// " x|y|z "
	private String studentComments;
	private String teacherComments;

	public Exam(String examID, String username, String bankID, String courseID, String allocatedTime, String scores,
			String studentComments, String teacherComments) {
		this.examID = examID;
		this.username = username;
		this.bankID = bankID;
		this.courseID = courseID;
		this.allocatedTime = allocatedTime;
		this.scores = scores;
		this.studentComments = studentComments;
		this.teacherComments = teacherComments;
	}

	public String getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getExamID() {
		return examID;
	}

	public String getTeacherComments() {
		return teacherComments;
	}

	public String getStudentComments() {
		return studentComments;
	}

	public String getCourseID() {
		return courseID;
	}

	public String getBankID() {
		return bankID;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s %s %s",examID,username,bankID,courseID,allocatedTime,scores,studentComments,teacherComments);
	}
}
