package sql.model;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private int categoryId;

    public Product(int productId, String productName, double price, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
