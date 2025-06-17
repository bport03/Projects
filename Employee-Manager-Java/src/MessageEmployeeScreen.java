
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.SystemColor;


public class MessageEmployeeScreen implements ActionListener{

    private Employee employee = null;
    private JFrame frame = new JFrame();
    private JLabel messageLabel = new JLabel("Messages");
    private JButton returnButton = new JButton("Return");
    private JButton messageButton = new JButton("Message Manager");
    //private JButton replyButton = new JButton("Reply");
    private JButton fullSizeButton = new JButton("View Message");
    private JButton removeButton = new JButton("Remove");


    private final JPanel panel = new JPanel();
    private JTable table;
  	DefaultTableModel model;


    public MessageEmployeeScreen(Employee employee){
        this.employee = employee;

        //sets return button
        returnButton.setBounds(500, 584, 175, 40);
        returnButton.addActionListener(this);

        //sets create button
        messageButton.setBounds(40, 100, 160, 30);
        messageButton.addActionListener(this);

        //sets reply button
        //replyButton.setBounds(370, 100, 120, 30);
        //replyButton.addActionListener(this);
        //sets fullSize button
        fullSizeButton.setBounds(280, 100, 120, 30);
        fullSizeButton.addActionListener(this);

        //sets remove button
        removeButton.setBounds(520, 100, 120, 30);
        removeButton.addActionListener(this);

        //add buttons
        frame.getContentPane().add(returnButton);
        frame.getContentPane().add(messageButton);
        //frame.getContentPane().add(replyButton);
        frame.getContentPane().add(fullSizeButton);
        frame.getContentPane().add(removeButton);

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
        Object[] column = {"From", "Message", "Date", "Time", "Select"};
        Object[] row = new Object[5];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        for(Message message: MessageList.managerMessages){
          if(message.getToId() == employee.getId()){
            row[0] = "Manager";
            row[1] = message.getMessage();
            row[2] = message.getDate();
            row[3] = message.getTime();
            row[4] = false;
            model.addRow(row);
          }
        }

        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(table);
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn messageColumn = columnModel.getColumn(1);
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
          EmpScreen screen = new EmpScreen(employee);
      }
      if(e.getSource()==messageButton){
        String  m = JOptionPane.showInputDialog("Type your Message:");
        if (m == null)
          ;
        else{
          Message tempMessage = new Message(employee.getFirstName(), employee.getLastName(), employee.getId(), -2, m);
          tempMessage.addEmployeeMessage();
          JOptionPane.showMessageDialog(null, "Message Sent");
        }

      /*} else if(e.getSource()==replyButton) {//fixxxxxx not picking up button
        int selectedMessages = 0;
        Boolean selected;
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 4) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
          if(selected)
            selectedMessages++;
        }
        if(selectedMessages == 1){
          String  m = JOptionPane.showInputDialog("Type your Message:");
          if (m == null){
            ;
          }else{
            Message tempMessage = new Message(employee.getFirstName(), employee.getLastName(), employee.getId(), -2, m);
            tempMessage.addEmployeeMessage();
            for(Message mess : MessageList.managerMessages)
              if(mess.getToId() == employee.getId())
                mess.removeManagerMessage();
            JOptionPane.showMessageDialog(null, "Message sent");
            MessageEmployeeScreen mscreen = new MessageEmployeeScreen(this.employee);
          }


        }else if (selectedMessages > 1)
          JOptionPane.showMessageDialog(null, "You can only reply to one message at a time");
        else
          JOptionPane.showMessageDialog(null, "Please select at least one Message to be removed");*/
      }else if(e.getSource()==fullSizeButton){
        boolean selected = false;
        int selectedMessages = 0;
        String f = "Manager";
        String m = "";
        String d = "";
        String t = "";
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 4) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
          if(selected){
            selectedMessages++;
            m = table.getValueAt(i, 1).toString();
            t = table.getValueAt(i, 2).toString();
            d = table.getValueAt(i, 3).toString();
          }
        }
        if(selectedMessages == 1)
          JOptionPane.showMessageDialog(null, m + "\n\nfrom: " + f + "\nsent at: " + d + "\ndated: " + t);
        else if(selectedMessages > 1)
          JOptionPane.showMessageDialog(null, "You can only view on message at a time");
        else
          JOptionPane.showMessageDialog(null, "Please select at least one Message to be viewed");

      }else if (e.getSource()==removeButton) {
        int selectedMessages = 0;
        int id;
        Boolean selected;
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 4) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
          if(selected)
            selectedMessages++;
        }
        if(selectedMessages >= 1){
          if(JOptionPane.showConfirmDialog(null, "Are you sure you wish to remove this message", "Confirm Aprrove", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            for(int i = 0; i < table.getRowCount(); i++){
              if(table.getValueAt(i, 4) == null)
                selected = false;
              else
                selected = Boolean.valueOf(table.getValueAt(i, 4).toString());
              if(selected){
                for(Message message : MessageList.managerMessages){
                  if(message.getToId() == employee.getId()){
                    message.removeManagerMessage();
                  }
                }
              }

            }
            if(selectedMessages == 1)
              JOptionPane.showMessageDialog(null, "Message deleted");
            else if (selectedMessages > 1)
              JOptionPane.showMessageDialog(null, "Messages deleted");
            }
            frame.dispose();
            MessageEmployeeScreen mes = new MessageEmployeeScreen(this.employee);
        } else
          JOptionPane.showMessageDialog(null, "Please select at least one Message to be removed");
      }
    }
}
