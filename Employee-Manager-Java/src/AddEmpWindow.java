
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

public class AddEmpWindow {

	private JFrame frame;
	DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmpWindow window = new AddEmpWindow();
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
	public AddEmpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 417, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		model = new DefaultTableModel();
		Object[] column = {"First","Last","Social","DOB","ID","HourlyWage"};
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);

		
		JLabel lblDOB = new JLabel("Date of Birth:");
		lblDOB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDOB.setBounds(10, 174, 101, 27);
		frame.getContentPane().add(lblDOB);
		
		JLabel lblHW = new JLabel("Hourly Wage:");
		lblHW.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHW.setBounds(10, 250, 101, 27);
		frame.getContentPane().add(lblHW);
		
		JLabel lblID = new JLabel("Employee Id:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(10, 212, 101, 27);
		frame.getContentPane().add(lblID);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstName.setBounds(10, 60, 76, 27);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblSSN = new JLabel("Social Security:");
		lblSSN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSSN.setBounds(10, 136, 101, 27);
		frame.getContentPane().add(lblSSN);
		
		JFormattedTextField firstNameEntry = new JFormattedTextField();
		firstNameEntry.setBounds(117, 60, 203, 27);
		frame.getContentPane().add(firstNameEntry);
		
		JFormattedTextField lastNameEntry = new JFormattedTextField();
		lastNameEntry.setBounds(117, 98, 203, 27);
		frame.getContentPane().add(lastNameEntry);
		
		JFormattedTextField SSNEntry = new JFormattedTextField();
		SSNEntry.setBounds(117, 136, 203, 27);
		frame.getContentPane().add(SSNEntry);
		
		JFormattedTextField DOBEntry = new JFormattedTextField();
		DOBEntry.setBounds(117, 174, 203, 27);
		frame.getContentPane().add(DOBEntry);
		
		JFormattedTextField IDEntry = new JFormattedTextField();
		IDEntry.setBounds(117, 212, 203, 27);
		frame.getContentPane().add(IDEntry);
		
		JFormattedTextField HWEntry = new JFormattedTextField();
		HWEntry.setBounds(117, 250, 203, 27);
		frame.getContentPane().add(HWEntry);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(10, 98, 76, 27);
		frame.getContentPane().add(lblLastName);
		

		//Add employee to JTable after filling information
		JButton btnAddEmp = new JButton("Add Employee");
		btnAddEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validEmployee = false;
				
				if(firstNameEntry.getText().equals("") ||lastNameEntry.getText().equals("")||SSNEntry.getText().equals("")||DOBEntry.getText().equals("")
						||IDEntry.getText().equals("")||HWEntry.getText().equals("")) {
						
					JOptionPane.showMessageDialog(null,"Fill in every entry first.");
				}
				else if(checkHW(HWEntry.getText()) && checkDOB(DOBEntry.getText()) && checkSSN(SSNEntry.getText()) && checkID(IDEntry.getText()))
				{
					validEmployee = true;
				}
			
				if(validEmployee)
				{
					//If here all entry points are valid
					Employee newEmp = new Employee();
		
					newEmp.setFirstName(firstNameEntry.getText());
					newEmp.setLastName(lastNameEntry.getText());
					newEmp.setSocialSecurity(Integer.parseInt(SSNEntry.getText()));
					newEmp.setDateOfBirth(Integer.parseInt(DOBEntry.getText()));
					newEmp.setId(Integer.parseInt(IDEntry.getText()));
					newEmp.setHourlyWage(Double.parseDouble(HWEntry.getText()));
					Roster.addEmp(newEmp);
			
					frame.dispose();
					JOptionPane.showMessageDialog(null,"Employee Successfully Added.");
					ManagerWindow managerWindow = new ManagerWindow();
                    managerWindow.main(null);
				}
	
			}
		});
		btnAddEmp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddEmp.setBounds(128, 305, 178, 37);
		frame.getContentPane().add(btnAddEmp);
		
		JPanel HeaderPanel = new JPanel();
		HeaderPanel.setToolTipText("");
		HeaderPanel.setBackground(SystemColor.inactiveCaption);
		HeaderPanel.setBounds(20, 11, 365, 37);
		frame.getContentPane().add(HeaderPanel);
		
		JLabel HeaderLabel = new JLabel("Enter Employee Info:");
		HeaderLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		HeaderPanel.add(HeaderLabel);		
		
	}
	
	
	private boolean checkHW(String entry)
	{
		boolean isValid = false;
		
		try
		{
			Double.parseDouble(entry);
			isValid = true;
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Hourly wage must be a decimal number, please enter again.");
		}
		
		return isValid;
	}
	
	private boolean checkDOB(String entry)
	{
		boolean isValid = false;
		
		try
		{
			Integer.parseInt(entry);
			isValid = true;
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Date of Birth must be a number in format mmddyyyy, please enter again.");
		}
		
		if(entry.length() != 8)
		{
			JOptionPane.showMessageDialog(null, "Date of Birth must be 8 digits long, mmddyyyy.");
			isValid = false;
		}
		
		return isValid;
	}
	
	private boolean checkSSN(String entry)
	{
		boolean isValid = false;
		
		try
		{
			Integer.parseInt(entry);
			isValid = true;
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Social Security Number must be a number in format #########, please enter again.");
		}
		
		if(entry.length() != 9)
		{
			JOptionPane.showMessageDialog(null, "Social Security Number must be 9 digits long, #########.");
			isValid = false;
		}
		
		
		return isValid;
	}
	
	private boolean checkID(String entry)
	{
		boolean isValid = false;
		
		try
		{
			Integer.parseInt(entry);
			isValid = true;
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "ID must be a number, please enter again.");
			return false;
		}	
		
		for(Employee emp : Roster.employeesList)
		{
			if(emp.getId() == Integer.parseInt(entry))
			{
				JOptionPane.showMessageDialog(null, "ID already used, please enter again.");
				return false;
			}
		}
		
		return isValid;
	}
}