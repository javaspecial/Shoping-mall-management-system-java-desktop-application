
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class StockOfItem extends JFrame {
    
    private static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    private static final String dbUser = "root";
    private static final String dbPass = "";

    JFrame jframe = new JFrame();
    JLabel ln;
    Connection connection;
    PreparedStatement ps;
    Statement statetment;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    private JPopupMenu popup;

    public StockOfItem() {

        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        ln = new JLabel("Stock Of Item");
        ln.setFont(new Font("Times New Roman", Font.BOLD, 25));
        ln.setBounds(300, 30, 300, 25);
        jframe.add(ln);

        scrlPane.setBounds(0, 150, 807, 500);
        jframe.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));

        model.addColumn("Batchno");
        model.addColumn("Name");
        model.addColumn("Company");
        model.addColumn("Type");
        model.addColumn("Purcahasedate");
        model.addColumn("Expirydate");
        model.addColumn("Purchaseprice");
        model.addColumn("Saleprice");
        model.addColumn("Rackno");
        model.addColumn("suppliername");
        
           popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabGrid.getSelectedRow();
                if (row >= 0) {

                    model.removeRow(row);
                }
            }
        });
        tabGrid.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                int row = tabGrid.rowAtPoint(e.getPoint());
                tabGrid.getSelectionModel().setSelectionInterval(row, row);
                if(e.getButton()== MouseEvent.BUTTON3){
                    popup.show(tabGrid, e.getX(), e.getY());
                }
            }
        });

        int r = 0;
        try {

            connection = DriverManager.getConnection(dbHost,dbUser,dbPass);
            statetment = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statetment.executeQuery("select * from item");
            while (rs.next()) {
                model.insertRow(r++, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)});

            }

            connection.close();
        } catch (SQLException se) {
            System.out.println(se);
            JOptionPane.showMessageDialog(null, "SQL Error:" + se);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error:" + e);
        }

        jframe.setTitle("Item  List");
        jframe.setSize(810, 600);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public static void main(String args[]) {
        new StockOfItem();
    }
}
