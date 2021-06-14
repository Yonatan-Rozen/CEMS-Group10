package logic.exam;

import java.io.Serializable;

/**
 * 
 * @author Yonatan Rozen
 */
@SuppressWarnings("serial")
public class ExamResultOfStudent implements Serializable{

	private String examID;
	private String studentID;
	private String type;

	public ExamResultOfStudent(String examID, String studentID) {
		this.examID = examID;
		this.studentID = studentID;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	@Override
	public String toString() {
		return String.format("[%s %s]",getExamID(), getStudentID());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
