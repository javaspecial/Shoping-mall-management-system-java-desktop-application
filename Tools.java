
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Toxic
 */
public class Tools extends JPanel {

    private static Font f;
    //converter
    public final double kg = 2.20462262;		// 1kg=2.2pounds
    public final double pounds = 0.45359237;
    public final double meter = 3.28;
    public final double foot = .3;
    public final double leter = 1000;
    public final double mleter = 0.001;

    private JRadioButton jr[] = new JRadioButton[3];
    private JTextField txtw = new JTextField();
    private JTextField txtw1 = new JTextField();
    private JTextField txtl = new JTextField();
    private JTextField txtl1 = new JTextField();
    private JTextField txtt = new JTextField();
    private JTextField txtt1 = new JTextField();
    private JLabel labelw = new JLabel("=");
    private JLabel labell = new JLabel("=");
    private JLabel labelt = new JLabel("=");
    private JButton convertbtn = new JButton("Convert");
    private JButton clearbtn = new JButton("Clear");

    private int checkInput(String inString) {
        StringBuffer stringbff = new StringBuffer(inString);
        int flag = 0;
        for (int index = 0; index < stringbff.length(); index++) {
            char ch = stringbff.charAt(index);
            if ((ch < '0' || ch > '9') && (ch != '.')) {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            return 0;//input not a number
        } else {
            return 1;//valid input
        }

    }

    //converter
    //calendar
    private JLabel lblmonth = new JLabel("January", SwingConstants.CENTER);
    private JLabel lblyear = new JLabel(" Year:");
    private JComboBox comboyear = new JComboBox();
    private JButton btnprev = new JButton("", new ImageIcon("images//prvbtn.png"));
    private JButton btnnext = new JButton("", new ImageIcon("images//nxtbtn.png"));
    private DefaultTableModel mtblcalendar = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
        }
    };
    private JTable tblcalendar = new JTable(mtblcalendar);
    private JScrollPane stblcalendar = new JScrollPane(tblcalendar);
    private int realyear, realmonth, realday, currentyear, currentmonth;

    //calendar
    //tools
    private JButton stopwatchbtn = new JButton("Stop Watch");
    private JButton watch = new JButton(" Watch");
    private JButton calender = new JButton("Calendar");
    private JButton Converter = new JButton("Converter");
    private JButton alarm = new JButton("Speaker");

    //tools
    //watch
    private JLabel watchlabel = new JLabel("");
    //watch

    //StopWatch
    private JButton STARTBTN = new JButton("Start");
    private JButton STOPBTN = new JButton("Stop");
    private JButton RESETBTN = new JButton("Reset");
    private Timer timer;
    private static final int onesec = 1000;
    private static final int tenthsec = 100;
    private JLabel timelabel = new JLabel("", SwingConstants.CENTER);
    private int clocktick;
    private double clocktime;
    private String clocktimesharing;
    private Font clockfont;
    //StopWatch
    //Speak

    private JButton speak = new JButton("Challenge Me");
    private JButton btnspeakeroff = new JButton("Stop me");
    private static final String voicename = "kevin16";
    private JLabel SPEAKERLABEL = new JLabel(new ImageIcon("images//speaker1.png"));

    //Speak
    public Tools() {
        this.setLayout(null);
        this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        this.setBackground(Color.lightGray);

        //speaker
        SPEAKERLABEL.setBounds(100, 20, 148, 150);
        SPEAKERLABEL.setVisible(false);
        SPEAKERLABEL.setForeground(Color.red);
        SPEAKERLABEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(SPEAKERLABEL);

        speak.setBounds(100, 177, 148, 25);
        speak.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //    speak.setContentAreaFilled(false);
        speak.setFocusPainted(false);
        speak.setVisible(false);
        speak.setFont(f);
        speak.setToolTipText("Click me to read");
        this.add(speak);
        speak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speak.setVisible(false);
                btnspeakeroff.setVisible(true);

                Voice voice;
                VoiceManager vm = VoiceManager.getInstance();
                voice = vm.getVoice(voicename);
                voice.allocate();

                try {
                    if (LeftPanel.textArea.getText() != null) {
                        voice.speak(LeftPanel.textArea.getText());
                    } else {
                        voice.speak("There is no input inside");

                    }

                } catch (Exception errr) {
                    errr.printStackTrace();
                }

            }
        });
        btnspeakeroff.setBounds(100, 177, 148, 25);
        btnspeakeroff.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // btnspeakeroff.setContentAreaFilled(false);
        btnspeakeroff.setFocusPainted(false);
        btnspeakeroff.setVisible(false);
        btnspeakeroff.setFont(f);
        btnspeakeroff.setToolTipText("Click me to pause");
        this.add(btnspeakeroff);
        btnspeakeroff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnspeakeroff.setVisible(false);
                speak.setVisible(true);

            }
        });
        //speaker

        //CONVERTER
        addWeights();
        //CONVERTER

        //CALENDAR
        btnprev.setBounds(100, 20, 34, 20);
        btnprev.setVisible(true);
        btnprev.setFont(f);
        btnprev.setContentAreaFilled(false);
        btnprev.setFocusPainted(false);
        btnprev.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(btnprev);
        btnprev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentmonth == 0) { //Back one year
                    currentmonth = 11;
                    currentyear -= 1;
                } else { //Back one month
                    currentmonth -= 1;
                }
                refreshCalendar(currentmonth, currentyear);

            }
        });

        btnnext.setBounds(210, 20, 34, 20);
        btnnext.setFont(f);
        btnnext.setVisible(true);
        btnnext.setContentAreaFilled(false);
        btnnext.setFocusPainted(false);
        btnnext.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(btnnext);
        btnnext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentmonth == 11) { //Foward one year
                    currentmonth = 0;
                    currentyear += 1;
                } else { //Foward one month
                    currentmonth += 1;
                }
                refreshCalendar(currentmonth, currentyear);
            }
        });

        lblmonth.setBounds(137, 20, 70, 20);
        lblmonth.setVisible(true);
        lblmonth.setForeground(Color.BLACK);
        this.add(lblmonth);

        lblyear.setBounds(98, 175, 70, 25);
        lblyear.setVisible(true);
        lblyear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblyear.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.add(lblyear);

        comboyear.setBounds(173, 175, 76, 25);
        comboyear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboyear.setVisible(true);
        comboyear.setFocusable(false);
        this.add(comboyear);
        comboyear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboyear.getSelectedItem() != null) {
                    String b = comboyear.getSelectedItem().toString();
                    currentyear = Integer.parseInt(b);
                    refreshCalendar(currentmonth, currentyear);
                }
            }
        });

        stblcalendar.setBounds(100, 50, 150, 120);
        stblcalendar.setBackground(Color.red);
        stblcalendar.setVisible(true);
        this.add(stblcalendar);

        GregorianCalendar cal = new GregorianCalendar();
        realday = cal.get(GregorianCalendar.DAY_OF_MONTH);
        realmonth = cal.get(GregorianCalendar.MONTH);
        realyear = cal.get(GregorianCalendar.YEAR);
        currentmonth = realmonth;
        currentyear = realyear;
        String[] header = {"S", "M", "T", "W", "T", "F", "S"};
        for (int i = 0; i < 7; i++) {
            mtblcalendar.addColumn(header[i]);

        }
        tblcalendar.getParent().setBackground(tblcalendar.getBackground());
        tblcalendar.getTableHeader().setResizingAllowed(false);
        tblcalendar.getTableHeader().setReorderingAllowed(false);
        tblcalendar.setColumnSelectionAllowed(true);
        tblcalendar.setRowSelectionAllowed(true);
        tblcalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblcalendar.setRowHeight(16);
        mtblcalendar.setColumnCount(7);
        mtblcalendar.setRowCount(6);

        for (int i = realyear - 100; i <= realyear + 100; i++) {
            comboyear.addItem(String.valueOf(i));
        }
        refreshCalendar(realmonth, realyear);

        //CALENDAR
        //tools
        stopwatchbtn.setBounds(10, 20, 80, 25);
        stopwatchbtn.setContentAreaFilled(false);
        stopwatchbtn.setFocusPainted(false);
        stopwatchbtn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(stopwatchbtn);
        stopwatchbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SPEAKERLABEL.setVisible(false);
                speak.setVisible(false);
                labelw.setVisible(false);
                labell.setVisible(false);
                labelt.setVisible(false);
                jr[0].setVisible(false);
                jr[1].setVisible(false);
                jr[2].setVisible(false);
                txtw.setVisible(false);
                txtw1.setVisible(false);
                txtl.setVisible(false);
                txtl1.setVisible(false);
                txtt.setVisible(false);
                txtt1.setVisible(false);
                convertbtn.setVisible(false);
                clearbtn.setVisible(false);
                lblmonth.setVisible(false);
                lblyear.setVisible(false);
                comboyear.setVisible(false);
                btnprev.setVisible(false);
                btnnext.setVisible(false);
                stblcalendar.setVisible(false);
                watchlabel.setVisible(false);
                timelabel.setVisible(true);
                STARTBTN.setVisible(true);
                STOPBTN.setVisible(true);
                RESETBTN.setVisible(true);

            }
        });
        watch.setBounds(10, 60, 80, 25);
        watch.setContentAreaFilled(false);
        watch.setFocusPainted(false);
        watch.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(watch);
        watch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SPEAKERLABEL.setVisible(false);
                speak.setVisible(false);
                labelw.setVisible(false);
                labell.setVisible(false);
                labelt.setVisible(false);
                jr[0].setVisible(false);
                jr[1].setVisible(false);
                jr[2].setVisible(false);
                txtw.setVisible(false);
                txtw1.setVisible(false);
                txtl.setVisible(false);
                txtl1.setVisible(false);
                txtt.setVisible(false);
                txtt1.setVisible(false);
                convertbtn.setVisible(false);
                clearbtn.setVisible(false);
                lblmonth.setVisible(false);
                lblyear.setVisible(false);
                comboyear.setVisible(false);
                btnprev.setVisible(false);
                btnnext.setVisible(false);
                stblcalendar.setVisible(false);
                timelabel.setVisible(false);
                STARTBTN.setVisible(false);
                STOPBTN.setVisible(false);
                RESETBTN.setVisible(false);
                watchlabel.setVisible(true);

            }
        });
        watchlabel.setBounds(100, 20, 150, 180);
        watchlabel.setVisible(false);
        watchlabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        watchlabel.setForeground(Color.RED);
        watchlabel.setFont(new Font("sansserif", Font.PLAIN, 30));
        this.add(watchlabel);
        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = new GregorianCalendar();
                String am_pm;
                Calendar now = Calendar.getInstance();
                int h = now.get(Calendar.HOUR);
                int m = now.get(Calendar.MINUTE);
                int s = now.get(Calendar.SECOND);
                if (calendar.get(Calendar.AM_PM) == 0) {
                    am_pm = "AM";

                } else {
                    am_pm = "PM";
                }
                watchlabel.setText("" + h + ":" + m + ":" + s + "" + am_pm);
            }
        });
        t.start();

        calender.setBounds(10, 100, 80, 25);
        calender.setContentAreaFilled(false);
        calender.setFocusPainted(false);
        calender.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(calender);
        calender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SPEAKERLABEL.setVisible(false);
                speak.setVisible(false);
                labelw.setVisible(false);
                labell.setVisible(false);
                labelt.setVisible(false);
                watchlabel.setVisible(false);
                timelabel.setVisible(false);
                STARTBTN.setVisible(false);
                STOPBTN.setVisible(false);
                RESETBTN.setVisible(false);
                jr[0].setVisible(false);
                jr[1].setVisible(false);
                jr[2].setVisible(false);
                txtw.setVisible(false);
                txtw1.setVisible(false);
                txtl.setVisible(false);
                txtl1.setVisible(false);
                txtt.setVisible(false);
                txtt1.setVisible(false);
                convertbtn.setVisible(false);
                clearbtn.setVisible(false);

                lblmonth.setVisible(true);
                lblyear.setVisible(true);
                comboyear.setVisible(true);
                btnprev.setVisible(true);
                btnnext.setVisible(true);
                stblcalendar.setVisible(true);

            }
        });

        Converter.setBounds(10, 140, 80, 25);
        Converter.setContentAreaFilled(false);
        Converter.setFocusPainted(false);
        Converter.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(Converter);
        Converter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SPEAKERLABEL.setVisible(false);
                speak.setVisible(false);
                lblmonth.setVisible(false);
                lblyear.setVisible(false);
                comboyear.setVisible(false);
                btnprev.setVisible(false);
                btnnext.setVisible(false);
                stblcalendar.setVisible(false);
                timelabel.setVisible(false);
                STARTBTN.setVisible(false);
                STOPBTN.setVisible(false);
                RESETBTN.setVisible(false);
                watchlabel.setVisible(false);
                jr[0].setVisible(true);
                jr[1].setVisible(true);
                jr[2].setVisible(true);
                txtw.setVisible(true);
                txtw1.setVisible(true);
                txtl.setVisible(true);
                txtl1.setVisible(true);
                txtt.setVisible(true);
                txtt1.setVisible(true);
                convertbtn.setVisible(true);
                clearbtn.setVisible(true);
                labelw.setVisible(true);
                labell.setVisible(true);
                labelt.setVisible(true);
            }
        });

        alarm.setBounds(10, 175, 80, 25);
        alarm.setContentAreaFilled(false);
        alarm.setFocusPainted(false);
        alarm.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(alarm);
        alarm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                lblmonth.setVisible(false);
                lblyear.setVisible(false);
                comboyear.setVisible(false);
                btnprev.setVisible(false);
                btnnext.setVisible(false);
                stblcalendar.setVisible(false);
                timelabel.setVisible(false);
                STARTBTN.setVisible(false);
                STOPBTN.setVisible(false);
                RESETBTN.setVisible(false);
                watchlabel.setVisible(false);
                jr[0].setVisible(false);
                jr[1].setVisible(false);
                jr[2].setVisible(false);
                txtw.setVisible(false);
                txtw1.setVisible(false);
                txtl.setVisible(false);
                txtl1.setVisible(false);
                txtt.setVisible(false);
                txtt1.setVisible(false);
                convertbtn.setVisible(false);
                clearbtn.setVisible(false);
                labelw.setVisible(false);
                labell.setVisible(false);
                labelt.setVisible(false);
                speak.setVisible(true);
                SPEAKERLABEL.setVisible(true);

            }
        });
        //tools

        //stopwatch
        clocktick = 0;
        clocktime = clocktick / 10.0;
        clocktimesharing = new Double(clocktime).toString();
        clockfont = new Font("Serif", Font.PLAIN, 100);

        timelabel.setBounds(100, 20, 150, 125);
        timelabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timelabel.setForeground(Color.RED);
        timelabel.setVisible(false);
        timelabel.setFont(clockfont);
        timelabel.setText(clocktimesharing);
        this.add(timelabel);

        STARTBTN.setBounds(100, 160, 70, 40);
        STARTBTN.setFont(f);
        STARTBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        STARTBTN.setContentAreaFilled(false);
        STARTBTN.setFocusPainted(true);
        STARTBTN.setVisible(false);
        this.add(STARTBTN);
        STARTBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                STOPBTN.setVisible(true);
                STARTBTN.setVisible(false);
                timer.start();
            }
        });

        STOPBTN.setBounds(100, 160, 70, 40);
        STOPBTN.setFont(f);
        STOPBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        STOPBTN.setContentAreaFilled(false);
        STOPBTN.setFocusPainted(false);
        STOPBTN.setVisible(false);
        this.add(STOPBTN);
        STOPBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                STOPBTN.setVisible(false);
                STARTBTN.setVisible(true);
                timer.stop();
            }
        });

        RESETBTN.setBounds(180, 160, 70, 40);
        RESETBTN.setFont(f);
        RESETBTN.setVisible(false);
        RESETBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        RESETBTN.setContentAreaFilled(false);
        RESETBTN.setFocusPainted(false);
        this.add(RESETBTN);
        RESETBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clocktick = 0;
                clocktime = clocktick / 10.0;
                clocktimesharing = new Double(clocktime).toString();
                timelabel.setText(clocktimesharing);

            }
        });

        timer = new Timer(tenthsec, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clocktick++;
                clocktime = clocktick / 10.0;
                clocktimesharing = new Double(clocktime).toString();
                timelabel.setText(clocktimesharing);
            }
        });
        //stopwatch

    }
