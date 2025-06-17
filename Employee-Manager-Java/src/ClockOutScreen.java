
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockOutScreen implements ActionListener{
	Employee employee = null;
    JFrame frame = new JFrame();
    JLabel clockOutLabel = new JLabel("ClockOut");
    JLabel successLabel = new JLabel("You have been successfully clocked out");
    JLabel returnLabel = new JLabel("Return to previous page");
    JButton returnButton = new JButton("Return");
    private final JPanel panel = new JPanel();
    private final JLabel lblClockedoutAt = new JLabel("Clocked-out time : " + getCurrentTime());
    JLabel lblYouWorkedFor = new JLabel();

    public ClockOutScreen(Employee employee, String timeWorked){
    	this.employee = employee;
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblYouWorkedFor = new JLabel(timeWorked);

        //sets success label
        successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        successLabel.setBounds(48, 348, 552, 60);
        frame.getContentPane().add(successLabel);
        returnButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        //sets return button
        returnButton.setBounds(266, 419, 122, 40);
        returnButton.addActionListener(this);
        frame.getContentPane().add(returnButton);
        frame.getContentPane().setBackground(SystemColor.activeCaption);

    //    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(659, 559);
        frame.getContentPane().setLayout(null);
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(48, 20, 552, 65);
        
        frame.getContentPane().add(panel);
        panel.add(clockOutLabel);
        clockOutLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblClockedoutAt.setHorizontalAlignment(SwingConstants.CENTER);
        lblClockedoutAt.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblClockedoutAt.setBounds(48, 165, 552, 60);
        
        frame.getContentPane().add(lblClockedoutAt);
        lblYouWorkedFor.setHorizontalAlignment(SwingConstants.CENTER);
        lblYouWorkedFor.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblYouWorkedFor.setBounds(10, 288, 623, 37);
        
        frame.getContentPane().add(lblYouWorkedFor);
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