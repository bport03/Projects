package sql.src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class RemoveEmpWindow {

	private JFrame frame;
	DefaultTableModel model;
	private JTextField EmpIdEntry;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveEmpWindow window = new RemoveEmpWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RemoveEmpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 464, 289);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				ManagerWindow managerWindow = new ManagerWindow();
                managerWindow.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 216, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Confirmation of employee deletion
				for(Employee emp : Roster.employeesList)
				{
					if(emp.getId() == Integer.parseInt(EmpIdEntry.getText()))
					{
						if(JOptionPane.showConfirmDialog(frame, "Is this the correct employee: \n" 
																+ "First Name: " + emp.getFirstName() + "\n" 
																+ "Last Name: " + emp.getLastName() + "\n"
																+ "Social Security Number: " + emp.getSocialSecurity() + "\n"
																+ "Date Of Birth: " + emp.getDateOfBirth() + "\n"
																+ "Employee Id: " + emp.getId() + "\n"
																+ "Hourly Wage: " + emp.getHourlyWage()
																, "Cormirm employee deletion",
																JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION)
						{
							Roster.employeesList.remove(emp);
							Roster.saveEmployees();
							frame.dispose();
							JOptionPane.showMessageDialog(null,"Employee Successfully Removed!");
							ManagerWindow managerWindow = new ManagerWindow();
		                    managerWindow.main(null);
						}
						else
						{
							frame.dispose();
							RemoveEmpWindow removeWin = new RemoveEmpWindow();
							removeWin.main(null);
							return;
						}
						
					}
				}
				JOptionPane.showMessageDialog(null, "No employee with id: " + EmpIdEntry.getText());
			}
			});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(152, 153, 121, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		EmpIdEntry = new JTextField();
		EmpIdEntry.setBounds(129, 96, 168, 30);
		frame.getContentPane().add(EmpIdEntry);
		EmpIdEntry.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(27, 31, 401, 38);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Enter id of the employee to be removed: ");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		model = new DefaultTableModel();
		Object[] column = {"First","Last","Social","DOB","ID","HourlyWage"};
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
	}
}