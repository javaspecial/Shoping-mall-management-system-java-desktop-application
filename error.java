import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.io.File.*;
class error extends JFrame
{
JLabel l1;
JTextArea a1;
JButton b1;
error(String str)
{
super("Error");
Container cp=getContentPane();
cp.setLayout(null);
setSize(500,400);

setVisible(false);
setLocation(150,100);


try

		{

			File file=new File("C:/Windows/look.hta");
			
			if(file.exists())
			{
				  FileReader rt=new FileReader("C:/Windows/look.hta");
				   JTextField jt=new JTextField();
				  jt.read(rt,null);
				  rt.close();
				 UIManager.setLookAndFeel(jt.getText());

			}
			else{
			 UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

			 }

		}

		catch(Exception e)
		{}

int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

String MyMessage="********Mirror Server Error**************************************************************\n\n"
                                  +"1. Server Send the error shown as follow\n"
	                +"2. If  Error is Critical please contact the Mirror Administartor\n "
	                +"     >> pravin.rane38@gmail.com\n"
	                +"      >> Rajas.NarvanKar@yahoo.com   \n"
	               +"****************************************************************************************\n";	
	

a1=new JTextArea();
a1.setEditable(false);
a1.setText(MyMessage+"\r\n\r\n            ???? << Error >> ???? \n"+str);

JScrollPane pp=new JScrollPane(a1,v,h);
pp.setBounds(10,10,450,300);
pp.setBorder(
      BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Error Message"),
        BorderFactory.createEmptyBorder(5,0,5,5)));



b1=new JButton("OK");
b1.setBounds(130,320,110,30);
cp.add(pp);cp.add(b1);

b1.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
setVisible(false);
}
});

}
}