/* Course Name: Computer programming
 * Student Name:Thuy Trang Nguyen
 * Class Name: CST8284_300_Object Oriented Java(Programming) 
 * Date:Oct 28 2019
 */

package cst8284.asgtm2.scheduler;

import java.util.Calendar;
import java.util.Scanner;

import cst8284.asgmt2.employee.Employee;

import java.io.*;
import java.util.ArrayList;

public class Scheduler {
	private static Scanner scan = new Scanner(System.in);
	private static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private Employee employee;

	private static final int SAVE_APPOINTMENT = 1;
	private static final int DELETE_APPOINTMENT = 2;
	private static final int CHANGE_APPOINTMENT = 3;
	private static final int DISPLAY_APPOINTMENT = 4;
	private static final int DISPLAY_SCHEDULE = 5;
	private static final int SAVE_APPOINTMENT_TO_FILE = 6;
	private static final int LOAD_APPOINTMENTS_FROM_FILE = 7;
	private static final int EXIT = 0;

	public Scheduler(Employee emp) {
		setEmployee(emp);
	}

	public void launch() {
		int choice = 0;
		do {
			choice = displayMenu();
			executeMenuItem(choice);
		} while (choice != EXIT);
	}

	private void setEmployee(Employee emp) {
		this.employee = emp;
	}

	private Employee getEmployee() {
		return employee;
	}

	private int displayMenu() {
		System.out.println("Enter a selection from the following menu");
		System.out.println(SAVE_APPOINTMENT + ". Save appointment\n" + DELETE_APPOINTMENT + ". Remove appointment\n"
				+ CHANGE_APPOINTMENT + ". Change appointment\n" + DISPLAY_APPOINTMENT + ". Get appointment\n"
				+ DISPLAY_SCHEDULE + ". Display schedule\n" + SAVE_APPOINTMENT_TO_FILE
				+ ". Backup appointment to file\n" + LOAD_APPOINTMENTS_FROM_FILE + ". Load appointments from file\n"
				+ EXIT + ". Exit program");
		int ac = scan.nextInt();
		scan.nextLine();
		System.out.println();
		return ac;

	}

