
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.SystemColor;


public class RequestScreen implements ActionListener{

    private JFrame frame = new JFrame();
    private JLabel messageLabel = new JLabel("Requests");
    private JButton returnButton = new JButton("Return");
    private JButton approveButton = new JButton("Approve");
    private JButton denyButton = new JButton("Deny");


    private final JPanel panel = new JPanel();
    private JTable table;
  	DefaultTableModel model;
    Object[] column;
    Object[] row;


    public RequestScreen(){ //fix so table removes accepted/denied request


        //sets return button
        returnButton.setBounds(500, 584, 130, 40);
        returnButton.addActionListener(this);

        //sets reply button
        approveButton.setBounds(40, 100, 130, 30);
        approveButton.addActionListener(this);

        //sets reply button
        denyButton.setBounds(520, 100, 120, 30);
        denyButton.addActionListener(this);

        //add buttons
        frame.getContentPane().add(returnButton);
        frame.getContentPane().add(approveButton);
        frame.getContentPane().add(denyButton);

        frame.getContentPane().setBackground(SystemColor.activeCaption);
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));

        //sets frame
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(688, 700);
        frame.getContentPane().setLayout(null);

        ////////////////Table//////////////////
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(46, 146, 590, 411);
        frame.getContentPane().add(scrollPane);
        table = new JTable();
        model = new DefaultTableModel(){
          public Class<?> getColumnClass(int column){
            if(column == 4)
              return Boolean.class;
            else
              return String.class;
          }
        };
        Object[] column = {"First Name","Last Name", "ID", "Date Range", "Select"};
        Object[] row = new Object[5];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        for(Request req: RequestList.pendingList){
          row[0] = req.getEmployeeFirstName();
          row[1] = req.getEmployeeLastName();
          row[2] = req.getEmployeeId();
          row[3] = req.getDate1() + " - " + req.getDate2();
          row[4] = false;
          model.addRow(row);
        }

        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(table);
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn messageColumn = columnModel.getColumn(3);
        messageColumn.setPreferredWidth(400);


        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(46, 11, 590, 68);

        frame.getContentPane().add(panel);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel);
        messageLabel.setFont(new Font("Tahoma", Font.BOLD, 28));

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==returnButton) {
          frame.dispose();
          MessageManagerScreen mms = new MessageManagerScreen();
      } else if(e.getSource()==approveButton) {//fixxxxxx not picking up button
        int id = -1;
        int selectedMessages = 0;
        Boolean selected;
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 4) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
          if(selected){
            selectedMessages++;
            id = Integer.valueOf(table.getValueAt(i, 2).toString());
          }
        }

        if (selectedMessages >= 1){
          if(JOptionPane.showConfirmDialog(null, "Are you sure you wish to accept this request", "Confirm Aprrove", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            selected = false;
            for(int i = 0; i < table.getRowCount(); i++){
              if(table.getValueAt(i, 4) == null)
                selected = false;
              else
                selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
              if(selected){
                System.out.println("here\n");
                for(Request req : RequestList.pendingList){
                  if(req.inPending()){
                    System.out.println("here\n");
                    System.out.println(req.getEmployeeId());
                    Request tmpRequest = req;
                    req.removePendingRequest();
                    tmpRequest.setStatus("accepted");
                    tmpRequest.addAcceptedRequest();
                    System.out.println(tmpRequest.getEmployeeId());
                  }
                }
              }
            }
            if(selectedMessages == 1)
              JOptionPane.showMessageDialog(null, "Employee's request has been approved");
            else
              JOptionPane.showMessageDialog(null, "Employees' requests have been approved");
            frame.dispose();
            RequestScreen rs = new RequestScreen();
          }
        }else
          JOptionPane.showMessageDialog(null, "Please select at least one request to be approved");
      } else if (e.getSource()==denyButton) {
        Boolean selected;
        int selectedMessages = 0;
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 4) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
          if(selected)
            selectedMessages++;

        }
        selected = false;
        if(selectedMessages >= 1){
          if(JOptionPane.showConfirmDialog(null, "Are you sure you wish to deny this request", "Confirm Aprrove", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
          for(int i = 0; i < table.getRowCount(); i++){
              if(table.getValueAt(i, 4) == null)
                selected = false;
              else
                selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
              if(selected){
                for(Request req: RequestList.pendingList){
                  if(req.getEmployeeId() == Integer.valueOf(table.getValueAt(i, 2).toString())){
                    Request tmpRequest = req;
                    req.removePendingRequest();
                    tmpRequest.setStatus("denied");
                    tmpRequest.addDeniedRequest();
                  }
                }
              }
            if(selectedMessages == 1)
              JOptionPane.showMessageDialog(null, "Employee's request has been denied");
            else if (selectedMessages > 1)
              JOptionPane.showMessageDialog(null, "Employees' requests have been denied");
            frame.dispose();
            RequestScreen rs = new RequestScreen();
          }
        }
      }else
        JOptionPane.showMessageDialog(null, "Please select at least one request to be denied");
      }
    }

    public static void main(String[] args){
      new RequestScreen();
    }
}
