import java.util.ArrayList;

public class Staff {
    static int id = 0;
    static ArrayList<Integer> last = new ArrayList<>(); // use any availabe id after employee removal
    static ArrayList<Integer> uid = new ArrayList<>(); // unique id for employees with same name
    static ArrayList<String> name = new ArrayList<>();
    static ArrayList<String> age = new ArrayList<>();
    static ArrayList<String> addr = new ArrayList<>();
    static ArrayList<String> pos = new ArrayList<>(); // <waiter,cook,etc>

    // offsets for table formatting
    static int mxN = -1;
    static final int mxA = 10;
    static int mxP = -1;
    static final int mxU = 10;

    // # name , age , addr , pos
    static void addStaff(String arr[]) {
        if (last.isEmpty())
            uid.add(id++);
        else {
            uid.add(last.get(0));
            last.remove(0);
        }

        // update offsets
        if (arr[0].length() + 10 > mxN)
            mxN = arr[0].length() + 10;
        if (arr[3].length() + 10 > mxP)
            mxP = arr[0].length() + 10;

        // add staff
        name.add(arr[0]);
        age.add(arr[1]);
        addr.add(arr[2]);
        pos.add(arr[3]);
    }

    static void removeStaff() {
        if (uid.isEmpty()) {
            System.out.println("\nNo staff ! [-_-]\n");
            return;
        }
        viewStaff();
        System.out.println("\nUse the above data for reference\n");
        int i = askUID();

        if (i == -1) {
            System.out.println("\nCouldn't remove staff!");
            return;
        }

        last.add(i);
        uid.remove(i);
        name.remove(i);
        age.remove(i);
        addr.remove(i);
        pos.remove(i);

        System.out.println("\nRemoved staff.");
    }

    static void viewStaff() {
        if (uid.isEmpty()) {
            System.out.println("\nNo staff ! [-_-]\n");
            return;
        }
        System.out.println("\n\n" + "-".repeat(mxU + mxN + mxA));
        System.out.println(String.format("%-" + mxU + "s%-" + mxN + "s%-"
                + mxA + "s", "UID", "NAME", "AGE"));
        System.out.println("-".repeat(mxU + mxN + mxA));

        for (int i = 0; i < uid.size(); i++)
            System.out.println(String.format("%-" + mxU + "s%-" + mxN + "s%-"
                    + mxA + "s", uid.get(i), name.get(i), age.get(i)));

        System.out.println("-".repeat(mxU + mxN + mxA) + "\n\n");
    }

    static void fullStaffInfo() {
        if (uid.isEmpty()) {
            System.out.println("\nNo staff ! [-_-]\n");
            return;
        }
        viewStaff();
        System.out.println("\nUse the above data for reference\n");

        int i = askUID();

        if (i == -1) {
            System.out.println("\nCouldn't get staff info!");
            return;
        }
        System.out.println("\nUID : " + uid.get(i) + "\n");
        System.out.println("\nNAME : " + name.get(i) + "\n");
        System.out.println("\nAGE : " + age.get(i) + "\n");
        System.out.println("\nADDRESS : " + addr.get(i) + "\n");
        System.out.println("\nPOSITION : " + pos.get(i) + "\n");
    }

    static void changePosition() {
        viewStaff();
        System.out.println("\nUse the above data for reference\n");

        int i = askUID();

        if (i == -1) {
            System.out.println("\nCouldn't change staff position");
            return;
        }

        System.out.print("\nEnter new staff position of " + name.get(i) + " : ");
        pos.set(i, Main.in.nextLine());

    }

    static int askUID() {
        int i = -1;
        while (true) {
            try {
                System.out.print("\nEnter uid :");
                i = Integer.parseInt(Main.in.nextLine());

                if (i >= uid.size() || i < 0) {
                    System.out.print("\nInavlid uid! Retry? (y/n) :");
                    char c = Main.in.nextLine().charAt(0);
                    if (c == 'y') {
                        System.out.println("\nRetry...");
                        continue;
                    } else if (c == 'n') {
                        System.out.println("\nOK!");
                        return -1;
                    } else {
                        System.out.println("\nError! Please try again");
                        continue;
                    }
                }

                break;
            } catch (Exception e) {
                System.out.println("\nError ! Please try again!");
            }
        }
        return i;

    }

    // # name , age , addr , pos
    static String[] askInfo() {
        String[] arr = new String[4];

        while (true) {
            try {
                System.out.print("\nEnter name :");
                arr[0] = Main.in.nextLine();

                while (true) {
                    try {
                        System.out.print("\nEnter age :");
                        arr[1] = Main.in.nextLine();
                        // Will trigger catch block if illegal(age ∉ Z) value is entered
                        if (Integer.parseInt(arr[1]) < 18) {
                            System.out.println("\n\nSay \"NO\" to child labour.\n");
                            continue;
                        }
                        if (Integer.parseInt(arr[1]) > 90) {
                            System.out.println("\nInvalid age!\n");
                            continue;
                        }

                    } catch (Exception e) {
                        System.out.println("\nError ! Please try again.");
                    }

                    break;
                }

                System.out.print("\nEnter Address : ");
                arr[2] = Main.in.nextLine();

                System.out.print("\nEnter Position : ");
                arr[3] = Main.in.nextLine();

                break;
            } catch (Exception e) {
                System.out.println("\nError ! Please try again.");
                continue;
            }

        }

        return arr;
    }
}
