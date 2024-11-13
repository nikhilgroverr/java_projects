//Server

import java.rmi.*;
import java.net.*;
import java.rmi.server.*;
import java.sql.*;

public class server extends UnicastRemoteObject
{
    public static String url,sql;
    public static Connection con;
    public static Statement stmt;
    
    public server() throws RemoteException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/exam","root","");
        }
        catch(Exception ce)
        {
            System.out.println(ce);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            server server1=new server();
            Naming.rebind("QuizServer",server1);

            System.out.println("Server is ready and running....");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public details[] getDetails(String sql) throws SQLException
    {
        details[] data=details.getInstance(con,sql);
        System.out.println(data[0].question);
        return data;
    }
}