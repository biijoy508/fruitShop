package se.sjv.happyblueberry.models;

public class OrderItem {

    private Vara vara;
    private int amount;

    public OrderItem(final Vara vara, final int amount) {
        this.vara = vara;
        this.amount = amount;
    }

    public void setVara(final Vara vara) {
        this.vara = vara;
    }

    public Vara getVara() {
        return vara;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return vara.getType();
    }

    public double getCostPerItem() {
        return vara.getPrice();
    }

    public double getCost() {
        return amount * vara.getPrice();
    }
}
