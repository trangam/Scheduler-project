/* Course Name: Computer programming
 * Student Name:Thuy Trang Nguyen
 * Class Name: CST8284_300_Object Oriented Java(Programming) 
 * Date:Oct 28 2019
 */
package cst8284.asgtm2.scheduler;

import java.io.Serializable;

public class TelephoneNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	private int areaCode;
	private int prefix;
	private int lineNumber;

	public TelephoneNumber(String phoneNumber) {

		int areaCode = Integer.parseInt(phoneNumber.split("-")[0].trim());
		int prefix = Integer.parseInt(phoneNumber.split("-")[1].trim());
		int lineNumber = Integer.parseInt(phoneNumber.split("-")[2].trim());
		setAreaCode(areaCode);
		setPrefix(prefix);
		setLineNumber(lineNumber);
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getPrefix() {
		return prefix;
	}

	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	// +toString(): String
	public String toString() {
		return "(" + getAreaCode() + ") " + getPrefix() + "-" + getLineNumber();
	}
}
