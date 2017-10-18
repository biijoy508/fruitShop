package se.sjv.happyblueberry.presenter;

import java.util.Scanner;

import se.sjv.happyblueberry.models.Grossist;
import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.Grossister;
import se.sjv.happyblueberry.util.GeneralPurposeTools;

public class HanteraGrossister {
    String frukt = "";

    public void skapaNyGrossist() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv in namn");
        String namn = scanner.nextLine();

        Grossist grossist = new Grossist();
        grossist.setNamn(namn);
        do {
            System.out.println("Skriv in de frukter som säljs, en i taget");
            System.out.println("Skriv in s för att spara och avsluta");
            frukt = scanner.next();
            if (!frukt.equals("s")) {
                grossist.läggTillVara(frukt);
            }
        } while (!frukt.equals("s"));

        Grossister grossister = Grossister.getInstance();
        grossister.grossistMap.put(grossist.getNamn(), grossist);
    }

    public void skapaNyGrossistMedVara(final Vara vara){
        Grossist grossist = new Grossist();
        grossist.setNamn(GeneralPurposeTools.generateRandomGrossistName());
        grossist.setOrderMinimiantal(vara.getAmount());
        grossist.läggTillVara(vara);
        Grossister.getInstance().grossistMap.put(grossist.getNamn(), grossist);
    }
}
