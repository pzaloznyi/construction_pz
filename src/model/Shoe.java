package model;

public class Shoe extends Product {
    private ShoeType type;

    public Shoe(String name, ShoeType type, double price, double discount) {
        super(name, ProductType.Shoe, price, discount);

        this.type = type;
    }

    public ShoeType getType() { return type; }
}

