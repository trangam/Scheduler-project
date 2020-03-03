/* Course Name: Computer programming
 * Student Name:Thuy Trang Nguyen
 * Class Name: CST8284_300_Object Oriented Java(Programming) 
 * Date:Oct 28 2019
 */
package cst8284.asgmt2.employee;
import java.util.Scanner;

public class Dentist extends Employee {
	Scanner scan = new Scanner(System.in);
  public Dentist(String fullName) {
	this.setName(fullName);
}


	public Dentist() {
	
}
	@Override
	public String getActivityType() {
		
		System.out.println("Enter a category from the following menu:");
		System.out.print("1.Assessment\n2.Filling\n3.Crown \n4.Cosmetic Repair\n");		
		int a = scan.nextInt();
		
		if (a==1)
			return "Assessment";
		 if(a==2)
			return "Filling";
		 if(a==3)
			return "Crown";
		 if(a==4)
			return "Cosmetic Repair";
		else
			return null;
	}

	 
	
}
