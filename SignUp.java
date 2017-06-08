
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Toxic
 */
public class SignUp extends JFrame implements ActionListener {

    //For connection
    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";
   
    
    
    
    
    private static String SignUp;

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;

    private String userName = "";
    private String password1 = "";
    private String firstName = "";
    private String lastName = "";
    private String COUNTRY = "";
    private String PHONENO = "";

    JLabel username = new JLabel("Username", JLabel.RIGHT);
    JLabel password = new JLabel("Password", JLabel.RIGHT);
    JLabel firstname = new JLabel("FirstName");
    JLabel lastname = new JLabel("LastName");
    JLabel country = new JLabel("Country");
    JLabel phoneno = new JLabel("PhoneNo");
    JLabel logintxtset = new JLabel("Already have an account??", JLabel.RIGHT);

    JTextField txtUsername = new JTextField(20);
    JTextField txtPassword = new JPasswordField(20);
    JTextField txtFirstname = new JTextField(20);
    JTextField txtLastname = new JTextField(20);
    JTextField txtSUser = new JTextField(20);
    JTextField txtcountry = new JTextField(20);
    JTextField txtphoneno = new JTextField(20);

    private String sUser = "";

    JButton newbtn = new JButton("Submit");
    JButton search = new JButton("Search");
    JButton Loginbtn = new JButton("Login");
    JButton Deletebtn = new JButton("Delete");
    private Object frame;

