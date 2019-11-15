package bg.sofia.uni.fmi.mjt.virtualwallet.core.card;

public class GoldenCard extends Card {

    public GoldenCard(String name) {
        super(name);
    }

    @Override
    public boolean executePayment(double cost) {
        if (cost > getAmount() || cost < 0) {
            return false;
        } else {
            setAmount(getAmount() - cost * 0.85);
        }
        return true;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getAmount() {
        return super.getAmount();
    }

    @Override
    public void setAmount(double amount) {
        super.setAmount(amount);
    }
}
