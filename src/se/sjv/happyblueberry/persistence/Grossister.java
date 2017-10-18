package se.sjv.happyblueberry.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.sjv.happyblueberry.models.Grossist;
import se.sjv.happyblueberry.util.FileUtil;

public class Grossister {

    public List<Grossist> grossistLista;

    private static Grossister grossister;

    private Grossister() {
        grossistLista = new ArrayList<Grossist>();
        laddaGrossister();
    }

    public static Grossister getInstance() {
        if (grossister == null) {
            grossister = new Grossister();
        }

        return grossister;
    }

    private void laddaGrossister() {
        try {
            String[] inputList = FileUtil.readLines("Grossister.txt");
            for (int i = 0; i < inputList.length; ++i) {
                if (inputList[i].length() > 0) {
                    Grossist grossist = extractGrossist(inputList[i]);
                    grossistLista.add(grossist);

                }
            }
        } catch (IOException e) {
            System.out.println("Problem uppstod vid läsning av grossister.");
            grossistLista = new ArrayList<Grossist>();

            e.printStackTrace();
        }

    }

    private Grossist extractGrossist(final String input) {
        String[] grossistString = input.split(";");
        Grossist grossist = new Grossist();
        grossist.setNamn(grossistString[0]);

        for (int i = 1; i < grossistString.length; i++) {
            grossist.läggTillVara(grossistString[i]);
        }
        return grossist;

    }

    public void sparaGrossister() {
    ArrayList<String> output = new ArrayList<>();
    for(Grossist grossist: grossistLista){
        output.add(grossist.getNamn());
        for(String vara: grossist.getVaruLista()){
           output.add(";" + vara);
        }
        output.add(System.lineSeparator());
    }
    try {
        FileUtil.writeLines("Grossister.txt", output.toArray(new String[0]));
    } catch (IOException e) {
        e.printStackTrace();
    }

  }
}
