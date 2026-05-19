

import java.util.ArrayList;
import java.util.Scanner;

public class CafeManagementSystem {
    private static ArrayList<MenuItem> menuList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Adding a few dummy items initially so the system isn't empty on startup
        menuList.add(new MenuItem(101, "Espresso", "Coffee", 120.0));
        menuList.add(new MenuItem(102, "Blueberry Muffin", "Dessert", 150.0));
        menuList.add(new MenuItem(103, "Green Tea", "Tea", 90.0));

        int choice;

        do {
            System.out.println("\n===== CAFE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Menu Item (Create)");
            System.out.println("2. View All Menu Items (Read)");
            System.out.println("3. Update Menu Item (Update)");
            System.out.println("4. Delete Menu Item (Delete)");
            System.out.println("5. Search Menu Item (Bonus Feature)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            // Input validation for integer choice
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number between 1 and 6: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    displayRecords();
                    break;
                case 3:
                    modifyRecord();
                    break;
                case 4:
                    removeRecord();
                    break;
                case 5:
                    searchRecord();
                    break;
                case 6:
                    System.out.println("Exiting application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 6);
    }

    // 1. CREATE -> Add Record
    private static void addRecord() {
        System.out.println("\n--- Add New Menu Item ---");
        System.out.print("Enter Item ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        // Check if ID already exists
        if (findItemById(id) != null) {
            System.out.println("Error: An item with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Price: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid price! Enter a numeric value: ");
            scanner.next();
        }
        double price = scanner.nextDouble();

        MenuItem newItem = new MenuItem(id, name, category, price);
        menuList.add(newItem);
        System.out.println("Success: Item added successfully!");
    }

    // 2. READ -> Display Records
    private static void displayRecords() {
        System.out.println("\n--- Current Cafe Menu ---");
        if (menuList.isEmpty()) {
            System.out.println("The menu is currently empty.");
            return;
        }
        for (MenuItem item : menuList) {
            System.out.println(item);
        }
    }

    // 3. UPDATE -> Modify Record
    private static void modifyRecord() {
        System.out.println("\n--- Update Menu Item ---");
        System.out.print("Enter the ID of the item to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        MenuItem item = findItemById(id);

        if (item == null) {
            System.out.println("Error: Item with ID " + id + " not found.");
            return;
        }

        System.out.println("Current Details: " + item);
        System.out.print("Enter New Name (Leave blank to keep unchanged): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            item.setName(newName);
        }

        System.out.print("Enter New Category (Leave blank to keep unchanged): ");
        String newCategory = scanner.nextLine();
        if (!newCategory.trim().isEmpty()) {
            item.setCategory(newCategory);
        }

        System.out.print("Enter New Price (Enter -1 to keep unchanged): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Enter a valid price or -1: ");
            scanner.next();
        }
        double newPrice = scanner.nextDouble();
        if (newPrice != -1) {
            item.setPrice(newPrice);
        }

        System.out.println("Success: Item updated successfully!");
    }

    // 4. DELETE -> Remove Record
    private static void removeRecord() {
        System.out.println("\n--- Delete Menu Item ---");
        System.out.print("Enter the ID of the item to remove: ");
        int id = scanner.nextInt();

        MenuItem item = findItemById(id);

        if (item == null) {
            System.out.println("Error: Item with ID " + id + " not found.");
            return;
        }

        menuList.remove(item);
        System.out.println("Success: Item '" + item.getName() + "' has been removed.");
    }

    // 5. BONUS -> Search Functionality
    private static void searchRecord() {
        System.out.println("\n--- Search Menu ---");
        System.out.print("Enter keyword to search by item name: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (MenuItem item : menuList) {
            if (item.getName().toLowerCase().contains(keyword)) {
                System.out.println(item);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items matched your search query.");
        }
    }

    // Helper Method to look up an object by its ID
    private static MenuItem findItemById(int id) {
        for (MenuItem item : menuList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}