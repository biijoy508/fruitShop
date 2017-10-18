package se.sjv.happyblueberry.models;

public class Vara {

    private double price;
    private String type;
    private int amount;


    public Vara(){

    }


    public Vara(final String type){
        this.type = type;
    }

    public void setPrice(final double price) {
        this.price = price;

    }

    public double getPrice() {
        return price;

    }

    public void setType(final String type) {
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
