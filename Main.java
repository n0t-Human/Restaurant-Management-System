import java.util.Scanner;

class Main {

  final static Scanner in = new Scanner(System.in);

  public static void main(String args[]) {
    if (Login.success()) {
      System.out.println("Login success");
      Admin.setRname();
      Admin.adminPanel();
    } else {
      System.out.println("Terminating program...");
    }

  }
}
