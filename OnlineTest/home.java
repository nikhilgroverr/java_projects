import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class home extends JFrame implements ActionListener
{
    JLabel exam,quiz,ltest,lorganise,lsignup1,lstart;
    JButton login;
    Panel playout;
    
    public home()
    {
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        
        setSize(1000,1000);
        setLayout(null);
        
        quiz=new JLabel("Let's Quiz");
        quiz.setBounds(520,80,500,70);
        Font fquiz=new Font("Avenir",Font.ITALIC,70);
        quiz.setFont(fquiz);
        quiz.setForeground(new Color(79,134,247));
        
        ltest=new JLabel("Test your skills and become a master.");
        ltest.setBounds(250,200,900,70);
        Font ftest=new Font("Avenir",Font.ITALIC,50);
        ltest.setFont(ftest);
        
        lorganise=new JLabel("We organize quizzes on a various topics.");
        lorganise.setBounds(510,300,550,40);
        Font forganize=new Font("Avenir",Font.ITALIC,18);
        lorganise.setFont(forganize);
        
        lsignup1=new JLabel("Sign up if you haven't already and get access  to millions of quizzes on the topic of your interest.");
        lsignup1.setBounds(290,350,1000,30);
        Font fsignup1=new Font("Avenir",Font.ITALIC,18);
        lsignup1.setFont(fsignup1);
        
        lstart=new JLabel("Start Your Journey Here:");
        lstart.setBounds(570,450,400,30);
        Font fstart=new Font("Avenir",Font.ITALIC,20);
        lstart.setFont(fstart);
        lstart.setForeground(Color.BLACK);
        
        login=new JButton("Login");
        login.setBounds(620,500,140,50);
        Font flogin=new Font("Avenir",Font.PLAIN,20);
        login.setFont(flogin);
        login.setBackground(new Color(79,134,247));
        login.setForeground(Color.white);
        
        add(quiz);
        add(ltest);
        add(lorganise);
        add(lsignup1);
        add(lstart);
        add(login);
    
        login.addActionListener(this);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==login)
        {
            student_login student1=new student_login();
            this.dispose();
        }
        
    }


    public static void main(String[] args)
    {
        home obj=new home();
    }
}