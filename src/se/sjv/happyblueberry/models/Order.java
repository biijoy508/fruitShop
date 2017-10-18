package se.sjv.happyblueberry.models;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int orderCounter;

    private String kund;
    private List<OrderItem> orderLista;
    private double totalKostnad;
    private int ordernummer;

    public Order() {
        ordernummer = orderCounter++;
        totalKostnad = 0;
        orderLista = new ArrayList<>();
    }

    public void add(final OrderItem orderItem) {
        totalKostnad += orderItem.getCost();
        orderLista.add(orderItem);
    }

    public OrderItem get(final int index) {
        return orderLista.get(index);
    }

    public void remove(final int index) {
        OrderItem item = orderLista.remove(index);
        totalKostnad -= item.getCost();
    }

    public int getOrderSize() {
        return orderLista.size();
    }

    public double getTotalKostnad() {
        return totalKostnad;
    }

    public void setKund(final String kund) {
        this.kund = kund;
    }

    public String getKund() {
        return kund;
    }

    public int getOrdernummer() {
        return ordernummer;
    }

    public static void setOrderCounter(final int count) {
        orderCounter = count;
    }

    public static int getOrderCounter() {
        return orderCounter;
    }
}
