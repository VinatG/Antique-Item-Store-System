package MyProject.java;

import java.sql.*;
import java.util.Scanner;

public class User {
    static int UserID;
    static String NAME;
    static String PASSWORD;
    static String EMAIL;
    static int ECASH;
    public User(int i)
    {
        UserID=i;
    }

    public User()
    {
        Scanner sc = new Scanner(System.in);
        UserID=GetUserID();
        NAME=sc.nextLine();
        EMAIL=sc.nextLine();
        PASSWORD=sc.nextLine();
        PASSWORD=MAIN.getMd5(PASSWORD);
        ECASH=200;
    }


    public User(String E,String P)
    {
        EMAIL=E;
        PASSWORD=P;
        UserID=GenerateId();
    }

    public static boolean Authenticity() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next())
            {
                String s=rs.getString(3);
                if (s.compareTo(EMAIL)==1)
                {
                    stmt.close();
                    con.close();
                    return false;
                }
            }
            UserID = columnsNumber;
            stmt.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
                return true;
        }
    }
        public static void INSERT() {
            Connection conn = null;
            Statement stmt = null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
                stmt = conn.createStatement();
                String sql = "INSERT INTO user VALUES("+UserID+","+"'"+NAME+"'"+","+"'"+EMAIL+"'"+","+"'"+PASSWORD+"'"+","+ECASH+")";
                stmt.executeUpdate(sql);
            }catch(SQLException se){
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        conn.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(conn!=null)
                        conn.close();
                }
                catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println("Sorry an error occurred from our side");
                }
                catch(SQLException se){
                    se.printStackTrace();
                }
            }
        }












    public static int GetUserID() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from user");
            rs.next();
            int count = rs.getInt(1);
            return (count+ 1);
        }
        catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }


    public static int GenerateId()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(MAIN.URL,MAIN.username,MAIN.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next())
            {
                String s=rs.getString(3);
                if (s.compareTo(EMAIL)==0)
                {
                  if(rs.getString(4).compareTo(PASSWORD)==0)
                  {
                      UserID = rs.getInt(1);
                      stmt.close();
                      con.close();
                      return UserID;
                  }
                  else
                      {
                          stmt.close();
                          con.close();
                          return 0;
                      }
                }
            }
            stmt.close();
            con.close();
            return 0;
        } catch (Exception e) {
            System.out.println(e);
                return 1;
        }

    }

    public void display()
    {
        System.out.println(UserID+" "+NAME + " " + EMAIL + " " + PASSWORD);
    }
}


