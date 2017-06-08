
import com.sun.rowset.internal.InsertRow;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * p
 *
 * @author Toxic
 */
public class SearchItem extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

    JFrame jframe;
    Font f;
    JTextField txt1, txt2, txt3, txt5, txt6, txt7, txt8, txt9, txt10, txt11, txt12;
    JLabel label1, label2, label3, label5, label6, label7, label8, label9, label10, label11, label12, labeln;
    JButton button1, button2, button3;
    JComboBox msname;
    String s;

    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    private JPopupMenu popup;

    public SearchItem() {

        jframe = new JFrame();
        f = new Font("Timens New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);


        labeln = new JLabel("Search Item");
        labeln.setFont(new Font("Times New Roman", Font.BOLD, 25));
        labeln.setBounds(330, 30, 400, 40);
        jframe.add(labeln);

        label1 = new JLabel("Item Batch No");
        label1.setFont(f);
        label1.setBounds(50, 100, 200, 25);
        jframe.add(label1);
        txt1 = new JTextField(20);
        txt1.setBounds(250, 100, 100, 25);
                txt1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt1.setToolTipText("EnterItemBatchNo");
        jframe.add(txt1);

        label2 = new JLabel("Item Name");
        label2.setFont(f);
        label2.setBounds(50, 140, 200, 25);
        jframe.add(label2);
        txt2 = new JTextField(20);
                txt2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt2.setBounds(250, 140, 100, 25);
        txt2.setToolTipText("EnterItemName");
        jframe.add(txt2);

        label3 = new JLabel("Item Company");
        label3.setFont(f);
        label3.setBounds(50, 180, 200, 25);
        jframe.add(label3);
        txt3 = new JTextField(20);
                txt3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt3.setBounds(250, 180, 100, 25);
        jframe.add(txt3);

        label5 = new JLabel("Item Expiry Date");
        label5.setFont(f);
        label5.setBounds(50, 220, 200, 25);
        jframe.add(label5);
        txt5 = new JTextField(20);
                txt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt5.setBounds(250, 220, 100, 25);
        jframe.add(txt5);

        label6 = new JLabel("Item Purchase Date");
        label6.setFont(f);
        label6.setBounds(50, 260, 250, 25);
        jframe.add(label6);
        txt6 = new JTextField(20);
                txt6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt6.setBounds(250, 260, 100, 25);
        jframe.add(txt6);

        label7 = new JLabel("Item Type");
        label7.setFont(f);
        label7.setBounds(430, 100, 200, 25);
        jframe.add(label7);
        txt7 = new JTextField(20);
                txt7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt7.setBounds(630, 100, 100, 25);
        jframe.add(txt7);

        label8 = new JLabel("Item Purchase Price");
        label8.setFont(f);
        label8.setBounds(430, 140, 220, 25);
        jframe.add(label8);
        txt8 = new JTextField(20);
                txt8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt8.setBounds(630, 140, 100, 25);
        jframe.add(txt8);

        label9 = new JLabel("Item Sale Price");
        label9.setFont(f);
        label9.setBounds(430, 180, 200, 25);
        jframe.add(label9);
        txt9 = new JTextField(20);
                txt9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt9.setBounds(630, 180, 100, 25);
        jframe.add(txt9);

        label10 = new JLabel("Item Rack No");
        label10.setFont(f);
        label10.setBounds(430, 220, 200, 25);
        jframe.add(label10);
        txt10 = new JTextField(20);
                txt10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt10.setBounds(630, 220, 100, 25);
        jframe.add(txt10);

        label11 = new JLabel("Supplier name");
        label11.setFont(f);
        label11.setBounds(430, 260, 180, 25);
        jframe.add(label11);
        txt11 = new JTextField(20);
        txt11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt11.setBounds(630, 260, 100, 25);
        jframe.add(txt11);

        button1 = new JButton("Search", new ImageIcon("images//search.png"));
        button1.setBounds(200, 330, 110, 35);
        button1.setToolTipText("Click To Search");
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Clear", new ImageIcon("images//clear.png"));
        button2.setBounds(350, 330, 110, 35);
        button2.setToolTipText("Click To Clear All");
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button2);
        button2.addActionListener(this);

        button3 = new JButton("All", new ImageIcon("images//all.png"));
        button3.setBounds(500, 330, 110, 35);
        button3.setToolTipText("Click To View All");
        button3.setContentAreaFilled(false);
        button3.setFocusPainted(false);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(button3);
        button3.addActionListener(this);

        scrlPane.setBounds(0, 380, 810, 300);
        jframe.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));

        model.addColumn("BNO");
        model.addColumn("NAME");
        model.addColumn("COMPANY");
        model.addColumn("EXPDATE");
        model.addColumn("PURDATE");
        model.addColumn("TYPE");
        model.addColumn("SALEPRICE");
        model.addColumn("PURPRICE");
        model.addColumn("RACKNO");
        model.addColumn("SNAME");
        
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


        jframe.setTitle("Search Item");
        jframe.setVisible(true);
        jframe.setLocation(270, 100);
        jframe.setSize(810, 600);
        jframe.setResizable(false);

    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==button1){
            try{
                if(((txt1.getText()).equals(""))||(txt2.getText().equals(""))){
                    JOptionPane.showMessageDialog(this, "Enter Item BatchNo or Name");
                }
                else{
                    int founderc = 0;
                    connection = (Connection)DriverManager.getConnection(dbHost,dbUser,dbPass);
                    ps = connection.prepareStatement("select * from item where Ibno = '"+txt1.getText()+"' or Iname = '"+txt2.getText()+"'");
                    rs = ps.executeQuery();
                    while(rs.next()){
                        txt1.setText(rs.getString(1));
                        txt2.setText(rs.getString(2));
                        txt3.setText(rs.getString(3));
                        txt5.setText(rs.getString(4));
                        txt6.setText(rs.getString(5));
                        txt7.setText(rs.getString(6));
                        txt8.setText(rs.getString(7));
                        txt9.setText(rs.getString(8));
                        txt10.setText(rs.getString(9));
                        txt11.setText(rs.getString(10));
                        founderc = 1;
                        
                    }
                    if(founderc == 0){
                        JOptionPane.showMessageDialog(this, "Record is not Available");
                    }
                }connection.close();
                
            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(null, "SQL ERR"+err);
                
            }
            catch(Exception err){
                JOptionPane.showMessageDialog(null, "ERR"+err);
            }
            
                
            
        }
      else  if(ae.getSource()==button2){
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt5.setText("");
            txt6.setText("");
            txt7.setText("");
            txt8.setText("");
            txt9.setText("");
            txt10.setText("");
            txt11.setText("");
        }
      else if(ae.getSource()==button3){
          try{
              int foundrec = 0;
              int row = 0;
              connection = (Connection)DriverManager.getConnection(dbHost,dbUser,dbPass);
              statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
              rs = statement.executeQuery("select * from item");
              while(rs.next()){
                  
                  model.insertRow(row++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)});
                        txt1.setText(rs.getString(1));
                        txt2.setText(rs.getString(2));
                        txt3.setText(rs.getString(3));
                        txt5.setText(rs.getString(4));
                        txt6.setText(rs.getString(5));
                        txt7.setText(rs.getString(6));
                        txt8.setText(rs.getString(7));
                        txt9.setText(rs.getString(8));
                        txt10.setText(rs.getString(9));
                        
                        foundrec = 1;
              }
              if(foundrec == 0){
                  JOptionPane.showMessageDialog(null, "Record is not available");
              }
          } catch (SQLException ex) {
                Logger.getLogger(SearchItem.class.getName()).log(Level.SEVERE, null, ex);
            }
      }  
      
    }

    public static void main(String args[]) {
        new SearchItem();
    }

}
