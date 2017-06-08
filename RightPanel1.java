
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.prompt.PromptSupport;

public class RightPanel1 extends JPanel implements ActionListener {

    private JLabel header;
    private JLabel barcode;
    private JLabel barlabel;
    private JButton txtclear, txtsubmit;
    public static JTextField txtfield = new JTextField();

    public RightPanel1() {
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(48, 25, 270, 320);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        barcode = new JLabel("Product Barcode");
        barcode.setForeground(Color.BLACK);
        barcode.setBounds(48, 20, 300, 35);
        barcode.setFont(new Font("Times New Roman", Font.BOLD, 25));
        this.add(barcode);

        barlabel = new JLabel("Enter Barcode:");
        barlabel.setForeground(Color.BLACK);
        barlabel.setBounds(22, 60, 150, 25);
        barlabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.add(barlabel);

        txtfield.setBackground(Color.getHSBColor(400, 300, 100));
        txtfield.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtfield.setBounds(137, 62, 100, 25);
        this.add(txtfield);
        txtfield.addKeyListener(new KeyAdapter() {
            public void KeyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    new ProductBarcode(true, false).setVisible(true);
                    txtfield.setText("");

                }
            }
        });
        PromptSupport.setPrompt("(Enter barcode)", txtfield);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txtfield);
        PromptSupport.setFontStyle(Font.BOLD, txtfield);

        txtclear = new JButton("Clear Field");
        //   txtclear.setContentAreaFilled(false);
        txtclear.setFocusPainted(false);
        txtclear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtclear.setBounds(22, 100, 100, 25);
        txtclear.setToolTipText("Click to clear");
        this.add(txtclear);
        txtclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtfield.setText("");

            }
        }
        );

        txtsubmit = new JButton("Press");
        // txtsubmit.setContentAreaFilled(false);
        txtsubmit.setFocusPainted(false);
        txtsubmit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtsubmit.setBounds(137, 100, 100, 25);
        txtsubmit.setToolTipText("Click to verified");
        this.add(txtsubmit);
        txtsubmit.addActionListener(this);

        header = new JLabel("Banking System");
        header.setForeground(Color.BLACK);
        header.setBounds(48, 135, 300, 35);
        header.setFont(new Font("Times New Roman", Font.BOLD, 25));
        this.add(header);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == txtsubmit) {
            new ProductBarcode(true, false).setVisible(true);
            txtfield.setText("");

        }

    }

}