	private void executeMenuItem(int choice) {
		// scan.nextLine();

		switch (choice) {
		case SAVE_APPOINTMENT:

			saveAppointment(makeAppointmentFromUserInput());

			break;
		case DELETE_APPOINTMENT:
			deleteAppointment(makeCalendarFromUserInput(false));
			break;
		case CHANGE_APPOINTMENT:
			changeAppointment(makeCalendarFromUserInput(false));
			break;
		case DISPLAY_APPOINTMENT:
			displayAppointment(makeCalendarFromUserInput(false));
			break;
		case DISPLAY_SCHEDULE:
			displayDaySchedule(makeCalendarFromUserInput(true));
			break;
		case SAVE_APPOINTMENT_TO_FILE:
			saveAppoinmentsToFile(getAppointments(), "CurrentAppointments.apts");

			break;

		case LOAD_APPOINTMENTS_FROM_FILE:
			loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments());
			break;
		case EXIT:
			System.out.println("Exitting Scheduler");
			break;
		default:
			System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");

		}
		System.out.println();
	}

	private boolean saveAppointment(Appointment apt) {

		Calendar cal = apt.getCalendar();
		if (findAppointment(cal) == null) {
			appointments.add(apt);
			System.out.println("Appointment saved.");
			return true;
		} else
			System.out.println("Cannot save; an appointment at that time already exists");
		return false;
	}

	private boolean deleteAppointment(Calendar cal) {

		if (findAppointment(cal) != null) {
			System.out.println("\n"+ findAppointment(cal).toString());
			System.out.println("Enter'Yes' to delete this appointment: ");
			String choice = scan.nextLine();
			if (choice.equalsIgnoreCase("Yes")) {
				for (int i = 0; i < appointments.size(); i++) {
					Appointment a = appointments.get(i);
					appointments.remove(a);
				}
				System.out.println("Appointment is deleted");
				return true;
			} else {
				System.out.println("Appointment is not deleted");
				return false;
			}
		} else {
			System.out.println("Appointment does not exists");
			return false;
		}

	}

	private boolean changeAppointment(Calendar cal) {

		Appointment apt = findAppointment(cal);
		if (apt != null) {
			System.out.println(apt.toString());
			System.out.println("Enter 'Yes' to change the date and time of this appointment");
			String choice = scan.nextLine();
			if (choice.equalsIgnoreCase("Yes")) {
				System.out.println("Enter new date and time");
				apt.setCalendar(makeCalendarFromUserInput(false));
				System.out.println("Appointment re-booked");
				return true;
			} else {
				System.out.println("Appointment is not changed");
				return false;
			}
		} else {
			System.out.println("Appointment does not exist.");
			return false;
		}

	}

	private void displayAppointment(Calendar cal) {

		Appointment apt = findAppointment(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println((apt != null) ? "\n" + apt.toString() + "\n"
				: "No appointment scheduled between " + hr + ":00 and " + (hr + 1) + ":00");
	}

	private void displayDaySchedule(Calendar cal) {
		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			displayAppointment(cal);
		}
	}

	private static boolean saveAppoinmentsToFile(ArrayList<Appointment> apts, String saveFile) {

		try {
			FileOutputStream myFile = new FileOutputStream(saveFile);
			ObjectOutputStream myOp = new ObjectOutputStream(myFile);

			myOp.writeObject(apts);

			myOp.close();
			System.out.println("Appointment data saved to \n" + saveFile);
		} catch (FileNotFoundException x) {
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("wrong" + e);

		}
		return true;

	}

	private static boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		// Appointment apt = null;
		try {
			FileInputStream file = new FileInputStream(sourceFile);
			ObjectInputStream op = new ObjectInputStream(file);

			apts.clear();
			apts.addAll((ArrayList<Appointment>) op.readObject());

			System.out.println("Appointments successfully loaded from\n " + sourceFile);
			op.close();
		} catch (ClassNotFoundException i) {
		} catch (EOFException x) {
		} catch (IOException e) {
		}

		return true;

	}

	private static String getResponseTo(String s) {
		System.out.print(s);
		return (scan.nextLine());
	}

	private Appointment makeAppointmentFromUserInput() {
		String fullName = getResponseTo("Enter Client Name (as FirstName LastName): ");
		String phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
		TelephoneNumber phone = new TelephoneNumber(phoneNumber);
		Calendar cal = makeCalendarFromUserInput(false);
		String activity = getResponseTo("Enter Activity: ");
		Activity act = new Activity(activity, getEmployee().getActivityType());

		return (new Appointment(cal, fullName, phone, act));
	}

	private static Calendar makeCalendarFromUserInput(boolean suppressHour) {

		Calendar cal = Calendar.getInstance();
		int hour = 0;
		cal.clear();

		String date = getResponseTo("Appointment Date (entered as DDMMYYYY): ");
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(2, 4)) - 1; 
																
		int year = Integer.parseInt(date.substring(4, 8));

		if (!suppressHour) {
			String time = getResponseTo("Appointment Time: ");
			hour = processTimeString(time);
		}

		cal.set(year, month, day, hour, 0);
		return (cal);

	}

	// -processTimeString(t: String):int
	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains(":"))
			hour = Integer.parseInt(t.split(":")[0]);
		else if (t.contains(" "))
			hour = Integer.parseInt(t.split(" ")[0]);
		else
			hour = Integer.parseInt(t);
		return ((hour < 8) ? hour + 12 : hour);
	}

	private Appointment findAppointment(Calendar cal) {
		for (Appointment idx : appointments) {
			if (idx.getCalendar().equals(cal))
				return idx;
		}
		return null;
	}

	private ArrayList<Appointment> getAppointments() {

		return appointments;
	}

}
