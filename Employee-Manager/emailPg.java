package sql.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.util.Properties;
// needs java mail api .jar folder not included
//import javax.mail.Session;
//import javax.mail.PasswordAuthentication;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.Transport;


public class emailPg implements ActionListener {
    Employee employee = null;
    JFrame frame = new JFrame();
    JButton BackBtn = new JButton("Back");
    JButton Send = new JButton("Send");

    JLabel Header = new JLabel("E-mail");

    // Email Labels
    JLabel ToLBL = new JLabel("To:");
    JLabel FromLBL = new JLabel("From:");
    JLabel SubjectLBL = new JLabel("Subject:");
    JLabel MessageLBL = new JLabel("Message:");

    // Email TextFields
    JTextField ToTXT = new JTextField();
    JTextField FromTXT = new JTextField();
    JTextField SubjectTXT = new JTextField();
    JTextField MessageTXT = new JTextField();

    emailPg(Employee employee) {
        // Email Layout
        // "To" LABEL
        ToLBL.setBounds(40, 50, 500, 50);
        ToLBL.setFocusable(true);
        frame.add(ToLBL);

        // "From" LABEL
        FromLBL.setBounds(25, 100, 500, 50);
        FromLBL.setFocusable(true);
        frame.add(FromLBL);

        // "Subject" LABEL
        SubjectLBL.setBounds(10, 150, 500, 50);
        SubjectLBL.setFocusable(true);
        frame.add(SubjectLBL);

        // "Message" LABEL
        MessageLBL.setBounds(3, 185, 500, 50);
        MessageLBL.setFocusable(true);
        frame.add(MessageLBL);

        /* ************************************************************ */

        // "To" TEXT FIELD
        ToTXT.setBounds(60, 70, 500, 25);
        ToTXT.setFocusable(true);
        frame.add(ToTXT);

        // "From" TEXT FIELD
        FromTXT.setBounds(60, 115, 500, 25);
        FromTXT.setFocusable(true);
        frame.add(FromTXT);

        // "Subject" TEXT FIELD
        SubjectTXT.setBounds(60, 165, 500, 25);
        SubjectTXT.setFocusable(true);
        frame.add(SubjectTXT);

        // "Message" TEXT FIELD
        MessageTXT.setBounds(60, 220, 500, 175);
        MessageTXT.setFocusable(true);
        frame.add(MessageTXT);

        /* ************************************************************ */

        // email button
        Send.setBounds(250, 425, 75, 25);
        Send.setFocusable(false);
        Send.addActionListener(this);
        frame.add(Send);

        // header
        Header.setBounds(60, 5, 150, 45);
        Header.setFont(new Font("Tohoma", Font.BOLD, 35));
        frame.add(Header);

        // Back to question page
        BackBtn.setBounds(550, 500, 75, 40);
        BackBtn.setFocusable(false);
        BackBtn.addActionListener(this);
        frame.add(BackBtn);

        // Logo
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        // general frame functions
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(750, 600);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    // EMAIL FUNCTION
//    private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {
//        String ToEmail = ToTXT.getText();
//        String FromEmail = FromTXT.getText(); // Your Email
//        String FormEmailPassword = "1234"; // Your password
//        String FromSubject = SubjectTXT.getText();
//        String Message = MessageTXT.getText();
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "102");
//
//        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication(){
//                return new PasswordAuthentication(FromEmail,FromEmailPassword);
//           }
//        });
//        try{
//            Mimemessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FromEmail));
//            message.addRecipient(Message.RecipientType.TO, new InternetAdress(ToEmail));
//            message.setSubject(FromSubject);
//            message.setText(jTextAreal.getText());
//            Transport.send(message);
//
//        }catch(Exception ex) {
//            System.out.println(""+ex);
//        }
//     }

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == BackBtn) {
                frame.setVisible(false);
                QuestionPg window = new QuestionPg(employee);
            }else if (e.getSource() == Send){
                JOptionPane.showMessageDialog(null, "Send Email", "Email", JOptionPane.YES_NO_CANCEL_OPTION);
            }
    }
}
