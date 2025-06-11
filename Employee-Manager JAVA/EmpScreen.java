package sql.src;//package Manager;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.awt.SystemColor;


public class EmpScreen implements ActionListener{
	Employee employee = null;
    JFrame frame = new JFrame();
    JButton myButton = new JButton("New Window");
    JLabel lblNewLabel = new JLabel();
    JLabel clockInLbl = new JLabel("Clock-in:");
    JButton btnClockIn = new JButton("Clock-in");
    JLabel clockOutLbl = new JLabel("Clock-out:");
    JButton btnClockOut = new JButton("Clock-out");
    JLabel scheduleLbl = new JLabel("Schedule:");
    JButton btnSchedule = new JButton("Schedule");
    JLabel timeOffLbl = new JLabel("Request Time Off:");
    JButton btnRequestTimeOff = new JButton("Time Off");
	JLabel messageLbl = new JLabel("Messages:");
    JButton btnMessage = new JButton("Message");
    JButton FAQ = new JButton("FAQ");
    private final JPanel panel = new JPanel();
    private final JLabel lblQuestions = new JLabel("Questions:");


    public EmpScreen(Employee employeePassed){
        this.employee = employeePassed;
    	clockInLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	
    	lblNewLabel = new JLabel("Employee - " + employeePassed.getFirstName() + " " + employeePassed.getLastName());

        //sets clockin label
        clockInLbl.setBounds(46, 114, 90, 13);

        //sets clockin button
        btnClockIn.setBounds(203, 102, 122, 40);
        btnClockIn.setFocusable(false);
        btnClockIn.addActionListener(this);
        clockOutLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //sets clockout label
        clockOutLbl.setBounds(46, 197, 90, 13);
        
        btnMessage.setBounds(203, 413, 122, 40);
        btnMessage.setFocusable(false);
        btnMessage.addActionListener(this);

        //sets clockout button
        btnClockOut.setBounds(203, 185, 122, 40);
        btnClockOut.setFocusable(false);
        btnClockOut.addActionListener(this);
        scheduleLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //sets schedule label
        scheduleLbl.setBounds(46, 277, 90, 13);

        //sets schedule button
        btnSchedule.setBounds(203, 265, 122, 40);
        btnSchedule.setFocusable(false);
        btnSchedule.addActionListener(this);
        timeOffLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));

        //sets timeoff label
        timeOffLbl.setBounds(46, 351, 115, 13);

        //sets timeoff button
        btnRequestTimeOff.setBounds(203, 339, 122, 40);
        btnRequestTimeOff.setFocusable(false);
        btnRequestTimeOff.addActionListener(this);

        // FAQ
        //sets timeoff button
        FAQ.setBounds(203, 479, 122, 40);
        FAQ.setFocusable(false);
        FAQ.addActionListener(this);

        FAQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== FAQ) {
                   FAQ faq = new FAQ(employee);

                }
            }
        });
        
        frame.getContentPane().add(clockInLbl);
        frame.getContentPane().add(clockOutLbl);
        frame.getContentPane().add(scheduleLbl);
        frame.getContentPane().add(timeOffLbl);
        frame.getContentPane().add(FAQ);

        //ads buttons
        frame.getContentPane().add(btnClockIn);
        frame.getContentPane().add(btnClockOut);
        frame.getContentPane().add(btnSchedule);
        frame.getContentPane().add(btnRequestTimeOff);
				frame.getContentPane().add(btnMessage);
        frame.getContentPane().setBackground(SystemColor.activeCaption);
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));

        //sets frame
        //    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(688, 700);
        frame.getContentPane().setLayout(null);
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(46, 11, 590, 68);
        
        
        
        frame.getContentPane().add(panel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblQuestions.setBounds(52, 491, 109, 13);
        
        frame.getContentPane().add(lblQuestions);
        
        JLabel lblMessages = new JLabel("Messages:");
        lblMessages.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMessages.setBounds(46, 426, 109, 13);
        frame.getContentPane().add(lblMessages);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
   		String nameOfFile= employee.getFirstName()+"TimeSheet.txt";
   		System.out.println(employee.getFirstName());

    	File file = new File(nameOfFile);

//Clock In gets written to TimeSheet.txt as first line
        if(e.getSource() == btnClockIn) { //opens clockin Screen
            frame.dispose();
            ClockInScreen clockIn = new ClockInScreen(employee);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
       	 	LocalDateTime now = LocalDateTime.now();  
       	 
       	 	String timeIn = dtf.format(now);  
	       	 try 
	 		{
	 			file = new File(nameOfFile);
	 			file.createNewFile();
	 			BufferedWriter timeWriter = new BufferedWriter(new FileWriter(file));
	 			timeWriter.write(timeIn+"\n");
	 			timeWriter.flush();
	 			timeWriter.close();
	 		}
	 		catch(IOException ex) 
	 		{
	 			ex.printStackTrace();
	 		
	 		}
        } else if (e.getSource() == btnClockOut) { //opens clockout screen
            frame.dispose();
            //ClockOutScreen clockOut = new ClockOutScreen(employee);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
       	 	LocalDateTime now = LocalDateTime.now();
            String timeOut = dtf.format(now);
            try 
	 		{
            	String timeStart = "";
//writing to already existent time sheet since this is clockout. 2nd argument true for FileWriter
            	System.out.println("this is emp "+employee.getFirstName());
	 			BufferedWriter timeWriter = new BufferedWriter(new FileWriter(file,true));
	 			timeWriter.write(timeOut+"\n");
	 			
	 			BufferedReader br = new BufferedReader(new FileReader(file));
	 			timeStart = br.readLine();
//Call calculateTimeWorked
	 			String timeWorked = calculateTimeWorked(timeStart,timeOut);
	 			timeWriter.write(timeWorked);
	 			ClockOutScreen clockOut = new ClockOutScreen(employee,timeWorked);
	 			timeWriter.flush();
	 			timeWriter.close();
	 		}
	 		catch(IOException ex) 
	 		{
	 			ex.printStackTrace();
	 		
	 		}
        } else if (e.getSource() == btnSchedule) { //opens schedule screen
            frame.dispose();
            ScheduleScreen schedule = new ScheduleScreen(employee);
        } else if (e.getSource() == btnRequestTimeOff) { //opens timeoff screen
            frame.dispose();
            TimeOffScreen timeOffScreen = new TimeOffScreen(employee);
        }
        else if (e.getSource() == btnMessage){
			frame.dispose();
			MessageEmployeeScreen messageEmployeeScreen = new MessageEmployeeScreen(employee);
	}

    }
    //pre:String for time in and time out
    //post:String representing time out - time in
    public static String calculateTimeWorked(String in, String out) {
    	String[] s ;
    	String[] s2 ;
    	String total = "";

    	s = in.split(":");
    	String sHours = s[0];
    	String sMins = s[1];
    	String sSecs = s[2];
    	int hours = Integer.parseInt(sHours);
    	int mins = Integer.parseInt(sMins);
    	int secs = Integer.parseInt(sSecs);
//    	System.out.println(secs);
    	s2 = out.split(":");
    	String eHours = s2[0];
    	String eMins = s2[1];
    	String eSecs = s2[2];
    	int endHours = Integer.parseInt(eHours);
    	int endMins = Integer.parseInt(eMins);
    	int endSecs = Integer.parseInt(eSecs);
    	
    	int hoursWorked = Math.abs(endHours-hours);
    	int minutesWorked = Math.abs(endMins - mins);
    	int secondsWorked = Math.abs(endSecs - secs);
    	
    	total = "Time worked: "+hoursWorked+" hours "+minutesWorked+
    			" minutes "+secondsWorked+" seconds";
    	return total;
    	
    	
    	
    }
}
