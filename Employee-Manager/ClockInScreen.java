package sql.src;//package Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ClockInScreen implements ActionListener{
	Employee employee = null;
    JFrame frame = new JFrame();
    JLabel clockInlabel = new JLabel("ClockIn");
    JLabel successLabel = new JLabel("You Have Successfully clocked in, have a wonderful day.");
    JLabel returnLabel = new JLabel("Return to previous page");
    JButton returnButton = new JButton("Return");
    private final JPanel panel = new JPanel();
    private final JLabel lbCurrentTime = new JLabel("Clock-in time : " + getCurrentTime());

    public ClockInScreen(Employee employee){
    	this.employee = employee;
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //sets success label
        successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        successLabel.setBounds(30, 332, 569, 60);
        frame.getContentPane().add(successLabel);
        returnButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        //sets return button
        returnButton.setBounds(158, 438, 295, 47);
        returnButton.addActionListener(this);
        frame.getContentPane().add(returnButton);
        frame.getContentPane().setBackground(SystemColor.activeCaption);

      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(651, 589);
        frame.getContentPane().setLayout(null);
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(30, 20, 569, 74);
        
        frame.getContentPane().add(panel);
        panel.add(clockInlabel);
        clockInlabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lbCurrentTime.setHorizontalAlignment(SwingConstants.CENTER);
        lbCurrentTime.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lbCurrentTime.setBounds(68, 189, 484, 74);
        
        frame.getContentPane().add(lbCurrentTime);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==returnButton) {
            frame.dispose();
            EmpScreen screen = new EmpScreen(employee);
        }
    }
    
    public String getCurrentTime()
    {
    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
    	 LocalDateTime now = LocalDateTime.now();  
    	 return dtf.format(now);  
    }
}