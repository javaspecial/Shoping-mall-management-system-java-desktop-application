
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class Food extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    JFrame jframe;
    JButton submit, clear;
    JLabel label1, labeln;
    JTextField txt1;
    Font f;
    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

    public Food() {
        jframe = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.CYAN);


        labeln = new JLabel("Daily Food Request");
        labeln.setFont(new Font("Times New Roman", Font.BOLD, 25));
        labeln.setBounds(300, 30, 300, 25);
        jframe.add(labeln);

        label1 = new JLabel("Enter Request  Date:");
        label1.setFont(f);
        label1.setBounds(120, 100, 250, 25);
        jframe.add(label1);

        txt1 = new JTextField(10);
        txt1.setBounds(310, 100, 100, 25);
        txt1.setToolTipText("EnterFoodRequestDate");
        jframe.add(txt1);

        submit = new JButton("Submit", new ImageIcon("images//open.png"));
        submit.setBounds(120, 150, 110, 35);
        submit.setToolTipText("ClickToOpenDailyPurchaseReport");
        jframe.add(submit);
        submit.addActionListener(this);

        clear = new JButton("Clear", new ImageIcon("images//clear.png"));
        clear.setBounds(300, 150, 110, 35);
        clear.setToolTipText("ClickToClear");
        jframe.add(clear);
        clear.addActionListener(this);

        scrlPane.setBounds(0, 280, 807, 400);
        jframe.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));

        model.addColumn("AVAILABLE");
        model.addColumn("NEED");
        model.addColumn("RDATE");
        model.addColumn("FTYPE");
        model.addColumn("FCATAGORY");
        model.addColumn("SNAME");

        jframe.setTitle("Daily Food Request");
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
                    JOptionPane.showMessageDialog(this, "Please Enter Request Date !");
                } else {
                    int foundrec = 0;
                    connection = DriverManager.getConnection(dbHost,dbUser,dbPass);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = statement.executeQuery("SELECT Favailable,Fneed,Rdate,Ftype,Fcatagory,Sname from foodrequest where Rdate='" + txt1.getText() + "' ");
                    while (rs.next()) {
                        model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                        foundrec = 1;
                    }
                    if (foundrec == 0) {
                        JOptionPane.showMessageDialog(null, "Not any Request on given date");
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
        new Food();
    }
}
