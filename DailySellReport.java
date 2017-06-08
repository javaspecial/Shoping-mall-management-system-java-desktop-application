
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DailySellReport extends JFrame implements ActionListener {

    private Font bd = new Font("Arial Unicode MS", Font.BOLD, 15);

    public static final String dbHost = "jdbc:mysql://localhost:3306/addnewsupplier";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    JFrame jframe;
    private JButton btnp = new JButton("Print", new ImageIcon("images//print.png"));
    private JButton btns = new JButton("Save", new ImageIcon("images//save_1.png"));
    private JButton btnq = new JButton("Quite", new ImageIcon("images//quite.png"));
    private JButton btntotal = new JButton("Total", new ImageIcon("images//total.png"));
    private JButton btnsubtotal = new JButton("Sub Total", new ImageIcon("images//subtotal.png"));
    private JButton btnreciept = new JButton("Reciept", new ImageIcon("images//recipt.png"));
    private JButton btncclear = new JButton("Clear", new ImageIcon("images//clear.png"));

    private JLabel labeln = new JLabel("Billing Information");
    private JLabel productn = new JLabel("Product Company:");
    private JLabel productq = new JLabel("Product Quantity:");
    private JLabel productp = new JLabel("Product Price:");
    private JLabel productno = new JLabel("Product Catagory:");
    private JLabel productc = new JLabel("Product Name:");

    private GregorianCalendar calendar = new GregorianCalendar();
    private String calendardate;
    private JCheckBox tax = new JCheckBox("Tax Amount:");
    private JCheckBox date = new JCheckBox("Selling Date:");
    private JLabel cusname = new JLabel("Customer Name:");
    private JLabel discount = new JLabel("Discount Amount:");

    public static JTextField txtdate = new JTextField(10);
    public static JTextField txtcusname = new JTextField(10);
    public static JTextField txttax = new JTextField(10);
    public static JTextField txtdiscount = new JTextField(10);

    public static JTextField txtn = new JTextField(10);
    public static JTextField txtq = new JTextField(10);
    public static JTextField txtp = new JTextField(10);
    public static JTextField txtno = new JTextField(10);
    public static JTextArea txtc = new JTextArea();
    public static JTextArea textArea = new JTextArea();
    public JScrollPane textscrolPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    public static JTextField txtcomp = new JTextField(10);

    public JFrame frm = new JFrame("Click Next TO Print");
    public JPanel panel = new JPanel();
    public JTextArea txta = new JTextArea();

    Font f;
    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;
//    DefaultTableModel model = new DefaultTableModel();
//    JTable tabGrid = new JTable(model);
//    JScrollPane scrlPane = new JScrollPane(tabGrid);

    public DailySellReport() {
        jframe = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        textArea.setFont(bd);

        labeln.setFont(new Font("Times New Roman", Font.BOLD, 25));
        labeln.setBounds(300, 30, 300, 25);
        jframe.add(labeln);

        productn.setFont(f);
        productn.setBounds(120, 100, 250, 25);
        jframe.add(productn);

        date.setFont(f);
        date.setBounds(430, 100, 140, 25);
        date.setContentAreaFilled(false);
        date.setBorder(BorderFactory.createLineBorder(Color.yellow));
        jframe.add(date);
        date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (date.isSelected()) {

                    calendardate = calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH)) + "-" + (calendar.get(Calendar.YEAR));

                    txtdate.setText(calendardate);
                }
            }
        });

        productq.setFont(f);
        productq.setBounds(120, 130, 250, 25);
        jframe.add(productq);

        tax.setFont(bd);
        tax.setBorder(BorderFactory.createLineBorder(Color.yellow));
        tax.setContentAreaFilled(false);
        tax.setBounds(430, 130, 140, 25);
        jframe.add(tax);
        tax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tax.isSelected()) {
                    double producttax = Double.parseDouble(txtp.getText());
                    double totaltax = producttax / 100;
                    String itoal = String.format("%.2f", totaltax);
                    txttax.setText(itoal);
                }
            }
        });

        productp.setFont(f);
        productp.setBounds(120, 160, 250, 25);
        jframe.add(productp);

        cusname.setFont(f);
        cusname.setBounds(430, 160, 250, 25);
        jframe.add(cusname);

        productno.setFont(f);
        productno.setBounds(120, 190, 250, 25);
        jframe.add(productno);

        discount.setFont(f);
        discount.setBounds(430, 190, 250, 25);
        jframe.add(discount);

        productc.setFont(f);
        productc.setBounds(120, 220, 250, 25);
        jframe.add(productc);

        txtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtn.setBounds(310, 100, 100, 25);
        txtn.setFont(bd);
        txtn.setToolTipText("EnterProductName");
        jframe.add(txtn);

        txtdate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtdate.setFont(bd);
        txtdate.setBounds(580, 100, 100, 25);
        txtdate.setToolTipText("EnterProductName");
        jframe.add(txtdate);

        txtq.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtq.setFont(bd);
        txtq.setBounds(310, 130, 100, 25);
        txtq.setToolTipText("EnterProductName");
        jframe.add(txtq);

        txttax.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txttax.setFont(bd);
        txttax.setBounds(580, 130, 100, 25);
        txttax.setToolTipText("EnterProductName");
        jframe.add(txttax);

        txtp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtp.setFont(bd);
        txtp.setBounds(310, 160, 100, 25);
        txtp.setToolTipText("EnterProductName");
        jframe.add(txtp);

        txtcusname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtcusname.setBounds(580, 160, 100, 25);
        txtcusname.setFont(bd);
        txtcusname.setToolTipText("EnterProductName");
        jframe.add(txtcusname);

        txtno.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtno.setFont(bd);
        txtno.setBounds(310, 190, 100, 25);
        txtno.setToolTipText("EnterProductName");
        jframe.add(txtno);

        txtdiscount.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtdiscount.setFont(bd);
        txtdiscount.setBounds(580, 190, 100, 25);
        txtdiscount.setToolTipText("EnterProductName");
        jframe.add(txtdiscount);

        txtc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtc.setFont(bd);
        txtc.setBounds(310, 220, 100, 70);
        txtc.setToolTipText("EnterProductName");
        jframe.add(txtc);

