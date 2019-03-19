package model;

public class Product {
    private double price;
    private String name;
    private ProductType productType;

    public Product(String name, ProductType productType, double price) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() { return price; }

    public boolean isLessThan(double amount) {
        return this.price < amount;
    }

    public boolean isMoreThan(double amount) {
        return this.price > amount;
    }
}

enum ProductType {
    Shoe
}
