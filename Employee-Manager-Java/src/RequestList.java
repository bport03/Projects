
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class RequestList {
  static ArrayList<Request> pendingList = new ArrayList<Request>();
  static ArrayList<Request> acceptedList = new ArrayList<Request>();
  static ArrayList<Request> deniedList = new ArrayList<Request>();
  static ArrayList<Request> requestList = new ArrayList<Request>();

	////////////////////////////Methods////////////////////////////

  static public void loadInfo()
  {
    try {
      File file1 = new File("pendingRequest.txt");
      file1.createNewFile();

      BufferedReader requestReader = new BufferedReader(new FileReader(file1));

      String line;
      while((line = requestReader.readLine())!= null)
      {
        Request tempRequest = new Request();
        tempRequest.setEmployeeFirstName(line);
        tempRequest.setEmployeeLastName(requestReader.readLine());
        tempRequest.setEmployeeId(Integer.parseInt(requestReader.readLine()));
        tempRequest.setDate1(requestReader.readLine());
        tempRequest.setDate2(requestReader.readLine());
        tempRequest.setStatus(requestReader.readLine());
        pendingList.add(tempRequest);
      }
      requestReader.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

    try {
      File file1 = new File("acceptedRequest.txt");
      file1.createNewFile();

      BufferedReader requestReader = new BufferedReader(new FileReader(file1));

      String line;
      while((line = requestReader.readLine())!= null)
      {
        Request tempRequest = new Request();
        tempRequest.setEmployeeFirstName(line);
        tempRequest.setEmployeeLastName(requestReader.readLine());
        tempRequest.setEmployeeId(Integer.parseInt(requestReader.readLine()));
        tempRequest.setDate1(requestReader.readLine());
        tempRequest.setDate2(requestReader.readLine());
        tempRequest.setStatus(requestReader.readLine());
        acceptedList.add(tempRequest);
      }
      requestReader.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

    try {
      File file1 = new File("deniedRequest.txt");
      file1.createNewFile();

      BufferedReader requestReader = new BufferedReader(new FileReader(file1));

      String line;
      while((line = requestReader.readLine())!= null)
      {
        Request tempRequest = new Request();
        tempRequest.setEmployeeFirstName(line);
        tempRequest.setEmployeeLastName(requestReader.readLine());
        tempRequest.setEmployeeId(Integer.parseInt(requestReader.readLine()));
        tempRequest.setDate1(requestReader.readLine());
        tempRequest.setDate2(requestReader.readLine());
        tempRequest.setStatus(requestReader.readLine());
        deniedList.add(tempRequest);
      }
      requestReader.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

    try {
      File file1 = new File("request.txt");
      file1.createNewFile();

      BufferedReader requestReader = new BufferedReader(new FileReader(file1));

      String line;
      while((line = requestReader.readLine())!= null)
      {
        Request tempRequest = new Request();
        tempRequest.setEmployeeFirstName(line);
        tempRequest.setEmployeeLastName(requestReader.readLine());
        tempRequest.setEmployeeId(Integer.parseInt(requestReader.readLine()));
        tempRequest.setDate1(requestReader.readLine());
        tempRequest.setDate2(requestReader.readLine());
        tempRequest.setStatus(requestReader.readLine());
        requestList.add(tempRequest);
      }
      requestReader.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }


  /*public static void main(String args[]){
    Request req = new Request("zar", "rey", "0001", "12/12/12 - 12/12/12", "pending");
    System.out.println(req.getEmployeeFirstName() + req.getEmployeeLastName() + req.getEmployeeId() + req.getDateRange() + req.getStatus());
  }*/
}
