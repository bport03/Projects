package sql.src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class AddToScheduleWindow {

	private JFrame frame;
	DefaultTableModel model;
	private JTextField EmpIdEntry;
	private JRadioButton amRadio;
	private JRadioButton pmRadio;
	private ButtonGroup buttonGroup;
	private JComboBox<Employee> employeeCombo;
	private JComboBox<String> dayCombo;
	static JTable table_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddToScheduleWindow window = new AddToScheduleWindow(table_1);
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
	public AddToScheduleWindow(JTable table_1) {
		this.table_1 = table_1;
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
		frame.setBounds(100, 100, 464, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				//EditScheduleScreen schedule = new EditScheduleScreen(table_1);
//                schedule.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 276, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
//		EmpIdEntry = new JTextField();
//		EmpIdEntry.setBounds(129, 96, 168, 30);
//		frame.getContentPane().add(EmpIdEntry);
//		EmpIdEntry.setColumns(10);
		
		employeeCombo = new JComboBox<Employee>(new DefaultComboBoxModel<Employee>(Roster.employeesList.toArray(new Employee[0])));
		employeeCombo.setBounds(129, 96, 168, 30);
		frame.getContentPane().add(employeeCombo);
		
		
		String [] daysOfWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		dayCombo = new JComboBox<>(new DefaultComboBoxModel<>(daysOfWeek));
		dayCombo.setBounds(129, 136, 168, 30);	
		frame.getContentPane().add(dayCombo);
		
		amRadio = new JRadioButton();
		pmRadio = new JRadioButton();
		buttonGroup = new ButtonGroup();
		amRadio.setText("am shift");
		pmRadio.setText("pm shift");
		amRadio.setBounds(129,176,75,30);
		pmRadio.setBounds(239,176,75,30);
		frame.getContentPane().add(amRadio);
		frame.getContentPane().add(pmRadio);
		buttonGroup.add(amRadio);
		buttonGroup.add(pmRadio);




		
		
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				
				String nameOfFile = "Schedule.txt";
				File file = new File(nameOfFile);
				Object empSelected = employeeCombo.getSelectedItem();
				String daySelected = (String) dayCombo.getSelectedItem();
				ArrayList<Employee> employeeList = Roster.employeesList;
				//need to get rid of schedule saver have two lists instead 
				try {
					file = new File(nameOfFile);
					file.createNewFile();
		 			BufferedWriter scheduleSaver = new BufferedWriter(new FileWriter(file));
				
		 			//Confirmation of employee deletion
					for(Employee emp : Roster.employeesList)
					{
						if(emp == empSelected)
						{
							int row = 0, col = 0;
							if(daySelected.equalsIgnoreCase("Sunday")) {
								col = 0;
								
							}
	
							else if(daySelected.equalsIgnoreCase("Monday")){
								col = 1;
				
							}
							else if(daySelected.equalsIgnoreCase("Tuesday")){
								col = 2;
	
							}
							else if(daySelected.equalsIgnoreCase("Wednesday")){
								col = 3;
	
							}
							else if(daySelected.equalsIgnoreCase("Thursday")){
								col = 4;
	
							}
							else if(daySelected.equalsIgnoreCase("Friday")){
								col = 5;
	
							}
							else {
								col = 6;
	
							}
							//boolean for radio buttons am or pm shift
							// if none selected am is assumed
							if(pmRadio.isSelected()) {
								row=1;
							}
							
							String existingData = "";
							if ( table_1.getValueAt(row, col) != null) {
								//br is same as new line
								existingData = table_1.getValueAt(row, col).toString() +  "<br>";
							}
							
//							System.out.println(daySelected+" "+emp);
							//only need one html tag so if it's in already replace
							existingData = existingData.replaceAll("<html>", "");
							existingData = existingData.replaceAll("</html>", "");
	
							if(!existingData.contains(emp.toString())) {
								table_1.setValueAt("<html>" + existingData + emp + "</html>", row, col);
								//assignEmployee calls method to add to specific file
								Schedule.assignEmployee(col, emp, row == 0);
//								scheduleSaver.write(col+" "+emp.toString()+" "+(row==0));	
							}
							
							
							
							frame.dispose();
							scheduleSaver.flush();
							scheduleSaver.close();
							return;
							
				            
							}	
						}
					}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
				
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(152, 216, 121, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(27, 31, 401, 38);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Select Employee, Day and Shift to add: ");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		model = new DefaultTableModel();
		Object[] column = {"First","Last","Social","DOB","ID","HourlyWage"};
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
	}
}