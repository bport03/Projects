package sql.src;//package Sprint4Classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class TimeOffScreen implements ActionListener{
		Employee employee = null;
    JFrame frame = new JFrame();
    JLabel timeOffLabel = new JLabel("Request Time Off");
    JLabel dateLabel = new JLabel("Enter a date range: ");
    JLabel rangeLabel = new JLabel("-");
    JTextField date1Text = new JTextField();
    JTextField date2Text = new JTextField();
		JLabel date1Range = new JLabel("(mm/dd/yyyy)");
		JLabel date2Range = new JLabel("(mm/dd/yyyy)");
    JButton enterButton = new JButton("Submit");
    JLabel successLabel = new JLabel("");
    JLabel returnLabel = new JLabel("Return to previous page");
    JButton returnButton = new JButton("Return");
    private final JPanel panel = new JPanel();

    public TimeOffScreen(Employee emp){
				employee = emp;
				employee.loadRequest();
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //sets date label
        dateLabel.setBounds(285, 95, 212, 60);
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

				//sets date1 label
				date1Range.setBounds(40, 200, 300, 40);
				date1Range.setFont(new Font("Tahoma", Font.PLAIN, 20));

        //sets first date textbox
        date1Text.setBounds(30, 167, 300, 40);

        //sets range label
        rangeLabel.setBounds(385, 167, 300, 40);
        rangeLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));

        //sets second date textbox
        date2Text.setBounds(460, 167, 300, 40);

				//sets date1 label
				date2Range.setBounds(480, 200, 300, 40);
				date2Range.setFont(new Font("Tahoma", Font.PLAIN, 20));

        //sets success label
        successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        successLabel.setBounds(190, 400, 400, 60);
        enterButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

        //sets enter button
        enterButton.setBounds(315, 234, 150, 40);
        enterButton.setFocusable(false);
        enterButton.addActionListener(this);
        returnButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        //sets return button
        returnButton.setBounds(315, 584, 175, 40);
        returnButton.addActionListener(this);
        frame.getContentPane().add(dateLabel);
				frame.getContentPane().add(date1Range);
				frame.getContentPane().add(date2Range);
        frame.getContentPane().add(successLabel);
        frame.getContentPane().add(rangeLabel);

        frame.getContentPane().add(enterButton);
        frame.getContentPane().add(returnButton);

        frame.getContentPane().add(date1Text);
        frame.getContentPane().add(date2Text);
        frame.getContentPane().setBackground(SystemColor.activeCaption);

    //    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.getContentPane().setLayout(null);
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(35, 11, 705, 60);

        frame.getContentPane().add(panel);
        panel.add(timeOffLabel);
        timeOffLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
        frame.setVisible(true);

				if(employee.getRequest() == null)
					;
				else if(employee.getRequest().inAccepted()){
					JOptionPane.showMessageDialog(null, "Your Request was accepted, feel free to make a new one");
					employee.getRequest().removeAcceptedRequest();
					employee.setRequest(null);
				} else if(employee.getRequest().inDenied()){
					JOptionPane.showMessageDialog(null, "Your Request was denied, feel free to make a new one");
					employee.getRequest().removeDeniedRequest();
					employee.setRequest(null);
				}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
				employee.loadRequest();
        if(e.getSource()==enterButton){
					if(employee.getRequest() == null || !(employee.getRequest().inPending())){
						Request tmpRequest = new Request(employee.getFirstName(), employee.getLastName(), employee.getId(), date1Text.getText(), date2Text.getText(), "pending");
						if (!(tmpRequest.isValidDate(tmpRequest.getDate1())))
							JOptionPane.showMessageDialog(null, "The First date is invalid\n");
						if (!(tmpRequest.isValidDate(tmpRequest.getDate2())))
							JOptionPane.showMessageDialog(null, "The Second date is invalid");
            if (tmpRequest.isValidDate(tmpRequest.getDate1()) && tmpRequest.isValidDate(tmpRequest.getDate2())){
							successLabel.setText("Your request has been sent to your manager");
							tmpRequest.addPendingRequest();
						}
					} else {
							JOptionPane.showMessageDialog(null, "Your Request is currently pending\nYou must wait for approval/denial before you make another request");
					}
        } else if(e.getSource()==returnButton) {
            frame.dispose();
            EmpScreen screen = new EmpScreen(employee);
        }

    }
}
