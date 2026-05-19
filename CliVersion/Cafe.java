import java.util.*;
import java.io.*;

public class Cafe {

    ArrayList<MenuItem> menu = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    // LOAD DATA FROM FILE
    public void loadFromFile() {
        try {
            File file = new File("menu.txt");
            if (!file.exists()) return;

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String category = data[2];
                double price = Double.parseDouble(data[3]);

                menu.add(new MenuItem(id, name, category, price));
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading file!");
        }
    }

    // SAVE DATA TO FILE
    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("menu.txt");

            for (MenuItem item : menu) {
                writer.write(item.id + "," +
                             item.name + "," +
                             item.category + "," +
                             item.price + "\n");
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    // ADD ITEM
    public void addItem() {
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        menu.add(new MenuItem(id, name, category, price));
        saveToFile();

        System.out.println("Item Added Successfully!");
    }

    // VIEW ITEMS
    public void viewItems() {
        if (menu.isEmpty()) {
            System.out.println("No Menu Items Found.");
            return;
        }

        for (MenuItem item : menu) {
            item.display();
        }
    }

    // UPDATE ITEM
    public void updateItem() {
        System.out.print("Enter Item ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (MenuItem item : menu) {
            if (item.id == id) {

                System.out.print("Enter New Name: ");
                item.name = sc.nextLine();

                System.out.print("Enter New Category: ");
                item.category = sc.nextLine();

                System.out.print("Enter New Price: ");
                item.price = sc.nextDouble();
                sc.nextLine();

                saveToFile();
                System.out.println("Item Updated Successfully!");
                return;
            }
        }

        System.out.println("Item Not Found.");
    }

    // DELETE ITEM
    public void deleteItem() {
        System.out.print("Enter Item ID to Delete: ");
        int id = sc.nextInt();

        for (MenuItem item : menu) {
            if (item.id == id) {
                menu.remove(item);
                saveToFile();

                System.out.println("Item Deleted Successfully!");
                return;
            }
        }

        System.out.println("Item Not Found.");
    }
}