package logic.exam;

import java.io.Serializable;

/**
 * a class for a student's exam
 * @author Yonatan Rozen
 */
@SuppressWarnings("serial")
public class ExamResultOfStudent implements Serializable{
	// FIELDS ****************************************************************************************
	private String examID;
	private String studentID;
	private String type;

	// CONSTRUCTOR ***********************************************************************************
	public ExamResultOfStudent(String examID, String studentID) {
		this.examID = examID;
		this.studentID = studentID;
	}

	// METHODS ***********************************************************************************
	/**
	 * a method to get the ID of the exam
	 * @return the ExamID of the student's exam(String)
	 */
	public String getExamID() {
		return examID;
	}

	/**
	 * sets ExamID string
	 * @param ExamID string to set
	 */
	public void setExamID(String examID) {
		this.examID = examID;
	}

	/**
	 * a method to get the ID of the student
	 * @return the ID of the student who took the exam(String)
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * sets StudentID string
	 * @param StudentID string to set
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	@Override
	public String toString() {
		return String.format("[%s %s]",getExamID(), getStudentID());
	}

	/**
	 * a method to get the type of the exam (Comp.\Manual)
	 * @return the type of the student's exam(String)
	 */
	public String getType() {
		return type;
	}

	/**
	 * sets type string
	 * @param type string to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
