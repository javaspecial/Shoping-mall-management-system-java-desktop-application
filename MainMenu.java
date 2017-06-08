
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.table.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Toxic
 */
public class MainMenu extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result, depositresult, withdrawresult, depositeresult;
    private String account_no, account_name, a_balance, w_balance;
    PreparedStatement ps;
    private String createAccountSql, createDepositeSql, createWithdrawSQl, updateAccountTableSql, createupdatebalancesql;

    //Banking System
    JButton deposite, withdraw, transaction, balance, close, createBaccount;
    private int totalbalance;

    //Banking System
    static JLabel lblMonth, lblYear;
    static JButton btnPrev, btnNext;
    static JTable tblCalendar;
    static JComboBox cmbYear;
    // static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scrollpane
    static JPanel pnlCalendar;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    static JFrame jframe, jframemain;
    JMenuBar jmenubar;
    JMenu menu1, menu2, menu3, menu4, menu5, menu6, menu7;
    JMenuItem m1_1, m1_2, m1_3, m1_4, m1_5, m2_1, m2_2, m2_3, m2_4, m2_5, m3_1, m3_2, m3_3, m3_4, m4_1, m5_1, m6_1, m6_2, m6_3, m6_4, m6_5, m7_1, m7_2, m7_3, m8_1;
    static JLabel labelb1, labelb2, labelb3, labelb4, labelb5, labelm1, label1, LogoCall, labelmonth, labelyear;
    static JButton signin, createaccount, buttonpreviuos, buttonnext;
    private JTextField txtname;
    private JPasswordField txtpassword;
    private String userName, passWord;

    static JTable tablecalendar;
    static JComboBox comboyear;
    static DefaultTableModel modeltablecalendar;
    static JScrollPane scrolltablecalender;
    static JPanel panelcalendar;
    static int realyear, realmonth, realday, currentyear, currentmonth;
    GridBagLayout gbl;
    private Image backgroundImage;
    Font F;

    public MainMenu() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        jframe = new JFrame();
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.GRAY);

        deposite = new JButton("Deposite");
        deposite.setForeground(Color.BLACK); //
        // deposite.setContentAreaFilled(false);
        deposite.setFocusPainted(false);
        deposite.setBounds(70, 212, 100, 25);
        deposite.setToolTipText("Click To Deposite");
        deposite.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(deposite);
        deposite.addActionListener(this);

        withdraw = new JButton("Withdraw");
        withdraw.setForeground(Color.BLACK); //
        // withdraw.setContentAreaFilled(false);
        withdraw.setFocusPainted(false);
        withdraw.setBounds(180, 212, 100, 25);
        withdraw.setToolTipText("Click To  Withdraw");
        withdraw.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(withdraw);
        withdraw.addActionListener(this);

        transaction = new JButton("Transaction");
        transaction.setForeground(Color.BLACK); //
        //   transaction.setContentAreaFilled(false);
        transaction.setFocusPainted(false);
        transaction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        transaction.setBounds(70, 250, 100, 25);
        transaction.setToolTipText("Click To  Transaction");
        jframe.add(transaction);
        transaction.addActionListener(this);

        balance = new JButton("Balance");
        balance.setForeground(Color.BLACK); //
        //balance.setContentAreaFilled(false);
        balance.setFocusPainted(false);
        balance.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        balance.setBounds(180, 250, 100, 25);
        balance.setToolTipText("Click To Balance");
        jframe.add(balance);
        balance.addActionListener(this);

        close = new JButton("Reset Pin");
        close.setForeground(Color.BLACK); //
        // close.setContentAreaFilled(false);
        close.setFocusPainted(false);
        close.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        close.setBounds(180, 285, 100, 25);
        close.setToolTipText("Click Change Pin");
        jframe.add(close);
        close.addActionListener(this);

        createBaccount = new JButton("New_BAcc");
        createBaccount.setForeground(Color.BLACK); //
        //  createBaccount.setContentAreaFilled(false);
        createBaccount.setFocusPainted(false);
        createBaccount.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        createBaccount.setBounds(70, 285, 100, 25);
        createBaccount.setToolTipText("Click To Create Account");
        jframe.add(createBaccount);
        createBaccount.addActionListener(this);

