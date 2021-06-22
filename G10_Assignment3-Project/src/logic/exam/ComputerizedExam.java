package logic.exam;

/**
 * An extension class of Exam : describes a computerized exam
 * @author Michael Malka and Meitar El Ezra and Tuval Zitelbach
 *
 */
@SuppressWarnings("serial")
public class ComputerizedExam extends Exam {
	// FIELDS ****************************************************************************************
	private String studentComments;
	private String teacherComments;
	private String scores;// " x|y|z "

	// CONSTRUCTOR ***********************************************************************************
	public ComputerizedExam(String examID, String bankID, String courseID, String allocatedTime, String scores,
			String studentComments, String teacherComments, String author, String type,String subjectID) {
		super(examID, bankID, courseID, allocatedTime, author, type, subjectID);
		this.scores = scores;
		this.studentComments = studentComments;
		this.teacherComments = teacherComments;
	}

	// METHODS ***********************************************************************************
	/**
	 * a method to get the questions' scores of the exam
	 * @return the scores of the exam's questions (String)
	 */
	public String getScores() {
		return scores;
	}

	/**
	 * sets scores string
	 * @param scores string to set
	 */
	public void setScores(String scores) {
		this.scores = scores;
	}

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
		return super.toString()+String.format("%s %s\n\n",studentComments,teacherComments);
	}
}
