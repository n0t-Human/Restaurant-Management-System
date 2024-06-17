import java.util.ArrayList;

public class Bills {

   static int mxN = -1;
   static ArrayList<Bill> bills = new ArrayList<>();
   static ArrayList<Long> ph = new ArrayList<>(); // * will act as uid for users
   static ArrayList<Integer> lp = new ArrayList<>();

   static class Bill {
      static String uname;
      static long ph;
      static String date;
      static String time;
      static double total;
      static boolean lpa; // ? loyalty pts applied
      static boolean cpa; // ? coupon applied

      static ArrayList<Integer> dishes; // store dish id instead of dish itself
      static ArrayList<Integer> qty;
      static ArrayList<Double> value;

      public Bill(String un, long p, String dt, String t, double ttl, boolean lpu, boolean cpu, ArrayList<Integer> dish,
            ArrayList<Integer> qnty, ArrayList<Double> values) {
         uname = un;
         ph = p;
         date = dt;
         time = t;
         total = ttl;
         lpa = lpu;
         cpa = cpu;
         dishes = new ArrayList<>(dish); // (ArrayList<Integer>)dish.clone();
         qty = new ArrayList<>(qnty); // (ArrayList<Integer>)qnty.clone();
         value = new ArrayList<>(values);// (ArrayList<Double>)values.clone();

         if (un.length() + 10 > mxN)
            mxN = un.length() + 10;
      }

      static void fullBill() {
         int padding = ((Menu.mxD + Menu.mxP + 40) - Admin.Rname.length()) >> 1;
         System.out.println("\n\n" + "-".repeat(Menu.mxD + 50));
         System.out.println(String.format("%" + padding + "s", Admin.Rname));
         System.out.println("Name : " + uname);
         System.out.println("Phone Number : " + ph);
         System.out.println("Date : " + date);
         System.out.println("Time : " + time);
         System.out.println("Loyalty points : " + lp.get(Bills.ph.indexOf(ph)));

         System.out.println("\n" + "-".repeat(Menu.mxD + 40));
         System.out.println(String.format("%-10s%-" + Menu.mxD + "s%-" + Menu.mxP + "s%-10s%-20s", "SL", "DISH",
               "PRICE", "QTY", "VALUE"));
         System.out.println("-".repeat(Menu.mxD + 50));

         // System.out.println("ds :" + dishes.size()); // ! DEBUGGING

         for (int i = 0; i < dishes.size(); i++)
            System.out.println(String.format("%-10s%-" + Menu.mxD + "s%-" + Menu.mxP + "s%-10s%-20s", (i + 1),
                  Menu.dish.get(dishes.get(i)), Menu.price.get(dishes.get(i)), qty.get(i), value.get(i)));

         System.out.println("-".repeat(Menu.mxD + 50));
         System.out.println("\nTotal : " + total);
         System.out.println("Loyalty points applied :" + ((lpa) ? "YES" : "NO"));
         System.out.println("Coupon code applied :" + ((cpa) ? "YES" : "NO"));
         System.out.println("\n\nTHANK YOU\n\n");
      }

   }

   static void viewBills() {
      if (bills.isEmpty()) {
         System.out.println("\n\nNo bills [-_-]");
         return;
      }
      System.out.println(
            "\n\n" + String.format("%-5s%-" + mxN + "s%-20s%-10s%-10s", "SL", "NAME", "PHONE", "TOTAL", "LOYALTY PTS"));
      System.out.println("-".repeat(mxN + 40));
      for (int i = 0; i < bills.size(); i++)
         System.out.println(String.format("%-5s%-" + mxN + "s%-20s%-10s%-10s", (i + 1), bills.get(i).uname,
               bills.get(i).ph, bills.get(i).total, lp.get(ph.indexOf(bills.get(i).ph))));

      while (true) {
         try {
            System.out.print("Enter sl no. (Enter -1 to exit): ");
            int s = Integer.parseInt(Main.in.nextLine());
            if (s == -1) {
               System.out.println("Exitting...");
            } else if (s < -1 || s == 0 || s > bills.size()) {
               System.out.println("Invalid SL no.Try again.\n");
               continue;
            } else {
               bills.get(s - 1).fullBill();
            }
         } catch (Exception e) {
            System.out.println("Error! Please try again.");
            continue;
         }
      }
   }

}
