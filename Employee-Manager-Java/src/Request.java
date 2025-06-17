
import java.util.*;
import java.io.*;
//import javax.swing.JOptionPane;

public class Request{
  private String employeeFirstName;
  private String employeeLastName;
  private int employeeId;
  private String date1;
  private String date2;
  private String status;


  public Request(){
    this.employeeFirstName = "";
    this.employeeLastName = "";
    this.employeeId = -1;
    this.date1 = "";
    this.date2 = "";
    this.status = "";
  }


  public Request(String employeeFirstName, String employeeLastName, int employeeId, String date1, String date2, String status) {
    this.employeeFirstName = employeeFirstName;
    this.employeeLastName = employeeLastName;
    this.employeeId = employeeId;
    this.date1 = date1;
    this.date2 = date2;
    this.status = status;
  }
  /////////////Getters/Setters////////////////////////////////////////

  public String getEmployeeFirstName() {
    return employeeFirstName;
  }
  public void setEmployeeFirstName(String firstName) {
    this.employeeFirstName = firstName;
  }
  public String getEmployeeLastName() {
    return employeeLastName;
  }
  public void setEmployeeLastName(String lastName) {
    this.employeeLastName = lastName;
  }
  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int id) {
    this.employeeId = id;
  }
  public String getDate1() {
    return date1;
  }
  public void setDate1(String date1) {
    this.date1 = date1;
  }
  public String getDate2() {
    return date2;
  }
  public void setDate2(String date2) {
    this.date2 = date2;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String stat) {
    this.status = stat;
  }

  public void addPendingRequest()
  {
    RequestList.pendingList.add(this);
    savePendingRequest();
  }

  public void addAcceptedRequest()
  {
    RequestList.acceptedList.add(this);
    saveAcceptedRequest();
    addRequest();
  }

  public void addDeniedRequest()
  {
    RequestList.deniedList.add(this);
    saveDeniedRequest();
  }

  private void addRequest(){
    RequestList.requestList.add(this);
    saveRequest();
  }

  public void removePendingRequest()
  {
    RequestList.pendingList.remove(this);
    savePendingRequest();
  }

  public void removeAcceptedRequest()
  {
    RequestList.acceptedList.remove(this);
    saveAcceptedRequest();
  }

  public void removeDeniedRequest()
  {
    RequestList.deniedList.remove(this);
    saveDeniedRequest();
  }

  public void removeRequest()
  {
    RequestList.requestList.remove(this);
    saveRequest();
  }

  public boolean inPending(){
    for(Request req : RequestList.pendingList){
      if(this.employeeId == req.getEmployeeId())
        return true;
    }

    return false;
  }

  public boolean inAccepted(){
    for(Request req : RequestList.acceptedList){
      if(this.employeeId == req.getEmployeeId())
        return true;
    }

    return false;
  }

  public boolean inDenied(){
    for(Request req : RequestList.deniedList){
      if(this.employeeId == req.getEmployeeId())
        return true;
    }

    return false;
  }

  public boolean inRequest(){
    for(Request req : RequestList.acceptedList){
      if(this.employeeId == req.getEmployeeId())
        return true;
    }

    return false;
  }

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
	public boolean isValidDate(String date) {
    String []ar = date.split("/");
    int m;
    int d;
    int y;
    try
		{
      m = Integer.parseInt(ar[0]);
      d = Integer.parseInt(ar[1]);
      y= Integer.parseInt(ar[2]);
		}
		catch(NumberFormatException e)
		{
      return false;
		}

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

  private void savePendingRequest(){
    try
    {
      File file = new File("pendingRequest.txt");
      file.createNewFile();
      BufferedWriter requestWriter = new BufferedWriter(new FileWriter(file));

      for(Request request : RequestList.pendingList)
      {
        requestWriter.write(request.getEmployeeFirstName()+"\n");
        requestWriter.write(request.getEmployeeLastName()+"\n");
        requestWriter.write(request.getEmployeeId()+"\n");
        requestWriter.write(request.getDate1()+"\n");
        requestWriter.write(request.getDate2()+"\n");
        requestWriter.write(request.getStatus()+"\n");
      }

      requestWriter.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

  private void saveAcceptedRequest(){
    try
    {
      File file = new File("acceptedRequest.txt");
      file.createNewFile();
      BufferedWriter requestWriter = new BufferedWriter(new FileWriter(file));

      for(Request request : RequestList.acceptedList)
      {
        requestWriter.write(request.getEmployeeFirstName()+"\n");
        requestWriter.write(request.getEmployeeLastName()+"\n");
        requestWriter.write(request.getEmployeeId()+"\n");
        requestWriter.write(request.getDate1()+"\n");
        requestWriter.write(request.getDate2()+"\n");
        requestWriter.write(request.getStatus()+"\n");
      }

      requestWriter.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

  private void saveDeniedRequest(){
    try
    {
      File file = new File("deniedRequest.txt");
      file.createNewFile();
      BufferedWriter requestWriter = new BufferedWriter(new FileWriter(file));

      for(Request request : RequestList.deniedList)
      {
        requestWriter.write(request.getEmployeeFirstName()+"\n");
        requestWriter.write(request.getEmployeeLastName()+"\n");
        requestWriter.write(request.getEmployeeId()+"\n");
        requestWriter.write(request.getDate1()+"\n");
        requestWriter.write(request.getDate2()+"\n");
        requestWriter.write(request.getStatus()+"\n");
      }

      requestWriter.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

  private void saveRequest(){
    try
    {
      File file = new File("request.txt");
      file.createNewFile();
      BufferedWriter requestWriter = new BufferedWriter(new FileWriter(file));

      for(Request request : RequestList.requestList)
      {
        requestWriter.write(request.getEmployeeFirstName()+"\n");
        requestWriter.write(request.getEmployeeLastName()+"\n");
        requestWriter.write(request.getEmployeeId()+"\n");
        requestWriter.write(request.getDate1()+"\n");
        requestWriter.write(request.getDate2()+"\n");
        requestWriter.write(request.getStatus()+"\n");
      }

      requestWriter.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

}
