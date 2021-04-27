package logic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Exam {

	private SimpleStringProperty eID;
	private SimpleStringProperty eProfession;
	private SimpleStringProperty eCourse;
	private SimpleIntegerProperty eAllocatedTime;
	private SimpleStringProperty eScores;
	//private SimpleListProperty<Integer> hi = new SimpleListProperty<>();
	//private List<Integer> scores = new ArrayList<>();
	
	/**
	 * @param eID 		 	 exam ID [format : ##$$%%]  [%% - represents exam number]
	 * @param eProfession	 exam profession [## - represents profession number]
	 * @param eCourse		 exam course [$$ - represents course number]
	 * @param eAllocatedTime allocated time (in minutes)
	 * @param scores		 array of scores. the length represents the amount of questions
	 */
	public Exam(String eID, String eProfession, String eCourse, int eAllocatedTime, String eScores)
	{
		this.eID = new SimpleStringProperty(eID);
		this.eProfession = new SimpleStringProperty(eProfession);
		this.eCourse = new SimpleStringProperty(eCourse);
		this.eAllocatedTime = new SimpleIntegerProperty(eAllocatedTime);
		this.eScores = new SimpleStringProperty(eScores);
		
		
		//String[] sArr = scores.split("\\|");
		//for (String s: sArr)
			//this.scores.add(Integer.parseInt(s));
	}
	
	// setters & getters **********************************************************
	
	/**
	 * @return the exam ID
	 */
	public String getEID() {
		return eID.get();
	}
	/**
	 * @param eID - set exam ID
	 */
	public void setEID(String eID) {
		this.eID.set(eID);
	}
	
	/**
	 * @return the exam profession name
	 */
	public String getEProfession() {
		return eProfession.get();
	}
	/**
	 * @param eProfession - set exam profession
	 */
	public void setEProfession(String eProfession) {
		this.eProfession.set(eProfession);
	}
	
	/**
	 * @return the exam course name
	 */
	public String getECourse() {
		return eCourse.get();
	}
	/**
	 * @param courseName - set name of exam course
	 */
	public void setECourse(String courseName) {
		this.eCourse.set(courseName);
	}
	
	/**
	 * @return the exam allocated Time
	 */
	public int getEAllocatedTime() {
		return eAllocatedTime.get();
	}
	
	/**
	 * @param eAllocatedTime - set exam allocated time (in minutes)
	 */
	public void setEAllocatedTime(int eAllocatedTime) {
		this.eAllocatedTime.set(eAllocatedTime);
	}

	public String getEScores() {
		return eScores.get();
	}

	public void setEScores(String eScores) {
		this.eScores.set(eScores);
	}
	
	
	// toString *******************************************************************

	@Override
	public String toString() {
		return String.format("ID: %s%nProfession: %s%nCourse: %s%n"
				+ "Allocated Time: %d%nScores (in order): {%s}", 
				getEID(), getEProfession(), getECourse(), getEAllocatedTime(), getEScores());
	}
	// End of Exam ****************************************************************
	
	//toString example
	public static void main(String[] args) {
		Exam examInfo = new Exam("010201", "Math", "Algebra 2", 120, "50|40|10");
		System.out.println(examInfo);
	}
}