//Banking System
        jmenubar = new JMenuBar();
        jmenubar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jmenubar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(Color.getHSBColor(400, 300, 100));
                g.fillRect(0, 10, c.getWidth(), c.getHeight());
            }
        });
        jframe.setJMenuBar(jmenubar);
        menu7 = new JMenu("File");
        menu7.setIcon(new ImageIcon("images//file.png"));
        menu7.setFont(new Font("Times New Roman", Font.BOLD, 25));
        menu7.setForeground(Color.BLACK);
        menu7.setBorder(BorderFactory.createRaisedBevelBorder());
        jmenubar.add(menu7);

//        m6_1 = new JMenuItem("Signup", new ImageIcon("images//login.png"));
//        m6_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
//        menu7.add(m6_1);
//        m6_1.addActionListener(this);
//        m7_1 = new JMenuItem("Settings", new ImageIcon("images//open.png"));
//        m7_1.setBorder(BorderFactory.createRaisedBevelBorder());
//        m7_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
//        menu7.add(m7_1);
//        m7_1.addActionListener(this);
        m8_1 = new JMenuItem("Help", new ImageIcon("images//help.png"));
        m8_1.setBorder(BorderFactory.createRaisedBevelBorder());
        m8_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu7.add(m8_1);
        m8_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Help();
            }
        });

        m5_1 = new JMenuItem("Exit", new ImageIcon("images//exit.png"));
        m5_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m5_1.setBorder(BorderFactory.createRaisedBevelBorder());
        menu7.add(m5_1);
        m5_1.addActionListener(this);

        menu1 = new JMenu("Supplier");
        menu1.setForeground(Color.BLACK);
        menu1.setBorder(BorderFactory.createRaisedBevelBorder());
        menu1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        menu1.setIcon(new ImageIcon("images//supplier.png"));
        jmenubar.add(menu1);

        m1_1 = new JMenuItem("Add New Supplier", new ImageIcon("images//addnew.png"));
        m1_1.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_1);
        m1_1.addActionListener(this);

        m1_2 = new JMenuItem("Search Supplier", new ImageIcon("images//search.png"));
        m1_2.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_2);
        m1_2.addActionListener(this);

        m1_3 = new JMenuItem("Update Supplier", new ImageIcon("images//update.png"));
        m1_3.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_3);
        m1_3.addActionListener(this);

        m1_4 = new JMenuItem("Delete Supplier", new ImageIcon("images//delete.png"));
        m1_4.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_4);
        m1_4.addActionListener(this);

        m1_5 = new JMenuItem("List Of Supplier", new ImageIcon("images//all.png"));
        m1_5.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_5);
        m1_5.addActionListener(this);

        menu2 = new JMenu("Item");
        menu2.setForeground(Color.BLACK);
        menu2.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        menu2.setIcon(new ImageIcon("images//item.png"));
        jmenubar.add(menu2);
        //Item means shop er jinis,,jemon car parking,,,,mobile side,,,,,food sector,,,,,etc
        m2_1 = new JMenuItem("Add New Item ", new ImageIcon("images//addnew.png"));
        m2_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m2_1.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.add(m2_1);
        m2_1.addActionListener(this);

        m2_2 = new JMenuItem("Search Item", new ImageIcon("images//search.png"));
        m2_2.setBorder(BorderFactory.createRaisedBevelBorder());
        m2_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu2.add(m2_2);
        m2_2.addActionListener(this);

        m2_3 = new JMenuItem("Update Item", new ImageIcon("images//update.png"));
        m2_3.setBorder(BorderFactory.createRaisedBevelBorder());
        m2_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu2.add(m2_3);
        m2_3.addActionListener(this);

        m2_4 = new JMenuItem("Delete Item", new ImageIcon("images//delete.png"));
        m2_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m2_4.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.add(m2_4);
        m2_4.addActionListener(this);

        m2_5 = new JMenuItem("Stock Of Item", new ImageIcon("images//all.png"));
        m2_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m2_5.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.add(m2_5);
        m2_5.addActionListener(this);

        menu3 = new JMenu("Billing");
        menu3.setForeground(Color.BLACK);
        menu3.setBorder(BorderFactory.createRaisedBevelBorder());
        menu3.setFont(new Font("Times New Roman", Font.BOLD, 25));
        menu3.setIcon(new ImageIcon("images//reportmenu.png"));
        jmenubar.add(menu3);

