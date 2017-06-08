
//creating a simple Web Browser
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class Browser extends JFrame implements ActionListener, HyperlinkListener {

    JTextField textField, search, yahoo;
    JScrollPane sp;
    JButton b;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, serch1, yahoo1;
    JEditorPane editorPane;
    JPanel p1, p2, p3, p4;
    JSpinner jsp;
    JLabel l1, l2, l3, l4, l5, l6, google, yahoo2;
    MenuBar mb;
    JToolBar t1, t2, t3;
    Menu m1, m2, m3, m4, m5, m6;
    MenuItem i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16;
    ImageIcon y1, y2, y3, y4, y5, y6, y7, y8, y9;
    Container cp;
    int count = 0;
    int store = 0;
    JTabbedPane jtb[];
    int y = 0;
    int max = 0;
    String stat[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    int count1 = 0;
    JFrame rg;
    JTextField tl1, tl0, tl2, tl3, tl4, tl5, tl6;
    JPasswordField ps1, ps2;
    int tabpos = 0;
    JButton b1c, b2c, b3c;
    int nan = 2;
    String webq = "";
    JProgressBar pq;
    //constructor

    public Browser() {
        //webq=web;	
        setLocation(0, 0);
        setTitle("Mirror Web Browser");
        setSize(new Dimension(1024, 800));

//	jtb[tabpos]=new JTabbedPane();
        try {
            File file = new File("C:/Windows/look.hta");

            if (file.exists()) {
                FileReader rt = new FileReader("C:/Windows/look.hta");
                JTextField jt = new JTextField();
                jt.read(rt, null);
                rt.close();
                UIManager.setLookAndFeel(jt.getText());

            } else {
                UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

            }
        } catch (Exception e) {
            error es = new error(e + "");
        }

        cp = getContentPane();
        cp.setLayout(null);
        // Ceate MenuBar

        mb = new MenuBar();
        m1 = new Menu(" File ");

        i1 = new MenuItem(" New ");
        i2 = new MenuItem(" Open ");

        i4 = new MenuItem(" Save ");
        i5 = new MenuItem(" Save As ");

        i3 = new MenuItem(" Set HomePage. ");

        i6 = new MenuItem(" close ");
        m1.add(i1);
        m1.add(i2);
        m1.addSeparator();
        m1.add(i4);
        m1.add(i5);
        m1.addSeparator();
        m1.add(i3);
        m1.addSeparator();
        m1.add(i6);

        mb.add(m1);

        m2 = new Menu(" Edit ");

        i7 = new MenuItem(" Cut ");
        i8 = new MenuItem(" Copy ");

        i9 = new MenuItem(" paste ");

        i10 = new MenuItem(" View Source ");
        m2.add(i7);
        m2.add(i8);
        m2.add(i9);
        m2.addSeparator();
        m2.add(i10);
        mb.add(m2);

        m3 = new Menu(" Manage ");

        i11 = new MenuItem(" Add Favourite ");
        i12 = new MenuItem(" View All Fav. ");

        i13 = new MenuItem(" Manage Fav. ");

        m3.add(i11);
        m3.add(i12);
        m3.add(i13);

        mb.add(m3);

        m4 = new Menu(" Help ");

        i14 = new MenuItem(" Help ");
        i15 = new MenuItem(" About ");

        i16 = new MenuItem(" Customer Forum ");
        m4.add(i14);
        m4.add(i15);
        m4.add(i16);

        mb.add(m4);

        setMenuBar(mb);

	// End of MenuBar
        // Create Toolbar Panel
        JPanel pt1 = new JPanel(null, true);

        pt1.setBackground(Color.white);
        pt1.setBounds(0, 0, 1028, 40);
        cp.add(pt1);

        try {

            File u = new File("C:/Temp");
            u.mkdir();

            FileWriter v = new FileWriter("C:/Temp/pointer.dat");
            v.write(0 + "");
            v.close();

            File m = new File("C:/Block.xml");

            if (!m.isFile() && !m.exists()) {
                FileWriter v3 = new FileWriter("C:/Block.xml");
                v3.write("<lock profile><web1>http://www.songs.pk</web1><web2>http://www.yahoo.com<web2><web3>http://www.orkut.com</web3><web4>http://www.facebook.com</web4><web5>http://www.ibibo.com<web5>      </lock profile>");
                v3.close();
            }

        } catch (Exception y) {
        }

        y1 = new ImageIcon("Icon/ico_alpha_nav_left_24x24.png");
        b1 = new JButton(y1);
        b1.setToolTipText("Back to previous");
        b1.setBounds(10, 02, 40, 35);
        pt1.add(b1);

        y2 = new ImageIcon("Icon/ico_alpha_nav_right_24x24.png");
        b2 = new JButton(y2);
        b2.setBounds(60, 02, 40, 35);
        b2.setToolTipText("Back to Next");
        pt1.add(b2);

        y3 = new ImageIcon("Icon/ico_alpha_Refresh_24x24.png");
        b3 = new JButton(y3);
        b3.setBounds(160, 02, 35, 35);
        b3.setToolTipText("Refresh");
        pt1.add(b3);

        y4 = new ImageIcon("Icon/ico_alpha_HomePage_32x32.png");
        b4 = new JButton(y4);
        b4.setBounds(210, 02, 35, 35);
        b4.setToolTipText("Goto Home Page");
        pt1.add(b4);

        y5 = new ImageIcon("Icon/ico_alpha_SecurityCenter_32x32.png");
        b5 = new JButton(y5);
        b5.setBounds(250, 02, 35, 35);
        b5.setToolTipText("Open Security Alerts");
        pt1.add(b5);

        y6 = new ImageIcon("Icon/ico_alpha_OpenLinkInPopup_32x32.png");
        b6 = new JButton(y6);
        b6.setBounds(300, 02, 35, 35);

        b6.setToolTipText("Block Harmful Websites");
        pt1.add(b6);

        y7 = new ImageIcon("Icon/ico_alpha_Information_32x32.png");
        b7 = new JButton(y7);
        b7.setBounds(350, 02, 35, 35);
        b7.setToolTipText("Show Information");
        pt1.add(b7);

        y8 = new ImageIcon("Icon/ico_alpha_Favorites_32x32.png");
        b8 = new JButton(y8);
        b8.setBounds(400, 02, 35, 35);
        b8.setToolTipText("Add To Favourite");
        pt1.add(b8);

        search = new JTextField();
        search.setBounds(500, 10, 150, 30);
        search.setToolTipText("Serch on GOOGLE");
        pt1.add(search);

        ImageIcon ic = new ImageIcon("ico/google.png");
        google = new JLabel(ic);
        google.setBounds(470, 10, 35, 30);
        google.setToolTipText("Serch on GOOGLE");
        pt1.add(google);

        ImageIcon ic2 = new ImageIcon("ico/serch.png");
        serch1 = new JButton(ic2);
        serch1.setBounds(650, 10, 35, 30);
        serch1.setToolTipText("Serch on GOOGLE");
        pt1.add(serch1);

        yahoo = new JTextField();
        yahoo.setBounds(740, 10, 150, 30);
        yahoo.setToolTipText("Serch on Yahoo");
        pt1.add(yahoo);

        ImageIcon ic1 = new ImageIcon("ico/yahoo.png");
        yahoo2 = new JLabel(ic1);
        yahoo2.setBounds(710, 10, 35, 30);
        yahoo2.setToolTipText("Serch on Yahoo");
        pt1.add(yahoo2);

        ImageIcon ic22 = new ImageIcon("ico/serch.png");
        yahoo1 = new JButton(ic22);
        yahoo1.setBounds(890, 10, 35, 30);
        yahoo1.setToolTipText("Serch on Yahoo");
        pt1.add(yahoo1);

	//End ToolBar
        p1 = new JPanel(new BorderLayout());
        p1.setBounds(0, 60, 1028, 650);
        cp.add(p1);

		//create an enviroment for showing web sites
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

        editorPane = new JEditorPane();
        editorPane.addHyperlinkListener(this);
        editorPane.setEditable(false);

        p2 = new JPanel(new GridLayout());

        //add the editorPane to a panel
        p2.add(editorPane);

        //creat a scroll for p2
        sp = new JScrollPane(p2, v, h);

        p1.add(sp, BorderLayout.CENTER);

        //creat a textField for writing web sites
        p3 = new JPanel();
        l1 = new JLabel(" Web  Address :");
        l1.setBounds(10, 02, 120, 30);

        textField = new JTextField(60);
        textField.setBounds(130, 02, 300, 30);
        textField.addActionListener(this);

        search.addActionListener(this);
        serch1.addActionListener(this);
        yahoo.addActionListener(this);
        yahoo1.addActionListener(this);

        //initialize our textField with "http" protocol
        textField.setText("");

        b = new JButton("Go");
        b.addActionListener(this);
        b.setBounds(450, 02, 100, 30);
        pq = new JProgressBar();
        pq.setVisible(false);
        pq.setBounds(10, 02, 100, 20);

        p3.add(l1);
        p3.add(textField);
        p3.add(b);

        p3.add(pq);

        p3.setBounds(0, 80, 1028, 34);
        p1.add(p3, BorderLayout.NORTH);

        p4 = new JPanel(new FlowLayout());

	//JSpinner jsp1=new JSpinner(); 
        //jsp1.setBounds(100,02,130,20); 
        //p4.add(jsp1);
        p4.setBounds(0, 150, 1028, 34);

        p1.add(p4, BorderLayout.SOUTH);

        i15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                about a = new about();
            }
        });

        i2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {

                JFrame jf = new JFrame();
                FileDialog f = new FileDialog(jf, "Select File");

                f.setVisible(true);

                String dir = f.getDirectory();
                String file = f.getFile();

                int i = file.lastIndexOf('.');
                if (file.substring(i + 1).toLowerCase().equals("html") || file.substring(i + 1).toLowerCase().equals("htm") || file.substring(i + 1).toLowerCase().equals("xml") || file.substring(i + 1).toLowerCase().equals("url")) {
                    try {
                        textField.setText("file://localHost/" + dir + file);
                        editorPane.setPage(new URL(textField.getText()));
                    } catch (Exception gt) {
                    }
                }

                stat[store] = "file://localHost/" + dir + file;

                store++;

                try {

                    editorPane.setFont(new Font("Arial", Font.PLAIN, 14));
                    editorPane.setPage(new URL(textField.getText()));

                    System.out.println("Text=" + textField.getText());

                } catch (Exception gt) {
                    System.out.println(gt);
                }

            }
        });

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                try {
                    store = store - 1;
                    System.err.println(store);

                    if (store == -1) {
                        b1.setEnabled(false);
                    }

                    b2.setEnabled(true);

                    System.err.println("Getting Previous Address =" + stat[store]);
		//FileReader rq=new FileReader("C:/Temp/"+y+".txt");
                    //JTextField ju=new JTextField();
                    //ju.read(rq,null);
                    //rq.close();

		//editorPane.setPage(new URL(ju.getText()));
                    textField.setText(stat[store]);

                    editorPane.setPage(new URL(stat[store]));

                } catch (Exception gt) {
                    try {

                        File rt = new File("C:/error.html");
                        if (rt.isFile() && rt.exists()) {
                            FileWriter w = new FileWriter("C:/error2.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            rt.delete();
                            editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                        } else {
                            FileWriter w = new FileWriter("C:/error.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            editorPane.setPage(new URL("file://localhost/C:/error.html"));

                        }
                    } catch (Exception hy) {
                        System.out.println("Error :" + "\n" + hy);
                    }
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                try {
                    store++;
                    System.err.println(store);
                    b1.setEnabled(true);
                    if (store == max) {
                        b2.setEnabled(false);
                    }

                    editorPane.setPage(new URL(stat[store]));

		//System.err.println("Reading File"+y+".txt");
                    //FileReader rq=new FileReader("C:/Temp/"+y+".txt");
                    //JTextField ju=new JTextField();
                    //ju.read(rq,null);
                    //rq.close();
                    //editorPane.setPage(new URL(ju.getText()));
                    textField.setText(stat[store]);

                } catch (Exception gt) {
                    try {

                        File rt = new File("C:/error.html");
                        if (rt.isFile() && rt.exists()) {
                            FileWriter w = new FileWriter("C:/error2.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            rt.delete();
                            editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                        } else {
                            FileWriter w = new FileWriter("C:/error.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            editorPane.setPage(new URL("file://localhost/C:/error.html"));

                        }
                    } catch (Exception hy) {
                        System.out.println("Error :" + "\n" + hy);
                    }
                }

            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {

                try {

                    System.out.println("Refreshing...");
                    System.out.println("Current Page..." + textField.getText());
                    int mycount = 0;
                    String m = textField.getText();
                    System.out.print("Refresh");
                    textField.setText("Refresh");
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < i; j++) {

                            textField.setText(textField.getText() + ".");

                            System.out.print(".");
                            try {
                                Thread.sleep(100);
                            } catch (Exception gt) {
                            }
                        }

                    }
                    textField.setText(m + "");
                    URL t = new URL(textField.getText());
                    editorPane.setPage(t);
                } catch (Exception hyu) {
                    System.out.println("Error:" + hyu);
                }
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                try {
                    textField.setText("http://www.google.co.in");
                    URL t = new URL("http://www.google.co.in");
                    editorPane.setPage(t);
                    stat[store] = "http://www.google.co.in";
                    store++;
                    System.out.println("Address store into Memory");

                } catch (Exception hyu) {
                    try {

                        File rt = new File("C:/error.html");

                        if (rt.isFile() && rt.exists()) {
                            FileWriter w = new FileWriter("C:/error2.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            rt.delete();
                            editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                        } else {
                            FileWriter w = new FileWriter("C:/error.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a> </body><html>");
                            w.close();
                            editorPane.setPage(new URL("file://localhost/C:/error.html"));

                        }

                    } catch (Exception gty) {
                    }

                }

            }
        });

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {

            }
        });

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                String msg1 = "";
                FileReader rd;
                JTextField jt;

                try {

                    rg = new JFrame();
                    rg.setTitle("Bloack WebSite");
                    rg.setLayout(null);
                    rg.setSize(310, 390);
                    rg.setLocation(150, 100);
                    rg.setVisible(true);

                    JLabel lw1 = new JLabel("Enter the Websites name");
                    lw1.setBounds(50, 10, 150, 30);
                    rg.add(lw1);

                    JLabel lw2 = new JLabel("WebSite 1:");
                    tl2 = new JTextField();
                    tl2.setEditable(false);
                    lw2.setBounds(10, 50, 80, 20);
                    tl2.setBounds(90, 50, 200, 20);
                    rg.add(lw2);
                    rg.add(tl2);

                    JLabel lw0 = new JLabel("WebSite 2:");
                    tl0 = new JTextField();
                    tl0.setEditable(false);
                    lw0.setBounds(10, 80, 80, 20);
                    tl0.setBounds(90, 80, 200, 20);
                    rg.add(lw0);
                    rg.add(tl0);

                    JLabel lw3 = new JLabel("WebSite 3:");
                    tl3 = new JTextField();
                    tl3.setEditable(false);
                    lw3.setBounds(10, 110, 80, 20);
                    tl3.setBounds(90, 110, 200, 20);
                    rg.add(lw3);
                    rg.add(tl3);

                    JLabel lw4 = new JLabel("WebSite 4:");
                    tl4 = new JTextField();
                    tl4.setEditable(false);
                    lw4.setBounds(10, 140, 80, 20);
                    tl4.setBounds(90, 140, 200, 20);
                    rg.add(lw4);
                    rg.add(tl4);

                    JLabel lw5 = new JLabel("WebSite 5:");
                    tl5 = new JTextField();
                    tl5.setEditable(false);
                    lw5.setBounds(10, 170, 80, 20);
                    tl5.setBounds(90, 170, 200, 20);
                    rg.add(lw5);
                    rg.add(tl5);

                    b1c = new JButton("Ok");

                    b1c.setBounds(90, 210, 100, 30);
                    rg.add(b1c);

                    JLabel lw6 = new JLabel("Password:");
                    lw6.setBounds(10, 260, 80, 20);
                    rg.add(lw6);

                    ps1 = new JPasswordField();
                    ps1.setBounds(100, 260, 200, 20);
                    rg.add(ps1);

                    JLabel lw7 = new JLabel("Confirme:");
                    lw7.setBounds(10, 290, 80, 20);
                    rg.add(lw7);
                    ps2 = new JPasswordField();
                    ps2.setBounds(100, 290, 200, 20);
                    rg.add(ps2);

                    b2c = new JButton("Unlock it!");
                    b2c.setBounds(100, 320, 100, 30);
                    rg.add(b2c);

                    rd = new FileReader("C:/Block.xml");
                    jt = new JTextField();
                    jt.read(rd, null);
                    rd.close();

                    String str1 = jt.getText();
                    int len = str1.length();

                    String str5 = "";

                    str1 = jt.getText();
                    len = str1.length();

                    for (int k = 0; k < len; k++) {
                        char ch = str1.charAt(k);
                        //System.out.println(ch);
                        if (ch == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '1' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;
                                } else {

                                    break;
                                }
                            }
                            //System.out.println("web1=" + str5);	
                            tl2.setText(str5 + "");

                        }
                    }

                    str1 = jt.getText();
                    len = str1.length();
                    str5 = "";

                    for (int k = 0; k < len; k++) {
                        char ch = str1.charAt(k);
                        System.out.println(ch);
                        if (ch == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '2' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len; j++) {
                                // System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    // System.out.println("Char Found");
                                    str5 = str5 + ch2;
                                } else {
                                    j = len;
                                }
                            }
                            //System.out.println("web2=" + str5);	
                            tl0.setText(str5);

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len = str1.length();
                    for (int k = 0; k < len; k++) {
                        char ch = str1.charAt(k);
                        // System.out.println(ch);
                        if (ch == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '3' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;
                                } else {
                                    j = len + 1;
                                }
                            }
                            //System.out.println("web3=" + str5);	
                            tl3.setText(str5);

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len = str1.length();

                    for (int k = 0; k < len; k++) {
                        char ch = str1.charAt(k);
                        // System.out.println(ch);
                        if (ch == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '4' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;
                                } else {
                                    j = len + 1;
                                }
                            }
                            //System.out.println("web4=" + str5);	
                            tl4.setText(str5);

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len = str1.length();
                    for (int k = 0; k < len; k++) {
                        char ch = str1.charAt(k);
                        // System.out.println(ch);
                        if (ch == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '5' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;
                                } else {
                                    j = len + 1;
                                }
                            }
                            //System.out.println("web5=" + str5);	
                            tl5.setText(str5);

                        }
                    }

                    b2c.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent r) {
                            String str11 = ps1.getText();
                            String str22 = ps2.getText();

                            if (str11.equals("sherkhan") && str22.equals("sherkhan")) {
                                if (nan % 2 == 0) {
                                    tl5.setEditable(true);
                                    tl0.setEditable(true);
                                    tl2.setEditable(true);
                                    tl3.setEditable(true);
                                    tl4.setEditable(true);
                                    b2c.setLabel("lock it");
                                    nan++;
                                } else {
                                    tl5.setEditable(false);
                                    tl0.setEditable(false);
                                    tl2.setEditable(false);
                                    tl3.setEditable(false);
                                    tl4.setEditable(false);
                                    b2c.setLabel("Unlock it");
                                    nan++;
                                    rg.setVisible(false);
                                }
                            } else {
                                JOptionPane.showMessageDialog((Component) null, "Please Check the Password you have Entered", "Password Not Match", JOptionPane.OK_OPTION);
                            }
                        }
                    });

                    b1c.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent r) {
                            String msg = "<lock profile> \n\r";
                            FileWriter wre;
                            try {
                                msg = msg + "<web1>" + tl2.getText() + "</web1> \n\r";
                                msg = msg + "<web2>" + tl0.getText() + "</web2> \n\r";
                                msg = msg + "<web3>" + tl3.getText() + "</web3> \n\r";
                                msg = msg + "<web4>" + tl4.getText() + "</web4> \n\r";
                                msg = msg + "<web5>" + tl5.getText() + "</web5> \n\r  </lock profile>";

                                wre = new FileWriter("C:/Block.xml");
                                wre.write(msg + "");
                                wre.close();
                                rg.setVisible(false);

                            } catch (Exception gty) {
                                System.out.println(gty);
                            }
                        }
                    });

                } catch (Exception gtyu) {
                    System.out.println(gtyu);
                }
            }
        });

        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {

                info f = new info(textField.getText());

            }
        });

        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {

            }
        });

    }//end Constructor

	//when an action performed
	// Hyperlink
    public void hyperlinkUpdate(HyperlinkEvent e) {

        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                HTMLDocument doc = (HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt);
                System.out.println("Downloading...");

            } else {
                try {
                    // pane.setPage(e.getURL());

                    String extension = "";
                    File f = new File(e.getURL().getPath());
                    String stre = f.getName();
                    int i = stre.lastIndexOf('.');
                    if (stre.substring(i + 1).toLowerCase().equals("exe") || stre.substring(i + 1).toLowerCase().equals("rar") || stre.substring(i + 1).toLowerCase().equals("mp3") || stre.substring(i + 1).toLowerCase().equals("avi") || stre.substring(i + 1).toLowerCase().equals("mp2") || stre.substring(i + 1).toLowerCase().equals("msi") || stre.substring(i + 1).toLowerCase().equals("zip") || stre.substring(i + 1).toLowerCase().equals("3gp") || stre.substring(i + 1).toLowerCase().equals("flv") || stre.substring(i + 1).toLowerCase().equals("torrent") || stre.substring(i + 1).toLowerCase().equals("mpeg")) {
                        System.out.println("Downloading Content...");
                        download d = new download(e.getURL().getPath());

                    } else {

                        System.out.println("Activate Hyper Page...");

                        try {
                            String str = e.getURL().getPath();
                            b1.setEnabled(true);

                            System.out.println("Hyper Page:" + e.getURL().getPath());
                            stat[count1] = e.getURL().getPath() + "";
                            count1++;

                            editorPane.setPage(e.getURL());

                            if (str.charAt(2) == ':') {
                                str = "file://localHost" + str;
                            }
                            textField.setText(str);

                            if (y == 0) {
                                y++;
                            }
                            b1.setEnabled(true);
                            if (str.charAt(4) == ':' || str.charAt(1) == ':') {
                                stat[store] = str;
                                max = store;
                                store++;

                                System.out.println(" Direct Address store into Memory");
                            } else {
                                stat[store] = textField.getText() + str;
                                max = store;
                                store++;
                                System.out.println("Indirect Address store into Memory");
                            }

                            System.err.println("Writing File" + y + ".txt");

                            FileWriter v2 = new FileWriter("C:/Temp/" + y + ".txt");
                            v2.write(textField.getText());
                            v2.close();
                            y++;

                        } catch (Exception hu) {
                            System.out.println(hu);
                        }

                    }
                } catch (Throwable t) {
                }

            }
        }
    }

