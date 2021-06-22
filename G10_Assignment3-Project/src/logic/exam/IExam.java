package logic.exam;
/**
 * Represent The Interface for all the types of exams.
 * @author Tuval Zitelbach, Meitar Elezra, Michael Malka
 */
public interface IExam
{
	//Get Allocated Time
	public String getAllocatedTime();
	//================================================================
	//Set Allocated Time
	public void setAllocatedTime(String allocatedTime);
	//================================================================
	//Get ExamID
	public String getExamID();
	//================================================================
	//Get Course ID
	public String getCourseID();
	//================================================================
	//Get Bank ID
	public String getBankID();
	//================================================================
	//Get Author
	public String getAuthor();
	//================================================================
	//Get Type
	public String getType() ;
	//================================================================
	//Get Number of total Students
	public String getNumtotalStudents();
	//================================================================
	//Set the Number of total Students.
	public void setNumtotalStudents(String NumtotalStudents);
	//================================================================
	//Get Number of student who submitted the exam.
	public String getNumSubmitted_1();
	//================================================================
	//Set Number of student who submitted the exam.
	public void setNumSubmitted_1(String NumSubmitted_1);
	//================================================================
	//Get Number of student who didn't submit the exam.
	public String getNumSubmitted_0();
	//================================================================
	//Set Number of student who didn't submit the exam.
	public void setNumSubmitted_0(String NumSubmitted_0);
	//================================================================
	//Get Duration
	public String getDuration();
	//================================================================
	//Set Duration
	public void setDuration(String duration);
	//================================================================
	//Get Date
	public String getDate();
	//================================================================
	//Set Date
	public void setDate(String date);
	//================================================================
	//Get Subject ID
	public String getSubjectID();
	//================================================================
	//Set Subject ID
	public void setSubjectID(String subjectID);
}
