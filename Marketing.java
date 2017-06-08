
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
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
public class Marketing extends JPanel {
    
    private JButton TEST = new JButton("test");
    public Marketing(){
    
    this.setLayout(null);
    this.setBorder (BorderFactory.createLineBorder(Color.YELLOW));
    this.setBackground (Color.lightGray);
    
    TEST.setBounds(100,150,40,20);
    this.add(TEST);
}
}