package MyProject.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill
{
    int SubTotal=0;
    int Discount=0;
    int CashBack=0;
    int Total=0;
    Bill(int dis,int cb,int uid)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("select * from cart");
            ResultSet rs2= stmt.executeQuery("select * from user WHERE USER_ID="+uid);
            rs2.next();
            int c=rs2.getInt(5);
            while (rs1.next())
            {
                if(rs1.getInt(1)==uid) {
                    SubTotal = SubTotal + (PRODUCT.price[(rs1.getInt(2) - 1)] * rs1.getInt(3));
                }
            }
        Discount=dis;//dis is in %
        CashBack=cb;
        Discount=SubTotal*dis/100;
        Total=SubTotal-(Discount+c);
        System.out.println("USER_ID="+uid);
        System.out.println("SubTotal="+SubTotal);
        System.out.println("Discount %="+dis);
        System.out.println("Discount="+Discount);
        System.out.println("E-Cash in your wallet="+c);
        System.out.println("Cashback="+CashBack);
        System.out.println("Total payable amount="+Total);
        c=CashBack;
        String query="UPDATE  user SET ECASH="+c+"  WHERE USER_ID="+uid;
        stmt.close();
        con.close();
        }
        catch(Exception e)
        {

        }
    }
}
