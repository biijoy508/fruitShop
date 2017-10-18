package se.sjv.happyblueberry.util;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.Lager;
import se.sjv.happyblueberry.presenter.HanteraLager;

public class GeneralPurposeTools {
    public static String promptForString() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        return string;
    }



    public static double promptForDouble(){
        double nummer = 0;
        Scanner scanner = new Scanner(System.in);
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

            if(nummer <= 0){
                okInput = false;
                System.out.println("Siffran får inte vara 0 eller mindre");
            }

        }while(!okInput);

        return nummer;
    }

    public static int promptForInteger(){
        int nummer = 0;
        Scanner scanner = new Scanner(System.in);

        boolean okInput = false;
        do
        {

            try{
                okInput = true;
                nummer = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Måste vara en siffra. I nummerformat.");
                okInput = false;
            }

            if(nummer <= 0){
                okInput = false;
                System.out.println("Siffran får inte vara 0 eller mindre");
            }

        }while(!okInput);

        return nummer;
    }

    public static Boolean varnaFörLågtLagerSaldo(final String varuNamn){
        Vara vara = Lager.getInstance().lagerMap.get(varuNamn);
        int antalKvar = vara.getAmount();

        if(HanteraLager.getInstance().getFruktAttBeställa().containsKey(varuNamn)){
            HanteraLager.getInstance().getFruktAttBeställa().get(varuNamn).setAmount(antalKvar);
            return true;
        }

        if(antalKvar < 10 && antalKvar >= 0){
            HanteraLager.getInstance().addToFruktAttBeställa(vara);
            return true;
        }

        return false;
    }

    public static String generateRandomGrossistName(){

        String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
                               "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
                               "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
                               "Mar", "Luk" };
        String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo",
                            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
                            "marac", "zoir", "slamar", "salmar", "urak" };
        String[] End = { "d", "ed", "ark", "arc", "es", "er", "der",
                         "tron", "med", "ure", "zur", "cred", "mur" };

        Random rand = new Random();

        return Beginning[rand.nextInt(Beginning.length)] +
                Middle[rand.nextInt(Middle.length)]+
                End[rand.nextInt(End.length)];

    }



}