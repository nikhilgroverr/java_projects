import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.rmi.*;
import javax.swing.*;


public class remoteclient extends JFrame implements ActionListener,Runnable
{
    //public static final String URL="rmi://127.0.0.1/QuizServer";
    String sql; 
    details[] data1;
    details[] data2;
    int[] rand1=new int[300];
    int dex=0;
    int scores=0;   
    int clicked=0;
    int count=0;
    
    //serverinterface theserver;
    JRadioButton radioption1,radioption2,radioption3,radioption4,radioption5;
    JButton nextbtn;//exitbtn;
    JLabel lblname1,lblname2,lblname3,lblname4,ltimer,timer1;
    ButtonGroup grp;
    int time1=30;
    
    
    String qn,ans1,ans2,ans3,ans4,selectedans,correctans,uname,cat;
    String ans="";
    int num=1,qno=0,question,index=0;
    ResultSet rs;
    Connection conn;

    public remoteclient(String uname1, String cat1)
    {

        try
        {
            uname=uname1;
            //System.out.println(uname);
            
            cat=cat1;
            //System.out.println(cat);

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/exam","root","");
            
            setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Online Quiz");
            
            setLayout(null);
            
            radioption1=new JRadioButton("",false);
            radioption2=new JRadioButton("",false);
            radioption3=new JRadioButton("",false);
            radioption4=new JRadioButton("",false);
            radioption5=new JRadioButton("",false);
       
            nextbtn=new JButton("Next");
            nextbtn.setBounds(250,480,100,40);
            Font fnextbtn=new Font("Avenir",Font.PLAIN,18);
            nextbtn.setFont(fnextbtn);
            nextbtn.setBackground(new Color(79,134,247));
            nextbtn.setForeground(Color.white);
            
            /*exitbtn=new JButton("Exit");
            exitbtn.setBounds(400,450,100,40);
            Font fexitbtn=new Font("Avenir",Font.PLAIN,18);
            exitbtn.setFont(fexitbtn);
            exitbtn.setBackground(new Color(79,134,247));
            exitbtn.setForeground(Color.white);*/
         
            lblname1=new JLabel("");
            //lblname1.setBounds(50,25,300,30);
            
            lblname2=new JLabel("");
            lblname2.setBounds(100,105,500,30);          //Question No. Label
            
            lblname3=new JLabel("");
            lblname3.setBounds(250,70,900,100);          //Question Label
            
            
            lblname4=new JLabel("");
            //lblname4.setBounds(50,25,300,30);

            ltimer=new JLabel("Timer : ");
            ltimer.setBounds(1100,10,120,40);
            Font fltimer=new Font("Avenir",Font.PLAIN,35);
            ltimer.setFont(fltimer);
            ltimer.setForeground(Color.black);
            
            timer1=new JLabel("");
            timer1.setBounds(1220,10,120,40);
            Font ftimer1=new Font("Avenir",Font.PLAIN,35);
            timer1.setFont(ftimer1);
            timer1.setForeground(Color.black);
            
            
            grp=new ButtonGroup();
    
            lblname1.setFont(new Font("Avenir",Font.BOLD,16));
            nextbtn.setFont(new Font("Avenir",Font.BOLD,18));
            //exitbtn.setFont(new Font("Avenir",Font.BOLD,18));
                
            radioption1.setFont(new Font("Avenir",Font.BOLD|Font.ITALIC,20));
            radioption1.setBounds(245,180,500,50);
            
            radioption2.setFont(new Font("Avenir",Font.BOLD|Font.ITALIC,20));
            radioption2.setBounds(245,250,500,50);
            
            radioption3.setFont(new Font("Avenir",Font.BOLD|Font.ITALIC,20));
            radioption3.setBounds(245,320,500,50);
            
            radioption4.setFont(new Font("Avenir",Font.BOLD|Font.ITALIC,20));
            radioption4.setBounds(245,390,500,50);
            
            lblname2.setFont(new Font("Avenir",Font.BOLD|Font.ITALIC,20));
            lblname3.setFont(new Font("Avenir",Font.BOLD|Font.ITALIC,20));
            lblname4.setFont(new Font("Avenir",Font.BOLD|Font.ITALIC,20));
            
            add(lblname2);
            add(lblname3);
            add(ltimer);
            add(timer1);
            add(radioption1);
            add(radioption2);
            add(radioption3);
            add(radioption4);
            add(radioption5);
            grp.add(radioption1);
            grp.add(radioption2);
            grp.add(radioption3);
            grp.add(radioption4);
            grp.add(radioption5);
    
            radioption5.setVisible(false);
        
            add(nextbtn);
            //add(exitbtn);
            
            
            nextbtn.addActionListener(this);
            //exitbtn.addActionListener(this);
            
            setVisible(true);
      
            //try
            //{
                sql="select * from quiz where category='" + cat +"'";
                server theserver=new server();
                data2=theserver.getDetails(sql);
                question=data2.length;
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery(sql);

                while(rs.next())
                {
                    count++;;
                }
                
                //System.out.println(question);
                
                display();

                Thread t1=new Thread(this);
                t1.start();
            /*}
            catch(Exception ce)
            {
                System.out.println(ce);
            } */
        }
        catch(Exception ce)
        {
            System.out.println(ce);
        } 
    }
    
