package sql.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerFAQ implements ActionListener {
    JFrame frame = new JFrame();
    JButton returnButton = new JButton("Return");
    JLabel label = new JLabel("Click on any button to view the answer.");
    JButton ansBtn = new JButton("Ans");
    JButton ans2btn2 = new JButton("Ans");
    JButton ans3btn3 = new JButton("Ans");
    JButton ans4btn4 = new JButton("Ans");
    JLabel label2 = new JLabel("What do I do if I added the wrong employee information?");
    JLabel label3 = new JLabel("What do I do if I removed the wrong employee?");
    JLabel label4 = new JLabel("Is there a way to re-add an employee?");
    JLabel label5 = new JLabel("Can I modify a current employees wage?");

    ManagerFAQ() {

        // sentence bounds
        label.setBounds(0, 0, 500, 50);
        label.setFont(new Font(null, Font.BOLD, 25));
        //
        label2.setBounds(0, 0, 700, 135);
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
        // questions
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

        // return button listen
        returnButton.setBounds(300, 500, 200, 40);
        returnButton.addActionListener(this);

        // add buttons to screen
        frame.add(ansBtn);
        frame.add(ans2btn2);
        frame.add(ans3btn3);
        frame.add(ans4btn4);

        // adding our text
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);

        // back buttons
        frame.getContentPane().add(returnButton);

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        label.setBounds(0,0,500,50);
        label.setFont(new Font(null, Font.BOLD, 25));

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,600);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==returnButton) {
            frame.dispose();
            ManagerWindow window = new ManagerWindow();
        }
        if (e.getSource() == ansBtn) {
            frame.setVisible(false);
            ManagerAnswers window = new ManagerAnswers();
        }
        if (e.getSource() == ans2btn2) {
            frame.setVisible(false);
            ManagerAnswers window = new ManagerAnswers();
        }
        if (e.getSource() == ans3btn3) {
            frame.setVisible(false);
            ManagerAnswers window = new ManagerAnswers();
        }
        if (e.getSource() == ans4btn4) {
            frame.setVisible(false);
            ManagerAnswers window = new ManagerAnswers();
        }

    }
}
