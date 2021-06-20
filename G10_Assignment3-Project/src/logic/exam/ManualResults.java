package logic.exam;

import common.MyFile;
/**
 * An extension class of ExamResultOfStudent : describes a Manual exam taken by a student
 * @author Yonatan Rozen
 *
 */
@SuppressWarnings("serial")
public class ManualResults extends ExamResultOfStudent{
	// FIELDS ****************************************************************************************
	private MyFile fileSubmit;

	// CONSTRUCTOR ***********************************************************************************
	public ManualResults(String examID, String studentID, MyFile wordDocument) {
		super(examID, studentID);
		this.fileSubmit = wordDocument;
	}

	// METHODS ***********************************************************************************
	/**
	 * gets the exam done by student
	 * @return MyFile the finished exam ny student's form
	 */
	public MyFile getExamFile() {
		return fileSubmit;
	}

	/**
	 * sets to the DB the exam file submitted by student
	 * @param examFile the exam file done by student
	 */
	public void setExamFile(MyFile examFile) {
		this.fileSubmit = examFile;
	}

	/**
	 * a method to get the type of the exam (Comp.\Manual)
	 * @return the type of the student's exam(String)
	 */
	@Override
	public String getType() {
		return "Manual";
	}
}
