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
        System.out.println(id + " | " + name + " | " + category + " | ₹" + price);
    }
}