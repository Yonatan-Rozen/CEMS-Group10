package logic.question;

import java.io.Serializable;

public class QuestionInExam extends Question implements Serializable {

	private String questionID;
	private String questionScore;
	private String questionBody;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String correctAnswer;
	private String author;

	public QuestionInExam(String questionID, String questionBody, String answer1, String answer2, String answer3,
			String answer4, String correctAnswer, String author, String questionScore) {
		super(questionID, questionBody, answer1, answer2, answer3, answer4, correctAnswer, author);
		this.questionScore = questionScore;
	}

	@Override
	public String toString() {
		return String.format("%s %s", questionID, questionScore);
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(String questionScore) {
		questionScore = questionScore;
	}
}