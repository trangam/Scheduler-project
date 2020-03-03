/* Course Name: Computer programming
 * Student Name:Thuy Trang Nguyen
 * Class Name: CST8284_300_Object Oriented Java(Programming) 
 * Date:Oct 28 2019
 */
package cst8284.asgtm2.scheduler;

import cst8284.asgmt2.employee.Dentist;


public class SchedulerLauncher {
	
	public static void main(String[] args) {
		Scheduler sc= new Scheduler(new Dentist());
		sc.launch();
        
	}
}
