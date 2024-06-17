import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cashier {

    static void init() {
        while (true) {
            System.out.println("1: Make bills");
            System.out.println("2: Exit");
            System.out.print("\nEnter your choice : ");
            int ch = Integer.parseInt(Main.in.nextLine());

            if (ch == 1) {
                mkBills();
            } else if (ch == 2) {
                if (Login.verified()) {
                    return;
                }
                System.out.println("Verification error!");
                continue;
            } else {
                System.out.println("Try Again!");
                continue;
            }
        }
    }

    private static void mkBills() {
        if (Menu.dish.isEmpty()) {
            System.out.println("Cannot make bill! No dishes available.\nContact admin to add dishes and their prices");
            return;
        }
        String un = "";
        Long p = -1l;
        Double ttl = 0.0;
        boolean lpu = false;
        boolean cpu = false;
        String dt = "";
        String t = "";

        int di = -1;
        int qty = -1;

        ArrayList<Integer> dish = new ArrayList<>();
        ArrayList<Integer> qnty = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        int c = 0; // counter variable
        boolean b = false;

        while (true) {

            while (true) {
                b = false;
                System.out.print("Enter users' name : ");
                un = Main.in.nextLine();
                for (int i = 0; i < un.length(); i++) {
                    if (!Character.isLetter(un.charAt(i))) {
                        System.out.println("Name cannot contain special characters or numbers.");
                        b = true;
                        break;
                    }
                }
                if (b)
                    continue;

                break;

            }

            while (true) {
                try {
                    System.out.print("Enter users' phone number : ");
                    p = Long.parseLong(Main.in.nextLine());
                    if (!(p.toString().length() == 10)) {
                        System.out.println("Phone number length must not exceed or be less than ten!");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error ! Please try again.");
                }

            }

            Menu.viewDishes();
            System.out.println("\n\nUse the above data for reference\n\n");
            while (true) {
                try {
                    System.out.print("Enter dish ID (Enter -1 to Exit): ");
                    di = Integer.parseInt(Main.in.nextLine());
                    if (di == -1) {
                        if (c == 0) {
                            System.out.println("Atleast 1 dish must be added in the bill!");
                            continue;
                        }
                        System.out.println("Exitting...");
                        break;
                    } else if (di >= Menu.dish.size() || di < -1) {
                        System.out.println("Invalid dish ID!");
                        continue;
                    } else {
                        while (true) {
                            try {
                                System.out.print("Enter quantity : ");
                                qty = Integer.parseInt(Main.in.nextLine());
                                if (qty <= 0 || qty > 100000) {
                                    System.out.println("Invalid Quantity!");
                                    continue;
                                }
                                dish.add(di);
                                qnty.add(qty);
                                values.add(Menu.price.get(di) * qty);
                                ttl += Menu.price.get(di) * qty;
                                c++;
                            } catch (Exception e) {
                                System.out.println("Error! Please try again.");
                                continue;
                            }
                            break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error ! Try again.");
                    continue;
                }

            }

            while (true) {
                System.out.print("Enter coupon code (if any) [Enter \"NIL\" if no there is no coupon from user] : ");
                String s = Main.in.nextLine();

                if (Menu.coupon.size() == 0) {
                    break;
                }

                if (Menu.coupon.contains(s.toLowerCase())) {
                    double disc_factor = (1.0 - (Menu.coupon_disc.get(Menu.coupon.indexOf(s)) / 100.0));
                    if (ttl * disc_factor >= 0.0) {
                        ttl *= disc_factor;
                        int idx2 = Menu.coupon.indexOf(s.toLowerCase());
                        Menu.coupon.remove(idx2);
                        Menu.coupon_disc.remove(idx2);
                        cpu = true;
                    } else {
                        System.out.println("Couldn't apply coupon code.");
                    }
                } else if (s.equals("NIL")) {
                    break;
                } else {
                    System.out.println("Invalid coupon code! Please try again.");
                }
                break;
            }

            if (Bills.ph.contains(p)) {
                int idx = Bills.ph.indexOf(p);
                Bills.lp.set(idx, Bills.lp.get(idx) + 100);
                if (Bills.lp.get(idx) >= 1000) {
                    if ((ttl * 0.9) >= 0.0) {
                        ttl *= 0.9; // 10% discount for loyal customers :)
                        Bills.lp.set(idx, Bills.lp.get(idx) - 1000);
                        lpu = true;
                    }
                }
            } else {
                Bills.ph.add(p);
                Bills.lp.add(100);
            }

            dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));

            t = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            Bills.bills.add(new Bills.Bill(un, p, dt, t, ttl, lpu, cpu, dish, qnty, values));

            dish.clear();
            qnty.clear();
            values.clear();
            un = "";
            p = -1l;
            ttl = 0.0;
            lpu = false;
            dt = "";
            t = "";
            cpu = false;

            Bills.bills.get(Bills.bills.size() - 1).fullBill();

            break;
        }
    }

}