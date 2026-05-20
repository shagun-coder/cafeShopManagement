public class Main {

    public static void main(String[] args) {

        Cafe cafe = new Cafe();
        cafe.loadFromFile(); // ✔ LOAD DATA AT START

        int choice;

        do {
            System.out.println("\n===== CAFE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = cafe.sc.nextInt();

            switch (choice) {
                case 1 -> cafe.addItem();
                case 2 -> cafe.viewItems();
                case 3 -> cafe.updateItem();
                case 4 -> cafe.deleteItem();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid Choice");
            }

        } while (choice != 5);

        cafe.closeScanner();
    }
}