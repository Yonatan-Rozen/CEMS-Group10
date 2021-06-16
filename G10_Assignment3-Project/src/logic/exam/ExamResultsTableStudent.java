package logic.exam;

/**
 * 
 * @author Michael Malka
 */
public class ExamResultsTableStudent extends ExamResultOfStudent{
	
	private String grade;
	//private String path;
	
	public ExamResultsTableStudent(String examID, String studentID,String grade) {
		super(examID, studentID);
		this.grade=grade;
		//*******this.path=path;
		// TODO Auto-generated constructor stub
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

//	public String getPath() {
//		return path;
//	}
//
//	public void setPath(String path) {
//		this.path = path;
//	}

}
