/* Course Name: Computer programming
 * Student Name:Thuy Trang Nguyen
 * Class Name: CST8284_300_Object Oriented Java(Programming) 
 * Date:Oct 28 2019
 */
package cst8284.asgmt2.employee;

import java.util.Scanner;

public abstract class Employee {
	private String fullName;
	
	protected Employee() {this("unknown");}
	protected Employee(String fullName) {setName(fullName);}
	protected static Scanner scan = new Scanner(System.in);
	
	public void setName(String fullName) {this.fullName = fullName;}
	public String getName() {return fullName;}
	
	public abstract String getActivityType();
	
		@Override
	public String toString() {return getName();}
	
}