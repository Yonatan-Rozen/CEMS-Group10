package logic.exam;

import java.util.List;

import logic.question.QuestionInExam;
/**
 * An extension class of ExamResultOfStudent : describes a computerized exam taken by a student
 * @author Yonatan Rozen
 *
 */
@SuppressWarnings("serial")
public class ComputerizedResults extends ExamResultOfStudent{
	// FIELDS ****************************************************************************************
	private String computerizedGrade;
	private List<QuestionInExam> questions;
	private String[] studentAnswers;
	private boolean[] isCorrect;
	//from teacher
	private String teacherGrade = "";
	private String teacherComment = "";
	private List<String> comments;

	// CONSTRUCTOR ***********************************************************************************
	public ComputerizedResults(String examID, String studentID, String computerizedGrade, String teacherGrade, String teacherComment) {
		super(examID, studentID);
		setComputerizedGrade(computerizedGrade);
		setTeacherGrade(teacherGrade);
		setTeacherComment(teacherComment);
	}

	// METHODS ***********************************************************************************
	/**
	 * a method to get the List of Question In the Exam
	 * @return  List<QuestionInExam> of the student's exam
	 */
	public List<QuestionInExam> getQuestions() {
		return questions;
	}

	/**
	 * sets QuestionInExam List
	 * @param List<QuestionInExam> to set
	 */
	public void setQuestions(List<QuestionInExam> questions) {
		this.questions = questions;
	}

	/**
	 * a method to get the student's answers to the Question In the Exam
	 * @return String[] of the student's answers in the exam
	 */
	public String[] getStudentAnswers() {
		return studentAnswers;
	}

	/**
	 * sets StudentAnswers String[]
	 * @param String[] studentAnswers to set
	 */
	public void setStudentAnswers(String[] studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	/**
	 * a method to get the the student's comments in the checked exam
	 * @return List<String> of the student's comments in the checked exam
	 */
	public List<String> getComments() {
		return comments;
	}

	/**
	 * sets Comments List
	 * @param List<Comments> to set
	 */
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	/**
	 * sets a comment in the index given in the Comments List
	 * @param Comment to set int the comments list
	 * @param index to set the comment in
	 */
	public void setCommentAtIndex(int index, String comment) {
		this.comments.set(index, comment);
	}

	/**
	 * a method to get the indication to the degree to ehich the student did well in the exam (what questions
	 * did he answer correctly and which not)
	 * @return boolean[] of the student's answers : TRUE if he was correct in his answers, otherwise FALSE
	 */
	public boolean[] getIsCorrect() {
		return isCorrect;
	}

	/**
	 * sets isCorrect boolean[]
	 * @param boolean[] isCorrect to set
	 */
	public void setIsCorrect(boolean[] isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * a method to get the Grade of the student's Computerized exam (automatic grade calculated)
	 * @return ComputerizedGrade of the student's exam(String)
	 */
	public String getComputerizedGrade() {
		return computerizedGrade;
	}

	/**
	 * sets computerizedGrade string
	 * @param computerizedGrade string to set
	 */
	public void setComputerizedGrade(String computerizedGrade) {
		this.computerizedGrade = computerizedGrade;
	}

	/**
	 * a method to get the Grade of the student's Computerized exam (final grade decided by teacher)
	 * @return TeacherGrade of the student's exam(String)
	 */
	public String getTeacherGrade() {
		return teacherGrade;
	}

	/**
	 * sets teacherGrade string
	 * @param teacherGrade string to set
	 */
	public void setTeacherGrade(String teacherGrade) {
		if (teacherGrade != null)
			this.teacherGrade = teacherGrade;
	}

	/**
	 * a method to get the Teacher's Comment In the Exam
	 * @return TeacherComment of the student's exam(String)
	 */
	public String getTeacherComment() {
		return teacherComment;
	}

	/**
	 * sets teacherComment string
	 * @param teacherComment string to set
	 */
	public void setTeacherComment(String teacherComment) {
		if (teacherComment != null)
			this.teacherComment = teacherComment;
	}

	/**
	 * a method to get the type of the exam (Comp.\Manual)
	 * @return the type of the student's exam(String)
	 */
	@Override
	public String getType() {
		return "Computerized";
	}

	/**
	 * Override methos of Object's ToString
	 */
	@Override
	public String toString() {
		return String.format("{%s, %s, %s, %s}", getExamID(), getStudentID(), getComputerizedGrade(), getTeacherGrade());
	}
}