//        txtcomp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        txtcomp.setBounds(310, 250, 100, 40);
//        txtcomp.setToolTipText("EnterProductName");
//        jframe.add(txtcomp);
        btntotal.setBounds(430, 220, 120, 35);
        btntotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btntotal.setContentAreaFilled(false);
        btntotal.setToolTipText("ClickToOpenDailyPurchaseReport");
        jframe.add(btntotal);
        btntotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");

                if (txtp.getText().equals("") || txttax.getText().equals("") || !tax.isSelected()) {
                    textscrolPane.setVisible(false);
                    btnp.setVisible(false);
                    btnq.setVisible(false);
                    btns.setVisible(false);

                    JOptionPane.showMessageDialog(null, "Please Enter Price and Tax");
                } else {
                    textscrolPane.setVisible(true);
                    btnp.setVisible(true);
                    btnq.setVisible(true);
                    btns.setVisible(true);
                }

                double totaltk1 = Double.parseDouble(txtp.getText());
                double totaltk2 = Double.parseDouble(txttax.getText());
                double totaltk = totaltk1 + totaltk2;
                String totaltkbill = String.format("%.2f", totaltk);
                textArea.setText("\t\t" + totaltkbill + "\ttk");

            }
        });

        btnsubtotal.setBounds(560, 220, 120, 35);
        btnsubtotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnsubtotal.setContentAreaFilled(false);
        btnsubtotal.setToolTipText("ClickToOpenDailyPurchaseReport");
        jframe.add(btnsubtotal);
        btnsubtotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                if (txtp.getText().equals("")) {
                    textscrolPane.setVisible(false);
                    btnp.setVisible(false);
                    btnq.setVisible(false);
                    btns.setVisible(false);

                    JOptionPane.showMessageDialog(null, "Please Enter Price");
                } else {
                    textscrolPane.setVisible(true);
                    btnp.setVisible(true);
                    btnq.setVisible(true);
                    btns.setVisible(true);
                }
                double totaltk1 = Double.parseDouble(txtp.getText());
                String totaltkbill = String.format("%.2f", totaltk1);
                textArea.setText("\t\t" + totaltkbill + "\ttk");
            }
        });

        btnreciept.setBounds(430, 256, 120, 35);
        btnreciept.setContentAreaFilled(false);
        btnreciept.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnreciept.setToolTipText("ClickToClear");
        jframe.add(btnreciept);
        btnreciept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtp.getText().equals("") || txttax.getText().equals("") || !tax.isSelected()) {
                    textscrolPane.setVisible(false);
                    btnp.setVisible(false);
                    btns.setVisible(false);
                    btnq.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Please Enter Price and Tax");
                } else {
                    textscrolPane.setVisible(true);
                    btnp.setVisible(true);
                    btns.setVisible(true);
                    btnq.setVisible(true);
                }

                textArea.setText("");
                String Date;
                GregorianCalendar calndr = new GregorianCalendar();
                Date = calndr.get(Calendar.DATE) + "-" + (calndr.get(Calendar.MONTH) + 1) + "-" + (calndr.get(Calendar.YEAR));

                double totaltk1 = Double.parseDouble(txtp.getText());
                double totaltk2 = Double.parseDouble(txttax.getText());
                double totaltk = totaltk1 + totaltk2;
                String totaltkbill = String.format("%.2f", totaltk);

                double totaltk11 = Double.parseDouble(txtp.getText());
                String subt = String.format("%.2f", totaltk11);

                textArea.append("\t\tShadath Shop\n\n" + "Date:\t\t" + Date + "\nCustomer Name:\t" + txtcusname.getText() + "\nProduct Name:\t\t" + txtc.getText() + "\nProduct Company:\t" + txtn.getText() + "\nProduct Quantity:\t" + txtq.getText() + "\nProduct Price:\t\t" + txtp.getText() + "\nProduct Catagory:\t" + txtno.getText() + "\n\nTotal :\t" + totaltkbill + "\ttk" + "\nSub Total\t" + subt + "\ttk" + "\nTax:\t" + txttax.getText() + "\ttk" + "\nDiscount:\t" + txtdiscount.getText() + "\ttk");

            }
        });

        btncclear.setBounds(560, 256, 120, 35);
        btncclear.setContentAreaFilled(false);
        btncclear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btncclear.setToolTipText("ClickToClear");
        jframe.add(btncclear);
        btncclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtc.setText("");
                txtcomp.setText("");
                txtcusname.setText("");
                txtdate.setText("");
                txtdiscount.setText("");
                txtn.setText("");
                txtno.setText("");
                txtp.setText("");
                txtq.setText("");
                txttax.setText("");

            }
        });

        textscrolPane.setBounds(120, 310, 560, 170);
        textscrolPane.setVisible(false);
        textscrolPane.setFont(bd);
        textscrolPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(textscrolPane);

        btnp.setBounds(120, 500, 80, 25);
        btnp.setVisible(false);
        btnp.setContentAreaFilled(false);
        btnp.setFocusPainted(false);
        btnp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(btnp);
        btnp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Boolean complete = textArea.print();

                } catch (PrinterException ex) {
                    Logger.getLogger(DailySellReport.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        btns.setBounds(220, 500, 80, 25);
        btns.setVisible(false);
        btns.setContentAreaFilled(false);
        btns.setFocusPainted(false);
        btns.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(btns);
        btns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser save = new JFileChooser("Save");
                int option = save.showSaveDialog(jframe);
                if (option == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                        out.write(textArea.getText());
                        out.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        btnq.setBounds(320, 500, 80, 25);
        btnq.setVisible(false);
        btnq.setContentAreaFilled(false);
        btnq.setFocusPainted(false);
        btnq.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jframe.add(btnq);
        btnq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnp.setVisible(false);
                btnq.setVisible(false);
                btns.setVisible(false);
                textscrolPane.setVisible(false);

            }
        });

        jframe.setTitle("Customer Billing Info");
        jframe.setSize(810, 600);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

    }

    public static void main(String args[]) {
        new DailySellReport();
    }
}
