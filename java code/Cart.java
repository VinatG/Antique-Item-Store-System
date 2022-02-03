package MyProject.java;
import java.sql.*;
public class Cart
{


    public static void display(int userid)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cart WHERE USER_ID="+userid);
            System.out.printf("%20s%20s","ProductName","Quantity");
            System.out.println();
            while (rs.next())
            {
                ResultSet rs2 = stmt2.executeQuery("select * from product_details WHERE ITEM_ID="+rs.getInt(2));
                rs2.next();
                System.out.printf("%20s%20s",rs2.getString(2),rs.getString(3));
                System.out.println();//Move to the next line to print the next row.
            }
            stmt.close();
            stmt2.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }






    public static boolean append(int uid,int i,int q)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cart");
            int flag=0;
            while(rs.next())
            {
                if(rs.getInt(1)==uid && rs.getInt(2)==i)
                {
                    flag=1;
                    ResultSet r = stmt2.executeQuery("select * from product_details");
                    while(r.next())
                    {
                        if(r.getInt(1)==i)
                            break;
                    }
                    if((q+rs.getInt(3))>r.getInt(4))
                    {
                        System.out.println("Sorry we dont have this amount of items with us.");
                        return false;
                    }
                    else
                        {
                            int qty=q+rs.getInt(3);
                            String query="UPDATE cart set QTY="+qty+" WHERE USER_ID="+uid+" and ITEM_ID="+i;
                            stmt2.executeUpdate(query);
                            return true;
                        }
                }

            }
            if(flag==0)
            {
                String sql = "INSERT INTO cart VALUES("+uid+","+i+","+q+")";
                stmt2.executeUpdate(sql);
                return true;
            }
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return true;
    }
    public static boolean remove(int uid,int i,int q)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cart");
            while(rs.next())
            {
                if(rs.getInt(1)==uid && rs.getInt(2)==i)
                {
                    if((rs.getInt(3)-q)>=0)
                    {
                        int qty=rs.getInt(3)-q;
                        String query="UPDATE cart set QTY="+qty+" WHERE USER_ID="+uid+" and ITEM_ID="+i;
                        stmt.executeUpdate(query);
                        return true;
                    }
                    else
                    {
                        System.out.println("You can't make the quantity of item negative");
                        return false;
                    }
                }
                else
                {
                    System.out.println("Item mentioned not present in your cart");
                    return false;
                }
            }
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
}
