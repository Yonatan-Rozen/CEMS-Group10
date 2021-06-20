package logic.exam;

/**
 * A class to display a line in the table of a student's exams results, extension to ExamResultOfStudent
 * @author Michael Malka
 */
@SuppressWarnings("serial")
public class ExamResultsTableStudent extends ExamResultOfStudent{
	// FIELDS ****************************************************************************************
	private String courseName;
	private String grade;

	// CONSTRUCTOR ***********************************************************************************
	public ExamResultsTableStudent(String examID, String studentID,String grade,String courseName) {
		super(examID, studentID);
		this.grade=grade;
		this.courseName=courseName;	}

	// METHODS ***********************************************************************************
	/**
	 * a method to get the student's Grade of the comp. exam
	 * @return the grade of the student in the exam (String)
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * sets Grade string
	 * @param Grade string to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * a method to get the CourseName of the comp. exam
	 * @return the CourseName of the exam (String)
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * sets CourseName string
	 * @param CourseName string to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
