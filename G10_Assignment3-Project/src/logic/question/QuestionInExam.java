package logic.question;

import java.io.Serializable;
/**
 * Represent Question in Exam.
 * @author Tuval Zitelbach, Meitar ElEzrea, Michael Malka 
 */
@SuppressWarnings("serial")
public class QuestionInExam extends Question implements Serializable {

	//QuestionInExam Private Fields.
	private String questionScore;
	private String examID;
	//==========================================================================================
	//Constructor
	public QuestionInExam(String questionID, String questionBody, String answer1, String answer2, String answer3,
			String answer4, String correctAnswer, String author, String questionScore, String examID) {
		super(questionID, questionBody, answer1, answer2, answer3, answer4, correctAnswer, author);
		this.questionScore = questionScore;
		this.examID = examID;
	}
	//==========================================================================================
	//Return the Question in the exam as String.
	@Override
	public String toString() {
		return String.format(super.toString() + " extends "+"%s %s",examID, questionScore);
	}
	//==========================================================================================
	//Get The Exam ID
	public String getExamID() {
		return examID;
	}
	//==========================================================================================
	//Set The Exam ID
	public void setExamID(String examID) {
		this.examID = examID;
	}
	//==========================================================================================
	//Get The Question Score
	public String getQuestionScore() {
		return questionScore;
	}
	//==========================================================================================
	//Set The Question Score
	public void setQuestionScore(String questionScore) {
		this.questionScore = questionScore;
	}
}