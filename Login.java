import java.util.Scanner;

public class Login {

    final static Scanner in = new Scanner(System.in);

    static class Admin {
        private static String uname = "admin";
        private static String pass = "admin";
    }

    static class UserName {
        private static boolean sanityChecks(String s) {

            char c = '\u0000';

            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (!Character.isLetterOrDigit(c))
                    return false;
            }
            return true;
        }

        static void setUserName() {
            String s = "";
            if (verified()) {
                while (true) {
                    try {
                        System.out.print("Enter new user name : ");
                        s = in.next() + in.nextLine();

                        if (!UserName.sanityChecks(s)) {
                            System.out.println(
                                    "[WARNING] : Username may only contain alphanumeric characters or numbers.");
                            continue;
                        }

                        Admin.uname = s;
                        break;
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("Error! Please try again!");
                        continue;
                    }
                }
            }
        }

        static String getUsername() {
            return Admin.uname;
        }
    }

    static class Password {

        private static boolean sanityChecks(String s) {

            char c = '\u0000';

            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (Character.isWhitespace(c))
                    return false;
            }

            return true;
        }

        static void setPassword() {
            String s = "";
            if (verified()) {
                while (true) {
                    try {
                        System.out.print("Enter new password : ");
                        s = in.next() + in.nextLine();

                        if (!UserName.sanityChecks(s)) {
                            System.out.println(
                                    "[WARNING] : Password may only contain alphanumeric characters , numbers or special characters.");
                            continue;
                        }

                        Admin.pass = s;
                        break;
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("Error! Please try again!");
                        continue;
                    }
                }

            }
        }
    }

    static boolean verified() {
        String pass = "";
        while (true) {
            try {

                System.out.print("Enter password :");
                pass = in.next() + in.nextLine();

                if (!Admin.pass.equals(pass)) {
                    System.out.println("Wrong password !");
                    if (!Password.sanityChecks(pass))
                        System.out.println(
                                "[WARNING] : Password may only contain alphanumeric characters , numbers or special characters.");

                    System.out.print("Retry (y/n) ? : ");
                    char c = in.nextLine().charAt(0);
                    if (c == 'y')
                        continue;
                    else if (c == 'n')
                        break;
                    else {
                        System.out.println("Error ! Please try again");
                        continue;
                    }
                } else {
                    return true;
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Error! Please try again!");
                continue;
            }
        }
        return false;
    }

    static boolean success() {
        String uname = "";
        String pass = "";
        while (true) {
            try {

                System.out.print("Enter admin unsername [default:admin]:");
                uname = in.next() + in.nextLine();
                System.out.print("Enter password [default:admin]:");
                pass = in.next() + in.nextLine();

                if (!Admin.uname.equals(uname)) {
                    System.out.println("Wrong user name !");
                    if (!UserName.sanityChecks(uname))
                        System.out.println("[WARNING] : Username may only contain alphanumeric characters or numbers.");
                }

                if (!Admin.pass.equals(pass)) {
                    System.out.println("Wrong password !");
                    if (!Password.sanityChecks(pass))
                        System.out.println(
                                "[WARNING] : Password may only contain alphanumeric characters , numbers or special characters.");

                }

                if (!Admin.uname.equals(uname) || !Admin.pass.equals(pass)) {
                    System.out.print("Retry (y/n) ? : ");
                    char c = in.nextLine().charAt(0);
                    if (c == 'y')
                        continue;
                    else if (c == 'n')
                        break;
                    else {
                        System.out.println("Error ! Please try again");
                        continue;
                    }

                }

                if (Admin.uname.equals(uname) && Admin.pass.equals(pass)) {
                    return true;
                }
                break;
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Error ! Please try again");
                continue;
            }

        }

        return false;
    }

}