//HyperLink
    public void actionPerformed(ActionEvent e) {

        System.out.println("Action");
        pq.setVisible(true);
        //if enter pressed in the textField

        if (e.getSource() == search || e.getSource() == serch1) {
            if (!search.getText().equals("")) {
                try {
                    textField.setText("http://www.google.com/search?q=" + search.getText() + "&ie=utf-8&oe=utf-8&aq=t&rls=org.mozilla:en-US:official&client=firefox-a");
                    editorPane.setPage(new URL(textField.getText()));

                } catch (Exception er) {

                    try {

                        File rt = new File("C:/error.html");
                        if (rt.isFile() && rt.exists()) {
                            FileWriter w = new FileWriter("C:/error2.html");
                            w.write("<Html> <head> Unable to Connect with <Font face=Arial Color=0x000ff>Google Server </font>(Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            rt.delete();
                            editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                        } else {
                            FileWriter w = new FileWriter("C:/error.html");
                            w.write("<Html> <head> Unable to Connect with <Font face=Arial Color=0x000ff>Google Server </font>(Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            editorPane.setPage(new URL("file://localhost/C:/error.html"));

                        }
                    } catch (Exception hy) {
                        System.out.println("Error :" + "\n" + hy);
                    }

                }
            } else {
                try {
                    textField.setText("http://www.google.com");
                    editorPane.setPage(new URL(textField.getText()));
                } catch (Exception fr) {
                }
            }

        }

        if (e.getSource() == yahoo || e.getSource() == yahoo1) {
            if (!yahoo.getText().equals("")) {
                try {
                    textField.setText("http://search.yahoo.com/search?p=" + yahoo.getText() + "&ei=UTF-8&fr=moz2");
                    editorPane.setPage(new URL(textField.getText()));

                } catch (Exception er) {

                    try {

                        File rt = new File("C:/error.html");
                        if (rt.isFile() && rt.exists()) {
                            FileWriter w = new FileWriter("C:/error2.html");
                            w.write("<Html> <head> Unable to Connect with <Font face=Arial Color=0x000ff>Google Server </font>(Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            rt.delete();
                            editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                        } else {
                            FileWriter w = new FileWriter("C:/error.html");
                            w.write("<Html> <head> Unable to Connect with <Font face=Arial Color=0x000ff>Google Server </font>(Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            editorPane.setPage(new URL("file://localhost/C:/error.html"));

                        }
                    } catch (Exception hy) {
                        System.out.println("Error :" + "\n" + hy);
                    }

                }
            } else {
                try {
                    textField.setText("http://www.yahoo.com");
                    editorPane.setPage(new URL(textField.getText()));
                } catch (Exception fr) {
                }
            }

        }

        if (e.getSource() == textField) {
            //get the text(site) written in textField
            int found = 0;

            try {
                b1.setEnabled(true);
                String site2 = textField.getText();

                int len = site2.length();

                if (len < 9 && (site2.charAt(1) != ':')) {
                    site2 = site2 + ".com";
                    textField.setText(site2);
                }

                char ch = site2.charAt(0);

                try {
                    pq.setValue(30);

                    Thread.sleep(500);
                } catch (Exception fr) {
                    System.out.println(fr);
                }

                if (site2.charAt(7) == 'w' && site2.charAt(8) == 'w' && site2.charAt(9) == 'w' || site2.charAt(1) == ':' || site2.charAt(4) == ':') {
                    site2 = site2;

                } else {
                    site2 = "www." + site2;

                }

                try {
                    pq.setValue(60);

                    Thread.sleep(1000);
                } catch (Exception fr) {
                    System.out.println(fr);
                }

                if (ch == 'h' && site2.charAt(1) == 't' && site2.charAt(2) == 't' && site2.charAt(3) == 'p' || site2.charAt(1) == ':' || site2.charAt(4) == ':') {
                    System.out.println("Http protocol fond");
                    site2 = site2;
                    textField.setText(site2);
                } else {
                    System.out.println("Http protocol Not fond");
                    site2 = "http://" + site2;
                    textField.setText(site2);
                }

                // Checking for Block Website
                try {

                    FileReader rd = new FileReader("C:/Block.xml");
                    JTextField jt = new JTextField();
                    jt.read(rd, null);
                    rd.close();

                    String str1 = jt.getText();
                    int len3 = str1.length();

                    String str5 = "";

                    str1 = jt.getText();
                    len3 = str1.length();

                    try {
                        for (int k = 0; k < len3; k++) {
                            char chu = str1.charAt(k);
                            //System.out.println(chu);
                            if (chu == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '1' && str1.charAt(k + 5) == '>') {
                                for (int j = k + 6; j < len3; j++) {
                                    //System.out.println("Match Found");
                                    char ch2v = str1.charAt(j);

                                    if (ch2v != '<') {

                                        //System.out.println("Char Found");
                                        str5 = str5 + ch2v;

                                    } else {
                                        //System.out.println("Breaking");	
                                        j = len3;
                                    }
                                }
                                System.out.println("web1=" + str5);
                                if (str5.equals(site2)) {
                                    found = 1;
                                    System.out.println("WebSite Found in Blocked Website");
                                }

                            }
                        }

                    } catch (Exception gty) {
                        System.out.println(gty);
                    }

                    str1 = jt.getText();
                    len3 = str1.length();
                    str5 = "";

                    for (int k = 0; k < len3; k++) {
                        char chy = str1.charAt(k);
                        // System.out.println(ch);
                        if (chy == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '2' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                // System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    // System.out.println("Char Found");
                                    str5 = str5 + ch2;

                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web2=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len3 = str1.length();
                    for (int k = 0; k < len3; k++) {
                        char cht = str1.charAt(k);
                        // System.out.println(ch);
                        if (cht == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '3' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;

                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web3=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len3 = str1.length();

                    for (int k = 0; k < len3; k++) {
                        char chr = str1.charAt(k);
                        // System.out.println(ch);
                        if (chr == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '4' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;

                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web4=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len3 = str1.length();
                    for (int k = 0; k < len3; k++) {
                        char che = str1.charAt(k);
                        // System.out.println(ch);
                        if (che == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '5' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;
                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web5=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                } catch (Exception frt) {
                    System.out.println(frt);
                }

				  //after getting site put the site in editorPane
                if (found == 1) {
                    try {

                        System.out.println("Error");
                        System.out.println("count:" + store);
                        stat[store] = site2 + "";
                        store++;
                        System.out.println("Store:" + stat[store]);

                        System.out.println("Page" + (site2));
                        count1++;

                        File rt = new File("C:/error.html");
                        if (rt.isFile() && rt.exists()) {
                            FileWriter w = new FileWriter("C:/error2.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The WebSite is Blocked By Administrator  </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            rt.delete();
                            textField.setText("WebSite is Blocked !!!");
                            editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                        } else {
                            FileWriter w = new FileWriter("C:/error.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The WebSite is Blocked By Administrator</h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            textField.setText("WebSite is Blocked !!!");
                            editorPane.setPage(new URL("file://localhost/C:/error.html"));

                        }
                    } catch (Exception hy) {
                        System.out.println(hy);
                    }

                } else {

                    System.out.println("count:" + store);
                    stat[store] = site2 + "";
                    System.out.println("Store:" + stat[store]);

                    System.out.println("Page" + (site2));
                    count1++;

                    System.out.println("Text=" + textField.getText());

                    editorPane.setPage(new URL(site2));

                    try {
                        pq.setValue(pq.getMaximum());

                    } catch (Exception fr) {
                        System.out.println(fr);
                    }
                }

            } catch (Exception f) {
                System.out.println("Error in ServerHost");
                try {

                    File rt = new File("C:/error.html");
                    if (rt.isFile() && rt.exists()) {
                        FileWriter w = new FileWriter("C:/error2.html");
                        w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                + " <Title> Error</Title></head>"
                                + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                        w.close();
                        rt.delete();
                        editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                    } else {
                        FileWriter w = new FileWriter("C:/error.html");
                        w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                + " <Title> Error</Title></head>"
                                + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                        w.close();
                        editorPane.setPage(new URL("file://localhost/C:/error.html"));

                    }
                } catch (Exception hy) {
                    System.out.println("Error :" + "\n" + hy);
                }
            }
        }//end if
        //if the button pressed
        else {

            int found = 0;

            try {
                b1.setEnabled(true);
                String site2 = textField.getText();

                int len = site2.length();

                if (len < 9 && (site2.charAt(1) != ':')) {
                    site2 = site2 + ".com";
                    textField.setText(site2);
                }

                char ch = site2.charAt(0);

                try {
                    pq.setValue(30);

                    Thread.sleep(500);
                } catch (Exception fr) {
                    System.out.println(fr);
                }

                if (site2.charAt(7) == 'w' && site2.charAt(8) == 'w' && site2.charAt(9) == 'w' || site2.charAt(1) == ':' || site2.charAt(4) == ':') {
                    site2 = site2;

                } else {
                    site2 = "www." + site2;

                }

                try {
                    pq.setValue(60);

                    Thread.sleep(1000);
                } catch (Exception fr) {
                    System.out.println(fr);
                }

                if (ch == 'h' && site2.charAt(1) == 't' && site2.charAt(2) == 't' && site2.charAt(3) == 'p' || site2.charAt(1) == ':' || site2.charAt(4) == ':') {
                    System.out.println("Http protocol fond");
                    site2 = site2;
                    textField.setText(site2);
                } else {
                    System.out.println("Http protocol Not fond");
                    site2 = "http://" + site2;
                    textField.setText(site2);
                }

                // Checking for Block Website
                try {

                    FileReader rd = new FileReader("C:/Block.xml");
                    JTextField jt = new JTextField();
                    jt.read(rd, null);
                    rd.close();

                    String str1 = jt.getText();
                    int len3 = str1.length();

                    String str5 = "";

                    str1 = jt.getText();
                    len3 = str1.length();

                    try {
                        for (int k = 0; k < len3; k++) {
                            char chu = str1.charAt(k);
                            //System.out.println(chu);
                            if (chu == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '1' && str1.charAt(k + 5) == '>') {
                                for (int j = k + 6; j < len3; j++) {
                                    //System.out.println("Match Found");
                                    char ch2v = str1.charAt(j);

                                    if (ch2v != '<') {

                                        //System.out.println("Char Found");
                                        str5 = str5 + ch2v;

                                    } else {
                                        //System.out.println("Breaking");	
                                        j = len3;
                                    }
                                }
                                System.out.println("web1=" + str5);
                                if (str5.equals(site2)) {
                                    found = 1;
                                    System.out.println("WebSite Found in Blocked Website");
                                }

                            }
                        }

                    } catch (Exception gty) {
                        System.out.println(gty);
                    }

                    str1 = jt.getText();
                    len3 = str1.length();
                    str5 = "";

                    for (int k = 0; k < len3; k++) {
                        char chy = str1.charAt(k);
                        // System.out.println(ch);
                        if (chy == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '2' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                // System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    // System.out.println("Char Found");
                                    str5 = str5 + ch2;

                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web2=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len3 = str1.length();
                    for (int k = 0; k < len3; k++) {
                        char cht = str1.charAt(k);
                        // System.out.println(ch);
                        if (cht == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '3' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;

                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web3=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len3 = str1.length();

                    for (int k = 0; k < len3; k++) {
                        char chr = str1.charAt(k);
                        // System.out.println(ch);
                        if (chr == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '4' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;

                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web4=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                    str5 = "";
                    str1 = jt.getText();
                    len3 = str1.length();
                    for (int k = 0; k < len3; k++) {
                        char che = str1.charAt(k);
                        // System.out.println(ch);
                        if (che == '<' && str1.charAt(k + 1) == 'w' && str1.charAt(k + 2) == 'e' && str1.charAt(k + 3) == 'b' && str1.charAt(k + 4) == '5' && str1.charAt(k + 5) == '>') {
                            for (int j = k + 6; j < len3; j++) {
                                //System.out.println("Match Found");
                                char ch2 = str1.charAt(j);
                                if (ch2 != '<') {
                                    //System.out.println("Char Found");
                                    str5 = str5 + ch2;
                                } else {
                                    j = len3;
                                }
                            }
                            System.out.println("web5=" + str5);
                            if (str5.equals(site2)) {
                                found = 1;
                                System.out.println("WebSite Found in Blocked Website");
                            }

                        }
                    }

                } catch (Exception frt) {
                    System.out.println(frt);
                }

				  //after getting site put the site in editorPane
                if (found == 1) {
                    try {

                        File rt = new File("C:/error.html");
                        stat[store] = site2 + "";
                        store++;

                        if (rt.isFile() && rt.exists()) {
                            FileWriter w = new FileWriter("C:/error2.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The WebSite is Blocked By Administrator  </h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            rt.delete();
                            editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                        } else {
                            FileWriter w = new FileWriter("C:/error.html");
                            w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                    + " <Title> Error</Title></head>"
                                    + "<Body> <Body> <h1> The WebSite is Blocked By Administrator</h1>"
                                    + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                    + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                            w.close();
                            textField.setText("WebSite is Blocked !!!");
                            editorPane.setPage(new URL("file://localhost/C:/error.html"));

                        }
                    } catch (Exception hy) {
                        System.out.println(hy);
                    }

                } else {

                    System.out.println("count1" + store);
                    stat[count1] = site2;
                    System.out.println("Store " + stat[store]);
                    store++;
                    editorPane.setPage(new URL(site2));

                    System.out.println("Text=" + textField.getText());

                    try {

                        FileWriter v2 = new FileWriter("C:/Temp/" + y + ".txt");
                        v2.write(textField.getText());
                        v2.close();

                    } catch (Exception hu) {
                        System.out.println(hu);
                    }

                    try {
                        pq.setValue(pq.getMaximum());

                    } catch (Exception fr) {
                        System.out.println(fr);
                    }
                }

            } catch (IOException f) {
                System.out.println(f);
                try {

                    File rt = new File("C:/error.html");
                    if (rt.isFile() && rt.exists()) {
                        FileWriter w = new FileWriter("C:/error2.html");
                        w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                + " <Title> Error</Title></head>"
                                + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                + "	<h3> <u><b>Url =" + textField.getText() + "</b> </u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                        w.close();
                        rt.delete();
                        editorPane.setPage(new URL("file://localhost/C:/error2.html"));
                    } else {
                        FileWriter w = new FileWriter("C:/error.html");
                        w.write("<Html> <head> Server not Found (Mirror Error Details) <head>" + "\n\r"
                                + " <Title> Error</Title></head>"
                                + "<Body> <Body> <h1> The page cannot be displayed </h1>"
                                + "The page you are looking for is currently unavailable.</br> \n\r  The Web site might be experiencing technical difficulties,</br> \n\r or you may need to adjust your browser settings.</br>"
                                + "	<h3> <u><b>Url =" + textField.getText() + "</b></u></h3><br> Please Search On <a href a=http://www.google.co.in> Google </a></body><html>");
                        w.close();
                        editorPane.setPage(new URL("file://localhost/C:/error.html"));

                    }
                } catch (Exception hy) {
                    System.out.println(hy);
                }
            }

        }//end else

        pq.setVisible(false);
    }//end method actionPerformed

    public static void main(String[] args) {
        new Browser().setVisible(true);
    }//end method main

}//end class WebBrowser

// It is Used To Download Any File From Internet Explorer
// Autor Pravin H. Rane
class download extends JFrame {

    JLabel l1, l2, l3, l4, l5, l6, l7;
    JButton b1, b2, b3, b4, b5, b6, b7;
    JTextField t1, t2, t3, t4, t5, t6;
    ImageIcon i1, i2, i3, i4, i5, i6;
    Container cp;
    JPanel j1, j2, j3, j4;
    String extension = "";
    String name = "";
    JProgressBar pg1;
    int u = 0;
    String stre = "";

    download(String fname) {

        super("Download File");
        name = fname;

        System.out.println("Container starts...");

        setVisible(true);
        setSize(360, 230);
        setLocation(100, 100);

        cp = getContentPane();
        cp.setLayout(null);

        Color c = new Color(120, 211, 150);
        Font f = new Font("Arial Balck", Font.BOLD, 14);
        j1 = new JPanel(null, true);
        l1 = new JLabel("Download File");
        l1.setBounds(30, 02, 150, 30);
        l1.setFont(f);
        l1.setForeground(c);
        j1.add(l1);

        l2 = new JLabel("(Mirror Browser)");
        l2.setBounds(150, 10, 150, 20);
        l2.setForeground(c);
        j1.add(l2);

        j1.setSize(350, 30);
        j1.setLocation(0, 0);
        j1.setBackground(Color.black);
        cp.add(j1);
        System.out.println("Panel1 starts...");

        File f2 = new File(fname);
        double len = f2.length();
        u = (int) len;
        stre = f2.getName();
        int i = stre.lastIndexOf('.');
        if (stre.substring(i + 1).toLowerCase().equals("exe") || stre.substring(i + 1).toLowerCase().equals("msi") || stre.substring(i + 1).toLowerCase().equals("cmd")) {
            extension = "exe";
        } else if (stre.substring(i + 1).toLowerCase().equals("mp2") || stre.substring(i + 1).toLowerCase().equals("mp3") || stre.substring(i + 1).toLowerCase().equals("wav") || stre.substring(i + 1).toLowerCase().equals("wmv") || stre.substring(i + 1).toLowerCase().equals("mp4")) {
            extension = "mp3";
        } else if (stre.substring(i + 1).toLowerCase().equals("3gp") || stre.substring(i + 1).toLowerCase().equals("flv") || stre.substring(i + 1).toLowerCase().equals("avi") || stre.substring(i + 1).toLowerCase().equals("mpeg") || stre.substring(i + 1).toLowerCase().equals("Dat")) {
            extension = "video";
        } else if (stre.substring(i + 1).toLowerCase().equals("rar") || stre.substring(i + 1).toLowerCase().equals("zip") || stre.substring(i + 1).toLowerCase().equals("7z") || stre.substring(i + 1).toLowerCase().equals("exe") || stre.substring(i + 1).toLowerCase().equals("mp4")) {
            extension = "rar";
        } else {
            extension = "other";
        }

        j2 = new JPanel(null, true);

        i1 = new ImageIcon("Icon/" + extension + ".png");
        l3 = new JLabel(i1);
        l3.setBounds(10, 15, 50, 50);
        j2.add(l3);

        l4 = new JLabel("Name= " + stre);
        l4.setBounds(65, 25, 180, 20);
        j2.add(l4);

        double len2 = (len / 1024) / 1024;
        l5 = new JLabel("Size= " + len2 + "Mb");
        l5.setBounds(65, 50, 180, 20);
        j2.add(l5);

        l6 = new JLabel("Save In");
        l6.setBounds(10, 75, 100, 30);
        j2.add(l6);

        t1 = new JTextField("C:/");
        t1.setBounds(80, 75, 200, 30);
        j2.add(t1);

        b1 = new JButton("....");
        b1.setBounds(290, 75, 50, 30);
        j2.add(b1);

        j2.setSize(350, 120);
        j2.setLocation(0, 30);
        j2.setBackground(Color.white);
        cp.add(j2);
        System.out.println("Panel2 starts...");

        j3 = new JPanel(null, true);

        b2 = new JButton("Ok");
        b2.setBounds(10, 05, 100, 30);
        j3.add(b2);

        b3 = new JButton("Cancel");
        b3.setBounds(130, 05, 100, 30);
        j3.add(b3);

        pg1 = new JProgressBar();
        pg1.setBounds(240, 10, 100, 20);
        j3.add(pg1);

        j3.setLocation(0, 150);
        j3.setSize(350, 45);
        j3.setBackground(Color.black);
        cp.add(j3);
        System.out.println("Panel3 starts...");

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {

                try {
                    System.out.println("Download  starts...");
                    pg1.setMaximum(u);
                    int i = 0;

                    File f1 = new File(name);
                    File f2 = new File(t1.getText() + stre);
                    InputStream in = new FileInputStream(f1);

                    OutputStream out1 = new FileOutputStream(f2, true);

                    OutputStream out = new FileOutputStream(f2);

                    byte[] buf = new byte[600000];
                    int len;

                    while ((len = in.read(buf)) > 0) {

                        out.write(buf, 0, len);
                        pg1.setValue(u - len);

                    }
                    pg1.setValue(100);
                    in.close();
                    out.close();
                    out1.close();
                    System.out.println("File copied.");
                    pg1.setValue(10000000);
                    double u1 = (u / 1024) / 1024;
                    JOptionPane.showMessageDialog((Component) null, "Copy Complete Sucessfully! \n" + "File Name=" + t1.getText() + stre + "\n Download From=" + name + "\n File Size=" + u1 + " Megabytes", "Copy Complete Sucessfully", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception fr) {
                    System.out.println(fr);
                }

                try {
                    pg1.setValue(1000);
                    Thread.sleep(1000);
                    setVisible(false);
                } catch (Exception fr) {
                    System.out.println(fr);
                }

            }
        });

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                JFrame jh = new JFrame();

                FileDialog fd = new FileDialog(jh, "Save Downloaded File");
                fd.setMode(FileDialog.SAVE);
                fd.setFile("Save Here");
                fd.setVisible(true);

                t1.setText(fd.getDirectory());
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                setVisible(false);
            }
        });

    }
}

class info extends JFrame {

    JLabel l1, l2, l3, l4, l5, l6, l7;
    JButton b1, b2, b3, b4, b5, b6, b7;
    JTextField t1, t2, t3, t4, t5, t6;
    ImageIcon i1, i2, i3, i4, i5, i6;
    JPanel j1, j2, j3, j4;
    Container cp;

    String extension = "";
    String name = "";
    JProgressBar pg1;
    int u = 0;
    String stre = "";

    info(String fname) {
        super("Web Page Information");

        setVisible(true);
        setSize(350, 400);
        setLocation(150, 100);

        cp = getContentPane();
        cp.setLayout(null);

        j1 = new JPanel(null, true);
        i1 = new ImageIcon("Icon/ico_alpha_Information_32x32.png");
        l1 = new JLabel(i1);
        l1.setBounds(10, 20, 40, 40);
        j1.add(l1);

        File f = new File(fname);
        String path = f.getPath();
        String name = f.getName();
        double len = f.length();

        l2 = new JLabel("Path " + path);
        l2.setBounds(60, 30, 220, 25);
        j1.add(l2);

        l3 = new JLabel("File Name " + name);
        l3.setBounds(60, 60, 220, 25);
        j1.add(l3);

        l4 = new JLabel("Size " + f.length() + " Bytes");
        l4.setBounds(60, 90, 220, 25);
        j1.add(l4);

        j1.setBounds(10, 10, 350, 150);
        cp.add(j1);
        j1.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("information"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        j2 = new JPanel(null, true);

        JCheckBox c1 = new JCheckBox("Read Only", true);
        c1.setBounds(30, 20, 120, 25);
        j2.add(c1);

        JCheckBox c3 = new JCheckBox("Archive  ", false);
        c3.setBounds(30, 50, 120, 25);
        j2.add(c3);

        JCheckBox c2 = new JCheckBox("Hidden   ", false);
        c2.setBounds(30, 80, 120, 25);
        j2.add(c2);

        j2.setBounds(10, 160, 350, 150);
        cp.add(j2);

        j2.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Attributes"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        b1 = new JButton("Ok");
        b1.setBounds(50, 320, 100, 30);
        cp.add(b1);

        b2 = new JButton("cancel");
        b2.setBounds(180, 320, 100, 30);
        cp.add(b2);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                setVisible(false);
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                setVisible(false);
            }
        });
    }
}

class about extends JFrame {

    ImageIcon i1, i2, i3;
    JButton b1, b2, b3;
    JLabel l1, l2, l3, l4, l5, l6;
    JPanel p1, p2, p3, p4, p5;
    Container cp;

    about() {
        cp = getContentPane();
        cp.setLayout(null);

        setVisible(true);
        setSize(250, 170);
        setTitle("About Software");
        setLocation(150, 100);

        i1 = new ImageIcon("icon/software.png");
        l1 = new JLabel(i1);
        l1.setBounds(10, 10, 40, 40);
        cp.add(l1);

        Font f = new Font("Arial", Font.BOLD, 12);
        setFont(f);

        l2 = new JLabel("Software Title : Mirror Web Browser");
        l2.setBounds(50, 10, 200, 20);
        cp.add(l2);

        l2 = new JLabel("Software Author : Pravin H. Rane");
        l2.setBounds(50, 30, 200, 20);
        cp.add(l2);

        l2 = new JLabel("Software Requirment : JDK 1.6 ");
        l2.setBounds(50, 50, 200, 20);
        cp.add(l2);

        l2.setForeground(Color.red);

        b1 = new JButton("OK");
        b1.setBounds(70, 90, 100, 20);
        cp.add(b1);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {
                setVisible(false);
            }
        });

    }
}
