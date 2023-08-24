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

public class ManagerAnswers implements ActionListener {
    JFrame frame = new JFrame();
    JButton qBtn = new JButton("Questions");
    JLabel label = new JLabel("Answers to Questions");

    JLabel Question1 = new JLabel("Q1: to fix this "
            + "it is as simple of selecting a box and "
            + " changing the information and clicking 'Enter'");
    JLabel Question3 = new JLabel("Q2/Q3 : Unfortunately you will need " +
            "to re-add this employee. We currently do not have an " +
            "'Undo' button.");

    JLabel Question4 = new JLabel("Q4: In order to change an employees wage " +
            "all you need is to select this box and add the value and click 'Enter'");

    ManagerAnswers() {
        label.setBounds(0, 0, 500, 50);
        label.setFont(new Font(null, Font.BOLD, 35));
        // q1 & q2
        Question1.setBounds(0, 0, 1000, 300);
        Question1.setFont(new Font(null, Font.PLAIN, 18));
        Question3.setBackground(SystemColor.activeCaption);
        // q3
        Question3.setBounds(0, 0, 1000, 550);
        Question3.setFont(new Font(null, Font.PLAIN, 18));
        //q3 button

        // q4
        Question4.setBounds(0, 0, 1000, 800);
        Question4.setFont(new Font(null, Font.PLAIN, 18));


        // back to Main
        qBtn.setBounds(450, 500, 100, 40);
        qBtn.setFocusable(false);
        qBtn.addActionListener(this);

        ImageIcon image = new ImageIcon("logo.png"); // create image icon
        frame.getContentPane().setForeground(SystemColor.activeCaption);
        frame.setBackground(SystemColor.activeCaption);
        frame.setIconImage(image.getImage());

        // buttons
        frame.getContentPane().add(qBtn);

        // Text
        frame.getContentPane().add(label);
        frame.getContentPane().add(Question1);
        frame.getContentPane().add(Question3);
        frame.getContentPane().add(Question4);

        // general frame actions
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(926, 600);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == qBtn) {
            frame.setVisible(false);
            ManagerFAQ window = new ManagerFAQ();
        }

    }
}