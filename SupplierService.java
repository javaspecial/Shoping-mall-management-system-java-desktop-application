
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class SupplierService extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    JFrame jframe;
    JButton submit, clear;
    JLabel label1, labeln;
    public static JTextField txt1 = new JTextField();

    Font f;
    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    private JPopupMenu popup;

    public SupplierService() {
        jframe = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);
        jframe.setLayout(null);

        labeln = new JLabel("Daily SupplierService Report");
        labeln.setFont(new Font("Times New Roman", Font.BOLD, 25));
        labeln.setBounds(250, 30, 320, 25);
        jframe.add(labeln);

        label1 = new JLabel("Enter Service  Date:");
        label1.setFont(f);
        label1.setBounds(120, 100, 250, 25);
        jframe.add(label1);

        txt1.setBounds(310, 100, 100, 25);
        txt1.setBackground(Color.getHSBColor(400, 300, 100));
        txt1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt1.setToolTipText("EnterServicetDate");
        jframe.add(txt1);
        txt1.addKeyListener(new KeyAdapter() {
            public void KeyPressed(KeyEvent k) {
                if (k.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt1.setText("");
                    int r = 0;
                    try {
                        if (((txt1.getText()).equals(""))) {
                            JOptionPane.showMessageDialog(null, "Please Enter Service Date !");
                        } else {
                            int foundrec = 0;
                            connection = DriverManager.getConnection(dbHost, dbUser, dbPass);
                            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            rs = statement.executeQuery("SELECT Sstart,Sstop,Sdate,Speriod,Sshop,Sname from supplierservice where Sdate='" + txt1.getText() + "' ");
                            while (rs.next()) {
                                model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                                foundrec = 1;
                            }
                            if (foundrec == 0) {
                                JOptionPane.showMessageDialog(null, "Not any person Services on given date");
                            }
                        }
                        connection.close();
                    } catch (SQLException se) {
                        System.out.println(se);
                        JOptionPane.showMessageDialog(null, "SQL Error:" + se);
                    }
                }
            }

        });

        submit = new JButton("Submit", new ImageIcon("images//open.png"));
        submit.setBounds(120, 150, 110, 35);
        submit.setContentAreaFilled(false);
        submit.setFocusPainted(false);
        submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setToolTipText("ClickToOpenDailyPurchaseReport");
        jframe.add(submit);
        submit.addActionListener(this);

        clear = new JButton("Clear", new ImageIcon("images//clear.png"));
        clear.setBounds(300, 150, 110, 35);
        clear.setContentAreaFilled(false);
        clear.setFocusPainted(false);
        clear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        clear.setToolTipText("ClickToClear");
        jframe.add(clear);
        clear.addActionListener(this);

        scrlPane.setBounds(0, 280, 807, 400);
        jframe.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));

        model.addColumn("START");
        model.addColumn("STOP");
        model.addColumn("DATE");
        model.addColumn("PERIOD");
        model.addColumn("SHOP");
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

        jframe.setTitle("Daily Service Report");
        jframe.setSize(810, 600);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {//list
            int r = 0;
            try {
                if (((txt1.getText()).equals(""))) {
                    JOptionPane.showMessageDialog(this, "Please Enter Service Date !");
                } else {
                    int foundrec = 0;
                    connection = DriverManager.getConnection(dbHost, dbUser, dbPass);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = statement.executeQuery("SELECT Sstart,Sstop,Sdate,Speriod,Sshop,Sname from supplierservice where Sdate='" + txt1.getText() + "' ");
                    while (rs.next()) {
                        model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                        foundrec = 1;
                    }
                    if (foundrec == 0) {
                        JOptionPane.showMessageDialog(null, "Not any person Services on given date");
                    }
                }
                connection.close();
            } catch (SQLException se) {
                System.out.println(se);
                JOptionPane.showMessageDialog(null, "SQL Error:" + se);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error:" + e);
            }
        } else if (ae.getSource() == clear) {
            txt1.setText("");
        }
    }

    public static void main(String args[]) {
        new SupplierService();
    }
}
