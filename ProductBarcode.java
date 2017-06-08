import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
public class ProductBarcode extends JFrame {
    
    public  String input = RightPanel1.txtfield.getText();
    public final long serialVersionUID = Long.parseLong(input);
    public Component createBarcode(String text, Color color, boolean usePanel, boolean useLabel) throws BarcodeException {
        JComponent retVal;
        if(useLabel)
            retVal = new JLabel(text);
        else {
            Barcode barcode = BarcodeFactory.createCode128B(text);
            barcode.setBarWidth(1);
            barcode.setBarHeight(40);
            if(usePanel) {
                barcode.setBackground(Color.gray);
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.add(barcode);
                retVal = panel;
            } else
                retVal = barcode;
        }
        retVal.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 5), BorderFactory.createEmptyBorder(10,10,10,10)));
        if(useLabel)
            retVal.setForeground(color); 
        else
            retVal.setBackground(color);
        return retVal;
    }
    public ProductBarcode(boolean usePanels, boolean useLablel) {
        super("Barcode");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0,0,220,105);
        setLocationRelativeTo(this);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        try {
            getContentPane().add(createBarcode(""+serialVersionUID, Color.DARK_GRAY, usePanels, useLablel), BorderLayout.NORTH);
        } catch (BarcodeException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ProductBarcode(true, false).setVisible(true);
    }
}