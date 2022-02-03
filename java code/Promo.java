package MyProject.java;

import java.sql.*;

public class Promo
{
    String promo="";
    int discount=0;
    int cashback=0;
    Promo()
    {

    }
    Promo(String p)
    {
        promo=p;
    }
    public static boolean PromoCode(Promo ob)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from promocodes");
            while (rs.next())
            {
                if(rs.getString(2).compareTo(ob.promo)==0)
                {
                    ob.cashback=rs.getInt(3);
                    ob.discount=rs.getInt(4);
                    stmt.close();
                    con.close();
                    return true;
                }
            }
            stmt.close();
            con.close();
            return false;
        } catch (Exception e) {
                return true;
        }
    }
}