//converter

    private void addWeights() {
        ButtonGroup convertgroup = new ButtonGroup();
        jr[0] = new JRadioButton("Weight");
        jr[0].setBounds(100, 20, 60, 20);
        jr[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jr[0].setContentAreaFilled(false);
        jr[0].setFocusPainted(false);
        jr[0].setVisible(false);
        this.add(jr[0]);
        convertgroup.add(jr[0]);

        txtw.setBounds(100, 40, 70, 20);
        txtw.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        txtw.setFont(f);
        txtw.setVisible(false);
        this.add(txtw);

        PromptSupport.setPrompt("Enter kg", txtw);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txtw);
        PromptSupport.setFontStyle(Font.BOLD, txtw);

        labelw.setBounds(172, 45, 10, 10);
        labelw.setVisible(false);
        this.add(labelw);

        txtw1.setBounds(181, 40, 70, 20);
        txtw1.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        txtw1.setFont(f);
        txtw1.setVisible(false);
        this.add(txtw1);

        PromptSupport.setPrompt("Enter pound", txtw1);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txtw1);
        PromptSupport.setFontStyle(Font.BOLD, txtw1);

        jr[1] = new JRadioButton("Length");
        jr[1].setBounds(100, 65, 60, 20);
        jr[1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jr[1].setContentAreaFilled(false);
        jr[1].setFocusPainted(false);
        jr[1].setVisible(false);
        this.add(jr[1]);
        convertgroup.add(jr[1]);

        txtl.setBounds(100, 85, 70, 20);
        txtl.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        txtl.setFont(f);
        txtl.setVisible(false);
        this.add(txtl);

        PromptSupport.setPrompt("Enter meter", txtl);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txtl);
        PromptSupport.setFontStyle(Font.BOLD, txtl);

        labell.setBounds(172, 90, 10, 10);
        labell.setVisible(false);
        this.add(labell);

        txtl1.setBounds(181, 85, 70, 20);
        txtl1.setVisible(false);
        txtl1.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        txtl1.setFont(f);
        this.add(txtl1);

        PromptSupport.setPrompt("Enter Foot", txtl1);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txtl1);
        PromptSupport.setFontStyle(Font.BOLD, txtl1);

        jr[2] = new JRadioButton("Liquid");
        jr[2].setBounds(100, 110, 100, 20);
        jr[2].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jr[2].setContentAreaFilled(false);
        jr[2].setFocusPainted(false);
        jr[2].setVisible(false);
        this.add(jr[2]);
        convertgroup.add(jr[2]);

        txtt.setBounds(100, 130, 70, 20);
        txtt.setVisible(false);
        txtt.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        txtt.setFont(f);
        this.add(txtt);
        PromptSupport.setPrompt("Enter leter", txtt);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txtt);
        PromptSupport.setFontStyle(Font.BOLD, txtt);

        labelt.setBounds(172, 135, 10, 10);
        labelt.setVisible(false);
        this.add(labelt);

        txtt1.setBounds(181, 130, 70, 20);
        txtt1.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        txtt1.setFont(f);
        txtt1.setVisible(false);
        this.add(txtt1);

        PromptSupport.setPrompt("Enter ML", txtt1);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.HIGHLIGHT_PROMPT, txtt1);
        PromptSupport.setFontStyle(Font.BOLD, txtt1);

        convertbtn.setBounds(100, 174, 70, 25);
        convertbtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        convertbtn.setContentAreaFilled(false);
        convertbtn.setFocusPainted(false);
        convertbtn.setVisible(false);
        convertbtn.setFont(f);
        this.add(convertbtn);
        convertbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!jr[0].isSelected() && !jr[1].isSelected() && !jr[2].isSelected()) {
                    JOptionPane.showMessageDialog(null, "No unit has been selected");
                }
                if (jr[0].isSelected()) {
                    weightConvert();
                    return;
                }
                if (jr[1].isSelected()) {
                    lengthConvert();
                    return;
                }
                if (jr[2].isSelected()) {
                    liquidConvert();
                    return;
                }
            }
        });

        clearbtn.setBounds(181, 174, 70, 25);
        clearbtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        clearbtn.setContentAreaFilled(false);
        clearbtn.setFocusPainted(false);
        clearbtn.setVisible(false);
        clearbtn.setFont(f);
        this.add(clearbtn);
        clearbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtw.setText("");
                txtw1.setText("");
                txtl.setText("");
                txtl1.setText("");
                txtt.setText("");
                txtt1.setText("");

            }
        });
    }

