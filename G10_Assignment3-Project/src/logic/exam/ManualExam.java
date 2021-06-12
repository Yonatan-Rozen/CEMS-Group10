package logic.exam;

@SuppressWarnings("serial")
public class ManualExam extends Exam{
	
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
