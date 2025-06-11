package sql.src;//package Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

public class ScheduleScreen implements ActionListener{
	Employee employee = null;
    JFrame frame = new JFrame();
    JLabel scheduleLabel = new JLabel("Schedule");
    JLabel successLabel = new JLabel("Employee View");
    JLabel returnLabel = new JLabel("Return to previous page");
    JButton returnButton = new JButton("Return");
    private JTable table_1;
    private final JPanel panel = new JPanel();

    public ScheduleScreen(Employee employee){
    	this.employee = employee;
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //sets success label
        successLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        successLabel.setBounds(29, 167, 681, 60);
        frame.getContentPane().add(successLabel);
        returnButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        //sets return button
        returnButton.setBounds(291, 543, 166, 41);
        returnButton.addActionListener(this);
        frame.getContentPane().add(returnButton);
        frame.getContentPane().setBackground(SystemColor.activeCaption);

   //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.getContentPane().setLayout(null);
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(29, 20, 712, 60);
        
        frame.getContentPane().add(panel);
        panel.add(scheduleLabel);
        scheduleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        frame.setVisible(true);
        
        ////////schedule
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(62, 243, 673, 343);
        frame.getContentPane().add(scrollPane);
        Object[][] objArray = new Object[2][7];
        
        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        		
        	objArray,

        	new String[] {
        		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        	}
        ));
        String existingData = "";
        for(int row = 0; row<objArray.length;row++) {
			for(int col = 0; col < objArray[row].length;col++) {
				for(int i = 0; i < Schedule.amList.size();i++) {
					if(!Schedule.amList.get(col).isEmpty()&&row==0) {
						existingData = Schedule.amList.get(col).toString();

						table_1.setValueAt("<html>"+existingData+"</html>", row, col);


				
					}
				}
				for(int j = 0; j<Schedule.pmList.size();j++) {
					if(!Schedule.pmList.get(col).isEmpty()&&row==1) {
						existingData = Schedule.pmList.get(col).toString();
						table_1.setValueAt("<html>"+existingData+"</html>", row, col);

					}
				}
			}
        }
        

        table_1.setForeground(Color.BLUE);
        table_1.setRowHeight(100);
        scrollPane.setViewportView(table_1);
        
        JLabel lblAM = new JLabel("AM");
        lblAM.setHorizontalAlignment(SwingConstants.CENTER);
        lblAM.setBounds(7, 296, 45, 49);
        frame.getContentPane().add(lblAM);
        
        JLabel lblPM = new JLabel("PM");
        lblPM.setHorizontalAlignment(SwingConstants.CENTER);
        lblPM.setBounds(7, 398, 45, 40);
        frame.getContentPane().add(lblPM);
        frame.setVisible(true);
 
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==returnButton) {
            frame.dispose();
            EmpScreen screen = new EmpScreen(employee);
        }

    }
}