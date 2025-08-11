public class Product {
    private int code;
    private String name;
    private int quantity;
    private double price;

    public Product(int code, String name, int quantity, double price) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getCode() { return code; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Setters
    public void setCode(int code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    // Helper: total value of this product
    public double getTotalValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return String.format("%-6d  %-25s  %-10d  %-10.2f", code, name, quantity, price);
    }
}
