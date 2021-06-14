package logic.exam;

@SuppressWarnings("serial")
public class ManualExam extends Exam{
	private String teacherComments="";
	private String studentComments="";

	public String getTeacherComments() {
		return teacherComments;
	}

	public String getStudentComments() {
		return studentComments;
	}

	//Blob file;
	public ManualExam(String examID, String bankID, String courseID, String allocatedTime, String author, String type/*, Blob file*/) {
		super(examID, bankID, courseID, allocatedTime, author, type);
		//this.file = file;
	}

	@Override
	public String toString() {
		return super.toString()+" Manual";
	}
}
