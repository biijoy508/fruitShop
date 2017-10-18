package se.sjv.happyblueberry.presenter;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import se.sjv.happyblueberry.models.Order;
import se.sjv.happyblueberry.models.OrderItem;
import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.Lager;
import se.sjv.happyblueberry.util.FileUtil;

public class HanteraOrder {

    private static final int PLACE_ORDER = 2;
    private static final int EXIT = 0;

    private Order order;
    private Lager lager;

    public HanteraOrder() {
        lager = Lager.getInstance();
        order = new Order();
    }

    public void skapaNyOrder() {

        int input;
        // Stänger inte scanner för att då stängs System.in så att det inte längre går att använda Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dessa varor finns för beställning: ");

        do {
            Vara vara = promptForVara(scanner);
            int antal = promptForAntal(scanner, vara);

            OrderItem orderitem = new OrderItem(vara, antal);
            order.add(orderitem);

            System.out.println("Skriv in 1 för att beställa mer");
            System.out.println("Skriv in 2 för att lägga beställning");
            System.out.println("Skriv in 0 för att avbryta");
            input = scanner.nextInt();

        } while (input != EXIT && input != PLACE_ORDER);

        if (input == PLACE_ORDER) {
            System.out.println("Skriv in namn");
            String namn = scanner.nextLine();
            order.setKund(namn);
            String[] foljesedel = skrivFoljesedel();
            for (String rad : foljesedel) {
                System.out.print(rad);
            }

            System.out.println("Vill du lägga ordern? Svara ja eller nej");
            String answer = scanner.next();

            if (answer.toLowerCase().startsWith("j")) {
                System.out.println("Tack för din beställning!");
                uppdateraLager();
                try {
                    FileUtil.writeLines(namn + " - " + order.getOrdernummer() + ".txt", foljesedel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return;
        }
        System.out.println("Beställning avbruten");
    }

    private int promptForAntal(final Scanner scanner, final Vara vara) {
        int antal = 0;
        boolean isOk;
        do {
            isOk = true;
            System.out.println("Välj antal ");
            try{
            antal = scanner.nextInt();
            }catch(InputMismatchException e){
              isOk = false;
              continue;
            }
            if (antal <= 0) {
                isOk = false;
                System.out.println("Antalet måste vara större än 0.");
            } else if (antal > vara.getAmount()) {
                isOk = false;
                System.out.println("Kan inte ta mer än vad som finns i lagret.");
            }
        } while (!isOk);

        return antal;
    }

    private Vara promptForVara(final Scanner scanner) {
        Vara vara;
        Lager lager = Lager.getInstance();
        do {
            System.out.println(lager.toString());
            System.out.println("Skriv in den vara du vill beställa: ");
            String valdVara = scanner.next();
            vara = lager.lagerMap.get(valdVara);

            if (vara == null) {
                System.out.println("Kunde inte hitta den angivna vara, försök igen.");
            }
        } while (vara == null);
        return vara;
    }


    private String[] skrivFoljesedel() {
        ArrayList<String> listafil = new ArrayList<String>();
        listafil.add(String.format("Följesedel%n"));
        listafil.add(String.format("Ordernummer: %d%n", order.getOrdernummer()));
        listafil.add(String.format("Namn: %s%n", order.getKund()));
        listafil.add(String.format("Du har beställt: %n"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));

        for (int i = 0; i < order.getOrderSize(); i++) {
            OrderItem item = order.get(i);
            String pris = currencyFormat.format(item.getCostPerItem());
            listafil.add(String.format("%d st %s á %s%n", item.getAmount(), item.getType(), pris));
        }

        listafil.add(String.format("Totalkostnaden är: %s %n", currencyFormat.format(order.getTotalKostnad())));
        return listafil.toArray(new String[0]);
    }

    private void uppdateraLager() {
        for (int i = 0; i < order.getOrderSize(); i++) {
            lager.taVara(order.get(i).getVara(), order.get(i).getAmount());
        }

    }
}
