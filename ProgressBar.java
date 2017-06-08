
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class ProgressBar extends JPanel {

    private JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
    private JLabel label = new JLabel("", JLabel.CENTER);
    private JLabel loading = new JLabel("Product Uploading......", JLabel.CENTER);
    private  ImageIcon icon = new ImageIcon("C:\\Users\\Toxic\\Documents\\NetBeansProjects\\MedicaStorelManagement\\images\\LOADING.png");
   

    private Timer timer = new Timer(100, new ActionListener() {
        private int counter = 1;
        @Override
        public void actionPerformed(ActionEvent ae) {
            bar.setStringPainted(true);
            int val = bar.getValue();
            if (val >= 100) {
                timer.stop();
                return;
            }
            bar.setValue(val+=(10));
            if (bar.getValue() == 100) {
                loading.setText("Product Upload Complete!");
                icon.getAccessibleContext();
                    

                
            }
            
               
        }
    });

    public ProgressBar() {
        super.setLayout(new GridLayout(3, 1));
        bar.setValue(0);
        timer.start();
        bar.setForeground(Color.GREEN);
        bar.setBorder(BorderFactory.createEmptyBorder());
        bar.setSize(30, 20);
        this.add(bar);
        this.add(loading);
        this.add(label);
 
        JOptionPane.showMessageDialog(null,this,"Upload Processing", JOptionPane.INFORMATION_MESSAGE,icon);// 
      //JOptionPane.showOptionDialog(null, this,"Upload Processing", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ProgressBar cdpb = new ProgressBar();

            }
        });
    }
}
