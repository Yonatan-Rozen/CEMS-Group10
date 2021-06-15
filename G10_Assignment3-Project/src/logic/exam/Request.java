package logic.exam;

import java.io.Serializable;

public class Request implements Serializable
{
	//Request Private Fields
	private String examID;
	private String author;
	private String allocatedTime;
	private String newAllocatedTime;
	private String principle;
	//================================================================
	public Request(String examID, String author, String allocatedTime, String newAllocatedTime,String principle) 
	{
		this.examID = examID;
		this.author = author;
		this.allocatedTime = allocatedTime;
		this.newAllocatedTime = newAllocatedTime;
		this.principle = principle;
	}
	//================================================================
	//Return the ExamID
	public String getExamID() 
	{
		return examID;
	}
	//================================================================
	//Get the Author
	public String getAuthor() {
		return author;
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
	//Get the New Allocated Time
	public String getPrinciple() {
		return principle;
	}
	//================================================================
	//Set ExamID
	public void setExamID(String examID) {
		this.examID = examID;
	}
	//================================================================
	//Set Author
	public void setAuthor(String author) {
		this.author = author;
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
	//Set Principle
	public void setPrinciple(String principle) {
		this.principle = principle;
	}
}
