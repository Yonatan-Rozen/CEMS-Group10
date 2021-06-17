package logic.exam;

/**
 *
 * @author Michael Malka
 */
@SuppressWarnings("serial")
public class ExamResultsTableStudent extends ExamResultOfStudent{
	private String courseName;
	private String grade;
	//private String path;

	public ExamResultsTableStudent(String examID, String studentID,String grade,String courseName) {
		super(examID, studentID);
		this.grade=grade;
		//*******this.path=path;
		this.courseName=courseName;	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	//	public String getPath() {
	//		return path;
	//	}
	//
	//	public void setPath(String path) {
	//		this.path = path;
	//	}

}
