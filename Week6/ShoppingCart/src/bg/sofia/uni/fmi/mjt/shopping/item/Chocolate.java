package bg.sofia.uni.fmi.mjt.shopping.item;

import java.util.Objects;

public class Chocolate implements Item {

    private String name = "";
    private String description = "";
    private double price = 0;

    public Chocolate(String name, String desc, double price) {
        if (name == null || desc == null || price <= 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.description = desc;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chocolate)) return false;
        Chocolate chocolate = (Chocolate) o;
        return Double.compare(chocolate.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), chocolate.getName()) &&
                Objects.equals(getDescription(), chocolate.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPrice());
    }
}