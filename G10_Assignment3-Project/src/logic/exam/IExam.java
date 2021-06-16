package logic.exam;

public interface IExam {
	public String getAllocatedTime();
	public void setAllocatedTime(String allocatedTime);
	public String getExamID();
	public String getCourseID() ;
	public String getBankID() ;
	public String getAuthor() ;
	public String getType() ;
	public String getNumtotalStudents();
	public void setNumtotalStudents(String NumtotalStudents);
	public String getNumSubmitted_1();
	public void setNumSubmitted_1(String NumSubmitted_1);
	public String getNumSubmitted_0();
	public void setNumSubmitted_0(String NumSubmitted_0);
	public String getDuration() ;
	public void setDuration(String duration) ;
	public String getDate() ;
	public void setDate(String date) ;
	public String getSubjectID() ;
	public void setSubjectID(String subjectID) ;
}
