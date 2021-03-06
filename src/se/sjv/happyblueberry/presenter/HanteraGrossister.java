package se.sjv.happyblueberry.presenter;

import java.util.Scanner;

import se.sjv.happyblueberry.models.Grossist;
import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.GrossistStorage;
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

        GrossistStorage grossister = GrossistStorage.getInstance();
        grossister.grossistMap.put(grossist.getNamn(), grossist);
    }

    public void skapaNyGrossistMedNyVara(final Vara nyVara){
        Grossist grossist = new Grossist();
        grossist.setNamn(GeneralPurposeTools.generateRandomName());
        grossist.läggTillVara(nyVara);
        GrossistStorage.getInstance().grossistMap.put(grossist.getNamn(), grossist);
    }
}
