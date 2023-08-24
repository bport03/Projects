package sql.src;

import java.awt.Color;
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

public class ManagerWindow
{
	private JFrame frame;
	private JTable table;
	DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerWindow window = new ManagerWindow();
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
	public ManagerWindow() {
		initialize();
	}
	
	private void initialize() 
	{
		
		frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.activeCaption);
        frame.setBounds(0, 0, 750, 656);
   //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        ////////////////Table//////////////////
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 146, 632, 411);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"First Name","Last Name","Social","DOB","ID","HourlyWage"};
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		//////////////load Employee data into table /////////////////////
		for(Employee emp: Roster.employeesList)
		{
			row[0] = emp.getFirstName();
			row[1] = emp.getLastName();
			row[2] = emp.getSocialSecurity();
			row[3] = emp.getDateOfBirth();
			row[4] = emp.getId();
			row[5] = emp.getHourlyWage();
			model.addRow(row);
		}
		
	
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(46, 22, 632, 59);
        frame.getContentPane().add(panel);

        JLabel managerLabel = new JLabel("Manager");
        managerLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(managerLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.inactiveCaption);
        panel_1.setBounds(46, 107, 632, 37);
        frame.getContentPane().add(panel_1);
        
        JButton btnAddEmployee = new JButton("Add Employee");
        btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_1.add(btnAddEmployee);
        btnAddEmployee.setForeground(new Color(0, 0, 0));
        btnAddEmployee.setBackground(SystemColor.inactiveCaption);
                
        JButton btnRemoveEmployee = new JButton("Remove Employee");
        btnRemoveEmployee.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        		frame.dispose();
                RemoveEmpWindow removeWindow = new RemoveEmpWindow();
                removeWindow.main(null);
        	}
        });
        btnRemoveEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_1.add(btnRemoveEmployee);
        btnRemoveEmployee.setBackground(SystemColor.inactiveCaption);
                        
        JButton btnEditSchedule = new JButton("Edit Schedule");
        btnEditSchedule.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        		frame.dispose();
        	EditScheduleScreen editSchedule = new EditScheduleScreen();
        	}
        });
        btnEditSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_1.add(btnEditSchedule);
        btnEditSchedule.setBackground(SystemColor.inactiveCaption);
        
        JButton btnMessages = new JButton("Check Messages");
		btnMessages.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			frame.dispose();
					MessageManagerScreen mes = new MessageManagerScreen();
		}
		});
		btnMessages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(btnMessages);
		btnMessages.setBackground(SystemColor.inactiveCaption);
                                        
        JButton btnFAQ = new JButton("FAQ");
        btnFAQ.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_1.add(btnFAQ);
        btnFAQ.setBackground(SystemColor.inactiveCaption);                                                        

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(325, 568, 59, 29);
        frame.getContentPane().add(btnExit);
        btnExit.setBackground(SystemColor.inactiveCaption);
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                frame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(frame, "You sure you want to exit", "Login Systems",
                        JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnFAQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== btnFAQ) {
                   ManagerFAQ FAQ = new ManagerFAQ();
                }
            }
        });
                                                
        btnAddEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== btnAddEmployee) {
                	frame.dispose();
                    AddEmpWindow addWindow = new AddEmpWindow();
                    AddEmpWindow.main(null);
                }
//                else if(e.getSource()==btnEditSchedule) {
////                	frame.dispose();
//                	EditScheduleScreen editSchedule = new EditScheduleScreen();
//
//                }
            }
        });

		
	}
}
