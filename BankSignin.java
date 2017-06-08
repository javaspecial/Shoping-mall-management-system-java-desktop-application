
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class BankSignin extends JFrame implements ActionListener {

    private static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    private static final String dbUser = "root";
    private static final String dbPass = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result, depositeresult, withdrawresult, balanceresult;
    PreparedStatement ps;
    JLabel labelb1;
    JFrame jframe;
    JButton createaccount, deposite, withdraw, transaction, balance, close;
    Font f;
    private int totalbalance;
    private String  account_name, a_balance, w_balance;
     private String account_no= null;
    private String createAccountSql, createDepositeSql, createWithdrawSQl, updateAccountTableSql, createupdatebalancesql;

    public BankSignin() {
        jframe = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        

        labelb1 = new JLabel("Shadath Bank");
        labelb1.setBounds(112, 60, 200, 20);
        labelb1.setFont(f);
        jframe.add(labelb1);

        deposite = new JButton("Deposite");
        deposite.setBounds(50, 100, 120, 25);
        deposite.setToolTipText("Click To Deposite");
        jframe.add(deposite);
        deposite.addActionListener(this);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(180, 100, 100, 25);
        withdraw.setToolTipText("Click To  Withdraw");
        jframe.add(withdraw);
        withdraw.addActionListener(this);

        transaction = new JButton("Transaction");
        transaction.setBounds(50, 130, 120, 25);
        transaction.setToolTipText("Click To  Transaction");
        jframe.add(transaction);
        transaction.addActionListener(this);

        balance = new JButton("Balance");
        balance.setBounds(180, 130, 100, 25);
        balance.setToolTipText("Click To Balance");
        jframe.add(balance);
        balance.addActionListener(this);

        close = new JButton("Close", new ImageIcon("images//exit.png"));
        close.setBounds(180, 160, 100, 25);
        close.setToolTipText("Click To Close");
        jframe.add(close);
        close.addActionListener(this);

        createaccount = new JButton("New Account");
        createaccount.setBounds(50, 160, 120, 25);
        createaccount.setToolTipText("Click To Create Account");
        jframe.add(createaccount);
        createaccount.addActionListener(this);

        jframe.setTitle("Banking System");
        jframe.setSize(350, 350);
        jframe.setLocation(40, 70);
        jframe.setVisible(true);
        jframe.setResizable(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createaccount) {

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

        } else if (e.getSource() == balance) {
            account_no = JOptionPane.showInputDialog(null, "Enter Bank Account Noumber:");
            int acc_no = Integer.parseInt(account_no);
            if (account_no== null) {
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
             

//            account_no = JOptionPane.showInputDialog(null, "Enter Bank Account Noumber:");
//            int acc_no = Integer.parseInt(account_no);
//            if (account_no == null) {
//                JOptionPane.showMessageDialog(null, " Please fill up Bank Account Number");
//
//            } else {
//                try {
//                    int tmp = 0; 
//                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
//                    statement = connection.createStatement();
//                    balanceresult = statement.executeQuery("select * from account_table where account_no = '" + acc_no + "'");
//                    
//                    result = statement.executeQuery("select * from account_table where balance = '" + acc_no + "'");
//                   if( result.next())
//                       
//                             
//                    int balance = (int) Double.parseDouble(result.getString("balance"));
//                    tmp = 1;
//                   
//                if(tmp == 1){
//                    JOptionPane.showMessageDialog(null, "Your Bank Balance is = "+balance);
//                }else{
//                   JOptionPane.showMessageDialog(null, "Account Number Mismatch!"); 
//                }
//                } catch (SQLException err) {
//                    JOptionPane.showMessageDialog(this, err.toString());
//                }
//
//            }
        } else if (e.getSource() == withdraw) {

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

        } else if (e.getSource() == transaction) {
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

        } else if (e.getSource() == deposite) {

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
        } else if (e.getSource() == close) {
            jframe.setVisible(false);
            jframe.dispose();
        }

    }

    public static void main(String args[]) {
        new BankSignin();

    }

}
