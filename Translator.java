
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.swingx.prompt.PromptSupport;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Toxic
 */
public class Translator extends JPanel {
    
    public Font font = new Font("Arial Unicode MS", Font.BOLD, 12);
    
    public static JLabel eng_label = new JLabel("\u0987\u09B0\u09C7\u099C\u09BF \u09B2\u09BF\u0996\u09C1\u09A8\u0983");
    public static JLabel bng_label = new JLabel("\u09AC\u09BE\u0982\u09B2\u09BE \u0985\u09A8\u09C1\u09AC\u09BE\u09A6\u0983");
    public static JLabel lblm = new JLabel("Per Month");
    
    public static JTextField txt_eng = new JTextField();
    public static JTextField txt_bng = new JTextField();
    public static JTextArea txt_area = new JTextArea();
    
    public static JButton btn_append = new JButton("\u09AF\u09CB\u0997");
    public static JButton btn_open = new JButton("\u0996\u09C1\u09B2\u09C1\u09A8");
    public static JButton btn_save = new JButton("\u099C\u09AE\u09BE\u09A8");
    public static JButton btn_clear = new JButton("\u09AE\u09C1\u099B\u09C1\u09A8");
    
    public Translator() {
        this.setLayout(null);
        this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        this.setBackground(Color.LIGHT_GRAY);
        
        eng_label.setBounds(30, 10, 100, 20);
        eng_label.setFont(font);
        eng_label.setForeground(Color.BLACK);
        this.add(eng_label);
        txt_eng.setBounds(30, 35, 95, 25);
        txt_eng.setFont(font);
        txt_eng.setForeground(Color.red);
        txt_eng.setBackground(Color.getHSBColor(400, 300, 100));
        txt_eng.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(txt_eng);
        txt_eng.getDocument().addDocumentListener(new MyDocumentListener());
        PromptSupport.setPrompt("small letter", txt_eng);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txt_eng);
        PromptSupport.setFontStyle(Font.BOLD, txt_eng);
        
        bng_label.setBounds(140, 10, 100, 20);
        bng_label.setForeground(Color.BLACK);
        bng_label.setFont(font);
        this.add(bng_label);
        txt_bng.setBounds(140, 35, 95, 25);
        txt_bng.setForeground(Color.red);
        txt_bng.setEditable(false);
        txt_bng.setFont(font);
        txt_bng.setBackground(Color.getHSBColor(400, 300, 100));
        txt_bng.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(txt_bng);
        
