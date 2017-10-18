package se.sjv.happyblueberry.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import se.sjv.happyblueberry.models.Grossist;
import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.Grossister;
import se.sjv.happyblueberry.persistence.Lager;
import se.sjv.happyblueberry.util.GeneralPurposeTools;

public class HanteraLager {
    private static HanteraLager hanteraFrukt;
    private HashMap<String, Vara> fruktAttBeställa;

    public HanteraLager() {
        fruktAttBeställa = new HashMap<>();
    }

    public static HanteraLager getInstance(){
        if (hanteraFrukt == null) {
            hanteraFrukt = new HanteraLager();
        }

        return hanteraFrukt;
    }

    public void läggTillNyFruktsortTillLager(){

        Boolean fruktOkej = false;
        do{
            System.out.println("Ange frukt eller '0' för att avsluta: ");
            String fruktAttLäggaTill = GeneralPurposeTools.promptForString();
            if(fruktAttLäggaTill.equals("0")){
                return;
            }

            if(valideraGiltigFrukt(fruktAttLäggaTill)){

                System.out.println("Ange hur många: ");
                int fruktAntal = GeneralPurposeTools.promptForInteger();

                System.out.println("Ange styckpris: ");
                double pris = GeneralPurposeTools.promptForDouble();

                Vara vara = skapaFrukt(fruktAttLäggaTill, fruktAntal, pris);
                Lager.getInstance().lagerMap.put(vara.getType(), vara);

                kollaFruktHosExisterandeGrossister(fruktAttLäggaTill, vara);

                fruktOkej = true;
            }

            else{
                fruktOkej = false;
                continue;
            }

        }while(!fruktOkej);

    }

    private Boolean valideraStyckPris(final double pris) {
        if(pris <= 0){
            System.out.println("FEL: Styckpriset får inte vara 0 eller negativt.");
            return false;
        }
        else{
            return true;
        }
    }

    private Vara skapaFrukt(final String fruktAttLäggaTill, final int fruktAntal, final double pris) {
        Vara vara = new Vara();
        vara.setType(fruktAttLäggaTill);
        vara.setAmount(fruktAntal);
        vara.setPrice(pris);
        return vara;
    }

    private Boolean valideraGiltigFrukt(final String fruktAttLäggaTill) {
        if(Lager.getInstance().lagerMap.containsKey(fruktAttLäggaTill))
        {
            System.out.println("FEL: Frukten finns redan!");
            return false;
        }
        else{
            return true;
        }
    }

    public HashMap<String, Vara> getFruktAttBeställa() {
        return fruktAttBeställa;
    }


    public void addToFruktAttBeställa(final Vara vara){
        fruktAttBeställa.put(vara.getType(), vara);
    }

   public void kollaFruktHosExisterandeGrossister(final String fruktType, final Vara vara){
       ArrayList<Grossist> grossister = vilkaGrossisterHarFrukten(fruktType);
       if(grossister.size() <= 0){
           HanteraGrossister hanteraGrossister = new HanteraGrossister();
           hanteraGrossister.skapaNyGrossistMedVara(vara);
       }
   }

    public  ArrayList<Grossist> vilkaGrossisterHarFrukten(final String fruktType){
       ArrayList<Grossist> grossisterSomHarFrukten = new ArrayList<>();

       for(Grossist grossist : Grossister.getInstance().grossistMap.values()){
           if(grossist.getVaruMap().containsKey(fruktType)){
               grossisterSomHarFrukten.add(grossist);
           }
       }

       return grossisterSomHarFrukten;
    }

}
