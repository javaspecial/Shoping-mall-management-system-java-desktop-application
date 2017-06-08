
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Toxic
 */
public class Help extends JFrame {

    private JLabel helpuser = new JLabel("User  Help Window");
    private JFrame jframe = new JFrame();
    private  JLabel helplabel = new JLabel("Everything will be ok if u try hard.");

    public Help() {
        jframe.setTitle("Help");
        jframe.setLayout(null);
        jframe.setSize(810, 600);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(this);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        helpuser.setVisible(true);
        helpuser.setBounds(300,100,300,25);
        helpuser.setFont(new Font("Times New Roman ",Font.BOLD,25));
        jframe.add(helpuser);
        
        helplabel.setVisible(true);
        helplabel.setBounds(250,250,500,25);
        helplabel.setFont(new Font("Times New Roman ",Font.BOLD,20));
        jframe.add(helplabel);
    }

    public static void main(String args[]) {
        new Help();

    }

}
