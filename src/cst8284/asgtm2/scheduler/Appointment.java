/* Course Name: Computer programming
 * Student Name:Thuy Trang Nguyen
 * Class Name: CST8284_300_Object Oriented Java(Programming) 
 * Date:Oct 28 2019
 */
package cst8284.asgtm2.scheduler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Appointment implements Serializable {
	private Calendar aptDate = Calendar.getInstance();
	private String firstName;
	private String lastName;
	private TelephoneNumber phone;
	private Activity activity;
	public static final long serialVersionUID = 1L;

	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity activity) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, activity);

	}

	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity activity) {

		setFirstName(firstName.trim());
		setLastName(lastName.trim());
		setCalendar(cal);
		setPhone(phone);
		setActivity(activity);
	}

	public void setCalendar(Calendar cal) {
		this.aptDate = cal;
	}

	public Calendar getCalendar() {
		return aptDate;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setPhone(TelephoneNumber phone) {
		this.phone = phone;
	}

	public TelephoneNumber getPhone() {
		return phone;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Activity getActivity() {
		return activity;
	}

	// +toString(): String
	
	public String toString() {

		SimpleDateFormat formatDate = new SimpleDateFormat("EEE MMM dd YYYY kk:mm ");	
		return 
			   formatDate.format(getCalendar().getTime()) + "\n" +
			   getFirstName() + " " + getLastName() + "\n" + 
			   getPhone().toString() + "\n" +
			   getActivity().toString();
	}
}
