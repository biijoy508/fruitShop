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

        System.out.println("I lagret finns följande");
        System.out.print(lager.toString());
        Scanner inputReader = null;
        String fruktAttLäggaTill = promptForFrukt(inputReader);
        int fruktAntal = promptForAntal(inputReader);

        Vara vara = new Vara();

    }

    private String promptForFrukt(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.println("Lägg till en frukt:");
        String valdFrukt = scanner.next();

        return valdFrukt;
    }

    private int promptForAntal(Scanner scanner){
        int antal = 0;
        scanner = new Scanner(System.in);
        System.out.println("Lägg till en frukt:");
        boolean okInput = false;
        do
        {
        try{
        okInput = true;
        antal = scanner.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Måste vara en siffra. I nummerformat.");
            okInput = false;
        }
        }while(!okInput);


        return antal;
    }

    private double promptForPris(Scanner scanner){
        double pris = 0;
        scanner = new Scanner(System.in);
        System.out.println("Lägg till en frukt:");
        boolean okInput = false;
        do
        {
        try{
        okInput = true;
        pris = scanner.nextDouble();
        }catch(InputMismatchException e){
            System.out.println("Måste vara en siffra. I nummerformat.");
            okInput = false;
        }
        }while(!okInput);


        return pris;
    }

}
