package MyProject.java;
import java.sql.*;
import java.util.Scanner;

public class CardDetails
{
    int user_id;
    String card_number;
    String name;
    String expiry;
    String cvv;
    CardDetails()
    {
        Scanner sc=new Scanner(System.in);
        name=sc.nextLine();
        card_number=sc.nextLine();
        cvv=sc.nextLine();
        expiry=sc.nextLine();
    }
    CardDetails(int uid,String cdno)
    {
        user_id=uid;
        card_number=cdno;
    }

    public  boolean Authenticity() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from carddetails");
            while (rs.next())
            {
                int user_id= rs.getInt(1);
                String cdn=rs.getString(3);
                if (cdn.compareTo(card_number)==0 && user_id==user_id)
                {
                    stmt.close();
                    con.close();
                    return true;
                }
            }
            stmt.close();
            con.close();
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }

    public  void display(int uid)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from carddetails");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next())
            {
                if(rs.getInt(1)==uid)
                {
                    System.out.printf("%20s", rs.getString(2));
                    System.out.printf("%20s", rs.getString(3));
                    System.out.println();//Move to the next line to print the next row.
                }
            }
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }






    public  void INSERT() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO carddetails VALUES(" + user_id + "," + "'" + name + "'" + "," + "'" + card_number + "'" + "," + "'" + expiry + "'" + "," +"'"+cvv+"'" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Sorry an error occurred from our side");
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}