    public void run()
    {
        while(dex!=10)
        {
            while(time1>=0)
            {
                try
                {
                    if(clicked==1)
                    {
                        time1=30;
                        clicked=0;
                    }
                    timer1.setText(String.valueOf(time1));
                    Thread.sleep(1000);
                    time1=time1-1;   
                    //System.out.println(time1);     
                }
                catch(Exception ee)
                {
                    System.out.println(ee);
                }
            }
            time1=30;
            abc();
        } 
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(dex==10)                                 //dex is the numbering of question
        //Last but one question
        nextbtn.setEnabled(false);

        
        if(e.getSource().equals(nextbtn))
        {
            clicked=1;

            if(radioption1.isSelected() || radioption2.isSelected() || radioption3.isSelected() || radioption4.isSelected())
            {
                
                if(radioption1.isSelected())
                ans=radioption1.getText().trim();
                else if(radioption2.isSelected())
                ans=radioption2.getText().trim();
                else if(radioption3.isSelected())
                ans=radioption3.getText().trim();
                else if(radioption4.isSelected())
                ans=radioption4.getText().trim();
                
                
                if(ans.trim().equals(correctans.trim()))
                {
                    scores=scores+5;
                    //System.out.println(scores);
                }
                
                radioption5.setSelected(true);
                
                while(true)
                {
                    Random r=new Random();
                    int a=r.nextInt(80);
                    int len=rand1.length;
                    int found=0;
                    
                    for(int k=0;k<len;k++)
                    {
                        if(rand1[k]==a)
                        {
                            found=1;
                            break;
                        }
                    }
                    
                    if(found==0)
                    {
                        rand1[dex]=a;
                        dex++;
                        System.out.println(dex);
                        
                        index=a;
                        break;        
                    }
                }
                //System.out.println("c");
               
                
                String nxt;
                nxt=nextbtn.getText();
                
                if(nxt.equals("Finish"))
                {
                    try
                    {
                        String str="update login set attempt='yes', marks='" + scores + "' where username='" + uname + "'";
                        System.out.println(str);
                        Statement update1=conn.createStatement();
                        update1.executeUpdate(str);
                        
                    }
                    catch(Exception ae)
                    {
                        System.out.println(ae);
                    }       
                    
                    JOptionPane.showMessageDialog(this,"Your scores are submitted");
                    System.exit(0);             
                }else        
                {
                    display();          
                }
                
                if(dex==9)
                {
                    nextbtn.setText("Finish");
                    System.out.println(dex);
                }
                //String a;
                //a=nextbtn.getText().toString();
            }else
            {
                JOptionPane.showMessageDialog(this,"Please select atleast 1 answer");
            }
        }
        
        
        /*else
        {
            if(e.getSource().equals(exitbtn))
            {
                String ans2="";
                
                if(radioption1.isSelected())
                ans2=radioption1.getText().trim();
                else if(radioption2.isSelected())
                ans2=radioption2.getText().trim();
                else if(radioption3.isSelected())
                ans2=radioption3.getText().trim();
                else if(radioption4.isSelected())
                ans2=radioption4.getText().trim();
                
                System.out.println(ans2);
                System.out.println(correctans);
                
                if(ans2.equals(correctans))
                {
                    scores=scores+5;
                }
                
                JOptionPane.showMessageDialog(this,"Your scores are submitted.");
                System.exit(0);
            }
        }*/
        
    }
    
