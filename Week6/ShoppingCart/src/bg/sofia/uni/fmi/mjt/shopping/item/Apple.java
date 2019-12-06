package bg.sofia.uni.fmi.mjt.shopping.item;

import java.util.Objects;

public class Apple implements Item {

    private String name = "";
    private String description = "";
    private double price = 0;

    public Apple(String name, String desc, double price) {
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
        if (!(o instanceof Apple)) return false;
        Apple apple = (Apple) o;
        return Double.compare(apple.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), apple.getName()) &&
                Objects.equals(getDescription(), apple.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPrice());
    }
}