        btn_append.setBounds(30, 73, 46, 20);
        btn_append.setMnemonic('a');
        btn_append.setFont(font);
        btn_append.setFocusPainted(false);
       // btn_append.setContentAreaFilled(false);
        btn_append.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_append.setToolTipText("Press \"Alt+a\" for append text");
        this.add(btn_append);
        btn_append.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt_area.append(txt_bng.getText() + "\n");
                btn_append.setFocusable(false);
                btn_open.setFocusable(false);
                btn_save.setFocusable(false);
                btn_clear.setFocusable(false);
                txt_bng.setText("");
                txt_eng.setText("");
                
            }
        });
        
        btn_open.setBounds(80, 73, 46, 20);
        btn_open.setFocusPainted(false);
        btn_open.setMnemonic('O');
        btn_open.setFont(font);
      //  btn_open.setContentAreaFilled(false);
        btn_open.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_open.setToolTipText("Press \"Alt+o\" to open");
        this.add(btn_open);
        btn_open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int openFile = fileChooser.showOpenDialog(null);
                if (openFile == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        
                        FileInputStream fileInputStream = new FileInputStream(file.getPath());
                        Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
                        char[] x = new char[1024];
                        StringBuilder data = new StringBuilder();
                        while (true) {
                            int size = reader.read(x);
                            if (size < 0) {
                                break;
                            }
                            data.append(x, 0, size);
                        }
                        txt_area.setText("");
                        txt_area.append("" + data);
                        
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                
            }
        });
        
        btn_save.setBounds(140, 73, 51, 20);
        btn_save.setFocusPainted(false);
        btn_save.setMnemonic('s');
        btn_save.setFont(font);
       // btn_save.setContentAreaFilled(false);
        btn_save.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_save.setToolTipText("Press \"Alt+s\" to save");
        this.add(btn_save);
        btn_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser fileChooser = new JFileChooser();
                int chooser = fileChooser.showSaveDialog(null);
                if (chooser == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        String x = "";
                        x = txt_area.getText();
                        Writer writer = new OutputStreamWriter(new FileOutputStream(file.getPath()), "UTF-8");
                        writer.append(x);
                        writer.close();
                        
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        
        btn_clear.setBounds(195, 73, 41, 20);
        btn_clear.setFocusPainted(false);
       // btn_clear.setContentAreaFilled(false);
        btn_clear.setMnemonic('c');
        btn_clear.setFont(font);
        btn_clear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn_clear.setToolTipText("Press \"Alt+a\" for clear");
        this.add(btn_clear);
        btn_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt_area.setText("");
                txt_bng.setText("");
                txt_eng.setText("");
            }
        });
        txt_area.setBounds(30, 110, 208, 120);
        txt_area.setFont(font);
        txt_area.setLineWrap(true);
        txt_area.setEditable(false);
        txt_area.setForeground(Color.BLUE);
        txt_area.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        txt_area.setBackground(Color.getHSBColor(800, 750, 300));
        this.add(txt_area);
        
    }
    
    private static class MyDocumentListener implements DocumentListener {
        
        public void insertUpdate(DocumentEvent e) {
            
            String txt = null;
            Hashtable etob = new Hashtable();
            etob.put("allah", "\u0986\u09B2\u09CD\u09B2\u09BE\u09B9");
            etob.put("prophet", "\u09A8\u09AC\u09C0 ");
            etob.put("father", "\u09AA\u09BF\u09A4\u09BE ");
            etob.put("mother", "\u09AE\u09BE");
            etob.put("brother", "\u09AD\u09BE\u0987");
            etob.put("sister", "\u09AC\u09CB\u09A8");
            etob.put("about", "\u09B8\u09AE\u09CD\u09AA\u09B0\u09CD\u0995\u09C7");
            etob.put("supplier", "\u09B8\u09B0\u09AC\u09B0\u09BE\u09B9\u0995\u09BE\u09B0\u09C0");
            etob.put("item", "\u09AA\u09A6");
            etob.put("purchase", "\u0995\u09CD\u09B0\u09AF\u09BC");
            etob.put("sell", "\u09AC\u09BF\u0995\u09CD\u09B0\u09BF \u0995\u09B0\u09BE");
            etob.put("delete", "\u09AE\u09C1\u099B\u09C7 \u09AB\u09C7\u09B2\u09BE");
            etob.put("help", "\u09B8\u09BE\u09B9\u09BE\u09AF\u09CD\u09AF");
            etob.put("search", "\u0985\u09A8\u09C1\u09B8\u09A8\u09CD\u09A7\u09BE\u09A8");
            etob.put("login", "\u09B2\u0997\u0987\u09A8");
            etob.put("insert", "\u09A2\u09CB\u0995\u09BE\u09A8");
            etob.put("update", "\u09B9\u09BE\u09B2\u09A8\u09BE\u0997\u09BE\u09A6");
            etob.put("signup", "\u09A8\u09BF\u09AC\u09A8\u09CD\u09A7\u09A8 \u0995\u09B0\u09C1\u09A8");
            etob.put("stock", "\u09B8\u09CD\u099F\u0995");
            etob.put("barcode", "\u09AC\u09BE\u09B0\u0995\u09CB\u09A1");
            etob.put("press", "\u09AA\u09CD\u09B0\u09C7\u09B8");
            etob.put("exit", "\u09AA\u09CD\u09B0\u09C7\u09B8");
            etob.put("enter", "\u09AA\u09CD\u09B0\u09AC\u09C7\u09B6 \u0995\u09B0\u09BE\u09A8");
            etob.put("withdraw", "\u09AA\u09CD\u09B0\u09A4\u09CD\u09AF\u09BE\u09B9\u09BE\u09B0");
            etob.put("transaction", "\u09B2\u09C7\u09A8\u09A6\u09C7\u09A8");
            etob.put("deposite", "\u0986\u09AE\u09BE\u09A8\u09A4");
            etob.put("amount", "\u09AA\u09B0\u09BF\u09AE\u09BE\u09A3");
            etob.put("open", "\u0996\u09CB\u09B2\u09BE");
            etob.put("converter", "\u09AA\u09B0\u09BF\u09AC\u09B0\u09CD\u09A4\u0995");
            etob.put("clear", "\u09AA\u09B0\u09BF\u09B7\u09CD\u0995\u09BE\u09B0");
            etob.put("file", "\u09AB\u09BE\u0987\u09B2");
            etob.put("billing", "\u09AC\u09BF\u09B2\u09BF\u0982");
            etob.put("add", "\u09AF\u09CB\u0997");
            etob.put("field", "\u0995\u09CD\u09B7\u09C7\u09A4\u09CD\u09B0");
            etob.put("upload", "\u0986\u09AA\u09B2\u09CB\u09A1");
            etob.put("event", "\u0998\u099F\u09A8\u09BE");
            etob.put("view", "\u09A6\u09C3\u09B6\u09CD\u09AF");
            etob.put("discount", "\u09A1\u09BF\u09B8\u0995\u09BE\u0989\u09A8\u09CD\u099F");
            etob.put("name", "\u09A8\u09BE\u09AE");
            etob.put("price", "\u09AE\u09C2\u09B2\u09CD\u09AF");
            etob.put("details", "\u09AC\u09BF\u09B8\u09CD\u09A4\u09BE\u09B0\u09BF\u09A4");
            etob.put("shop", "\u09A6\u09CB\u0995\u09BE\u09A8");
            etob.put("rental", "\u09AD\u09BE\u09A1\u09BC\u09BE");
            etob.put("note", "\u09AE\u09A8\u09CD\u09A4\u09AC\u09CD\u09AF");
            etob.put("watch", "\u0998\u09A1\u09BC\u09BF");
            etob.put("speaker", "\u09AC\u0995\u09CD\u09A4\u09BE");
            etob.put("sir", "\u099C\u09A8\u09BE\u09AC");
            etob.put("student", "\u099B\u09BE\u09A4\u09CD\u09B0");
            etob.put("pstu", "\u09AA\u099F\u09C1\u09AF\u09BC\u09BE\u0996\u09BE\u09B2\u09C0 \u09AC\u09BF\u099C\u09CD\u099E\u09BE\u09A8 \u0993 \u09AA\u09CD\u09B0\u09AF\u09C1\u0995\u09CD\u09A4\u09BF \u09AC\u09BF\u09B6\u09CD\u09AC\u09AC\u09BF\u09A6\u09CD\u09AF\u09BE\u09B2\u09AF\u09BC");
            etob.put("cse", "\u0995\u09AE\u09CD\u09AA\u09BF\u0989\u099F\u09BE\u09B0 \u09AC\u09BF\u099C\u09CD\u099E\u09BE\u09A8 \u0993 \u0995\u09CC\u09B6\u09B2");
            etob.put("engineering", "\u09AA\u09CD\u09B0\u0995\u09CC\u09B6\u09B2");
            etob.put("i", "\u0986\u09AE\u09BF");
            etob.put("you", "\u0986\u09AA\u09A8\u09BF");
            etob.put("me", "\u0986\u09AE\u09BE\u0995\u09C7");
            etob.put("his", "\u09A4\u09BE\u09B0");
            etob.put("her", "\u09A4\u09BE\u09B0");
            etob.put("here", "\u098F\u0996\u09BE\u09A8\u09C7");
            etob.put("there", "\u09B8\u09C7\u0996\u09BE\u09A8\u09C7");
            etob.put("both", "\u0989\u09AD\u09AF\u09BC");
            etob.put("us", "\u0986\u09AE\u09BE\u09A6\u09C7\u09B0");
            etob.put("ok", "\u09A0\u09BF\u0995 \u0986\u099B\u09C7");
            etob.put("start", "\u09B6\u09C1\u09B0\u09C1");
            etob.put("stop", "\u09AC\u09A8\u09CD\u09A7 \u0995\u09B0\u09BE");
            etob.put("yes", "\u09B9\u09BE\u0981");
            etob.put("no", "\u09A8\u09BE");
            etob.put("very", "\u0996\u09C1\u09AC");
            etob.put("good", "\u09AD\u09BE\u09B2");
            etob.put("best", "\u09B8\u09C7\u09B0\u09BE");
            etob.put("know", "\u099C\u09BE\u09A8\u09BE");
            etob.put("new", "\u09A8\u09A4\u09C1\u09A8");
            etob.put("hi", "\u09AE\u09A8\u09CB\u09AF\u09CB\u0997 \u0986\u0995\u09B0\u09CD\u09B7\u09A8 ");
            etob.put("today", "\u0986\u099C");
            etob.put("day", "\u09A6\u09BF\u09A8");
            etob.put("month", "\u09AE\u09BE\u09B8");
            etob.put("year", "\u09AC\u099B\u09B0");
            etob.put("week", "\u09B8\u09AA\u09CD\u09A4\u09BE\u09B9");
            etob.put("nice", "\u09AE\u09A8\u09C7\u09BE\u09B0\u09AE");
            etob.put("dear", "\u09AA\u09CD\u09B0\u09BF\u09AF\u09BC");
            
            txt = txt_eng.getText();
            txt_bng.setText("" + etob.get(txt));
            
        }
        
        public void removeUpdate(DocumentEvent e) {
            String txt;
            Hashtable etob = new Hashtable();
            
            etob.put("allah", "\u0986\u09B2\u09CD\u09B2\u09BE\u09B9");
            etob.put("prophet", "\u09A8\u09AC\u09C0 ");
            etob.put("father", "\u09AA\u09BF\u09A4\u09BE ");
            etob.put("mother", "\u09AE\u09BE");
            etob.put("brother", "\u09AD\u09BE\u0987");
            etob.put("sister", "\u09AC\u09CB\u09A8");
            etob.put("about", "\u09B8\u09AE\u09CD\u09AA\u09B0\u09CD\u0995\u09C7");
            etob.put("supplier", "\u09B8\u09B0\u09AC\u09B0\u09BE\u09B9\u0995\u09BE\u09B0\u09C0");
            etob.put("item", "\u09AA\u09A6");
            etob.put("purchase", "\u0995\u09CD\u09B0\u09AF\u09BC");
            etob.put("sell", "\u09AC\u09BF\u0995\u09CD\u09B0\u09BF \u0995\u09B0\u09BE");
            etob.put("delete", "\u09AE\u09C1\u099B\u09C7 \u09AB\u09C7\u09B2\u09BE");
            etob.put("help", "\u09B8\u09BE\u09B9\u09BE\u09AF\u09CD\u09AF");
            etob.put("search", "\u0985\u09A8\u09C1\u09B8\u09A8\u09CD\u09A7\u09BE\u09A8");
            etob.put("login", "\u09B2\u0997\u0987\u09A8");
            etob.put("insert", "\u09A2\u09CB\u0995\u09BE\u09A8");
            etob.put("update", "\u09B9\u09BE\u09B2\u09A8\u09BE\u0997\u09BE\u09A6");
            etob.put("signup", "\u09A8\u09BF\u09AC\u09A8\u09CD\u09A7\u09A8 \u0995\u09B0\u09C1\u09A8");
            etob.put("stock", "\u09B8\u09CD\u099F\u0995");
            etob.put("barcode", "\u09AC\u09BE\u09B0\u0995\u09CB\u09A1");
            etob.put("press", "\u09AA\u09CD\u09B0\u09C7\u09B8");
            etob.put("exit", "\u09AA\u09CD\u09B0\u09C7\u09B8");
            etob.put("enter", "\u09AA\u09CD\u09B0\u09AC\u09C7\u09B6 \u0995\u09B0\u09BE\u09A8");
            etob.put("withdraw", "\u09AA\u09CD\u09B0\u09A4\u09CD\u09AF\u09BE\u09B9\u09BE\u09B0");
            etob.put("transaction", "\u09B2\u09C7\u09A8\u09A6\u09C7\u09A8");
            etob.put("deposite", "\u0986\u09AE\u09BE\u09A8\u09A4");
            etob.put("amount", "\u09AA\u09B0\u09BF\u09AE\u09BE\u09A3");
            etob.put("open", "\u0996\u09CB\u09B2\u09BE");
            etob.put("converter", "\u09AA\u09B0\u09BF\u09AC\u09B0\u09CD\u09A4\u0995");
            etob.put("clear", "\u09AA\u09B0\u09BF\u09B7\u09CD\u0995\u09BE\u09B0");
            etob.put("file", "\u09AB\u09BE\u0987\u09B2");
            etob.put("billing", "\u09AC\u09BF\u09B2\u09BF\u0982");
            etob.put("add", "\u09AF\u09CB\u0997");
            etob.put("field", "\u0995\u09CD\u09B7\u09C7\u09A4\u09CD\u09B0");
            etob.put("upload", "\u0986\u09AA\u09B2\u09CB\u09A1");
            etob.put("event", "\u0998\u099F\u09A8\u09BE");
            etob.put("view", "\u09A6\u09C3\u09B6\u09CD\u09AF");
            etob.put("discount", "\u09A1\u09BF\u09B8\u0995\u09BE\u0989\u09A8\u09CD\u099F");
            etob.put("name", "\u09A8\u09BE\u09AE");
            etob.put("price", "\u09AE\u09C2\u09B2\u09CD\u09AF");
            etob.put("details", "\u09AC\u09BF\u09B8\u09CD\u09A4\u09BE\u09B0\u09BF\u09A4");
            etob.put("shop", "\u09A6\u09CB\u0995\u09BE\u09A8");
            etob.put("rental", "\u09AD\u09BE\u09A1\u09BC\u09BE");
            etob.put("note", "\u09AE\u09A8\u09CD\u09A4\u09AC\u09CD\u09AF");
            etob.put("watch", "\u0998\u09A1\u09BC\u09BF");
            etob.put("speaker", "\u09AC\u0995\u09CD\u09A4\u09BE");
            etob.put("sir", "\u099C\u09A8\u09BE\u09AC");
            etob.put("student", "\u099B\u09BE\u09A4\u09CD\u09B0");
            etob.put("pstu", "\u09AA\u099F\u09C1\u09AF\u09BC\u09BE\u0996\u09BE\u09B2\u09C0 \u09AC\u09BF\u099C\u09CD\u099E\u09BE\u09A8 \u0993 \u09AA\u09CD\u09B0\u09AF\u09C1\u0995\u09CD\u09A4\u09BF \u09AC\u09BF\u09B6\u09CD\u09AC\u09AC\u09BF\u09A6\u09CD\u09AF\u09BE\u09B2\u09AF\u09BC");
            etob.put("cse", "\u0995\u09AE\u09CD\u09AA\u09BF\u0989\u099F\u09BE\u09B0 \u09AC\u09BF\u099C\u09CD\u099E\u09BE\u09A8 \u0993 \u0995\u09CC\u09B6\u09B2");
            etob.put("engineering", "\u09AA\u09CD\u09B0\u0995\u09CC\u09B6\u09B2");
            etob.put("i", "\u0986\u09AE\u09BF");
            etob.put("you", "\u0986\u09AA\u09A8\u09BF");
            etob.put("me", "\u0986\u09AE\u09BE\u0995\u09C7");
            etob.put("his", "\u09A4\u09BE\u09B0");
            etob.put("her", "\u09A4\u09BE\u09B0");
            etob.put("here", "\u098F\u0996\u09BE\u09A8\u09C7");
            etob.put("there", "\u09B8\u09C7\u0996\u09BE\u09A8\u09C7");
            etob.put("both", "\u0989\u09AD\u09AF\u09BC");
            etob.put("us", "\u0986\u09AE\u09BE\u09A6\u09C7\u09B0");
            etob.put("ok", "\u09A0\u09BF\u0995 \u0986\u099B\u09C7");
            etob.put("start", "\u09B6\u09C1\u09B0\u09C1");
            etob.put("stop", "\u09AC\u09A8\u09CD\u09A7 \u0995\u09B0\u09BE");
            etob.put("yes", "\u09B9\u09BE\u0981");
            etob.put("no", "\u09A8\u09BE");
            etob.put("very", "\u0996\u09C1\u09AC");
            etob.put("good", "\u09AD\u09BE\u09B2");
            etob.put("best", "\u09B8\u09C7\u09B0\u09BE");
            etob.put("know", "\u099C\u09BE\u09A8\u09BE");
            etob.put("new", "\u09A8\u09A4\u09C1\u09A8");
            etob.put("hi", "\u09AE\u09A8\u09CB\u09AF\u09CB\u0997 \u0986\u0995\u09B0\u09CD\u09B7\u09A8 ");
            etob.put("today", "\u0986\u099C");
            etob.put("day", "\u09A6\u09BF\u09A8");
            etob.put("month", "\u09AE\u09BE\u09B8");
            etob.put("year", "\u09AC\u099B\u09B0");
            etob.put("week", "\u09B8\u09AA\u09CD\u09A4\u09BE\u09B9");
            etob.put("nice", "\u09AE\u09A8\u09C7\u09BE\u09B0\u09AE");
            etob.put("dear", "\u09AA\u09CD\u09B0\u09BF\u09AF\u09BC");
            
            txt = txt_eng.getText();
            txt_bng.setText("" + etob.get(txt));
            
        }
        
        public void changedUpdate(DocumentEvent e) {
            String txt;
            Hashtable etob = new Hashtable();
            
            etob.put("allah", "\u0986\u09B2\u09CD\u09B2\u09BE\u09B9");
            etob.put("prophet", "\u09A8\u09AC\u09C0 ");
            etob.put("father", "\u09AA\u09BF\u09A4\u09BE ");
            etob.put("mother", "\u09AE\u09BE");
            etob.put("brother", "\u09AD\u09BE\u0987");
            etob.put("sister", "\u09AC\u09CB\u09A8");
            etob.put("about", "\u09B8\u09AE\u09CD\u09AA\u09B0\u09CD\u0995\u09C7");
            etob.put("supplier", "\u09B8\u09B0\u09AC\u09B0\u09BE\u09B9\u0995\u09BE\u09B0\u09C0");
            etob.put("item", "\u09AA\u09A6");
            etob.put("purchase", "\u0995\u09CD\u09B0\u09AF\u09BC");
            etob.put("sell", "\u09AC\u09BF\u0995\u09CD\u09B0\u09BF \u0995\u09B0\u09BE");
            etob.put("delete", "\u09AE\u09C1\u099B\u09C7 \u09AB\u09C7\u09B2\u09BE");
            etob.put("help", "\u09B8\u09BE\u09B9\u09BE\u09AF\u09CD\u09AF");
            etob.put("search", "\u0985\u09A8\u09C1\u09B8\u09A8\u09CD\u09A7\u09BE\u09A8");
            etob.put("login", "\u09B2\u0997\u0987\u09A8");
            etob.put("insert", "\u09A2\u09CB\u0995\u09BE\u09A8");
            etob.put("update", "\u09B9\u09BE\u09B2\u09A8\u09BE\u0997\u09BE\u09A6");
            etob.put("signup", "\u09A8\u09BF\u09AC\u09A8\u09CD\u09A7\u09A8 \u0995\u09B0\u09C1\u09A8");
            etob.put("stock", "\u09B8\u09CD\u099F\u0995");
            etob.put("barcode", "\u09AC\u09BE\u09B0\u0995\u09CB\u09A1");
            etob.put("press", "\u09AA\u09CD\u09B0\u09C7\u09B8");
            etob.put("exit", "\u09AA\u09CD\u09B0\u09C7\u09B8");
            etob.put("enter", "\u09AA\u09CD\u09B0\u09AC\u09C7\u09B6 \u0995\u09B0\u09BE\u09A8");
            etob.put("withdraw", "\u09AA\u09CD\u09B0\u09A4\u09CD\u09AF\u09BE\u09B9\u09BE\u09B0");
            etob.put("transaction", "\u09B2\u09C7\u09A8\u09A6\u09C7\u09A8");
            etob.put("deposite", "\u0986\u09AE\u09BE\u09A8\u09A4");
            etob.put("amount", "\u09AA\u09B0\u09BF\u09AE\u09BE\u09A3");
            etob.put("open", "\u0996\u09CB\u09B2\u09BE");
            etob.put("converter", "\u09AA\u09B0\u09BF\u09AC\u09B0\u09CD\u09A4\u0995");
            etob.put("clear", "\u09AA\u09B0\u09BF\u09B7\u09CD\u0995\u09BE\u09B0");
            etob.put("file", "\u09AB\u09BE\u0987\u09B2");
            etob.put("billing", "\u09AC\u09BF\u09B2\u09BF\u0982");
            etob.put("add", "\u09AF\u09CB\u0997");
            etob.put("field", "\u0995\u09CD\u09B7\u09C7\u09A4\u09CD\u09B0");
            etob.put("upload", "\u0986\u09AA\u09B2\u09CB\u09A1");
            etob.put("event", "\u0998\u099F\u09A8\u09BE");
            etob.put("view", "\u09A6\u09C3\u09B6\u09CD\u09AF");
            etob.put("discount", "\u09A1\u09BF\u09B8\u0995\u09BE\u0989\u09A8\u09CD\u099F");
            etob.put("name", "\u09A8\u09BE\u09AE");
            etob.put("price", "\u09AE\u09C2\u09B2\u09CD\u09AF");
            etob.put("details", "\u09AC\u09BF\u09B8\u09CD\u09A4\u09BE\u09B0\u09BF\u09A4");
            etob.put("shop", "\u09A6\u09CB\u0995\u09BE\u09A8");
            etob.put("rental", "\u09AD\u09BE\u09A1\u09BC\u09BE");
            etob.put("note", "\u09AE\u09A8\u09CD\u09A4\u09AC\u09CD\u09AF");
            etob.put("watch", "\u0998\u09A1\u09BC\u09BF");
            etob.put("speaker", "\u09AC\u0995\u09CD\u09A4\u09BE");
            etob.put("sir", "\u099C\u09A8\u09BE\u09AC");
            etob.put("student", "\u099B\u09BE\u09A4\u09CD\u09B0");
            etob.put("pstu", "\u09AA\u099F\u09C1\u09AF\u09BC\u09BE\u0996\u09BE\u09B2\u09C0 \u09AC\u09BF\u099C\u09CD\u099E\u09BE\u09A8 \u0993 \u09AA\u09CD\u09B0\u09AF\u09C1\u0995\u09CD\u09A4\u09BF \u09AC\u09BF\u09B6\u09CD\u09AC\u09AC\u09BF\u09A6\u09CD\u09AF\u09BE\u09B2\u09AF\u09BC");
            etob.put("cse", "\u0995\u09AE\u09CD\u09AA\u09BF\u0989\u099F\u09BE\u09B0 \u09AC\u09BF\u099C\u09CD\u099E\u09BE\u09A8 \u0993 \u0995\u09CC\u09B6\u09B2");
            etob.put("engineering", "\u09AA\u09CD\u09B0\u0995\u09CC\u09B6\u09B2");
            etob.put("i", "\u0986\u09AE\u09BF");
            etob.put("you", "\u0986\u09AA\u09A8\u09BF");
            etob.put("me", "\u0986\u09AE\u09BE\u0995\u09C7");
            etob.put("his", "\u09A4\u09BE\u09B0");
            etob.put("her", "\u09A4\u09BE\u09B0");
            etob.put("here", "\u098F\u0996\u09BE\u09A8\u09C7");
            etob.put("there", "\u09B8\u09C7\u0996\u09BE\u09A8\u09C7");
            etob.put("both", "\u0989\u09AD\u09AF\u09BC");
            etob.put("us", "\u0986\u09AE\u09BE\u09A6\u09C7\u09B0");
            etob.put("ok", "\u09A0\u09BF\u0995 \u0986\u099B\u09C7");
            etob.put("start", "\u09B6\u09C1\u09B0\u09C1");
            etob.put("stop", "\u09AC\u09A8\u09CD\u09A7 \u0995\u09B0\u09BE");
            etob.put("yes", "\u09B9\u09BE\u0981");
            etob.put("no", "\u09A8\u09BE");
            etob.put("very", "\u0996\u09C1\u09AC");
            etob.put("good", "\u09AD\u09BE\u09B2");
            etob.put("best", "\u09B8\u09C7\u09B0\u09BE");
            etob.put("know", "\u099C\u09BE\u09A8\u09BE");
            etob.put("new", "\u09A8\u09A4\u09C1\u09A8");
            etob.put("hi", "\u09AE\u09A8\u09CB\u09AF\u09CB\u0997 \u0986\u0995\u09B0\u09CD\u09B7\u09A8 ");
            etob.put("today", "\u0986\u099C");
            etob.put("day", "\u09A6\u09BF\u09A8");
            etob.put("month", "\u09AE\u09BE\u09B8");
            etob.put("year", "\u09AC\u099B\u09B0");
            etob.put("week", "\u09B8\u09AA\u09CD\u09A4\u09BE\u09B9");
            etob.put("nice", "\u09AE\u09A8\u09C7\u09BE\u09B0\u09AE");
            etob.put("dear", "\u09AA\u09CD\u09B0\u09BF\u09AF\u09BC");
            
            txt = txt_eng.getText();
            txt_bng.setText("" + etob.get(txt));
            
        }
        
    }
}
