/* Course Name: Computer programming
 * Student Name:Thuy Trang Nguyen
 * Class Name: CST8284_300_Object Oriented Java(Programming) 
 * Date:Oct 28 2019
 */
package cst8284.asgtm2.scheduler;

import java.io.Serializable;

public class Activity  implements  Serializable {

	private static final long serialVersionUID = 1L;
	private String descriptionOfWork;	
	private String category;
	
	public Activity(String description, String category) {

		setDescription(description);
		setCategory(category);
	}

	
	public String getDescription() {return descriptionOfWork;}
	public void setDescription(String description) {this.descriptionOfWork = description;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	
	public String toString() {return getCategory()+"\n"+ getDescription();}
}
