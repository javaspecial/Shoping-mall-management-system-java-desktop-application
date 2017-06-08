
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Toxic
 */
public class DeleteSupplier extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    PreparedStatement ps;

    JFrame jframe;
    JTextField txt1, txt2, txt3, txt4, txt5;
    JLabel label1, label2, label3, label4, label5, label6;
    JButton button1, button2, button3, button4, button5;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Font f;

    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrollpane = new JScrollPane(tabGrid);
    private JPopupMenu popup;
    //private int reply;

    DeleteSupplier() {
        jframe = new JFrame();
        // Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        label6 = new JLabel("Delete Supplier");
        label6.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label6.setBounds(300, 50, 300, 40);
        jframe.add(label6);

        label1 = new JLabel("Supplier ID:");
        label1.setFont(f);
        label1.setBounds(210, 120, 135, 25);
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
        txt2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt2.setBounds(375, 160, 200, 25);
        txt2.setToolTipText("EnterSupplierName");
        jframe.add(txt2);

        label3 = new JLabel("Supplier Address:");
        label3.setFont(f);
        label3.setBounds(210, 200, 170, 25);
        jframe.add(label3);
        txt3 = new JTextField(20);
        txt3.setBounds(375, 200, 200, 25);
        txt3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt3.setToolTipText("EnterSupplierAddress");
        jframe.add(txt3);

        label4 = new JLabel("Supplier Phone No");
        label4.setFont(f);
        label4.setBounds(210, 240, 170, 25);
        jframe.add(label4);
        txt4 = new JTextField(20);
        txt4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt4.setBounds(375, 240, 200, 25);
        txt4.setToolTipText("EnterSupplierPhoneNo");
        jframe.add(txt4);

        label5 = new JLabel("Supplier Email id");
        label5.setFont(f);
        label5.setBounds(210, 280, 170, 25);
        jframe.add(label5);
        txt5 = new JTextField(20);
        txt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt5.setBounds(375, 280, 200, 25);
        txt5.setToolTipText("EnterSupplierEmailId");
        jframe.add(txt5);

        button1 = new JButton("Open", new ImageIcon("images//open.png"));
        button1.setBounds(180, 330, 110, 35);
        button1.setToolTipText("click to open supplier details");
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Delete", new ImageIcon("images//delete.png"));
        button2.setBounds(295, 330, 110, 35);
        button2.setToolTipText("click to update supplier details");
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button2);
        button2.addActionListener(this);

        button3 = new JButton("Clear", new ImageIcon("images//clear.png"));
        button3.setBounds(410, 330, 110, 35);
        button3.setToolTipText("click to clear all textfilds");
        button3.setContentAreaFilled(false);
        button3.setFocusPainted(false);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button3);
        button3.addActionListener(this);

        button4 = new JButton("All", new ImageIcon("images//all.png"));
        button4.setBounds(525, 330, 110, 35);
        button4.setToolTipText("click to view all supplier details");
        button4.setContentAreaFilled(false);
        button4.setFocusPainted(false);
        button4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button4);
        button4.addActionListener(this);

//        button5 = new JButton("Home", new ImageIcon("images//home.png"));
//        button5.setBounds(0, 0, 110, 35);
//        button5.setToolTipText("Click To Back Home");
//        jframe.add(button5);
//        button5.addActionListener(this);
        scrollpane.setBounds(0, 380, 807, 500);
        jframe.add(scrollpane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);

        tabGrid.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent evt) {
                int row = tabGrid.rowAtPoint(evt.getPoint());
                tabGrid.getSelectionModel().setSelectionInterval(row, row);
                if (evt.getButton() == MouseEvent.BUTTON3) {
                    popup.show(tabGrid, evt.getX(), evt.getY());
                }

            }
        });
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabGrid.getSelectedRow();
                if (row >= 0) {
                model.removeRow(row);
                }
            }
        });

        model.addColumn("S_ID");
        model.addColumn("S_NAME");
        model.addColumn("S_ADDRESS");
        model.addColumn("S_PHONENO");
        model.addColumn("S_EMAILID");

        jframe.setTitle("Delete Supplier");
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
        } else if (ae.getSource() == button2) {
            if (txt1.getText().equals("") || txt2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Enter Supplier ID or Name");
            } else {
                try {
                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement("delete from supplier where SupplierID='" + txt1.getText() + "' or Name = '" + txt2.getText() + "'");
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record is Deleted  Successfully");
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                    connection.close();
                } catch (SQLException err) {
                    System.out.println(err);
                    JOptionPane.showMessageDialog(null, "SQL err" + err);
                }

            }
        } else if (ae.getSource() == button3) {
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
        } else if (ae.getSource() == button4) {
            int row = 0;
            try {
                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = statement.executeQuery("select * from supplier");
                while (rs.next()) {
                    model.insertRow(row++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DeleteSupplier.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ae.getSource() == button5) {
            try {
                jframe.dispose();
                new MainMenu();
            } catch (IOException ex) {
                Logger.getLogger(DeleteSupplier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String args[]) {
        new DeleteSupplier();
    }

}
