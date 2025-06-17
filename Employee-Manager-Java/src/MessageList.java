
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class MessageList {
  static ArrayList<Message> managerMessages = new ArrayList<Message>();
  static ArrayList<Message> employeeMessages = new ArrayList<Message>();



	////////////////////////////Methods////////////////////////////

  static public void loadInfo()
  {
    try {
      File file1 = new File("employeeMessages.txt");
      file1.createNewFile();

      BufferedReader messageReader = new BufferedReader(new FileReader(file1));

      String line;
      while((line = messageReader.readLine())!= null)
      {
        Message tempMessage = new Message();
        tempMessage.setFromFirstName(line);
        tempMessage.setFromLastName(messageReader.readLine());
        tempMessage.setFromId(Integer.parseInt(messageReader.readLine()));
        tempMessage.setToId(Integer.parseInt(messageReader.readLine()));
        tempMessage.setDate(messageReader.readLine());
        tempMessage.setTime(messageReader.readLine());
        tempMessage.setMessage(messageReader.readLine());
        employeeMessages.add(tempMessage);
      }
      messageReader.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

    try {
      File file1 = new File("managerMessages.txt");
      file1.createNewFile();

      BufferedReader messageReader = new BufferedReader(new FileReader(file1));

      String line;
      while((line = messageReader.readLine())!= null)
      {
        Message tempMessage = new Message();
        tempMessage.setFromFirstName(line);
        tempMessage.setFromId(Integer.parseInt(messageReader.readLine()));
        tempMessage.setToId(Integer.parseInt(messageReader.readLine()));
        tempMessage.setDate(messageReader.readLine());
        tempMessage.setTime(messageReader.readLine());
        tempMessage.setMessage(messageReader.readLine());
        managerMessages.add(tempMessage);
      }
      messageReader.close();
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
