import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Cafe cafe = new Cafe();

     
        int choice;

        do {
            System.out.println("\n===== CAFE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    cafe.addItem();
                    break;
                case 2:
                    cafe.viewItems();
                    break;
                case 3:
                    cafe.updateItem();
                    break;
                case 4:
                    cafe.deleteItem();
                    break;
                case 5:
                    System.out.println("Exiting Program...");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}