//    private void record(String result){
//        try{
//            File dataFile = new File("shadatah.txt");
//            if(dataFile.exists()){
//                System.out.print("existed");
//                FileWriter myFileWriter = new FileWriter(dataFile,true);
//                BufferedWriter myBffWriter = new BufferedWriter(myFileWriter);
//                myBffWriter.write(result + ",");
//                myBffWriter.newLine();
//                myBffWriter.close();
//                myFileWriter.close();
//            }
//            else{
//                System.out.print("create new");
//                FileWriter myFwriter = new FileWriter(dataFile);
//                BufferedWriter myBwriter = new BufferedWriter(myFwriter);
//                myBwriter.write(result+ ",");
//                myBwriter.newLine();
//                myBwriter.close();
//                myFwriter.close();
//             }
//            
//            
//            
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//    
    private void weightConvert() {
        if (txtw.getText().length() == 0 && txtw1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "No input");
            return;
        }
        if (txtw.getText().length() != 0 && txtw1.getText().length() != 0) {
            JOptionPane.showMessageDialog(null, " Enter only one field!");
            return;
        }
        if ((txtw.getText().length() != 0 && checkInput(txtw.getText()) == 0)
                || (txtw1.getText().length() != 0 && checkInput(txtw1.getText()) == 0)) {
            JOptionPane.showMessageDialog(null,
                    "Given value must be number", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtw.getText().length() != 0) {
            double in = Double.parseDouble(txtw.getText());
            double out = in * kg;
            JOptionPane.showMessageDialog(null, "Your input = " + in + "KG\n The Result = " + out + "Pound");

        }
        if (txtw1.getText().length() != 0) {
            double in = Double.parseDouble(txtw1.getText());
            double out = in * pounds;
            JOptionPane.showMessageDialog(null, "Your input = " + in + "Pounds\n The Result = " + out + "KG");
        }
    }

    private void lengthConvert() {
        if (txtl.getText().length() == 0 && txtl1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "No input");
        }
        if (txtl.getText().length() != 0 && txtl1.getText().length() != 0) {
            JOptionPane.showMessageDialog(null, "Enter only one field ");

        }
        if ((txtl.getText().length() != 0 && checkInput(txtl.getText()) == 0)
                || (txtl1.getText().length() != 0 && checkInput(txtl1.getText()) == 0)) {
            JOptionPane.showMessageDialog(null, "Given value must be number ");
        }
        if (txtl.getText().length() != 0) {
            double in = Double.parseDouble(txtl.getText());
            double out = in * meter;
            JOptionPane.showMessageDialog(null, "Your input = " + in + "Meter\n The Result = " + out + "Foot");

        }
        if (txtl1.getText().length() != 0) {
            double in = Double.parseDouble(txtl1.getText());
            double out = in * foot;
            JOptionPane.showMessageDialog(null, "Your input = " + in + "Foot\n  The Result = " + out + "Meter");

        }
    }

    public void liquidConvert() {
        if (txtt.getText().length() == 0 && txtt1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "No input");
        }
        if (txtt.getText().length() != 0 && txtt1.getText().length() != 0) {
            JOptionPane.showMessageDialog(null, "Enter only one field");
        }
        if ((txtt.getText().length() != 0 && checkInput(txtt.getText()) == 0)
                || (txtt1.getText().length() != 0 && checkInput(txtt1.getText()) == 0)) {
            JOptionPane.showMessageDialog(null, "Given value must be number");
        }
        if (txtt.getText().length() != 0) {
            double in = Double.parseDouble(txtt.getText());
            double out = in * leter;
            JOptionPane.showMessageDialog(null, "Your input = " + in + "Leter\n  The Result = " + out + "MLeter");

        }
        if (txtt1.getText().length() != 0) {
            double in = Double.parseDouble(txtt1.getText());
            double out = in * mleter;
            JOptionPane.showMessageDialog(null, "Your input = " + in + "MLeter\n  The Result = " + out + "Leter");
        }
    }

    //converter
    //calendar
    public void refreshCalendar(int month, int year) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som;

        btnprev.setEnabled(true);
        btnnext.setEnabled(true);
        if (month == 0 && year <= realyear - 10) {
            btnprev.setEnabled(false);
        }
        if (month == 11 && year >= realyear + 100) {
            btnnext.setEnabled(false);
        }
        lblmonth.setText(months[month]);
        // lblmonth.setBounds(160 - lblmonth.getPreferredSize().width/2 , 25, 180, 25);
        comboyear.setSelectedItem(String.valueOf(year));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mtblcalendar.setValueAt(null, i, j);
            }
        }
        GregorianCalendar cal = new GregorianCalendar();
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.getActualMaximum(GregorianCalendar.DAY_OF_WEEK);
        for (int i = 1; i <= nod; i++) {
            int row = new Integer((i + som - 2) / 7);
            int column = (i + som - 2) % 7;
            mtblcalendar.setValueAt(i, row, column);
        }
        tblcalendar.setDefaultRenderer(tblcalendar.getColumnClass(0), new tblCalendarRenderer());

    }

    public class tblCalendarRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6) { //Week-end
                setBackground(new Color(230, 28, 62));
            } else { //Week
                setBackground(new Color(255, 220, 220));
            }
            if (value != null) {
                if (Integer.parseInt(value.toString()) == realday && currentmonth == realmonth && currentyear == realyear) { //Today
                    setBackground(new Color(230, 230, 28));
                }
            }
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setForeground(Color.black);
            return this;
        }

    }
  //  calendar
}
