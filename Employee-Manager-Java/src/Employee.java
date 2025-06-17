
import java.util.Scanner;

public class Employee {
	private String firstName;
	private String lastName;
	private int id;
	private int socialSecurity;
	private int dateOfBirth;
	private double hourlyWage;
	private Request request = null;
	
	public Employee() {
		firstName = "";
		lastName = "";
		id = 0;
		socialSecurity = 0;
		dateOfBirth = 0;
		hourlyWage = 0;
	}
	
	
	public Employee(String firstName,String lastName, int id, int socialSecurity, int dateOfBirth, double hourlyWage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.socialSecurity = socialSecurity;
		this.dateOfBirth = dateOfBirth;
		this.hourlyWage = hourlyWage;
	}
	/////////////Getters/Setters////////////////////////////////////////
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}
	public double getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getSocialSecurity() {
		return socialSecurity;
	}
	public void setSocialSecurity(int socialSecurity) {
		this.socialSecurity = socialSecurity;
	}
	public int getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(int dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request){
		this.request = request;
	}

	public void loadRequest() {
		for(Request request : RequestList.pendingList){
			if(this.id == request.getEmployeeId())
				this.request = request;
		}

		for(Request request : RequestList.acceptedList){
			if(this.id == request.getEmployeeId())
				this.request = request;
		}

		for(Request request : RequestList.deniedList){
			if(this.id == request.getEmployeeId())
				this.request = request;
		}
	}
//	public static Employee matchEmpToId(String userId) {
//		Roster.viewEmployeesName();
//		return null;
//		
//	}
	////////////////////////////Methods////////////////////////////
	
	public void viewAnnouncements() {
		System.out.println("no annnouncements yet");
	}
	public void viewSchedule() {
		System.out.println("under maintenance");
	}
	public void requestTimeOff() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter days needed off with start date first in format mm/dd/yyyy."
				+ "\n please include the foward-Slashes!!");
		String daysNeeded = scan.nextLine();
		String []ar = daysNeeded.split("/");
		int mm = Integer.parseInt(ar[0]);
		int dd = Integer.parseInt(ar[1]);
		int yyyy = Integer.parseInt(ar[2]);
		if(isValidDate(mm,dd,yyyy)) {
			System.out.println("Request for day off starting "+ mm+"/"+dd+"/"+yyyy);
		}
		else {
			System.out.println("Invalid date!");
		}
		System.out.println("enter last day off in requested perion in mm/dd/yyyy ");
		String lastDayOff = scan.nextLine();
		String []ar2 = lastDayOff.split("/");

		mm = Integer.parseInt(ar2[0]);
		dd = Integer.parseInt(ar2[1]);
		yyyy = Integer.parseInt(ar2[2]);
		if(isValidDate(mm,dd,yyyy)) {
			System.out.println("until "+ mm+"/"+dd+"/"+yyyy);
		}
		else {
			System.out.println("Invalid date!");
		}
		scan.close();
	}
	////checking valid date
	//first check leap year
	//pre: int year
	//post: true if leap year false otherwise
	public boolean isLeapYear(int y) {
		if(y%400 == 0)
			return true;
		else if(y%100==0)
			return false;
		else
			return y % 4 == 0;
	}
	//check for valid date
	//pre:int month, int day, int year
	//post:return true if it is a valid date passed else false
	public boolean isValidDate(int m, int d, int y) {
		int[] daysPerEachMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		if(m<1||d<1||y<2022||m>12) {
			return false;
		}
		else if(m==2 && isLeapYear(y)) {
			return d < 30;
		}
		else {
			return d <= daysPerEachMonth[m];
		}
	}
	public void viewHours() {
		System.out.println("working on it");
	}
	public void editAvailability() {
		System.out.println("open ");
	}
	public void messageManager() {
		System.out.println("messaging the boss");
	}


	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	
	
	

}
