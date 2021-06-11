package logic.exam;

@SuppressWarnings("serial")
public class ManualExam extends Exam{
	
	public ManualExam(String examID, String bankID, String courseID, String allocatedTime, String author, String type) {
		super(examID, bankID, courseID, allocatedTime, author, type);
	}

	@Override
	public String toString() {
		return super.toString()+" Manual";
	}
	public String getTeacherComments() {
		return "";
	}

	public String getStudentComments() {
		return "";
	}

}