//        m3_1 = new JMenuItem("Daily Purchase Report", new ImageIcon("images//report.png"));
//        m3_1.setBorder(BorderFactory.createRaisedBevelBorder());
//        m3_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
//        menu3.add(m3_1);
//        m3_1.addActionListener(this);
        m3_4 = new JMenuItem("Customer Billing Info", new ImageIcon("images//report.png"));
        m3_4.setBorder(BorderFactory.createRaisedBevelBorder());
        m3_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu3.add(m3_4);
        m3_4.addActionListener(this);

        m3_3 = new JMenuItem("Daily Supplier Service", new ImageIcon("images//report.png"));
        m3_3.setBorder(BorderFactory.createRaisedBevelBorder());
        m3_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu3.add(m3_3);
        m3_3.addActionListener(this);

        menu4 = new JMenu("About");
        menu4.setForeground(Color.BLACK);
        menu4.setBorder(BorderFactory.createRaisedBevelBorder());
        menu4.setFont(new Font("Times New Roman", Font.BOLD, 25));
        menu4.setIcon(new ImageIcon("images//aboutmenu.png"));
        jmenubar.add(menu4);

        m4_1 = new JMenuItem("About System", new ImageIcon("images//help.png"));
        m4_1.setBorder(BorderFactory.createRaisedBevelBorder());
        m4_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu4.add(m4_1);
        m4_1.addActionListener(this);

        jframe.setTitle("Shadath Shop Management System");
        jframe.setSize(1365, 763);
        jframe.setLocation(2, 2);
        jframe.setResizable(true);
        jframe.setVisible(true);
        jframe.setBackground(Color.BLUE);
        jframe.add(new LeftPanel());//panel
        jframe.add(new RightPanel());//panel
        jframe.add(new RightPanel1());//panel
        jframe.add(new EcomersPanel());
        jframe.add(new LeftPanel1());

    }

    public void actionPerformed(ActionEvent ae) {

        //bANKING System
        if (ae.getSource() == createBaccount) {

            int i = 500;
            // String s = Integer.toString(i);
            account_name = JOptionPane.showInputDialog(null, "Enter account name:");
            account_no = JOptionPane.showInputDialog(null, "Enter account number:");
            a_balance = JOptionPane.showInputDialog(null, "Enter RS balance 500tk:");
            if (Integer.parseInt(a_balance) < i) {
                JOptionPane.showMessageDialog(null, "Keep minimum 500tk");

            } else {

                if (account_name != null && account_no != null && a_balance != null) {
                    try {
                        connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                        statement = connection.createStatement();
                        createAccountSql = "insert into account_table (account_name,account_no,balance)values('" + account_name + "','" + Integer.parseInt(account_no) + "','" + Double.parseDouble(a_balance) + "')";
                        statement.executeUpdate(createAccountSql);
                        statement.close();
                        JOptionPane.showMessageDialog(null, "Account Create Successfully");

                    } catch (SQLException err) {
                        JOptionPane.showMessageDialog(this, "Account Number already exist");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter account_name and account_no");

                }
            }

        } else if (ae.getSource() == balance) {
            account_no = JOptionPane.showInputDialog(null, "Enter Bank Account Noumber:");
            int acc_no = Integer.parseInt(account_no);
            if (account_no == null) {
                JOptionPane.showMessageDialog(this, " Please fill up Bank Account Number");
            }
            try {
                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement();
                result = statement.executeQuery("select * from account_table where account_no like '" + acc_no + "'");
                result.next();
                totalbalance = (int) Double.parseDouble(result.getString("balance"));
                JOptionPane.showMessageDialog(null, "Your Bank Balance is = " + totalbalance + "tk");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Account Number Mismatch!");

            }

        } else if (ae.getSource() == withdraw) {

            account_no = JOptionPane.showInputDialog(null, "Enter Bank Account Number:");
            w_balance = JOptionPane.showInputDialog(null, "Enter Withdraw Amount:");
            int acc_no = Integer.parseInt(account_no);
            double w_bal = Double.parseDouble(w_balance);

            if (account_no.isEmpty() && w_balance.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Account Number and Withdraw Amount:");

            } else {

                try {
                    int tmp = 0;
                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    statement = connection.createStatement();
                    createWithdrawSQl = "insert into withdraw(account_no,w_amount)values('" + acc_no + "','" + w_bal + "')";
                    statement.executeUpdate(createWithdrawSQl);
                    result = statement.executeQuery("select * from account_table where account_no = '" + acc_no + "'");
                    if (result.next()) {

                        if (Double.parseDouble(result.getString("balance")) < w_bal) {
                            JOptionPane.showMessageDialog(null, "You Have Not Sufficient Balance");
                            connection.close();
                            statement.close();

                        } else {
                            w_bal = Double.parseDouble(result.getString("balance")) - w_bal;
                        }
                        createupdatebalancesql = "update account_table set balance = '" + w_bal + "' where account_no = '" + acc_no + "'";
                        statement.executeUpdate(createupdatebalancesql);
                        tmp = 1;
                    }
                    if (tmp == 1) {
                        JOptionPane.showMessageDialog(null, "Withdrawn Successfully!\n Your Current Balance is tk:" + w_bal);
                    } else {
                        JOptionPane.showMessageDialog(null, "Password Mismatch");
                    }

                } catch (SQLException err) {
                    // JOptionPane.showMessageDialog(this, err.toString());

                }

            }

        } else if (ae.getSource() == transaction) {
            account_no = JOptionPane.showInputDialog(null, "Enter Bank Account Noumber:");
            int acc_no = Integer.parseInt(account_no);
            if (account_no == null) {
                JOptionPane.showMessageDialog(null, " Please fill up Bank Account Number");

            } else {
                try {
                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    statement = connection.createStatement();
                    depositeresult = statement.executeQuery("select * from deposite where account_no = '" + acc_no + "'");
                    if (depositeresult.next()) {
                        JOptionPane.showMessageDialog(null, "Deposited Details\n" + "Account No:" + depositeresult.getString("account_no") + "\nDeposite Amount:" + depositeresult.getString("d_amount") + "\nDate:" + depositeresult.getString("date") + "\nClick 'OK' To See Withdraw");
                    }
                    withdrawresult = statement.executeQuery("select * from withdraw where account_no = '" + acc_no + "'");
                    if (withdrawresult.next()) {
                        JOptionPane.showMessageDialog(null, "Withdrw Details\n" + "Account No:" + withdrawresult.getString("account_no") + "\nWithdraw Amount:" + withdrawresult.getString("w_amount") + "\nDate:" + withdrawresult.getString("date") + "");
                    }

                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(this, err.toString());
                }

            }

        } else if (ae.getSource() == deposite) {

            int i = 0;
            account_no = JOptionPane.showInputDialog(null, "Enter Bank Account Number:");
            a_balance = JOptionPane.showInputDialog(null, "Enter Deposite Amount:");
            if (Integer.parseInt(a_balance) < i) {
                JOptionPane.showMessageDialog(null, "Please Deposite Minimum 1 tk");

            } else {
                if (account_no.isEmpty() || a_balance.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Account Number and Deposite Amount");
                } else {
                    int acc_no = Integer.parseInt(account_no);
                    double d_balance = Double.parseDouble(a_balance);
                    try {
                        int tmp = 0;
                        connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                        statement = connection.createStatement();
                        createDepositeSql = "insert into deposite (account_no,d_amount)values('" + acc_no + "','" + d_balance + "')";
                        statement.executeUpdate(createDepositeSql);
                        result = statement.executeQuery("select * from account_table where account_no = '" + acc_no + "'");
                        if (result.next()) {

                            d_balance = d_balance + Double.parseDouble(result.getString("balance"));
                            updateAccountTableSql = "update account_table set balance = '" + d_balance + "' where account_no = '" + acc_no + "'";
                            statement.executeUpdate(updateAccountTableSql);

                            tmp = 1;
                        }
                        if (tmp == 1) {
                            JOptionPane.showMessageDialog(null, "Amount Deposited Successfully.\nYour Current Balance is tk = " + d_balance);

                        } else {
                            JOptionPane.showMessageDialog(null, "Account Number Mismatch!");

                        }

                    } catch (SQLException err) {
                        JOptionPane.showMessageDialog(this, err.toString());

                    }

                }
            }
        } else if (ae.getSource() == close) {

            String old = JOptionPane.showInputDialog(this, "Enter Old Account_no");
            int oldpin = Integer.parseInt(old);
            String present = JOptionPane.showInputDialog(this, "Enter New Account_no");
            double presentpin = Double.parseDouble(present);
            try {

                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement();
                result = statement.executeQuery("select * from account_table where account_no = '" + oldpin + "'");
                if (result.next()) {
                    updateAccountTableSql = "update account_table set account_no = '" + presentpin + "' where account_no = '" + oldpin + "'";
                    statement.executeUpdate(updateAccountTableSql);
                    JOptionPane.showMessageDialog(null, "Account_no updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Account_no Mismatch!");

                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

        //Banking System
        if (ae.getSource() == m1_1) {

            //jframe.dispose();
            new AddNewSupplier();

        } else if (ae.getSource()
                == m1_2) {

            // jframe.dispose();
            new SearchSupplier();

        } else if (ae.getSource()
                == m1_3) {

            //  jframe.dispose();
            new UpdateSupplier();

        } else if (ae.getSource()
                == m1_4) {

            //  jframe.dispose();
            new DeleteSupplier();

        } else if (ae.getSource()
                == m1_5) {

            // jframe.dispose();
            new StockOfSupplier();

        } else if (ae.getSource()
                == m2_1) {
            //jframe.dispose();
            new AddNewItem();
        } else if (ae.getSource()
                == m2_2) {
            // jframe.dispose();
            new SearchItem();
        } else if (ae.getSource()
                == m2_3) {
            // jframe.dispose();
            new UpdateItem();
        } else if (ae.getSource()
                == m2_4) {
            // jframe.dispose();
            new DeleteItem();
        } else if (ae.getSource()
                == m2_5) {
            // jframe.dispose();
            new StockOfItem();
        } else if (ae.getSource()
                == m3_1) {
            // jframe.dispose();
            new DailyPurchaseReport();

        } else if (ae.getSource()
                == m3_3) {
            new SupplierService();
        } else if (ae.getSource()
                == m3_4) {
            new DailySellReport();
        } else if (ae.getSource()
                == m6_1) {
            new Food();
        } else if (ae.getSource()
                == m6_2) {
            new Electronics();
        } else if (ae.getSource()
                == m6_3) {
            new Dress();
        } else if (ae.getSource()
                == m6_4) {
            new Shoes();
        } else if (ae.getSource()
                == m6_5) {
            new Cosmatics();
        } else if (ae.getSource()
                == m7_1) {
            new Fire();
        } else if (ae.getSource()
                == m7_3) {
            // new Others();
        } else if (ae.getSource()
                == m4_1) {
//            jframe.dispose();
            new About_me();
        } else if (ae.getSource()
                == m5_1) {
            System.exit(0);
        } //Blogin        
        else if (ae.getSource()
                == signin) {
            if (txtname == null && txtpassword == null) {
                JOptionPane.showMessageDialog(null, "Please Enter UserName AND Password!");

            } else {

                try {
                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement("select `sname`,`snumber` from createaccount where `sname`=? and `snumber`=?");
                    ps.setString(1, txtname.getText());
                    ps.setString(2, String.copyValueOf(txtpassword.getPassword()));
                    ResultSet result = ps.executeQuery();
                    if (result.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successfully!");
                        txtname.setText("");
                        txtpassword.setText("");
                        deposite.setVisible(true);
                        withdraw.setVisible(true);
                        transaction.setVisible(true);
                        balance.setVisible(true);
                        createBaccount.setVisible(true);
                        close.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                    }

                } catch (SQLException err) {
                    System.out.println("err");
                    JOptionPane.showMessageDialog(this, "SQL ERR" + err);
                }
            }

        } else if (ae.getSource()
                == createaccount) {
            userName = JOptionPane.showInputDialog(null, "Enter Username");
            passWord = JOptionPane.showInputDialog(null, "Enter passwoed");
            if (userName == null && passWord == null) {
                JOptionPane.showMessageDialog(null, "Every Field is Required");
            } else {

                try {

                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    statement = connection.createStatement();
                    String sql = "insert into createaccount(sname,snumber)values('" + userName + "','" + passWord + "')";
                    statement.executeUpdate(sql);
                    statement.close();
                    JOptionPane.showMessageDialog(null, "Account Create Successfully");
                    txtname.setText(userName);
                    txtpassword.setText(passWord);

                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(this, "SQL ERR" + err);

                }

            }

        }
        //Blogin    

    }

    public static void main(String args[]) throws IOException {
        new MainMenu();

    }
}
