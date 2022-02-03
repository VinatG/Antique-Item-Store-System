package MyProject.java;

import java.sql.*;

public class PastOrders
{
    public static void processTransaction(int uid)
    {
        Connection con = null;
        Statement stmt = null;
        Statement stmt2=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from cart WHERE USER_ID="+uid);
            while(r.next())
            {
               String sql = "INSERT INTO pastorders VALUES(" + r.getInt(1) + "," + r.getInt(2) + "," + r.getInt(3) + ",'" + java.time.LocalDate.now() + "')";
               stmt2.executeUpdate(sql);
            }
            String query = "DELETE FROM cart WHERE USER_ID="+uid;
            stmt.executeUpdate(query);
            stmt.close();
            con.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources

        }
    }

}
