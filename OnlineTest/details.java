import java.sql.*;
import java.io.Serializable;
import java.util.Vector;

public class details implements Serializable
{
    int qno;
    String question,ans1,ans2,ans3,ans4,correctans;

    public details(int mqno,String mquestion,String m_ans1,String m_ans2,String m_ans3,String m_ans4,String mcorrectans)
    {
        qno=mqno;
        question=mquestion;
        ans1=m_ans1;
        ans2=m_ans2;
        ans3=m_ans3;
        ans4=m_ans4;
        correctans=mcorrectans;
    }

    public static details[] getInstance(Connection con,String sql) throws SQLException
    {
        String mquestion,m_ans1,m_ans2,m_ans3,m_ans4,mcorrectans;

        Vector vect=new Vector();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);

        while(rs.next())
        {
            int mqno=rs.getInt(1);
            mquestion=rs.getString(2);
            m_ans1=rs.getString(3);
            m_ans2=rs.getString(4);
            m_ans3=rs.getString(5);
            m_ans4=rs.getString(6);
            mcorrectans=rs.getString(7);
            details data=new details(mqno,mquestion,m_ans1,m_ans2,m_ans3,m_ans4,mcorrectans);
            vect.addElement(data);
        }
        rs.close();
        int num=vect.size();
        details[] data=new details[num];

        for(int i=0;i<num;i++)
        {
            data[i]=(details) vect.elementAt(i);
        }

        return data;
    }
}