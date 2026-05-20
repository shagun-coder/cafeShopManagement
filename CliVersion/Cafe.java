import java.util.*;
import java.io.*;

public class Cafe {

    ArrayList<MenuItem> menu = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    // LOAD FROM FILE
    public void loadFromFile() {
        try {
            File file = new File("menu.txt");

            if (!file.exists()) return;

            Scanner fs = new Scanner(file);

            while (fs.hasNextLine()) {
                String line = fs.nextLine();
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String category = data[2];
                double price = Double.parseDouble(data[3]);

                menu.add(new MenuItem(id, name, category, price));
            }

            fs.close();
        } catch (Exception e) {
            System.out.println("Error loading file");
        }
    }

    // SAVE TO FILE
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter("menu.txt");

            for (MenuItem m : menu) {
                fw.write(m.id + "," + m.name + "," + m.category + "," + m.price + "\n");
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    // ADD
    public void addItem() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        menu.add(new MenuItem(id, name, category, price));
        saveToFile();

        System.out.println("✔ Item Added");
    }

    // VIEW
    public void viewItems() {
        if (menu.isEmpty()) {
            System.out.println("❌ No Items Found");
            return;
        }

        for (MenuItem m : menu) {
            m.display();
        }
    }

    // UPDATE
    public void updateItem() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (MenuItem m : menu) {
            if (m.id == id) {

                System.out.print("New Name: ");
                m.name = sc.nextLine();

                System.out.print("New Category: ");
                m.category = sc.nextLine();

                System.out.print("New Price: ");
                m.price = sc.nextDouble();

                saveToFile();

                System.out.println("✔ Updated");
                return;
            }
        }

        System.out.println("❌ Not Found");
    }

    // DELETE
    public void deleteItem() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).id == id) {
                menu.remove(i);
                saveToFile();

                System.out.println("✔ Deleted");
                return;
            }
        }

        System.out.println("❌ Not Found");
    }

    public void closeScanner() {
        sc.close();
    }
}