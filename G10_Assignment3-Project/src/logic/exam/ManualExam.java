package logic.exam;


/**
 * An extension class of Exam : describes a manual exam
 * @author Michael Malka and Meitar El Ezra and Tuval Zitelbach
 *
 */
@SuppressWarnings("serial")
public class ManualExam extends Exam{
	// FIELDS ****************************************************************************************
	private String teacherComments="";
	private String studentComments="";

	// CONSTRUCTOR ***********************************************************************************
	public ManualExam(String examID, String bankID, String courseID, String allocatedTime, String author, String type,String subjectID/*, Blob file*/) {
		super(examID, bankID, courseID, allocatedTime, author, type,subjectID);
	}

	// METHODS ***********************************************************************************
	/**
	 * a method to get the comments for examining teacher
	 * @return the teacher's comments for the examining teacher (String)
	 */
	public String getTeacherComments() {
		return teacherComments;
	}

	/**
	 * a method to get the comments for the examinees
	 * @return the teacher's comments for the examnees (String)
	 */
	public String getStudentComments() {
		return studentComments;
	}

	/**
	 * Override methos of Object's ToString
	 */
	@Override
	public String toString() {
		return super.toString()+" Manual";
	}
}
