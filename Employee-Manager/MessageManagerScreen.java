package sql.src;//package Sprint4Classes;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.SystemColor;


public class MessageManagerScreen implements ActionListener{

    private JFrame frame = new JFrame();
    private JLabel messageLabel = new JLabel("Messages");
    private JButton returnButton = new JButton("Return");
    private JButton messageButton = new JButton("Create Message");
    //private JButton replyButton = new JButton("Reply");
    private JButton fullSizeButton = new JButton("View Message");
    private JButton requestButton = new JButton("Requests");
    private JButton removeButton = new JButton("Remove");


    private final JPanel panel = new JPanel();
    private JTable table;
  	DefaultTableModel model;


    public MessageManagerScreen(){

        //sets return button
        returnButton.setBounds(500, 584, 175, 40);
        returnButton.addActionListener(this);

        //sets create button
        messageButton.setBounds(40, 100, 160, 30);
        messageButton.addActionListener(this);

        //sets reply button
        //replyButton.setBounds(180, 100, 120, 30);
        //replyButton.addActionListener(this);

        //sets fullSize button
        fullSizeButton.setBounds(210, 100, 120, 30);
        fullSizeButton.addActionListener(this);

        //sets remove button
        removeButton.setBounds(360, 100, 120, 30);
        removeButton.addActionListener(this);

        //sets request button
        requestButton.setBounds(520, 100, 120, 30);
        requestButton.addActionListener(this);

        //add buttons
        frame.getContentPane().add(returnButton);
        frame.getContentPane().add(messageButton);
        //frame.getContentPane().add(replyButton);
        frame.getContentPane().add(fullSizeButton);
        frame.getContentPane().add(removeButton);
        frame.getContentPane().add(requestButton);

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
            if(column == 5)
              return Boolean.class;
            else
              return String.class;
          }
        };
        Object[] column = {"From", "Id", "Message", "Date", "Time", "Select"};
        Object[] row = new Object[6];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        for(Message message: MessageList.employeeMessages){
          row[0] = message.getFromFirstName();
          row[1] = message.getFromId();
          row[2] = message.getMessage();
          row[3] = message.getDate();
          row[4] = message.getTime();
          row[5] = false;
          model.addRow(row);
        }

        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(table);
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn messageColumn = columnModel.getColumn(2);
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
          ManagerWindow mw = new ManagerWindow();
          mw.main(null);
      } else if(e.getSource()==requestButton){
        frame.dispose();
        RequestScreen rs = new RequestScreen();
      } else if(e.getSource()==fullSizeButton){
        boolean selected = false;
        int selectedMessages = 0;
        String f = "";
        String m = "";
        String d = "";
        String t = "";
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 5) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 5).toString());
          if(selected){
            selectedMessages++;
            f = table.getValueAt(i, 0).toString();
            m = table.getValueAt(i, 2).toString();
            t = table.getValueAt(i, 3).toString();
            d = table.getValueAt(i, 4).toString();
          }
        }
        if(selectedMessages == 1)
          JOptionPane.showMessageDialog(null, m + "\n\nfrom: " + f + "\nsent at: " + d + "\ndated: " + t);
        else if(selectedMessages > 1)
          JOptionPane.showMessageDialog(null, "You can only view on message at a time");
        else
          JOptionPane.showMessageDialog(null, "Please select at least one Message to be viewed");
      } else if(e.getSource()==messageButton){
        boolean isValid = false;
        int id = -1;
        String entryid = JOptionPane.showInputDialog("Type the employee's id:");
        try{
          id = Integer.parseInt(entryid);
          isValid = false;
          for(Employee emp : Roster.employeesList){
            if(emp.getId() == id)
              isValid = true;
          }
          if (isValid == false)
            JOptionPane.showMessageDialog(null, "Invalid Entry");
        }catch(NumberFormatException a){
          JOptionPane.showMessageDialog(null, "Invalid Entry");
        }

        if(isValid){
          String  m = JOptionPane.showInputDialog("Type your Message:");
          if (m.equals(""))
            JOptionPane.showMessageDialog(null, "Invalid Entry");
          else{
            if(JOptionPane.showConfirmDialog(null, "Are you sure you wish to send this message", "Confirm Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
              Message tempMessage = new Message("Manager", "Manager", -2, id, m);
              tempMessage.addManagerMessage();
              JOptionPane.showMessageDialog(null, "Message Sent");
            }
          }
        }

      /*} else if(e.getSource()==replyButton) {//fixxxxxx not picking up button
        int selectedMessages = 0;
        Boolean selected;
        int id = -1;
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 5) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 5).toString());
          if(selected){
            selectedMessages++;
            if(selectedMessages == 1){
              id = Integer.parseInt(table.getValueAt(i, 1).toString());
            }
          }

        }
        if(selectedMessages == 1){
          String  m = JOptionPane.showInputDialog("Type your Message:");
          if (m == null || m.equals("")){
            JOptionPane.showMessageDialog(null, "Invalid Entry");;
          }else{
            Message tempMessage = new Message("Manager", "Manager", -2, id, m);
            tempMessage.addManagerMessage();
            for(Message mess : MessageList.employeeMessages)
              if(mess.getToId() == id)
                mess.removeEmployeeMessage();
            JOptionPane.showMessageDialog(null, "Message sent");
            MessageManagerScreen mscreen = new MessageManagerScreen();
          }


        }else if (selectedMessages > 1)
          JOptionPane.showMessageDialog(null, "You can only reply to one message at a time");
        else
          JOptionPane.showMessageDialog(null, "Please select at least one Message to be removed");*/
      } else if (e.getSource()==removeButton) {
        int selectedMessages = 0;
        Boolean selected = false;
        int id;
        for(int i = 0; i < table.getRowCount(); i++){
          if(table.getValueAt(i, 5) == null)
            selected = false;
          else
            selected = Boolean.valueOf(table.getValueAt(i, 5).toString());
          if(selected){
            selectedMessages++;
          }
        }
        if(selectedMessages >= 1){
          selected = false;
          if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected messages", "Confirm Aprrove", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            for(int i = 0; i < table.getRowCount(); i++){
              if(table.getValueAt(i, 5) == null)
                selected = false;
              else
                selected = Boolean.valueOf(table.getValueAt(i, 5).toString());
              if(selected){
                id = Integer.parseInt(table.getValueAt(i, 1).toString());
                System.out.println(id);
                for(Message message : MessageList.employeeMessages){
                  if(id == message.getFromId()){
                    message.removeEmployeeMessage();
                  }
                }
              }

            }
            if(selectedMessages == 1)
              JOptionPane.showMessageDialog(null, "Message deleted");
            else if (selectedMessages > 1)
              JOptionPane.showMessageDialog(null, "Messages deleted");
            frame.dispose();
            MessageManagerScreen mms = new MessageManagerScreen();
          }

        } else
            JOptionPane.showMessageDialog(null, "Please select at least one Message to be removed");
      }
    }
}
