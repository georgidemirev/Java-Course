package bg.sofia.uni.fmi.mjt.virtualwallet.core.card;

import java.util.Objects;

public abstract class Card {

    private String name;
    private double amount;

    Card(String name) {
        this.name = name;
    }

    public abstract boolean executePayment(double cost);

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(getName(), card.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
