import java.util.ArrayList;
import java.util.Scanner;

class Product {

    int id;
    String name;
    String category;
    double price;
    int quantity;

    // Constructor
    Product(int id, String name,
            String category,
            double price,
            int quantity) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Display Method
    void display() {

        System.out.println(
                id + "\t" +
                name + "\t" +
                category + "\t" +
                price + "\t" +
                quantity
        );
    }
}

public class CafeShopManagement {

    static ArrayList<Product> products =
            new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    // ADD PRODUCT
    static void addProduct() {

        System.out.println("\n--- ADD PRODUCT ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        Product p =
                new Product(id, name, category,
                        price, quantity);

        products.add(p);

        System.out.println("Product Added Successfully!");
    }

    // VIEW PRODUCTS
    static void viewProducts() {

        System.out.println("\n--- PRODUCT LIST ---");

        if(products.isEmpty()) {

            System.out.println("No Products Available!");
            return;
        }

        System.out.println(
                "ID\tNAME\tCATEGORY\tPRICE\tQUANTITY");

        for(Product p : products) {

            p.display();
        }
    }

    // UPDATE PRODUCT
    static void updateProduct() {

        System.out.println("\n--- UPDATE PRODUCT ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for(Product p : products) {

            if(p.id == id) {

                sc.nextLine();

                System.out.print("Enter New Name: ");
                p.name = sc.nextLine();

                System.out.print("Enter New Category: ");
                p.category = sc.nextLine();

                System.out.print("Enter New Price: ");
                p.price = sc.nextDouble();

                System.out.print("Enter New Quantity: ");
                p.quantity = sc.nextInt();

                System.out.println(
                        "Product Updated Successfully!");

                return;
            }
        }

        System.out.println("Product Not Found!");
    }

    // DELETE PRODUCT
    static void deleteProduct() {

        System.out.println("\n--- DELETE PRODUCT ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for(Product p : products) {

            if(p.id == id) {

                products.remove(p);

                System.out.println(
                        "Product Deleted Successfully!");

                return;
            }
        }

        System.out.println("Product Not Found!");
    }

    // SEARCH PRODUCT
    static void searchProduct() {

        System.out.println("\n--- SEARCH PRODUCT ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for(Product p : products) {

            if(p.id == id) {

                System.out.println(
                        "ID\tNAME\tCATEGORY\tPRICE\tQUANTITY");

                p.display();

                return;
            }
        }

        System.out.println("Product Not Found!");
    }

    // MAIN METHOD
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println(" CAFE SHOP MANAGEMENT SYSTEM ");
            System.out.println("=================================");

            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    viewProducts();
                    break;

                case 3:
                    updateProduct();
                    break;

                case 4:
                    deleteProduct();
                    break;

                case 5:
                    searchProduct();
                    break;

                case 6:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while(choice != 6);
    }
}