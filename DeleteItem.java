
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class DeleteItem extends JFrame implements ActionListener {

    private static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    private static final String dbUser = "root";
    private static final String dbPass = "";

    JFrame jframe;
    JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10;
    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, labeln;
    JButton button1, button2, button3, button4;
    Font f;
    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    private JPopupMenu popup;

    DeleteItem() {
        jframe = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        labeln = new JLabel(" Delete Item ");
        labeln.setFont(new Font("Times New Roman", Font.BOLD, 25));
        labeln.setBounds(330, 30, 300, 40);
        jframe.add(labeln);

        label1 = new JLabel("Item Batch No");
        label1.setFont(f);
        label1.setBounds(50, 100, 200, 25);
        jframe.add(label1);
        txt1 = new JTextField(20);
        txt1.setBounds(250, 100, 100, 25);
        txt1.setToolTipText("Enter Item batch no");
        txt1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(txt1);

        label2 = new JLabel("Item Name");
        label2.setFont(f);
        label2.setBounds(50, 140, 200, 25);
        jframe.add(label2);
        txt2 = new JTextField(20);
        txt2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt2.setBounds(250, 140, 100, 25);
        txt2.setToolTipText("Enter Item Name");
        jframe.add(txt2);

        label3 = new JLabel("Item Company");
        label3.setFont(f);
        label3.setBounds(50, 180, 200, 25);
        jframe.add(label3);
        txt3 = new JTextField(20);
        txt3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt3.setBounds(250, 180, 100, 25);
        jframe.add(txt3);

        label4 = new JLabel("Item Expiry Date");
        label4.setFont(f);
        label4.setBounds(50, 220, 250, 25);
        jframe.add(label4);
        txt4 = new JTextField(20);
        txt4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt4.setBounds(250, 220, 100, 25);
        jframe.add(txt4);

        label5 = new JLabel("Item Purchase Date");
        label5.setFont(f);
        label5.setBounds(50, 260, 250, 25);
        jframe.add(label5);
        txt5 = new JTextField(20);
        txt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt5.setBounds(250, 260, 100, 25);
        jframe.add(txt5);

        label6 = new JLabel("Item Type");
        label6.setFont(f);
        label6.setBounds(470, 100, 200, 25);
        jframe.add(label6);
        txt6 = new JTextField(20);
        txt6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt6.setBounds(650, 100, 100, 25);
        jframe.add(txt6);

        label7 = new JLabel("Item Purchase Price");
        label7.setFont(f);
        label7.setBounds(470, 140, 220, 25);
        jframe.add(label7);
        txt7 = new JTextField(20);
        txt7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt7.setBounds(650, 140, 100, 25);
        jframe.add(txt7);

        label8 = new JLabel("Item Sale Price");
        label8.setFont(f);
        label8.setBounds(470, 180, 200, 25);
        jframe.add(label8);
        txt8 = new JTextField(20);
        txt8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt8.setBounds(650, 180, 100, 25);
        jframe.add(txt8);

        label9 = new JLabel("Item Rack No");
        label9.setFont(f);
        label9.setBounds(470, 220, 200, 25);
        jframe.add(label9);
        txt9 = new JTextField(20);
        txt9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt9.setBounds(650, 220, 100, 25);
        jframe.add(txt9);

        label10 = new JLabel("Supplier name");
        label10.setFont(f);
        label10.setBounds(470, 260, 180, 25);
        jframe.add(label10);
        txt10 = new JTextField(20);
        txt10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt10.setBounds(650, 260, 100, 25);
        jframe.add(txt10);

        button1 = new JButton("Open", new ImageIcon("images//open.png"));
        button1.setBounds(150, 330, 110, 35);
        button1.setToolTipText("Click To Open ");
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Delete", new ImageIcon("images//delete.png"));
        button2.setBounds(300, 330, 110, 35);
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button2.setToolTipText("Click To Delete ");
        jframe.add(button2);
        button2.addActionListener(this);

        button3 = new JButton("Clear", new ImageIcon("images//clear.png"));
        button3.setBounds(450, 330, 110, 35);
        button3.setToolTipText("Click To Clear All");
        button3.setContentAreaFilled(false);
        button3.setFocusPainted(false);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button3);
        button3.addActionListener(this);

        button4 = new JButton("All", new ImageIcon("images//all.png"));
        button4.setBounds(600, 330, 110, 35);
        button4.setToolTipText("Click To View All");
        button4.setContentAreaFilled(false);
        button4.setFocusPainted(false);
        button4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button4);
        button4.addActionListener(this);

        scrlPane.setBounds(0, 380, 810, 300);
        jframe.add(scrlPane);
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
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(tabGrid, e.getX(), e.getY());
                }
            }
        });

        jframe.setTitle("Delete Item ");
        jframe.setSize(810, 600);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button1) {
            try {
                if (((txt1.getText()).equals("")) && ((txt2.getText()).equals(""))) {
                    JOptionPane.showMessageDialog(this, "Please Enter Item Bno or Name !");
                } else {
                    int foundrec = 0;

                    connection = DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement("select * from item where Iname='" + txt2.getText() + "' or Ibno='" + txt1.getText() + "'");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        txt1.setText(rs.getString(1));
                        txt2.setText(rs.getString(2));
                        txt3.setText(rs.getString(3));
                        txt4.setText(rs.getString(4));
                        txt5.setText(rs.getString(5));
                        txt6.setText(rs.getString(6));
                        txt7.setText(rs.getString(7));
                        txt8.setText(rs.getString(8));
                        txt9.setText(rs.getString(9));
                        txt10.setText(rs.getString(10));

                        foundrec = 1;
                    }
                    if (foundrec == 0) {
                        JOptionPane.showMessageDialog(null, "Record is not available");
                    }
                }
                connection.close();
            } catch (SQLException se) {
                System.out.println(se);
                JOptionPane.showMessageDialog(null, "SQL Error." + se);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error." + e);
            }
        } else if (ae.getSource() == button2) {
            if (((txt1.getText()).equals("")) && ((txt2.getText()).equals(""))) {
                JOptionPane.showMessageDialog(this, "Please Enter Item Batchno or Name !");
            } else {
                try {

                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement("delete from item where Ibno='" + txt1.getText() + "' or Iname='" + txt2.getText() + "'");
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record is deleted.");
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                    txt6.setText("");
                    txt7.setText("");
                    txt8.setText("");
                    txt9.setText("");
                    txt10.setText("");
                    connection.close();
                } catch (SQLException se) {
                    System.out.println(se);
                    JOptionPane.showMessageDialog(null, "SQL Error:" + se);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error:" + e);
                }
            }
        } else if (ae.getSource() == button3) {
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
            txt7.setText("");
            txt8.setText("");
            txt9.setText("");
            txt10.setText("");

        } else if (ae.getSource() == button4) {
            int r = 0;
            try {
                int foundrec = 0;
                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = statement.executeQuery("SELECT * from item");
                while (rs.next()) {
                    model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)});
                    txt1.setText(rs.getString(1));
                    txt2.setText(rs.getString(2));
                    txt3.setText(rs.getString(3));
                    txt4.setText(rs.getString(4));
                    txt5.setText(rs.getString(5));
                    txt6.setText(rs.getString(6));
                    txt7.setText(rs.getString(7));
                    txt8.setText(rs.getString(8));
                    txt9.setText(rs.getString(9));
                    txt10.setText(rs.getString(10));

                    foundrec = 1;
                }
                if (foundrec == 0) {
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
    }

    public static void main(String args[]) {
        new DeleteItem();
    }
}
