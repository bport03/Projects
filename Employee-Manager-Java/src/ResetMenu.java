
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

public class ResetMenu extends JFrame {

    private JPanel contentPane;
    private JTextField usernameInput;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPassword;

    /**
     * Launch the application.
     * @return
     */
    public static void Rest() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ResetMenu frame = new ResetMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    // this function call would work for reset password for user when reset password is being press

    public ResetMenu() {
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 635, 544);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Enter UserID:");
        lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        lblNewLabel.setBounds(12, 45, 158, 16);
        contentPane.add(lblNewLabel);

        usernameInput = new JTextField();
        usernameInput.setBounds(260, 32, 262, 29);
        contentPane.add(usernameInput);
        usernameInput.setColumns(10);

        JLabel newPassword = new JLabel("Enter New Password:");
        newPassword.setForeground(new Color(0, 0, 0));
        newPassword.setFont(new Font("Courier New", Font.PLAIN, 20));
        newPassword.setBounds(12, 140, 241, 16);
        contentPane.add(newPassword);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(260, 127, 262, 29);
        contentPane.add(newPasswordField);

        JLabel ConfirmPassword = new JLabel("Confirm Password:");
        ConfirmPassword.setForeground(new Color(0, 0, 0));
        ConfirmPassword.setFont(new Font("Courier New", Font.PLAIN, 20));
        ConfirmPassword.setBounds(12, 220, 204, 16);
        contentPane.add(ConfirmPassword);

        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(260, 203, 262, 29);
        contentPane.add(confirmPassword);

        JButton btnNewButton = new JButton("Confirm ");
        btnNewButton.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 30));
        btnNewButton.setBounds(193, 389, 204, 43);
        contentPane.add(btnNewButton);
    }
}