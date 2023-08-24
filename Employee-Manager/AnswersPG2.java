package sql.src;

import javax.print.attribute.URISyntax;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class AnswersPG2 implements ActionListener {
    Employee employee = null;
    JFrame frame = new JFrame();
    JButton bckBtn = new JButton("Back");
    JButton qBtn = new JButton("Questions");
    JLabel label = new JLabel("Answers to Questions");

    JLabel Question1 = new JLabel("Q5: Unfortunately we don't have  "
            + " a function for this yet.");
    JLabel Question3 = new JLabel("Q6: "
            + "When you click on our schedule button you'll see" +
            "two weeks worth of schedules");

    JLabel Question4 = new JLabel("Q8:"
            + "We have an option to request time off, it's as simple" +
            " as clicking the button and selecting a date! ");
    JLabel email = new JLabel("My question is not on here.");
    JButton Redirect = new JButton("Email");

    AnswersPG2(Employee employee) {
        label.setBounds(0, 0, 500, 50);
        label.setFont(new Font(null, Font.BOLD, 35));
        // q1 & q2
        Question1.setBounds(0, 0, 1000, 300);
        Question1.setFont(new Font(null, Font.PLAIN, 18));
        
        this.employee = employee;
        
        // q3
        Question3.setBounds(0, 0, 1000, 550);
        Question3.setFont(new Font(null, Font.PLAIN, 18));
        //q3 button
        Redirect.setBounds(200, 525, 75, 25);
        Redirect.setFocusable(false);
        Redirect.addActionListener(this);
        // q4
        Question4.setBounds(0, 0, 1000, 750);
        Question4.setFont(new Font(null, Font.PLAIN, 18));
        // email
        email.setBounds(0, 0, 1000, 1000);
        email.setFont(new Font(null, Font.PLAIN, 18));
        // back to FAQ
        bckBtn.setBounds(575, 500, 100, 40);
        bckBtn.setFocusable(false);
        bckBtn.addActionListener(this);

        // back to Main
        qBtn.setBounds(450, 500, 100, 40);
        qBtn.setFocusable(false);
        qBtn.addActionListener(this);

        ImageIcon image = new ImageIcon("logo.png"); // create image icon
        frame.setIconImage(image.getImage());

        // buttons
        frame.add(bckBtn);
        frame.add(qBtn);
        frame.add(Redirect);
        // Text
        frame.add(label);
        frame.add(Question1);
        frame.add(Question3);
        frame.add(Question4);
        frame.add(email);

        // general frame actions
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(750, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Redirect) {
            frame.setVisible(false);
            emailPg window = new emailPg(employee);
        }else if (e.getSource() == bckBtn) {
            frame.setVisible(false);
            FAQ faq = new FAQ(employee);
        }else if (e.getSource() == qBtn) {
            frame.setVisible(false);
            QuestionPg window = new QuestionPg(employee);
        }

    }
}
