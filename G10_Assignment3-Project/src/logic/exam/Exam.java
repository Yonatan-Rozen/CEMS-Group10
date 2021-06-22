package logic.exam;

import java.io.Serializable;
/**
 *  An implementation class of Exam : describes an exam in the CEMS system
 * @author Michael Malka and Meitar El Ezra and Tuval Zitelbach
 *
 */
@SuppressWarnings("serial")
public class Exam implements Serializable,IExam{
	// FIELDS ****************************************************************************************
	private String examID;
	private String author;
	private String bankID;
	private String courseID;
	private String subjectID;
	private String allocatedTime;
	private String type;
	private String numtotalStudents;
	private String numSubmitted_1; // submitted student
	private String numSubmitted_0; // non submitted students
	private String duration;
	private String date;

	// CONSTRUCTOR ***********************************************************************************
	public Exam(String examID, String bankID, String courseID, String allocatedTime,
			String author,String type,String subjectID) {
		this.examID = examID;
		this.author = author;
		this.type = type;
		this.bankID = bankID;
		this.courseID = courseID;
		this.allocatedTime = allocatedTime;
		this.subjectID = subjectID;
	}

	// METHODS ***********************************************************************************
	/**
	 * a method to get the duration of the exam of student
	 * @return the duration of the exam of student(String)
	 */
	@Override
	public String getDuration() {
		return duration;
	}

	/**
	 * sets duration string
	 * @param duration string to set
	 */
	@Override
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * a method to get the AllocatedTime of the exam
	 * @return the AllocatedTime of the exam (String)
	 */
	@Override
	public String getAllocatedTime() {
		return allocatedTime;
	}

	/**
	 * sets AllocatedTime string
	 * @param AllocatedTime string to set
	 */
	@Override
	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	/**
	 * a method to get the ExamID of the exam
	 * @return the ExamID of the exam (String)
	 */
	@Override
	public String getExamID() {
		return examID;
	}

	/**
	 * a method to get the CourseID of the exam
	 * @return the CourseID of the exam (String)
	 */
	@Override
	public String getCourseID() {
		return courseID;
	}

	/**
	 * a method to get the BankID of the exam
	 * @return the BankID of the exam (String)
	 */
	@Override
	public String getBankID() {
		return bankID;
	}

	/**
	 * a method to get the Author of the exam
	 * @return the Author(teacher who wrote it) of the exam (String)
	 */
	@Override
	public String getAuthor() {
		return author;
	}

	/**
	 * a method to get the Type of the exam
	 * @return the Type (Manual/Computerized) of the exam (String)
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * a method to get the amount of student who took the exam
	 * @return the NumtotalStudents of the exam (String)
	 */
	@Override
	public String getNumtotalStudents() {
		return numtotalStudents;
	}

	/**
	 * sets NumtotalStudents string
	 * @param NumtotalStudents string to set
	 */
	@Override
	public void setNumtotalStudents(String numtotalStudents) {
		this.numtotalStudents = numtotalStudents;
	}

	/**
	 * a method to get the amount of student who took the exam and submitted it by themseles
	 * @return the NumSubmitted_1 of the exam (String)
	 */
	@Override
	public String getNumSubmitted_1() {
		return numSubmitted_1;
	}

	/**
	 * sets NumSubmitted_1 string
	 * @param NumSubmitted_1 string to set
	 */
	@Override
	public void setNumSubmitted_1(String numSubmitted_1) {
		this.numSubmitted_1 = numSubmitted_1;
	}

	/**
	 * a method to get the amount of student who took the exam and submitted automathically (LOCK/TIME ENDED)
	 * @return the NumSubmitted_0 of the exam (String)
	 */
	@Override
	public String getNumSubmitted_0() {
		return numSubmitted_0;
	}

	/**
	 * sets NumSubmitted_0 string
	 * @param NumSubmitted_0 string to set
	 */
	@Override
	public void setNumSubmitted_0(String numSubmitted_0) {
		this.numSubmitted_0 = numSubmitted_0;
	}

	/**
	 * a method to get the Date of the exam
	 * @return the Date of the exam(String)
	 */
	@Override
	public String getDate() {
		return date;
	}

	/**
	 * sets Date string of exam
	 * @param Date string to set
	 */
	@Override
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * a method to get the SubjectID of the exam
	 * @return the SubjectID of the exam(String)
	 */
	@Override
	public String getSubjectID() {
		return subjectID;
	}

	/**
	 * sets SubjectID string of exam
	 * @param SubjectID string to set
	 */
	@Override
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	/**
	 * Override methos of Object's ToString
	 */
	@Override
	public String toString() {
		return String.format("examID=%s\n bankID=%s\n courseID=%s\n allocatedTime=%s\n author=%s\n type=%s\n numtotalStudents=%s\n numSubmitted_1 =%s\n numSubmitted_0 =%s\n ",examID,bankID,courseID,allocatedTime,author,type,date,duration,numtotalStudents,numSubmitted_1,numSubmitted_0);
	}
}
