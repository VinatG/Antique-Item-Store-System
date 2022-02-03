package MyProject.java;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

class MAIN
{
    int uid = 0;//UserID of registered or logged in User.
    static int n=6; //No. of characters in our captcha
    static final String URL="jdbc:mysql://localhost:3306/giraffe";
    static final String username="root";
    static final String password="";
    static String GenerateCaptcha()
    {
        String AlphaNumericString ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++)
        {
            int index=(int)(AlphaNumericString.length() *Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public void MainMenu()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do now?");
        System.out.println("1.Explore our wide range of products 2.Go to your cart 3.Logout 4.Exit");
        int choice = sc.nextInt();
        switch (choice)
        {
            case 1:
                SubMenu1();
                break;
            case 2:
                SubMenu2();
                break;
            case 3:
                SubMenu3();
                break;
            case 4:
                EXIT();
                break;
            default:
                System.out.println("Wrong Input");
                MainMenu();
        }

    }



    public void SubMenu1()
    {
        Scanner sc = new Scanner(System.in);
        PRODUCT.display();
        System.out.println("What would you like to do now?");
        System.out.println("1.Add items to your cart 2.Delete items from cart 3.Checkout 4.View the cart 5.Return to main menu 6.Exit");
        int ch;
        ch = sc.nextInt();
        switch (ch) {
            case 1:
                AddITEMS();
                SubMenu1();
                break;
            case 2:
                DeleteItems();
                SubMenu1();
                break;
            case 3:
                Checkout();
                break;
            case 4:
                System.out.flush();
                Cart.display(uid);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Error at Thread.sleep()");
                }
                SubMenu1();
                break;
            case 5:
                System.out.println("Returning to Main Menu");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Error at Thread.sleep()");
                }
                System.out.flush();
                MainMenu();
                break;
            case 6:EXIT();
                   break;
            default:
                System.out.println("Wrong Input");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Error at Thread.sleep()");
                }
                System.out.flush();
                break;
        }
    }





    public void SubMenu2()
    {
        Scanner sc = new Scanner(System.in);
        Cart.display(uid);
        System.out.println("What would you like to do now?");
        System.out.println("1.Buy 2.Delete some items 3.Checkout 4.Back to main menu ");
        int ch2;
        ch2 = sc.nextInt();
        switch (ch2) {
            case 1:
                AddITEMS();
                SubMenu2();
                break;
            case 2:
                DeleteItems();
                SubMenu2();
                break;
            case 3:
                Checkout();
                break;
            case 4:
                MainMenu();
                break;
            default:
                System.out.println("Wrong Input");
        }
    }







    public void SubMenu3()
    {
        uid=0;
        System.out.println("Logging out...");
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Error at Thread.sleep()");
        }
        System.out.println("Successfully logged out");
        LoginSignup();
    }



    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }














    public void EXIT()
    {
        System.out.println("Exiting...");
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Error at Thread.sleep()");
        }
        System.exit(0);
    }



    public void Checkout()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Would you like to add any promo code? 1.Yes 2.No");
        int chh;
        chh=sc.nextInt();
        Promo promo_ob=new Promo();
        if (chh == 1)
        {
            System.out.println("Enter the promo code");
            String p;
            p=sc.nextLine();
            p=sc.nextLine();
            promo_ob=new Promo(p);
            Promo.PromoCode(promo_ob);
        }
        System.out.println(uid);
        Bill bill=new Bill(promo_ob.discount, promo_ob.cashback ,uid);
        Payment();
        System.out.println("Thank you for shopping with us your package will be delivered by 8th march");
        PastOrders.processTransaction(uid);
        System.out.println("Here is your receipt:");
        System.out.println("What would you like to do now?");
        System.out.println("1.Return to main menu 2.Exit");
        int ch2 = sc.nextInt();
        switch (ch2) {
            case 1:
                MainMenu();
                break;
            case 2:
                EXIT();
                break;
            default:
                System.out.println("Wrong input");
                System.out.println("Directing you to Main Menu..");
                try
                {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Error at Thread.sleep()");
                }
                System.out.flush();
                MainMenu();
        }
    }





    public void Payment()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("How would you like to pay");
        System.out.println("1.COD 2.Card payment");
        int ch2;
        ch2 = sc.nextInt();
        if (ch2 == 2) //Card payment
        {
            System.out.println("What would you like to do?");
            System.out.println("1.Use an old card 2.Add a new card");
            int ch3;
            ch3 = sc.nextInt();
            if (ch3 == 1)
            {
                System.out.println("Enter the card number");
                String cn;
                cn=sc.nextLine();
                CardDetails obj= new CardDetails(uid,cn);
                if(obj.Authenticity())
                {
                    System.out.println("Card found and payment made");
                }
                else
                {
                    System.out.println("Card not found");
                    Payment();
                }
            }
            else if (ch3 == 2)
            {
                System.out.println("Kindly enter the following details 1.Name of card holder 2. Card number 3.CVV 4. Date of expiry");
                CardDetails obj2=new CardDetails();
                if(obj2.Authenticity()!=true)
                {
                    obj2.INSERT();
                    System.out.println("Card added and payment made");
                }
                else
                {
                    System.out.println("Card already present in our database");
                    System.out.println("Would you like to make the payment with this card? 1.YES 2.NO");
                    int choice;
                    choice=sc.nextInt();
                    if(choice==1)
                    {

                    }
                    else
                    {
                        Payment();
                    }
                }
            }
        }
    }




    public void AddITEMS()
    {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the ItemID followed by Quantity");
        System.out.println("Press 0 when you are done");
        Cart cart_obj = new Cart();
        int i = 0, q = 0;
        do {
            i = sc.nextInt();
            q = sc.nextInt();
            if (i != 0) {
                if (cart_obj.append(uid, i, q)) {
                    System.out.println("Item added to cart");
                } else {
                    System.out.println("Wrong credentials entered");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Error at Thread.sleep()");
                    }
                    System.out.flush();
                }
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Error at Thread.sleep()");
                }
                System.out.flush();
                break;
            }
        } while (i != 0);
    }





    public void DeleteItems()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the ItemID followed by Quantity");
        System.out.println("Press 0 when you are done");
        Cart cart_obj2 = new Cart();
        int i2 = 0, q2 = 0;
        do {
            i2 = sc.nextInt();
            q2 = sc.nextInt();
            if (i2 != 0) {
                if (cart_obj2.remove(uid, i2, q2)) {
                    System.out.println("Item and mentioned quantity removed from cart");
                } else {
                    System.out.println("Wrong credentials entered");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Error at Thread.sleep()");
                    }
                    System.out.flush();
                }
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Error at Thread.sleep()");
                }
                System.out.flush();
            }
        } while (i2 != 0);
    }





    public void LoginSignup()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Login 2.Signup");
        String Email;
        String Password;
        String Captcha;
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter the emailID and password");
                Captcha = GenerateCaptcha();
                System.out.println("Enter the captch:" + Captcha);
                Email = sc.nextLine();
                Email = sc.nextLine();
                Password = sc.nextLine();
                Password=getMd5(Password);
                User ob = new User(Email, Password);
                String input;
                input = sc.nextLine();
                if (ob.UserID == 0) {
                    System.out.println("Wrong EmailID or Password entered");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Error at Thread.sleep()");
                    }
                    System.out.flush();
                    System.out.println("What would you like to do now?");
                    break;
                } else if (input.compareTo(Captcha) != 0) {
                    System.out.println("Wrong Captcha Entered");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Error at Thread.sleep()");
                    }
                    System.out.flush();
                    break;
                } else
                    System.out.println("Successful login");
                uid = ob.UserID;
                break;
            case 2:
                System.out.println("Enter the name email and password");
                User ob2 = new User();
                ob2.INSERT();
                uid = ob2.UserID;
                if (ob2.Authenticity()) {
                    System.out.println("Congratulations Account Created");
                    System.out.println("As an award Rs200 has been added to you E-Cash");
                } else {
                    System.out.println("Email ID already in use");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Error at Thread.sleep()");
                    }
                    System.out.flush();
                    break;
                }
                break;
            default:
                System.out.println("Wrong Input");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Error at Thread.sleep()");
                }
                System.out.flush();
                break;
        }

    }







    public  static void main(String[] args)
    {
        PRODUCT.initialize();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to our Antique store");
        System.out.println("We are currently working without a GUI so press the integers accordingly");
        MAIN obj=new MAIN();
        obj.LoginSignup();
        obj.MainMenu();

        }

}