package MyProject.java;
import java.sql.*;

public class PRODUCT
{
    static int[] p_id=new int[]{1,2,3,4,5,6,7};
    static int price[]=new int[7];
    public static void initialize()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from product_details");
            int i=0;
            while (rs.next())
            {
                price[i]=rs.getInt(5);
                i++;
            }
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void display()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from product_details");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next())
            {
                for (int i = 1; i <= columnsNumber; i++)
                {
                    System.out.printf("%20s", rs.getString(i));
                }
                System.out.println();//Move to the next line to print the next row.
            }
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
