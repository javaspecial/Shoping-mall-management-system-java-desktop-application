/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Toxic
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class InvalidException extends Exception {
}

public class Login extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    private Connection connection = null;
    private PreparedStatement ps;

    JButton b1, b2, b3;
    JLabel l1, l2, l3, l4, l5, l6;
    JTextField t1, t2;
    JPasswordField p1;
    JLabel username = new JLabel("Username", JLabel.RIGHT);
    JLabel password = new JLabel("Password", JLabel.RIGHT);
    JLabel admn = new JLabel("Administrator Login", JLabel.RIGHT);
    JLabel login = new JLabel("!!Welcome!!", JLabel.RIGHT);
    Font f;
    int cnt = 0, cnt1 = 0;

  public  Login() {

        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setBackground(Color.LIGHT_GRAY);
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login with username&password"));


        login.setBounds(200, 178, 300, 40);
        pane.add(login);
        login.setFont(new Font("Times New Roman", Font.BOLD, 20));

        admn.setBounds(237, 140, 305, 40);
        pane.add(admn);
        admn.setFont(new Font("Times New Roman", Font.BOLD, 22));

        l3 = new JLabel(new ImageIcon("images//users.png"));
        l3.setBounds(200, 250, 50, 25);
        pane.add(l3);

        username.setBounds(230, 245, 110, 25);
        username.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pane.add(username);

        t1 = new JTextField(20);
        t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        t1.setBounds(350, 250, 200, 25);
        t1.setToolTipText("Enter Username");
        pane.add(t1);

        l4 = new JLabel(new ImageIcon("images//pass.png"));
        l4.setBounds(200, 300, 50, 25);
        pane.add(l4);

        password.setBounds(230, 300, 110, 25);
        password.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pane.add(password);

        p1 = new JPasswordField(20);
        p1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        p1.setBounds(350, 300, 200, 25);
        p1.setToolTipText("Enter Password");
        pane.add(p1);

        b1 = new JButton("Login", new ImageIcon("images//Login.png"));
        b1.setToolTipText("Click To Login");
        b1.setFocusPainted(false);
        b1.setBounds(350, 380, 95, 35);
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane.add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Clear", new ImageIcon("images//clear.png"));
        b2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b2.setFocusPainted(false);
        b2.setBounds(455, 380, 95, 35);
        b2.setToolTipText("Click To Clear All Textfields");
        pane.add(b2);
        b2.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {

                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                ps = connection.prepareStatement("SELECT `username`, `password` FROM `login` WHERE `username` = ? AND `password` = ?");
                ps.setString(1, t1.getText());
                ps.setString(2, String.valueOf(p1.getPassword()));
                ResultSet result = ps.executeQuery();
                if (result.next()) {

                    JOptionPane.showMessageDialog(null, " Login Successesfully ");

                    dispose();
                    new MainMenu();
                } else {
                    JOptionPane.showMessageDialog(null, " Login Failed");

                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (ae.getSource() == b2) {
            t1.setText("");
            p1.setText("");

        }

    }

    public static void main(String args[]) {

        Login panel = new Login();
        panel.setTitle("Login Window");
        panel.setLocation(270,80);
        panel.setSize(810, 600);
        panel.setResizable(false);
        panel.setVisible(true);

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
