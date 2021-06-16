package logic.exam;

import java.util.List;

import logic.question.QuestionInExam;

@SuppressWarnings("serial")
public class ComputerizedResults extends ExamResultOfStudent{

	private String computerizedGrade;
	private List<QuestionInExam> questions;
	private String[] studentAnswers;
	private boolean[] isCorrect;
	//from teacher
	private String teacherGrade = "";
	private String teacherComment = "";
	private List<String> comments;

	public ComputerizedResults(String examID, String studentID, String computerizedGrade, String teacherGrade, String teacherComment) {
		super(examID, studentID);
		setComputerizedGrade(computerizedGrade);
		setTeacherGrade(teacherGrade);
		setTeacherComment(teacherComment);
	}

	public List<QuestionInExam> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionInExam> questions) {
		this.questions = questions;
	}

	public String[] getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(String[] studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public void setCommentAtIndex(int index, String comment) {
		this.comments.set(index, comment);
	}

	public boolean[] getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean[] isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getComputerizedGrade() {
		return computerizedGrade;
	}

	public void setComputerizedGrade(String computerizedGrade) {
		this.computerizedGrade = computerizedGrade;
	}

	public String getTeacherGrade() {
		return teacherGrade;
	}

	public void setTeacherGrade(String teacherGrade) {
		if (teacherGrade != null)
			this.teacherGrade = teacherGrade;
	}


	public String getTeacherComment() {
		return teacherComment;
	}

	public void setTeacherComment(String teacherComment) {
		if (teacherComment != null)
			this.teacherComment = teacherComment;
	}

	@Override
	public String getType() {
		return "Computerized";
	}

	@Override
	public String toString() {
		return String.format("{%s, %s, %s, %s}", getExamID(), getStudentID(), getComputerizedGrade(), getTeacherGrade());
	}
}
