import java.util.ArrayList;

public class Menu {
    static ArrayList<String> coupon = new ArrayList<>();
    static ArrayList<Integer> coupon_disc = new ArrayList<>();

    static ArrayList<String> dish = new ArrayList<>();
    static ArrayList<Double> price = new ArrayList<>();

    static final int mxI = 10;
    static int mxD = -1;
    static int mxP = -1;

    static void addCoupon() {
        String s = "";
        int d = -1;

        while (true) {
            System.out.println("Coupon length must not exceed 10 and may contain only letters and digits.");
            System.out.print("Enter coupon code to be added :");
            s = Main.in.nextLine();
            s = s.toLowerCase();
            if (s.length() > 10) {
                System.out.println("Coupon length exceeded 10! Enter again.");
                continue;
            } else if (coupon.contains(s)) {
                System.out.println("Coupon already present!");
                continue;
            }

            for (int i = 0; i < s.length(); i++)
                if (!Character.isLetterOrDigit(s.charAt(i))) {
                    System.out.println("Coupon code contains illegal characters! Enter again.");
                    continue;
                }
            while (true) {
                try {
                    System.out.print("Enter coupon discount percentage [Integer] :");
                    d = Integer.parseInt(Main.in.nextLine());

                    if (d <= 0 || d > 100) { // 100% discount intentionally skipped
                        System.out.println("Invalid discount percentage! Try again.");
                        continue;
                    }

                    break;
                } catch (Exception e) {
                    System.out.println("Error! Please try again.");
                    continue;
                }
            }

            Menu.coupon.add(s.toLowerCase());
            Menu.coupon_disc.add(d);

            break;

        }
    }

    static void addDish() {
        String d = "";
        Double p = 0.0;
        while (true) {
            try {
                System.out.print("Enter dish name :");
                d = Main.in.nextLine();

                if (dish.contains(d)) {
                    System.out.println("Dish already present.Enter a new one!");
                    continue;
                }

                System.out.print("Enter price : ");
                p = Double.parseDouble(Main.in.nextLine());

                if (p <= 0.0) {
                    System.out.println("Invalid price!");
                    continue;
                }

                if (d.length() + 10 > mxD) {
                    mxD = d.length() + 10;
                }

                if (Double.toString(p).length() + 10 > mxP) {
                    mxP = Double.toString(p).length() + 10;
                }

                dish.add(d);
                price.add(p);

                break;
            } catch (Exception e) {
                // System.out.println(e.getMessage());
                System.out.println("Error! Try again");
                continue;
            }

        }
    }

    static void removeDish() {
        viewDishes();
        if (dish.isEmpty()) {
            return;
        }
        System.out.println("\nUse the above data for reference.\n");

        while (true) {
            try {
                System.out.print("Enter dish id :");
                int n = Integer.parseInt(Main.in.nextLine());

                if (n < 0 || n >= dish.size()) {
                    System.out.println("Invalid ID!");
                    System.out.print("Try again ? (y/n):");
                    char ch = Main.in.nextLine().charAt(0);
                    if (ch == 'y' || ch == 'Y') {
                        continue;
                    } else if (ch == 'n' || ch == 'N') {
                        break;
                    } else {
                        System.out.println("Error! exitting...");
                        break;
                    }
                }

                dish.remove(n);
                price.remove(n);
                break;
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                continue;
            }
        }
    }

    static void viewDishes() {
        if (dish.isEmpty()) {
            System.out.println("\nNo dishes. [-_-]\n");
            return;
        }

        System.out.println("-".repeat(mxI + mxP + mxD));
        System.out.println(String.format("%-" + mxI + "s%-" + mxD + "s%-" + mxP + "s", "ID", "DISH", "PRICE"));
        System.out.println("-".repeat(mxI + mxP + mxD));
        for (int i = 0; i < dish.size(); i++)
            System.out.println(String.format("%-" + mxI + "s%-" + mxD + "s%-" + mxP + "s", Integer.toString(i),
                    dish.get(i), price.get(i)));
        System.out.println("-".repeat(mxI + mxP + mxD));
    }
}
