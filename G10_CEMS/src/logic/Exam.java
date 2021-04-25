package logic;

import java.util.ArrayList;
import java.util.List;

public class Exam {

	private String eID;
	private String eProfession;
	private String eCourse;
	private int eAllocatedTime;
	private List<Integer> scores = new ArrayList<>();
	
	/**
	 * @param eID 		 	 exam ID [format : ##$$%%]  [%% - represents exam number]
	 * @param eProfession	 exam profession [## - represents profession number]
	 * @param eCourse		 exam course [$$ - represents course number]
	 * @param eAllocatedTime allocated time (in minutes)
	 * @param scores		 array of scores. the length represents the amount of questions
	 */
	public Exam(String eID, String eProfession, String eCourse, int eAllocatedTime, String scores)
	{
		setEID(eID);
		setEProfession(eProfession);
		setECourse(eCourse);
		setEAllocatedTime(eAllocatedTime);
		String[] sArr = scores.split("\\|");
		for (String s: sArr)
			this.scores.add(Integer.parseInt(s));
	}
	
	// setters & getters **********************************************************
	
	/**
	 * @return the exam ID
	 */
	public String getEID() {
		return eID;
	}
	/**
	 * @param eID - set exam ID
	 */
	public void setEID(String eID) {
		this.eID = eID;
	}
	
	/**
	 * @return the exam profession name
	 */
	public String getEProfession() {
		return eProfession;
	}
	/**
	 * @param eProfession - set exam profession
	 */
	public void setEProfession(String eProfession) {
		this.eProfession = eProfession;
	}
	
	/**
	 * @return the exam course name
	 */
	public String getECourse() {
		return eCourse;
	}
	/**
	 * @param courseName - set name of exam course
	 */
	public void setECourse(String courseName) {
		this.eCourse = courseName;
	}
	
	/**
	 * @return the exam allocated Time
	 */
	public int getEAllocatedTime() {
		return eAllocatedTime;
	}
	
	/**
	 * @param eAllocatedTime - set exam allocated time (in minutes)
	 */
	public void setEAllocatedTime(int eAllocatedTime) {
		this.eAllocatedTime = eAllocatedTime;
	}
	
	// toString *******************************************************************
	
	@Override
	public String toString() {
		String ret = String.format("ID: %s%nProfession: %s%nCourse: %s%n"
				+ "Allocated Time: %d%nThere are %d questions. "
				+ "These are their scores (in order):%n", eID, eProfession, eCourse,
				eAllocatedTime, scores.size());
		int index = 1;
		for (Integer sc : scores)
			ret += String.format("%d) %d%n", index++, sc);
		return ret;
	}
	// End of Exam ****************************************************************
	
	//toString example
	public static void main(String[] args) {
		Exam examInfo = new Exam("010201", "Math", "Algebra 2", 120, "50|40|10");
		System.out.println(examInfo);
	}
}
