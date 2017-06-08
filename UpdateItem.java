
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class UpdateItem extends JFrame implements ActionListener {

    private static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    private static final String dbUser = "root";
    private static final String dbPass = "";

    JFrame jf;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, ln;
    JButton b0, b1, b2, b3;
    JComboBox sname, tabtype;
    String s, sid1, tabt;
    Font f;
    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    private JPopupMenu popup;

    UpdateItem() {
        jf = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jf.getContentPane().setBackground(Color.LIGHT_GRAY);
        jf.setLayout(null);

        ln = new JLabel("Update Item");
        ln.setFont(new Font("Times New Roman", Font.BOLD, 25));
        ln.setBounds(330, 30, 300, 40);
        jf.add(ln);

        l1 = new JLabel("Item Batch no");
        l1.setFont(f);
        l1.setBounds(50, 100, 200, 25);
        jf.add(l1);
        t1 = new JTextField(20);
        t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t1.setBounds(250, 100, 100, 25);
        t1.setToolTipText("Enter Item batch no");
        jf.add(t1);

        l2 = new JLabel("Item name");
        l2.setFont(f);
        l2.setBounds(50, 140, 200, 25);
        jf.add(l2);
        t2 = new JTextField(20);
        t2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t2.setBounds(250, 140, 100, 25);
        t2.setToolTipText("Enter Item name");
        jf.add(t2);

        l3 = new JLabel("Item company");
        l3.setFont(f);
        l3.setBounds(50, 180, 200, 25);
        jf.add(l3);
        t3 = new JTextField(20);
        t3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t3.setBounds(250, 180, 100, 25);
        t3.setToolTipText("Enter Item company");
        jf.add(t3);


        l5 = new JLabel("Item expiry date");
        l5.setFont(f);
        l5.setBounds(50, 220, 200, 25);
        jf.add(l5);
        t5 = new JTextField(20);
        t5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t5.setBounds(250, 220, 100, 25);
        t5.setToolTipText("Enter Item expiry date");
        jf.add(t5);

        l6 = new JLabel("Item purchase date");
        l6.setFont(f);
        l6.setBounds(50, 260, 250, 25);
        jf.add(l6);
        t6 = new JTextField(20);
        t6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t6.setBounds(250, 260, 100, 25);
        t6.setToolTipText("Enter Item expiry date");
        jf.add(t6);

        l7 = new JLabel("Item type");
        l7.setFont(f);
        l7.setBounds(400, 100, 200, 25);
        jf.add(l7);
        t7 = new JTextField(20);
        t7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t7.setBounds(650, 100, 100, 25);
        t7.setToolTipText("Enter Item type");
        jf.add(t7);

        tabtype = new JComboBox();
        tabtype.addItem("--Type--");
        tabtype.addItem("Mobile");
        tabtype.addItem("Electronics");
        tabtype.addItem("Food");
        tabtype.addItem("Toy");
        tabtype.addItem("Parking");
        tabtype.addItem("Shop");
        tabtype.addItem("Dress");
        tabtype.addItem("Movie");
        tabtype.addItem("Hotel");
        tabtype.addItem("Cosmatics");
        tabtype.setBounds(530, 100, 100, 25);
        tabtype.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tabtype.setToolTipText("Select Item type");
        jf.add(tabtype);
        tabtype.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tabt = (String) tabtype.getSelectedItem();
                t7.setText(tabt);
            }
        });

        l8 = new JLabel("Item purchase price");
        l8.setFont(f);
        l8.setBounds(400, 140, 220, 25);
        jf.add(l8);
        t8 = new JTextField(20);
        t8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t8.setBounds(650, 140, 100, 25);
        t8.setToolTipText("Enter Item purchase price");
        jf.add(t8);

        l9 = new JLabel("Item Sale Price");
        l9.setFont(f);
        l9.setBounds(400, 180, 200, 25);
        jf.add(l9);
        t9 = new JTextField(20);
        t9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t9.setBounds(650, 180, 100, 25);
        t9.setToolTipText("Enter Item sale price");
        jf.add(t9);

        l10 = new JLabel("Item Rackno");
        l10.setFont(f);
        l10.setBounds(400, 220, 200, 25);
        jf.add(l10);
        t10 = new JTextField(20);
        t10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t10.setBounds(650, 220, 100, 25);
        t10.setToolTipText("Enter Item rack no");
        jf.add(t10);

        l11 = new JLabel("Supplier Name");
        l11.setFont(f);
        l11.setBounds(400, 260, 180, 25);
        jf.add(l11);
        t11 = new JTextField(20);
        t11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t11.setBounds(650, 260, 100, 25);
        jf.add(t11);


        sname = new JComboBox();
        sname.setBounds(530, 260, 100, 25);
        sname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sname.setToolTipText("Select Supplier Name");
        jf.add(sname);
        sname.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                s = (String) sname.getSelectedItem();
                t11.setText(s);
            }
        });

        try {

            con = DriverManager.getConnection(dbHost, dbUser, dbPass);
            ps = con.prepareStatement("select Name from supplier");
            rs = ps.executeQuery();
            while (rs.next()) {
                String sname1 = rs.getString(1);
                sname.addItem(sname1);
            }

            con.close();
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        }

        b0 = new JButton("Open", new ImageIcon("images//open.png"));
        b0.setBounds(150, 330, 110, 35);
        b0.setToolTipText("Click to open Item ");
        b0.setContentAreaFilled(false);
        b0.setFocusPainted(false);
        b0.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jf.add(b0);
        b0.addActionListener(this);

        b1 = new JButton("Update", new ImageIcon("images//update.png"));
        b1.setBounds(300, 330, 110, 35);
        b1.setToolTipText("Click to update Item");
        b1.setContentAreaFilled(false);
        b1.setFocusPainted(false);
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jf.add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Clear", new ImageIcon("images//clear.png"));
        b2.setBounds(450, 330, 110, 35);
        b2.setToolTipText("Click to clear all");
        b2.setContentAreaFilled(false);
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jf.add(b2);
        b2.addActionListener(this);

        b3 = new JButton("All", new ImageIcon("images//all.png"));
        b3.setBounds(600, 330, 110, 35);
        b3.setToolTipText("Click to view all");
        b3.setContentAreaFilled(false);
        b3.setFocusPainted(false);
        b3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jf.add(b3);
        b3.addActionListener(this);

        scrlPane.setBounds(0, 380, 810, 300);
        jf.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));

        model.addColumn("BNO");
        model.addColumn("NAME");
        model.addColumn("COMPANY");
        model.addColumn("EXPDATE");
        model.addColumn("PURDATE");
        model.addColumn("TYPE");
        model.addColumn("SALEPRICE");
        model.addColumn("PURPRICE");
        model.addColumn("RACKNO");
        model.addColumn("SNAME");
        
           popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabGrid.getSelectedRow();
                if (row >= 0) {

                    model.removeRow(row);
                }
            }
        });
        tabGrid.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                int row = tabGrid.rowAtPoint(e.getPoint());
                tabGrid.getSelectionModel().setSelectionInterval(row, row);
                if(e.getButton()== MouseEvent.BUTTON3){
                    popup.show(tabGrid, e.getX(), e.getY());
                }
            }
        });


        jf.setTitle("Update Item ");
        jf.setSize(810, 600);
        jf.setLocation(270, 100);
        jf.setResizable(false);
        jf.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b0) {
            if (((t1.getText()).equals("")) && ((t2.getText()).equals(""))) {
                JOptionPane.showMessageDialog(this, "Please enter Item Ibno or Iname !");
            } else {

                try {
                    int foundrec = 0;
                    con = DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = con.prepareStatement("select * from item where Iname='" + t2.getText() + "' or Ibno='" + t1.getText() + "' ");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        t1.setText(rs.getString(1));
                        t2.setText(rs.getString(2));
                        t3.setText(rs.getString(3));
                        t5.setText(rs.getString(4));
                        t6.setText(rs.getString(5));
                        t7.setText(rs.getString(6));
                        t8.setText(rs.getString(7));
                        t9.setText(rs.getString(8));
                        t10.setText(rs.getString(9));
                        t11.setText(rs.getString(10));
                        foundrec = 1;
                    }
                    if (foundrec == 0) {
                        JOptionPane.showMessageDialog(null, "Record is not available");
                    }

                    con.close();
                } catch (SQLException se) {
                    System.out.println(se);
                    JOptionPane.showMessageDialog(null, "SQL Error:" + se);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error:" + e);
                }
            }
        } else if (ae.getSource() == b1) {
            try {
                if (((t1.getText()).equals("")) || ((t2.getText()).equals("")) || ((t3.getText()).equals("")) || ((t5.getText()).equals("")) || ((t6.getText()).equals("")) || ((t7.getText()).equals("")) || ((t8.getText()).equals("")) || ((t9.getText()).equals("")) || ((t10.getText()).equals("")) || ((t11.getText()).equals(""))) {
                    JOptionPane.showMessageDialog(this, "Detail are Missing !");
                } else {

                    con = DriverManager.getConnection(dbHost, dbUser, dbPass);
                    stmt = con.createStatement();
                    String str1 = "UPDATE item SET Ibno='" + t1.getText() + "',Iname='" + t2.getText() + "',Icompany='" + t3.getText() + "',Iexpdate='" + t5.getText() + "',Ipurdate='" + t6.getText() + "',Itype='" + t7.getText() + "',Ipurprice='" + t8.getText() + "',Isaleprice='" + t9.getText() + "',Irackno='" + t10.getText() + "',Sname='" + t11.getText() + "' where Ibno='" + t1.getText() + "'or Iname='" + t2.getText() + "'";
                    stmt.executeUpdate(str1);
                    JOptionPane.showMessageDialog(null, "Record is updated");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");
                    t8.setText("");
                    t9.setText("");
                    t10.setText("");
                    t11.setText("");
                    con.close();
                }
            } catch (SQLException se) {
                System.out.println(se);
                JOptionPane.showMessageDialog(null, "SQL Error:" + se);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error:" + e);
            }
        } else if (ae.getSource() == b2) {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t5.setText("");
            t6.setText("");
            t7.setText("");
            t8.setText("");
            t9.setText("");
            t10.setText("");
            t11.setText("");

        } else if (ae.getSource() == b3) {
            int r = 0;
            try {
                int foundrec = 0;
                con = DriverManager.getConnection(dbHost, dbUser, dbPass);
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery("SELECT * from item");
                while (rs.next()) {
                    model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),});
                    t1.setText(rs.getString(1));
                    t2.setText(rs.getString(2));
                    t3.setText(rs.getString(3));
                    t4.setText(rs.getString(4));
                    t5.setText(rs.getString(5));
                    t6.setText(rs.getString(6));
                    t7.setText(rs.getString(7));
                    t8.setText(rs.getString(8));
                    t9.setText(rs.getString(9));
                    t10.setText(rs.getString(10));

                    foundrec = 1;
                }
                if (foundrec == 0) {
                    JOptionPane.showMessageDialog(null, "Record is not available");
                }
                con.close();
            } catch (SQLException se) {
                System.out.println(se);
                JOptionPane.showMessageDialog(null, "SQL Error:" + se);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error:" + e);
            }
        }
    }

    public static void main(String args[]) {
        new UpdateItem();
    }
}
