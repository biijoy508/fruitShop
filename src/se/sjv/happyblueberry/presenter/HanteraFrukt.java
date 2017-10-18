package se.sjv.happyblueberry.presenter;

import java.util.InputMismatchException;
import java.util.Scanner;

import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.Lager;

public class HanteraFrukt {
    private Lager lager = Lager.getInstance();

    public HanteraFrukt() {
        läggTillFrukt();
    }

    private void läggTillFrukt(){

        Scanner inputReader = null;

        System.out.println("Lägg till frukt: ");
        String fruktAttLäggaTill = promptForString(inputReader);

        System.out.println("Ange hur många: ");
        int fruktAntal = (int) promptForSiffra(inputReader);

        System.out.println("Ange styckpris: ");
        double pris = promptForSiffra(inputReader);

        Vara vara = new Vara();

        vara.setType(fruktAttLäggaTill);
        vara.setAmount(fruktAntal);
        vara.setPrice(pris);

        lager.lagerMap.put(vara.getType(), vara);
    }

    private String promptForString(Scanner scanner) {
        scanner = new Scanner(System.in);
        String string = scanner.next();

        return string;
    }



    private double promptForSiffra(Scanner scanner){
        double nummer = 0;
        scanner = new Scanner(System.in);
        boolean okInput = false;
        do
        {
        try{
        okInput = true;
        nummer = scanner.nextDouble();
        }catch(InputMismatchException e){
            System.out.println("Måste vara en siffra. I nummerformat.");
            okInput = false;
        }
        }while(!okInput);


        return nummer;
    }

}
