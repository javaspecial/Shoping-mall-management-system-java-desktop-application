
import java.awt.Color;
import java.awt.Font;
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
import static java.time.Clock.system;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
 * @author Toxic
 */
/*

 */

public class AddNewSupplier extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;

    PreparedStatement ps;

    private String id = null;
    private String name = null;
    private String address = null;
    private String phoneno = null;
    private String email = null;

    JFrame jframe;
    JTextField txt1, txt2, txt3, txt4, txt5;
    JLabel label1, label2, label3, label4, label5, label6;
    JButton button1, button2, button3, button4;
    Font f;

    DefaultTableModel model = new DefaultTableModel();
    JTable tabgrid = new JTable(model);
    JScrollPane scrollpane = new JScrollPane(tabgrid);
    //private int reply;
    private int reply;

    private JPopupMenu popup;

    AddNewSupplier() {
        jframe = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        label6 = new JLabel("New Supplier Details");
        label6.setFont(f);
        label6.setBounds(350, 50, 300, 40);
        jframe.add(label6);

        label1 = new JLabel("Supplier ID ");
        label1.setFont(f);
        label1.setBounds(210, 120, 135, 25);
        jframe.add(label1);
        txt1 = new JTextField(20);
        txt1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt1.setBounds(358, 120, 200, 25);
        txt1.setToolTipText("EnterSupplierID");
        jframe.add(txt1);

        label2 = new JLabel(" Name:");
        label2.setFont(f);
        label2.setBounds(210, 160, 170, 25);
        jframe.add(label2);

        txt2 = new JTextField(20);
        txt2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt2.setBounds(358, 160, 200, 25);
        txt2.setToolTipText("EnterSupplierName");
        jframe.add(txt2);

        label3 = new JLabel("Address:");
        label3.setFont(f);
        label3.setBounds(210, 200, 170, 25);
        jframe.add(label3);

        txt3 = new JTextField(20);
        txt3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt3.setBounds(358, 200, 200, 25);
        txt3.setToolTipText("EnterSupplierAddress");
        jframe.add(txt3);

        label4 = new JLabel("Phone No:");
        label4.setFont(f);
        label4.setBounds(210, 240, 170, 25);
        jframe.add(label4);
        txt4 = new JTextField(20);
        txt4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt4.setBounds(358, 240, 200, 25);
        txt4.setToolTipText("EnterSupplierPhoneNo");
        jframe.add(txt4);

        label5 = new JLabel("Email Id:");
        label5.setFont(f);
        label5.setBounds(210, 280, 170, 25);
        jframe.add(label5);
        txt5 = new JTextField(20);
        txt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt5.setBounds(358, 280, 200, 25);
        txt5.setToolTipText("EnterSuppliereEmailId");
        jframe.add(txt5);

        button1 = new JButton("Save", new ImageIcon("images//save.png"));
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button1.setBounds(212, 330, 110, 35);
        button1.setToolTipText("Click To Save Supplier Details ");
        jframe.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Clear", new ImageIcon("images//clear.png"));
        button2.setBounds(332, 330, 110, 35);
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button2.setToolTipText("Click To Clear All Textfields");
        jframe.add(button2);
        button2.addActionListener(this);

        button3 = new JButton("All", new ImageIcon("images//all.png"));
        button3.setBounds(452, 330, 110, 35);
        button3.setContentAreaFilled(false);
        button3.setFocusPainted(false);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button3.setToolTipText("Click To View All");
        jframe.add(button3);
        button3.addActionListener(this);

        scrollpane.setBounds(0, 380, 807, 500);
        jframe.add(scrollpane);
        tabgrid.setFont(new Font("Times New Roman", 25, 15));

        model.addColumn("Supplier_ID");
        model.addColumn("Supplier_Name");
        model.addColumn("Supplier_Address");
        model.addColumn("Supplier_PhoneNo");
        model.addColumn("Supplier_EmailID");

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);

        tabgrid.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = tabgrid.rowAtPoint(e.getPoint());
                tabgrid.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(tabgrid, e.getX(), e.getY());

                }

            }
        });
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabgrid.getSelectedRow();
                if (row >= 0) {
                    model.removeRow(row);
                }

            }
        });

        jframe.setTitle("Add New Supplier");
        jframe.setSize(810, 600);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        // jframe.getContentPane().setBackground(Color.cyan);
        jframe.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button1) {

            id = txt1.getText().toString();
            name = txt2.getText().toString();
            address = txt3.getText().toString();
            phoneno = txt4.getText().toString();
            email = txt5.getText().toString();
            Pattern p = Pattern.compile("[_a-z_A-Z_0-9]+[0-9]*@[a-zA-Z0-9]+.[a-zA-Z0-9]+");
            Matcher m = p.matcher(email);

            boolean matchFound = m.matches();

            if (((txt1.getText()).equals("")) || ((txt2.getText()).equals("")) || ((txt3.getText()).equals("")) || ((txt4.getText()).equals("")) || ((txt5.getText()).equals(""))) {
                JOptionPane.showMessageDialog(this, "Every Field Is Required");

            } else if (!matchFound) {
                JOptionPane.showMessageDialog(this, "Invalid Email ID");
            } else {
                try {

                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement("insert into supplier (SupplierID,Name,Address,PhoneNo,Email)values(?,?,?,?,?)");
                    ps.setString(1, txt1.getText());
                    ps.setString(2, txt2.getText());
                    ps.setString(3, txt3.getText());
                    ps.setString(4, txt4.getText());
                    ps.setString(5, txt5.getText());
                    ps.executeUpdate();
                    System.out.println(email);
                    reply = JOptionPane.showConfirmDialog(null, "Supplier Added Successfully.Do you want add more supplier?", "Added Supplier", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        jframe.setVisible(false);
                        new AddNewSupplier();

                    } else if (reply == JOptionPane.NO_OPTION) {

                        jframe.setVisible(false);
                        new AddNewSupplier();
                    }
                    connection.close();

                } catch (SQLException err) {
                    System.out.println(err);
                    JOptionPane.showMessageDialog(null, "SQL Error:" + err);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error:" + e);
                }

            }

        } else if (ae.getSource() == button2) {

            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
        } else if (ae.getSource() == button3) {
            int r = 0;
            try {

                connection = DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = statement.executeQuery("select * from supplier group by SupplierID asc");
                while (rs.next()) {
                    model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
                }
                connection.close();
            } catch (SQLException se) {
                System.out.println(se);
                JOptionPane.showMessageDialog(null, "SQL Error:" + se);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error:" + e);
            }

        } else if (ae.getSource() == button4) {
            try {
                jframe.dispose();
                new MainMenu();
            } catch (IOException ex) {
                Logger.getLogger(AddNewSupplier.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void main(String args[]) {
        new AddNewSupplier();
    }

}
