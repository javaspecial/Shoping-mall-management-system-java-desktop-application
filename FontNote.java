

import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class FontNote extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame frame = new JFrame("Choose Font");
    private JComboBox fontbox;
    private javax.swing.Timer timer = null;
    private JButton button = new JButton("");
    

    public FontNote() {
        GraphicsEnvironment eg = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = eg.getAvailableFontFamilyNames();
        fontbox = new JComboBox(fontName);
        fontbox.setSelectedItem(0);
        fontbox.setRenderer(new ComboRenderer(fontbox));
        fontbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    final String fontName = fontbox.getSelectedItem().toString();
                    fontbox.setFont(new Font(fontName, Font.PLAIN, 16));
                    start();
                }

            }

        });
        fontbox.setSelectedItem(0);
        fontbox.getEditor().selectAll();
        frame.setLayout(new GridLayout(4, 0, 20, 20));
        frame.add(fontbox);
        frame.setLocation(50, 500);
        frame.pack();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                fontbox.setPopupVisible(true);
                fontbox.setPopupVisible(false);
            }
        });
        frame.setVisible(true);
    }

    private void start() {
        timer = new javax.swing.Timer(750, updateCol());
        timer.setRepeats(false);
        timer.start();

    }

    private Action updateCol() {
        return new AbstractAction("") {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                final Font fnt = new Font(fontbox.getSelectedItem().toString(), Font.PLAIN, 12);
                final FontUIResource res = new FontUIResource(fnt);
                UIManager.getLookAndFeelDefaults().put("TextArea.font", res);
                SwingUtilities.updateComponentTreeUI(frame);

            }
        };

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                FontNote fontNote = new FontNote();
            }
        });
    }

    private static class ComboRenderer extends BasicComboBoxRenderer {

        private static final long serialVersionUID = 1L;
        private JComboBox comboBox;
        final DefaultListCellRenderer defaultRenderrer = new DefaultListCellRenderer();
        private int row;

        public ComboRenderer(JComboBox fontbox) {
            comboBox = fontbox;
        }

        public void manItemInCombo() {
            if (comboBox.getItemCount() > 0) {
                final Object comp = comboBox.getUI().getAccessibleChild(comboBox, 0);
                if ((comp instanceof JPopupMenu)) {
         
                    final JList list = new JList(comboBox.getModel());
                    final JPopupMenu popup = (JPopupMenu) comp;
                    final JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
                    final JViewport viewport = scrollPane.getViewport();
                    final Rectangle rect = popup.getVisibleRect();
                    final Point pt = viewport.getViewPosition();
                    row = list.locationToIndex(pt);

                }
            }
        }

         @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (list.getModel().getSize() > 0) {
                manItemInCombo();
            }
            final JLabel renderer = (JLabel) defaultRenderrer.getListCellRendererComponent(list, value, row, isSelected, cellHasFocus);
            final Object fntObj = value;
            final String fontFamilyName = (String) fntObj;
            setFont(new Font(fontFamilyName, Font.PLAIN, 16));
            return this;

        }
    }

}
