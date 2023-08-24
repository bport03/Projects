package sql.src;//package Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class EditScheduleScreen implements ActionListener{
	Employee employee = null;
    JFrame frame = new JFrame();
    JLabel scheduleLabel = new JLabel("Schedule");
    JLabel returnLabel = new JLabel("Return to previous page");
    JButton returnButton = new JButton("Return");
    static JTable table_1;
    private final JPanel panel = new JPanel();

    public EditScheduleScreen(){
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
        DefaultTableModel dft = new DefaultTableModel();
        Object[][] objArray = new Object[2][7];
        

		      
//					existingData = Schedule.amList.get(col).toString();
//					table_1.setValueAt("<html>" + existingData + "</html>", row, col);

		        
//		        existingData = existingData.replaceAll("<html>", "");
//				existingData = existingData.replaceAll("</html>", "");
				
				
	
        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        		
        	objArray,

        	new String[] {
        		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        	}
        ));
        String existingData = "";
        for(int col = 0; col < Schedule.amList.size();col++) {
        	if (!Schedule.amList.get(col).isEmpty()) {
	        	existingData = Schedule.amListToString(col);
	        	table_1.setValueAt(existingData, 0, col);
        	}
        	if (!Schedule.pmList.get(col).isEmpty()) {
	        	existingData = Schedule.pmListToString(col);
	        	table_1.setValueAt(existingData, 1, col);
        	}
        }
        
        

        
//        table_1.setValueAt(s, 1, 1);
        table_1.setForeground(Color.black);
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
        
        JButton btnAddSunday = new JButton("Add Employee");
        btnAddSunday.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
//        		frame.dispose();
        	AddToScheduleWindow addToSchedule = new AddToScheduleWindow(table_1);
        	addToSchedule.main(null);
        	}
        });
        btnAddSunday.setBounds(62, 210, 140, 23);
        frame.getContentPane().add(btnAddSunday);
        frame.setVisible(true);
 
        
    }

    private TableModel dft(Object[][] objects, String[] strings) {
		// TODO Auto-generated method stub
		return null;
	}

	public void EditScheduleScreen(JTable table_1) {
		this.table_1 = table_1;
	}

	@Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==returnButton) {
            frame.dispose();
            ManagerWindow managerWindow = new ManagerWindow();
            managerWindow.main(null);
        }

    }


}