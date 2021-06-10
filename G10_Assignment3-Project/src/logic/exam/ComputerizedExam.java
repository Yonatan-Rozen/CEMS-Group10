package logic.exam;

@SuppressWarnings("serial")
public class ComputerizedExam extends Exam {

	private String studentComments;
	private String teacherComments;
	private String scores;// " x|y|z "


	public ComputerizedExam(String examID, String bankID, String courseID, String allocatedTime, String scores,
			String studentComments, String teacherComments, String author, String type) {
		super(examID, bankID, courseID, allocatedTime, author, type);
		this.scores = scores;
		this.studentComments = studentComments;
		this.teacherComments = teacherComments;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getTeacherComments() {
		return teacherComments;
	}

	public String getStudentComments() {
		return studentComments;
	}

	@Override
	public String toString() {
		return super.toString()+String.format("%s %s %s",scores,studentComments,teacherComments);
	}
}
