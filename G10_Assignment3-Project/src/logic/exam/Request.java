package logic.exam;

import java.io.Serializable;

public class Request implements Serializable
{
	//Request Private Fields
	private String examID;
	private String usernameT;
	private String allocatedTime;
	private String newAllocatedTime;
	//================================================================
	public Request(String examID, String usernameT, String allocatedTime, String newAllocatedTime) 
	{
		this.examID = examID;
		this.usernameT = usernameT;
		this.allocatedTime = allocatedTime;
		this.newAllocatedTime = newAllocatedTime;
	}
	//================================================================
	//Return the ExamID
	public String getExamID() 
	{
		return examID;
	}
	//================================================================
	//Get the usernameT
	public String getUsernameT() {
		return usernameT;
	}
	//================================================================
	//Get the Allocated Time
	public String getAllocatedTime() {
		return allocatedTime;
	}
	//================================================================
	//Get the New Allocated Time
	public String getNewAllocatedTime() {
		return newAllocatedTime;
	}
	//================================================================
	//Set ExamID
	public void setExamID(String examID) {
		this.examID = examID;
	}
	//================================================================
	//Set usernameT
	public void setusernameT(String usernameT) {
		this.usernameT = usernameT;
	}
	//================================================================
	//Set Allocated Time
	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}
	//================================================================
	//Set New Allocated Time
	public void setNewAllocatedTime(String newAllocatedTime) {
		this.newAllocatedTime = newAllocatedTime;
	}
	//================================================================
}
