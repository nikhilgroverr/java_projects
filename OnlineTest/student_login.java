import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class student_login extends JFrame implements ActionListener
{
    JLabel hello,please,lusername,lpassword;
    JTextField username;
    JPasswordField password;
    JButton home,teacher,student1,login;
    Connection conn;

    public student_login()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/exam","root","");

            setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Student Login");
            
            setSize(1000,1000);
            setLayout(null);
    
            hello=new JLabel("Hello, Student");
            hello.setBounds(465,50,800,70);
            Font fhello=new Font("Avenir",Font.ITALIC,70);
            hello.setFont(fhello);
            
            please=new JLabel("Please enter your username and password!");
            please.setBounds(495,120,400,50);
            Font fplease=new Font("Anevir",Font.PLAIN,20);
            please.setFont(fplease);
    
            lusername=new JLabel("Username");
            lusername.setBounds(600,220,200,50);
            Font fusername=new Font("Avenir",Font.PLAIN,35);
            lusername.setFont(fusername);
            
            username=new JTextField(20);
            username.setBounds(505,290,370,50);
            username.setFont(fusername);
            
            lpassword=new JLabel("Password");
            lpassword.setBounds(600,340,200,50);
            Font fpassword=new Font("Avenir",Font.PLAIN,35);
            lpassword.setFont(fpassword);
            
            password=new JPasswordField(20);
            password.setBounds(505,410,370,50);
            password.setFont(fpassword);
            
            login=new JButton("Login");
            login.setBounds(640,495,100,50);
            Font flogin=new Font("Avenir",Font.PLAIN,18);
            login.setFont(flogin);
            login.setBackground(new Color(79,134,247));
            login.setForeground(Color.white);
            
            add(hello);
            add(please);
            add(username);
            add(lusername);
            add(password);
            add(lpassword);
            add(login);
            
            
            login.addActionListener(this);
            
            setVisible(true);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void actionPerformed(ActionEvent a)
    {
        try
        {
            if(a.getSource()==login)
            {
                String uname,pass,str;
                uname=username.getText().toString();
                pass=password.getText().toString();
            
                str="Select * from login where username='" + uname + "' and password='" + pass + "'";
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery(str);
                
                if(uname.equals("") || pass.equals(""))
                {
                    JOptionPane.showMessageDialog(this,"Username and Password is must");
                }else if(rs.next())
                {
                    String attempt1=rs.getString(5);
                        
                    if(attempt1.equals("no"))
                    {
                        String uname1=rs.getString(1);
                        String name1=rs.getString(3);
                        String cat1=rs.getString(4);
                        exam exam1=new exam(uname1,name1,cat1);
                        this.dispose();
                    }else
                    {
                        JOptionPane.showMessageDialog(this,"You have already attempted the exam");
                        System.exit(0);
                    }
                }else
                {
                    JOptionPane.showMessageDialog(this,"Invalid User");
                }  
            } 
                

        }     
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    
    
    /*public static void main(String[] args)
    {
        student_login obj=new student_login();
    }*/
}