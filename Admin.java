import java.util.Calendar;
import java.util.GregorianCalendar;

public class Admin {
    static String Rname = null;

    public static void setRname() {
        while (true) {
            System.out.print("Enter name of Restraunt :");
            Rname = Main.in.nextLine();

            for (int i = 0; i < Rname.length(); i++)
                if (!Character.isLetterOrDigit(Rname.charAt(i)))
                    System.out.println("[WARNING] : Restaurant name contains special Chracters.");
            if (Rname.length() < 5)
                System.out.println("[WARNING] : Restaurant name too short!");

            System.out.print("Save restaurant name ? (y/n) :");
            char c = Main.in.nextLine().charAt(0);
            if (c == 'y') {
                System.out.println("Restaurant name saved.");
                break;
            } else if (c == 'n') {
                System.out.println("Retry...");
                continue;
            } else {
                System.out.println("Error! Please try again");
                continue;
            }
        }
    }

    public static void adminPanel() {
        int ch = -1;
        while (true) {
            GregorianCalendar time = new GregorianCalendar();
            int hour = time.get(Calendar.HOUR_OF_DAY);
            if (hour < 12)
                System.out.print("Good morning ! ");
            else if (hour < 17 && !(hour == 12))
                System.out.print("good afternoon ! ");
            else if (hour == 12)
                System.out.print("Good noon ! ");
            else
                System.out.print("Good Evening ");

            System.out.println(Login.UserName.getUsername() + "!");
            System.out.println("\n\n1: Change Restraunt Name.");
            System.out.println("2: Change Admin Username.");
            System.out.println("3: Change Admin Password.");
            System.out.println("4: Add staff.");
            System.out.println("5: Remove Staff.");
            System.out.println("6: View Staff Info [All].");
            System.out.println("7: View Full Staff Info [Single Staff].");
            System.out.println("8: Change position of staff.");
            System.out.println("9: Add coupon code.");
            System.out.println("10: View dishes.");
            System.out.println("11: Add dishes.");
            System.out.println("12: Remove dishes");
            System.out.println("13: Enter cashier mode.");
            System.out.println("14: View Previous bill(s).");
            System.out.println("15: Exit.\n\n");

            try {
                System.out.print("Enter your choice : ");
                ch = Integer.parseInt(Main.in.nextLine());
            } catch (Exception e) {
                System.out.println("Error ! Please try again!");
            }

            switch (ch) {
                case 1:
                    setRname();
                    break;
                case 2:
                    Login.UserName.setUserName();
                    break;
                case 3:
                    Login.Password.setPassword();
                    break;
                case 4:
                    Staff.addStaff(Staff.askInfo());
                    break;
                case 5:
                    Staff.removeStaff();
                    break;
                case 6:
                    Staff.viewStaff();
                    break;
                case 7:
                    Staff.fullStaffInfo();
                    break;
                case 8:
                    Staff.changePosition();
                    break;
                case 9:
                    Menu.addCoupon();
                    break;
                case 10:
                    Menu.viewDishes();
                    break;
                case 11:
                    Menu.addDish();
                    break;
                case 12:
                    Menu.removeDish();
                    break;
                case 13:
                    Cashier.init();
                    break;
                case 14:
                    Bills.viewBills();
                    break;
                case 15:
                    System.out.println("\nTHANK YOU!");
                    return;
                default:
                    System.out.println("Invalid Choice. ");
                    continue;

            }
        }
    }

}
