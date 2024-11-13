import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class exam extends JFrame implements ActionListener
{
    JLabel name,category,before,examdetails,d1,d2,d3,rules,r1,r2,r3,r4,r5,r6;
    JButton startexam;
    Connection conn;
    String uname,cat;
    
    public exam( String uname1, String name1, String cat1)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/exam","root","");
            
            uname=uname1;
            cat=cat1;

            setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Start Exam");
            
            setSize(1000,1000);
            setLayout(null);
            
            name=new JLabel("");
            name.setBounds(470,20,500,50);
            name.setText("Hello, " + name1);
            Font fname=new Font("Avenir",Font.ITALIC,40);
            name.setFont(fname);
            name.setBackground(new Color(79,134,247));
            
            before=new JLabel("Before you start the Exam, here are the rules");
            before.setBounds(350,70,800,50);
            Font fbefore=new Font("Avenir",Font.ITALIC,30);
            before.setFont(fbefore);
            
            examdetails=new JLabel("Exam Details : ");
            examdetails.setBounds(40,160,200,50);
            Font fexamdetails=new Font("Avenir",Font.PLAIN,20);
            examdetails.setFont(fexamdetails);
            
            d1=new JLabel("1. Exam Name     : " + cat.toUpperCase());
            d1.setBounds(40,220,500,30);
            Font fd1=new Font("Avenir",Font.PLAIN,20);
            d1.setFont(fd1);
            
            d2=new JLabel("2. Total Question :  20" );
            d2.setBounds(40,250,200,30);
            Font fd2=new Font("Avenir",Font.PLAIN,20);
            d2.setFont(fd2);
            
            d3=new JLabel("3. Total Marks     : 100" );
            d3.setBounds(40,280,250,30);
            Font fd3=new Font("Avenir",Font.PLAIN,20);
            d3.setFont(fd3);
            
            rules=new JLabel("Rules : " );
            rules.setBounds(40,340,100,30);
            Font frules=new Font("Avenir",Font.PLAIN,20);
            rules.setFont(frules);
 
            r1=new JLabel("1. All questions are multiple choice question." );
            r1.setBounds(40,390,700,30);
            Font fr1=new Font("Avenir",Font.PLAIN,20);
            r1.setFont(fr1);
            
            r2=new JLabel("2. Only one choice is correct");
            r2.setBounds(40,420,700,30);
            Font fr2=new Font("Avenir",Font.PLAIN,20);
            r2.setFont(fr2);
            
            r3=new JLabel("3. Every question carry different marks.");
            r3.setBounds(40,450,700,30);
            Font fr3=new Font("Avenir",Font.PLAIN,20);
            r3.setFont(fr3);
            
            r4=new JLabel("4. Try to answer as quickly as you can.");
            r4.setBounds(40,480,700,30);
            Font fr4=new Font("Avenir",Font.PLAIN,20);
            r4.setFont(fr4);
            
            r5=new JLabel("5. For each question you get 30 seconds to answer.");
            r5.setBounds(40,510,700,30);
            Font fr5=new Font("Avenir",Font.PLAIN,20);
            r5.setFont(fr5);

            r6=new JLabel("6. Questions are displayed randomly for every user.");
            r6.setBounds(40,540,700,30);
            Font fr6=new Font("Avenir",Font.PLAIN,20);
            r6.setFont(fr6);

            startexam=new JButton("Start Exam");
            startexam.setBounds(40,600,140,50);
            Font fstartexam=new Font("Avenir",Font.PLAIN,20);
            startexam.setFont(fstartexam);
            startexam.setBackground(new Color(79,134,247));
            startexam.setForeground(Color.white);
            
            add(name);
            add(before);
            add(examdetails);
            add(d1);
            add(d2);
            add(d3);
            add(rules);
            add(r1);
            add(r2);
            add(r3);
            add(r4);
            add(r5);
            add(r6);
            add(startexam);
            
            startexam.addActionListener(this);
            
            setVisible(true);
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            
        }
    }
    
    public void actionPerformed(ActionEvent a)
    {
        
        if(a.getSource()==startexam)
        {
            remoteclient obj=new remoteclient(uname,cat);
            this.dispose();
        }
    }
}