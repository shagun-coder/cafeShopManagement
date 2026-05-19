public class MenuItem {
    int id;
    String name;
    String category;
    double price;

    public MenuItem(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void display() {
        System.out.println("---------------------------");
        System.out.println("Item ID   : " + id);
        System.out.println("Name      : " + name);
        System.out.println("Category  : " + category);
        System.out.println("Price     : ₹" + price);
    }
}