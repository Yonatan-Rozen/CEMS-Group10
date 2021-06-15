package logic.exam;

import common.MyFile;

@SuppressWarnings("serial")
public class ManualResults extends ExamResultOfStudent{
	
	private MyFile fileSubmit;
	
	public ManualResults(String examID, String studentID, MyFile wordDocument) {
		super(examID, studentID);
		this.fileSubmit = wordDocument;
	}

	public MyFile getExamFile() {
		return fileSubmit;
	}

	public void setExamFile(MyFile examFile) {
		this.fileSubmit = examFile;
	}
	
	@Override
	public String getType() {
		return "Manual";
	}
}
