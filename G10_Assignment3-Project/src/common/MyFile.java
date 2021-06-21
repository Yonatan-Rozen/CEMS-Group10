package common;

import java.io.Serializable;

/**
 * MyFile helps store the info about a file either when 
 * uploading it to OR downloading it from the database
 */
@SuppressWarnings("serial")
public class MyFile implements Serializable {

	private String Description = null;
	private String fileName = null;
	private int size = 0;
	public byte[] mybytearray;

	/**
	 * Creates a byte array of a specified size
	 * @param size The size of the created byte array
	 */
	public void initArray(int size) {
		mybytearray = new byte[size];
	}

	/**
	 * Sets the specified name of the file
	 * @param fileName The name of the file
	 */
	public MyFile(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the name of the file
	 * @return The name of the file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the specified name of the file (change name)
	 * @param fileName The name of the file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the size of the file
	 * @return size of the file
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size of the file 
	 * @param size The size of the file
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the byte array that stores the info of the file
	 * @return The byte array
	 */
	public byte[] getMybytearray() {
		return mybytearray;
	}

	/**
	 * Gets byte i-th from the byte array
	 * @param i
	 * @return The i-th byte from the array
	 */
	public byte getMybytearray(int i) {
		return mybytearray[i];
	}

	/**
	 * Sets the byte array with the info of the file
	 * @param mybytearray The byte array that contains the info of the file
	 */
	public void setMybytearray(byte[] mybytearray) {

		for (int i = 0; i < mybytearray.length; i++)
			this.mybytearray[i] = mybytearray[i];
	}

	/**
	 * Gets the description of the file
	 * @return The description of the file
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Sets the description of the file
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}
}
