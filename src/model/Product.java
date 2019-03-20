package model;

public class Product {
    private double price;
    private String name;
    private ProductType productType;
    private double discount;

    public Product(String name, ProductType productType, double price, double discount) {
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() { return price; }

    public ProductType getProductType() { return productType; }

    public double getDiscount() { return discount; }

    public boolean isLessThan(double amount) {
        return this.price < amount;
    }

    public boolean isMoreThan(double amount) {
        return this.price > amount;
    }
}

