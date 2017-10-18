package se.sjv.happyblueberry.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import se.sjv.happyblueberry.models.Grossist;
import se.sjv.happyblueberry.models.Order;
import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.Grossister;
import se.sjv.happyblueberry.persistence.Lager;
import se.sjv.happyblueberry.util.FileUtil;

public class MenyHanterare {

    public int dag;
    private Lager lager;

    public static void main(final String[] args) {
        MenyHanterare hanterare = new MenyHanterare();
        hanterare.run();
    }

    public MenyHanterare() {
        dag = 0;
        lager = Lager.getInstance();
        laddaLager();
    }

    public void run() {
        menyLoop();
        sparaLager();
        Grossister.getInstance().sparaGrossister();
    }

    private void menyLoop() {
        System.out.println("HappyBlueberry orderhanteringssystem");
        String input = "";
        Scanner scanner = new Scanner(System.in);

        do {
            skrivUtMeny();
            input = scanner.next();
            hanteraMenyVal(input);
        } while(input.charAt(0) != '0');

        scanner.close();
    }

    private void hanteraMenyVal(final String val) {
        switch (val.charAt(0)) {
            case '1':
                skrivUtSaldo();
                break;
            case '2':
                HanteraOrder order = new HanteraOrder();
                order.skapaNyOrder();
                break;
            case '3':
                skrivUtGrossister();
                break;
            case '4':
                HanteraGrossister grossister = new HanteraGrossister();
                grossister.skapaNyGrossist();
                break;

            case '5':
                HanteraFrukt frukthanterare = new HanteraFrukt();
                break;

            default:
                break;
        }
    }

    private void skrivUtGrossister() {
        System.out.println("Grossister");
        Grossister grossister = Grossister.getInstance();
        for(Grossist grossist : grossister.grossistLista) {
            System.out.println(grossist.toString());
        }
    }

    private void skrivUtSaldo() {
        Lager lager = Lager.getInstance();
        System.out.println("I lagret finns följande");
        System.out.print(lager.toString());
    }

    private void skrivUtMeny() {
        System.out.println();
        System.out.println("1. Kontrollera lagersaldo");
        System.out.println("2. Lägg till beställning");
        System.out.println("3. Lista grossister");
        System.out.println("4. Lägg till grossist");
        System.out.println("5. Lägg till frukt");
        System.out.println("0. Avsluta");
    }

    private void laddaLager() {
        try {
            String[] inputList = FileUtil.readLines("inventarie.txt");
            dag = Integer.parseInt(inputList[0].trim());
            Order.setOrderCounter(Integer.parseInt(inputList[1].trim()));
            for (int i = 2; i < inputList.length; ++i) {
                if (inputList[i].length() > 0) {
                    Vara vara = extractVara(inputList[i]);
                    lager.lagerMap.put(vara.getType(), vara);
                }
            }
        } catch (Exception e) {
            System.out.println("Problem uppstod vid läsning av lager.");
            lager.lagerMap = new HashMap<String, Vara>();
            Order.setOrderCounter(1);
            dag = 0;
        }
    }

    private Vara extractVara(final String input) {
        String[] varaString = input.split(";");
        Vara vara = new Vara();
        vara.setType(varaString[0]);
        vara.setAmount(Integer.parseInt(varaString[1]));
        vara.setPrice(Double.parseDouble(varaString[2]));
        return vara;
    }

    private void sparaLager() {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(String.format("%d%n%d", dag + 1, Order.getOrderCounter()));

        for (Map.Entry<String, Vara> entry: lager.lagerMap.entrySet()) {
            outputs.add(formateraVara(entry.getValue()));
        }

        try {
            FileUtil.writeLines("inventarie.txt", outputs.toArray(new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formateraVara(final Vara vara) {
        // För att få punkt som decimaltecken används Locale.US. Detta för att Double.parseDouble behöver det.
        String price = String.format(Locale.US, "%.2f", vara.getPrice());
        return String.format("%n%s;%d;%s", vara.getType(), vara.getAmount(), price);
    }

}