    public void abc()
    {
        
        if(dex==10)
        //Last but one question
        nextbtn.setEnabled(false);

        if(radioption1.isSelected() || radioption2.isSelected() || radioption3.isSelected() || radioption4.isSelected())
        {
            
            if(radioption1.isSelected())
            ans=radioption1.getText().trim();
            else if(radioption2.isSelected())
            ans=radioption2.getText().trim();
            else if(radioption3.isSelected())
            ans=radioption3.getText().trim();
            else if(radioption4.isSelected())
            ans=radioption4.getText().trim();
            
            
            if(ans.trim().equals(correctans.trim()))
            {
                scores=scores+5;
                //System.out.println(scores);
            }
            
            radioption5.setSelected(true);
            
            while(true)
            {
                Random r=new Random();
                int a=r.nextInt(80);
                int len=rand1.length;
                int found=0;
                
                for(int k=0;k<len;k++)
                {
                    if(rand1[k]==a)
                    {
                        found=1;
                        break;
                    }
                }
                
                if(found==0)
                {
                    rand1[dex]=a;
                    dex++;
                    System.out.println(dex);
                    
                    index=a;
                    break;        
                }
            }
            //System.out.println("c");
            
            
            String nxt;
            nxt=nextbtn.getText();
            
            if(nxt.equals("Finish"))
            {
                try
                {
                    String str="update login set attempt='yes', marks='" + scores + "' where username='" + uname + "'";
                    System.out.println(str);
                    Statement update1=conn.createStatement();
                    update1.executeUpdate(str);
                    
                }
                catch(Exception ae)
                {
                    System.out.println(ae);
                }       
                
                JOptionPane.showMessageDialog(this,"Your scores are submitted");
                System.exit(0);             
            }else        
            {
                display();          
            }
            
            if(dex==9)
            {
                nextbtn.setText("Finish");
                System.out.println(dex);
            }
            //String a;
            //a=nextbtn.getText().toString();
        }else
        {
            display();          
        }
    }
     
    public void display()
    {
        //System.out.println(index);
        String qnn=data2[index].question;
        StringBuffer bstr=new StringBuffer("<html>");
        
        for(int l=0;l<qnn.length();l++) 
        {
            if(l%110==0 && l!=0)
            {
                bstr=bstr.append("<br>");
            }
            
            bstr=bstr.append(qnn.charAt(l));
        }
        
        bstr.append("</html>");
        //System.out.println(bstr.toString());
        
        
        String qn=data2[index].question;
        
        ans1=data2[index].ans1;
        ans2=data2[index].ans2;
        ans3=data2[index].ans3;
        ans4=data2[index].ans4;
        
        correctans=data2[index].correctans;
        qno=qno+1;       

        radioption5.setEnabled(true);

        lblname2.setText("Question " + qno + ".");
        lblname3.setText(bstr.toString());

        radioption1.setLabel(ans1);
        radioption2.setLabel(ans2);
        radioption3.setLabel(ans3);
        radioption4.setLabel(ans4);
        
        radioption1.setSelected(false);
        radioption2.setSelected(false);
        radioption3.setSelected(false);
        radioption4.setSelected(false);

        /*while(time1>=0)
        {
            try
            {
                timer1.setText(String.valueOf(time1));
                Thread.sleep(1000);
                time1=time1-1;  
                System.out.println(time1);                        
            }
            catch(Exception ee)
            {
                System.out.println(ee);
            }
            
        }*/
    }     
}