
import java.util.*;
import java.text.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
//import javax.swing.JOptionPane;

public class Message{
  private String fromFirstName;
  private String fromLastName;
  private int fromId;
  private int toId;
  private String date;
  private String time;
  private String message;


  public Message(){
    this.fromFirstName = "";
    this.fromLastName = "";
    this.fromId = -1;
    this.toId = -1;
    this.date = "";
    this.time = "";
    this.message = "";
  }




  public Message(String fromFirstName, String fromLastName, int fromId, int toId, String message) {
    this.fromFirstName = fromFirstName;
    this.fromLastName = fromLastName;
    this.fromId = fromId;
    this.toId = toId;
    this.message = message;
  }
  /////////////Getters/Setters////////////////////////////////////////

  public String getFromFirstName() {
    return fromFirstName;
  }
  public void setFromFirstName(String firstName) {
    this.fromFirstName = firstName;
  }
  public String getFromLastName() {
    return fromLastName;
  }
  public void setFromLastName(String lastName) {
    this.fromLastName = lastName;
  }
  public int getFromId() {
    return fromId;
  }

  public void setFromId(int id) {
    this.fromId = id;
  }

  public int getToId() {
    return toId;
  }

  public void setToId(int id) {
    this.toId = id;
  }

  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }

  public void setDateTime(){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime now = LocalDateTime.now();
    DateFormat dtf2 = DateFormat.getDateInstance();
    Calendar cal = Calendar.getInstance();
    this.time = dtf.format(now);
    this.date = dtf2.format(cal.getTime());
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  public void addEmployeeMessage()
  {
    setDateTime();
    System.out.println(this.date + "\n" + this.time + "\n");
    MessageList.employeeMessages.add(this);
    saveEmployeeMessage();
  }

  public void removeEmployeeMessage()
  {
    MessageList.employeeMessages.remove(this);
    saveEmployeeMessage();
  }

  public void addManagerMessage()
  {
    setDateTime();
    MessageList.managerMessages.add(this);
    saveManagerMessage();
  }

  public void removeManagerMessage()
  {
    MessageList.managerMessages.remove(this);
    saveManagerMessage();
  }

  public boolean inManagerMessages(){
    for(Message message : MessageList.managerMessages){
      if(this.toId == message.getFromId())
        return true;
    }

    return false;
  }

  public boolean inEmployeeMessages(){
    for(Message message : MessageList.employeeMessages){
      if(this.fromId == message.getFromId())
        return true;
    }

    return false;
  }

  private void saveManagerMessage(){
    try
    {
      File file = new File("managerMessages.txt");
      file.createNewFile();
      BufferedWriter messageWriter = new BufferedWriter(new FileWriter(file));

      for(Message message : MessageList.managerMessages)
      {
        messageWriter.write("Manager\n");
        messageWriter.write(message.getFromId()+"\n");
        messageWriter.write(message.getToId()+"\n");
        messageWriter.write(message.getDate()+"\n");
        messageWriter.write(message.getTime()+"\n");
        messageWriter.write(message.getMessage()+"\n");
      }

      messageWriter.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

  private void saveEmployeeMessage(){
    try
    {
      File file = new File("employeeMessages.txt");
      file.createNewFile();
      BufferedWriter messageWriter = new BufferedWriter(new FileWriter(file));

      for(Message message : MessageList.employeeMessages)
      {
        messageWriter.write(message.getFromFirstName()+"\n");
        messageWriter.write(message.getFromLastName()+"\n");
        messageWriter.write(message.getFromId()+"\n");
        messageWriter.write(message.getToId()+"\n");
        messageWriter.write(message.getDate()+"\n");
        messageWriter.write(message.getTime()+"\n");
        messageWriter.write(message.getMessage()+"\n");

      }

      messageWriter.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }
}
