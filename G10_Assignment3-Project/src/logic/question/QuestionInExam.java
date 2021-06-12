package logic.question;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QuestionInExam extends Question implements Serializable {

	private String questionScore;
	private String examID;

	public QuestionInExam(String questionID, String questionBody, String answer1, String answer2, String answer3,
			String answer4, String correctAnswer, String author, String questionScore, String examID) {
		super(questionID, questionBody, answer1, answer2, answer3, answer4, correctAnswer, author);
		this.questionScore = questionScore;
		this.examID = examID;
	}

	@Override
	public String toString() {
		return String.format(super.toString() + " extends "+"%s %s",examID, questionScore);
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(String questionScore) {
		this.questionScore = questionScore;
	}

}