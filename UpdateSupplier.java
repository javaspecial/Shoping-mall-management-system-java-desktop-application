
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class UpdateSupplier extends JFrame implements ActionListener {

    private static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    private static final String dbUser = "root";
    private static final String dbPass = "";

    private Connection connection = null;
    private Statement statement = null;
    ResultSet rs = null;
    PreparedStatement ps;

    JFrame jframe;
    JLabel label1, label2, label3, label4, label5, label6;
    JTextField txt1, txt2, txt3, txt4, txt5;
    JButton button1, button2, button3, button4, button5;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Font f;

    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    private JPopupMenu popup;

    UpdateSupplier() {

        jframe = new JFrame();
        // Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);
        jframe.setLayout(null);

        label6 = new JLabel("Update Supplier");
        label6.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label6.setBounds(300, 50, 300, 40);
        jframe.add(label6);

        label1 = new JLabel("Supplier ID:");
        label1.setFont(f);
        label1.setBounds(210, 120, 130, 25);
        jframe.add(label1);
        txt1 = new JTextField(20);
        txt1.setBounds(375, 120, 200, 25);
        txt1.setToolTipText("EnterSupplierID");
        txt1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(txt1);

        label2 = new JLabel("Supplier Name:");
        label2.setFont(f);
        label2.setBounds(210, 160, 170, 25);
        jframe.add(label2);
        txt2 = new JTextField(20);
        txt2.setBounds(375, 160, 200, 25);
        txt2.setToolTipText("EnterSupplierName");
        txt2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(txt2);

        label3 = new JLabel("Supplier Address:");
        label3.setFont(f);
        label3.setBounds(210, 200, 170, 25);
        jframe.add(label3);
        txt3 = new JTextField(20);
        txt3.setBounds(375, 200, 200, 25);
        txt3.setToolTipText("EnterSupplierAddress");
        txt3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(txt3);

        label4 = new JLabel("Supplier Phone No");
        label4.setFont(f);
        label4.setBounds(210, 240, 170, 25);
        jframe.add(label4);
        txt4 = new JTextField(20);
        txt4.setBounds(375, 240, 200, 25);
        txt4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt4.setToolTipText("EnterSupplierPhoneNo");
        jframe.add(txt4);

        label5 = new JLabel("Supplier Email id");
        label5.setFont(f);
        label5.setBounds(210, 280, 170, 25);
        jframe.add(label5);
        txt5 = new JTextField(20);
        txt5.setBounds(375, 280, 200, 25);
        txt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt5.setToolTipText("EnterSupplierEmailId");
        jframe.add(txt5);

        button1 = new JButton("Open", new ImageIcon("images//open.png"));
        button1.setBounds(180, 330, 110, 35);
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button1.setToolTipText("click to open supplier details");
        jframe.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Update", new ImageIcon("images//update.png"));
        button2.setBounds(295, 330, 110, 35);
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button2.setToolTipText("click to update supplier details");
        jframe.add(button2);
        button2.addActionListener(this);

        button3 = new JButton("Clear", new ImageIcon("images//clear.png"));
        button3.setBounds(410, 330, 110, 35);
        button3.setContentAreaFilled(false);
        button3.setFocusPainted(false);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button3.setToolTipText("click to clear all textfilds");
        jframe.add(button3);
        button3.addActionListener(this);

        button4 = new JButton("All", new ImageIcon("images//all.png"));
        button4.setBounds(525, 330, 110, 35);
        button4.setContentAreaFilled(false);
        button4.setFocusPainted(false);
        button4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button4.setToolTipText("click to view all supplier details");
        jframe.add(button4);
        button4.addActionListener(this);

        scrlPane.setBounds(0, 380, 807, 500);
        jframe.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));
        
        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);
        
        tabGrid.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                int row = tabGrid.rowAtPoint(e.getPoint());
                tabGrid.getSelectionModel().setSelectionInterval(row, row);
                if(e.getButton()== MouseEvent.BUTTON3){
                    popup.show(tabGrid, e.getX(), e.getY());
                }
                
            }
        });
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            int row = tabGrid.getSelectedRow();
            if(row >= 0){
                model.removeRow(row);
            }
                
            }
        });
        
        

        model.addColumn("S_ID");
        model.addColumn("S_NAME");
        model.addColumn("S_ADDRESS");
        model.addColumn("S_PHONENO");
        model.addColumn("S_EMAILID");

        jframe.setTitle("Update Supplier");
        jframe.setSize(810, 600);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button1) {
            if (((txt1.getText()).equals("")) && ((txt2.getText()).equals(""))) {
                JOptionPane.showMessageDialog(this, "Please Enter supplier Id Or Name");
            } else {

                try {
                    int tmp = 0;

                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement("select * from supplier where SupplierID='" + txt1.getText() + "' or Name='" + txt2.getText() + "'");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        txt1.setText(rs.getString(1));
                        txt2.setText(rs.getString(2));
                        txt3.setText(rs.getString(3));
                        txt4.setText(rs.getString(4));
                        txt5.setText(rs.getString(5));
                        tmp = 1;
                    }
                    if (tmp == 0) {
                        JOptionPane.showMessageDialog(null, "Record is not available");
                    }
                    connection.close();
                } catch (SQLException se) {
                    System.out.println(se);
                    JOptionPane.showMessageDialog(null, "SQL Error:" + se);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error:" + e);
                }
            }
        } else if (ae.getSource() == button2) {//update

            String email = txt5.getText();
            Pattern p = Pattern.compile("[_a-z_A-Z_0-9]*[0-9]*@[a-zA-Z0-9]*.[a-zA-Z0-9]*");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();

            if (((txt1.getText()).equals("")) && ((txt2.getText()).equals(""))) {

                JOptionPane.showMessageDialog(this, "Please Enter Supplier Id or Name");
            } else if (((txt2.getText()).equals("")) || ((txt3.getText()).equals("")) || ((txt4.getText()).equals("")) || ((txt5.getText()).equals(""))) {

                JOptionPane.showMessageDialog(this, "Detail are Missing !", "Warning!!!", JOptionPane.ERROR_MESSAGE);
            } else if (!matchFound) {
                JOptionPane.showMessageDialog(this, "Invalid Email Id!");
            } else {
                try {

                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    statement = connection.createStatement();
                    String sql = "UPDATE supplier SET SupplierID='" + txt1.getText() + "',Name='" + txt2.getText() + "',Address='" + txt3.getText() + "',PhoneNo='" + txt4.getText() + "',Email='" + txt5.getText() + "' where SupplierID='" + txt1.getText() + "' or Name='" + txt2.getText() + "' ";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Record is Updated");
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                    connection.close();
                } catch (SQLException se) {
                    System.out.println(se);
                    JOptionPane.showMessageDialog(null, "SQL Error:" + se);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error:" + e);
                }
            }
        } else if (ae.getSource() == button3) {//clear
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
        } else if (ae.getSource() == button4) {
            int r = 0;
            try {

                connection = DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = statement.executeQuery("SELECT * from supplier");
                while (rs.next()) {
                    model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
                }
                connection.close();
            } catch (SQLException se) {
                System.out.println(se);
                JOptionPane.showMessageDialog(null, "SQL Error" + se);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error:" + e);
            }
        } else if (ae.getSource() == button5) {
            try {
                jframe.dispose();
                new MainMenu();
            } catch (IOException ex) {
                Logger.getLogger(UpdateSupplier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String args[]) {
        new UpdateSupplier();
    }
}
