package logic.question;

import java.io.Serializable;

/**
 * Represent Question
 * @author Tuval Zitelbach, Meitar ElEzrea, Michael Malka 
 */
@SuppressWarnings("serial")
public class Question implements Serializable {

	//Question Private Fields.
	private String questionID;
	private String questionBody;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String correctAnswer;
	private String author;
	//==========================================================================================
	//Constructor
	public Question(String questionID, String questionBody, String answer1, String answer2, String answer3, String answer4, String correctAnswer,String author) {
		this.questionID = questionID;
		//bankID="";
		this.questionBody = questionBody;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correctAnswer = correctAnswer;
		this.author=author;
	}
	//==========================================================================================
	//Get the Question ID
	public String getQuestionID() {
		return questionID;
	}
	//==========================================================================================
	//Set the Question ID
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	//==========================================================================================
	//Get The Question Body
	public String getQuestionBody() {
		return questionBody;
	}
	//==========================================================================================
	//Set The Question Body
	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}
	//==========================================================================================
	//Get The First Answer
	public String getAnswer1() {
		return answer1;
	}
	//==========================================================================================
	//Set The First Answer
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	//==========================================================================================
	//Get The Second Answer
	public String getAnswer2() {
		return answer2;
	}
	//==========================================================================================
	//Set The Second Answer
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	//==========================================================================================
	//Get The Third Answer
	public String getAnswer3() {
		return answer3;
	}
	//==========================================================================================
	//Set The Third Answer
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	//==========================================================================================
	//Get The Fourth Answer
	public String getAnswer4() {
		return answer4;
	}
	//==========================================================================================
	//Set The Fourth Answer
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	//==========================================================================================
	//Get The Correct Answer
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	//==========================================================================================
	//Set The Correct Answer
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	//==========================================================================================
	//Get The Author Of The Exam
	public String getAuthor() {
		return author;
	}
	//==========================================================================================
	//Set The Author Of The Exam
	public void setAuthor(String author) {
		this.author = author;
	}
	//==========================================================================================
	//Return the Question as String.
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s %s %s", questionID,questionBody,answer1,answer2,answer3,answer4,correctAnswer,author);
	}


}
