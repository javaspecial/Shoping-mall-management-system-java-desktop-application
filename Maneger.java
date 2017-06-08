
import java.awt.Color;
import javax.swing.BorderFactory;
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
public class Maneger extends JPanel {
    public Maneger(){
        
        this.add( new JLabel("Shop Manager "));
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        this.setBackground(Color.lightGray);
        
    }
    
}
