
import javax.print.attribute.URISyntax;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class QuestionPg implements ActionListener {
    Employee employee = null;
    JFrame frame = new JFrame();
    JButton bckBtn = new JButton("Back");
    JButton ansBtn = new JButton("Ans");
    JButton ans2btn2 = new JButton("Ans");
    JButton ans3btn3 = new JButton("Ans");
    JButton ans4btn4 = new JButton("Ans");

    JLabel label = new JLabel("PAGE 2");
    JLabel label2 = new JLabel("Is there a way to send emails to co-workers?");
    JLabel label3 = new JLabel("How can I view who is working with me?");
    JLabel label4 = new JLabel("I finished my shift, will my hours be recorded?");
    JLabel label5 = new JLabel("How can I request time off?");

    QuestionPg(Employee employee) {
        label.setBounds(0, 0, 500, 50);
        label.setFont(new Font(null, Font.BOLD, 25));
        //
        label2.setBounds(0, 0, 500, 135);
        label2.setFont(new Font(null, Font.ITALIC, 20));
        //
        label3.setBounds(0, 0, 500, 400);
        label3.setFont(new Font(null, Font.ITALIC, 20));
        //
        label4.setBounds(0, 0, 500,625 );
        label4.setFont(new Font(null, Font.ITALIC, 20));
        //
        label5.setBounds(0, 0, 500, 850);
        label5.setFont(new Font(null, Font.ITALIC, 20));

        // Back Button functionality
        bckBtn.setBounds(500,500,200,40);
        bckBtn.setFocusable(false);
        bckBtn.addActionListener(this);

        ImageIcon image = new ImageIcon("logo.png"); // create image icon
        frame.setIconImage(image.getImage());


        // later method will include a side-bar or a menu
        // button 1 - 4 for questions
        ansBtn.setBounds(150,100,100,25);
        ansBtn.setFocusable(false);
        ansBtn.addActionListener(this);
        // Second Ans Button
        ans2btn2.setBounds(150,215,100,25);
        ans2btn2.setFocusable(false);
        ans2btn2.addActionListener(this);
        // Third Ans Button
        ans3btn3.setBounds(150,330,100,25);
        ans3btn3.setFocusable(false);
        ans3btn3.addActionListener(this);
        // Fourth Ans Button
        ans4btn4.setBounds(150,445,100,25);
        ans4btn4.setFocusable(false);
        ans4btn4.addActionListener(this);

        // adding buttons to our frame
        frame.add(ans2btn2);
        frame.add(ans3btn3);
        frame.add(ans4btn4);
        frame.add(bckBtn);
        frame.add(ansBtn);

        // adding our text
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);


        // general frame functions
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(750,600);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ansBtn) {
            frame.setVisible(false);
            AnswersPG2 window = new AnswersPG2(employee);
        }else if(e.getSource() == ans2btn2) {
            frame.setVisible(false);
            AnswersPG2 window = new AnswersPG2(employee);
        }else if(e.getSource() == ans3btn3) {
            frame.setVisible(false);
            AnswersPG2 window = new AnswersPG2(employee);
        }else if(e.getSource() == ans4btn4) {
            frame.setVisible(false);
            AnswersPG2 window = new AnswersPG2(employee);
        }else if(e.getSource() == bckBtn) {
            frame.setVisible(false);
            FAQ faq = new FAQ(employee);
        }


    }
}