    public SignUp() throws SQLException {

        super("User account settings");

        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setBackground(Color.CYAN);
        //pane.set(new ImageIcon("images//SIGNUP.png"));

        username.setBounds(262, 50, 93, 25);
        pane.add(username);

        username.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //username.setForeground(Color.GREEN);
        txtUsername.setToolTipText("Enter Username");
        txtUsername.setBounds(355, 50, 150, 25);
        pane.add(txtUsername);

        password.setBounds(269, 85, 81, 25);
        pane.add(password);
        password.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //password.setForeground(Color.GREEN);
        txtPassword.setToolTipText("Enter Password");
        txtPassword.setBounds(355, 85, 150, 25);
        pane.add(txtPassword);

        firstname.setBounds(270, 120, 90, 25);
        pane.add(firstname);
        firstname.setFont(new Font("Times New Roman", Font.BOLD, 20));
      //  firstname.setForeground(Color.GREEN);
        txtFirstname.setToolTipText("Enter FirstName");
        txtFirstname.setBounds(355, 120, 150, 25);
        pane.add(txtFirstname);

        lastname.setBounds(270, 155, 90, 25);
        pane.add(lastname);
        lastname.setFont(new Font("Times New Roman", Font.BOLD, 20));
       // lastname.setForeground(Color.GREEN);
        txtLastname.setToolTipText("Enter LastName");
        txtLastname.setBounds(355, 155, 150, 25);
        pane.add(txtLastname);

        country.setBounds(270, 190, 90, 25);
        pane.add(country);
        country.setFont(new Font("Times New Roman", Font.BOLD, 20));
      //  country.setForeground(Color.GREEN);
        txtcountry.setToolTipText("Enter Country");
        txtcountry.setBounds(355, 190, 150, 25);
        pane.add(txtcountry);

        phoneno.setBounds(270, 225, 90, 25);
        pane.add(phoneno);
        phoneno.setFont(new Font("Times New Roman", Font.BOLD, 20));
      //  phoneno.setForeground(Color.GREEN);
        txtphoneno.setToolTipText("Enter PhoneNo");
        txtphoneno.setBounds(355, 225, 150, 25);
        pane.add(txtphoneno);

        logintxtset.setBounds(270, 315, 180, 25);
        pane.add(logintxtset);
        logintxtset.setFont(new Font("Times New Roman", Font.BOLD, 15));
        logintxtset.setForeground(Color.BLACK);

        newbtn = new JButton("Submit", new ImageIcon("images//addnew.png"));
        newbtn.setToolTipText("Click To Submit Details");
        newbtn.setBounds(270, 280, 100, 35);
        pane.add(newbtn);
        newbtn.setForeground(Color.BLACK);
        newbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        newbtn.addActionListener(this);

        search = new JButton("Search", new ImageIcon("images//search.png"));
        search.setToolTipText("Click To Search Account");
        search.setBounds(407, 280, 100, 35);
        pane.add(search);
        search.setForeground(Color.BLACK);
        search.setFont(new Font("Times New Roman", Font.BOLD, 15));
        search.addActionListener(this);

        Loginbtn = new JButton("Login", new ImageIcon("images//Login.png"));
        Loginbtn.setToolTipText("Click To Login");
        Loginbtn.setBounds(270, 345, 100, 35);
        pane.add(Loginbtn);
        Loginbtn.setForeground(Color.BLACK);
        Loginbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        Loginbtn.addActionListener(this);

        Deletebtn = new JButton("Delete", new ImageIcon("images//delete.png"));
        Deletebtn.setToolTipText("Click To Delete Account");
        Deletebtn.setBounds(407, 345, 100, 35);
        pane.add(Deletebtn);
        Deletebtn.setForeground(Color.BLACK);
        Deletebtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        Deletebtn.addActionListener(this);

        //pane.setBackground(Color.GRAY);
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Create user account....."));

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == newbtn) {
            userName = txtUsername.getText().toString();
            password1 = txtPassword.getText().toString();
            firstName = txtFirstname.getText().toString();
            lastName = txtLastname.getText().toString();
            COUNTRY = txtcountry.getText().toString();
            PHONENO = txtphoneno.getText().toString();

//            String number = "01-123456789";
//            Pattern pattern = Pattern.compile("\\d{2}-\\d{9}");
//            Matcher matcher = pattern.matcher(PHONENO);
//            boolean matchFound = matcher.matches();
           

            if (((txtUsername.getText().equals("")) || txtPassword.getText().equals("") || txtFirstname.getText().equals("") || txtLastname.getText().equals("") || txtcountry.getText().equals("") || txtphoneno.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Every Field is Required");
            }
//            else if (!matchFound) {
//                JOptionPane.showMessageDialog(this, "Invalid Phone Number");
//            }
            
            
            
            else {
                try {
                    int tmp = 0;
                    // clear();
                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    String sql = "insert into login (Username,Password,Firstname,Lastname,Country,PhoneNo) values ('" + userName + "','" + password1 + "','" + firstName + "','" + lastName + "','" + COUNTRY + "','" + PHONENO + "')";
                    statement = connection.prepareStatement("SELECT * FROM login where username ='" + userName + "' ");
                    rs = statement.executeQuery("SELECT * FROM login where username ='" + userName + "' ");

                    if (rs.next()) {

                        txtUsername.setText(null);
                        txtPassword.setText(null);
                        txtFirstname.setText(null);
                        txtLastname.setText(null);
                        txtcountry.setText(null);
                        txtphoneno.setText(null);
                        tmp = 1;

                    }

                    if (tmp == 1) {
                        JOptionPane.showMessageDialog(null, "Username already exists");
                    } else {

                        statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Create account successfully");
                        dispose();
                        Login.ImagePanel panel2 = new Login.ImagePanel(new ImageIcon("images//LOGIN_1.png").getImage());
                        Login panel = new Login();
                        panel.setTitle("Login");
                        panel.setLocation(270, 20);
                        panel.setSize(810, 600);
                        panel.setResizable(false);
                        panel.setVisible(true);
                        panel.getContentPane().add(panel2);

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (source == search) {
            try {
                int tmp = 0;
                //clear();
//                JOptionPane.showInputDialog(null, "Enter Username to search.", "Payroll System: User settings", JOptionPane.QUESTION_MESSAGE);

                JFrame frame = new JFrame("InputDialog Example #1");

                // prompt the user to enter their name
                String name = JOptionPane.showInputDialog(frame, "Enter Username: ");
                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.prepareStatement("SELECT * FROM login where username ='" + name + "' ");
                rs = statement.executeQuery("SELECT * FROM login where username ='" + name + "' ");
                if (rs.next()) {

                    txtUsername.setText(null);
                    txtPassword.setText(null);
                    txtFirstname.setText(null);
                    txtLastname.setText(null);
                    txtcountry.setText(null);
                    txtphoneno.setText(null);
                    tmp = 1;

                }

                if (tmp == 1) {
                    JOptionPane.showMessageDialog(null, "Record found!");
                } else if (tmp == 0) {
                    JOptionPane.showMessageDialog(null, "  Record Not found!");
                }
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null, "Unable to search!.", "Payroll System: User settings", JOptionPane.ERROR_MESSAGE);
                System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
            }
        }
        if (source == Loginbtn) {
            dispose();
            //Login.ImagePanel panel2 = new Login.ImagePanel(new ImageIcon("images//LOGIN_1.png").getImage());
            Login panel = new Login();
            panel.setTitle("Login");
            panel.setLocation(270, 100);
            panel.setSize(810, 600);
            panel.setResizable(false);
            panel.setVisible(true);
           // panel.getContentPane().add(panel2);
        }

        if (source == Deletebtn) {
            try {

                userName = txtUsername.getText().toString().trim();

                String deleteSQL = "delete from login where Username = '" + userName + "'";

                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement();
                if (statement.executeUpdate(deleteSQL) != 0) {

                    JOptionPane.showMessageDialog(this, "Record Delete Successesfully");

                } else {
                    JOptionPane.showMessageDialog(this, "Record Delete Failed");
                }

            } catch (SQLException err) {
                JOptionPane.showMessageDialog(this, err.getMessage());
            }

        }
    }

    //	public void frameUser(){
    public static void main(String[] args) throws SQLException {

        ImagePanel panel2 = new ImagePanel(new ImageIcon("images//SIGN1.png").getImage());
        SignUp panel = new SignUp();
        panel.setTitle("SignUp");
        panel.setSize(810, 600);
        panel.setLocation(270, 100);
        panel.setResizable(false);
        panel.setVisible(true);
       // panel.getContentPane().add(panel2);

    }

    private boolean matchFound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class ImagePanel extends JPanel {

        private Image img;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

    }

}
