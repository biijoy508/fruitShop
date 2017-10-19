package se.sjv.happyblueberry.presenter;

import java.util.HashMap;

import se.sjv.happyblueberry.models.Grossist;
import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.persistence.GrossistStorage;
import se.sjv.happyblueberry.persistence.Lager;
import se.sjv.happyblueberry.util.GeneralPurposeTools;

public class HanteraGrossistBeställning {

    private static HanteraGrossistBeställning hanteraGrossistBeställning;

    public static HanteraGrossistBeställning getInstance() {
        if (hanteraGrossistBeställning == null) {
            hanteraGrossistBeställning = new HanteraGrossistBeställning();
        }
        return hanteraGrossistBeställning;
    }

    public void hittaLämpligGrossistAttBeställaIfrån(){
        HashMap<String, Vara> frukterSomÄrSlut = HanteraLager.getInstance().getFruktSomÄrSlut();
        Grossist grossistSomHarAllaFrukter = hittaBästaGrossist(frukterSomÄrSlut);
        beställFrukterFrånGrossist(grossistSomHarAllaFrukter);

    }

    private void beställFrukterFrånGrossist(final Grossist grossist) {
        for (Vara vara : HanteraLager.getInstance().getFruktSomÄrSlut().values()) {
            if (grossist != null) {
                skapaGrossistBeställning(grossist, vara.getType());
            }
            else{
                Grossist tmpGrossist = hittaNågonGrossistSomHarFrukten(vara.getType());
                skapaGrossistBeställning(tmpGrossist, vara.getType());
            }
        }
    }

    private void skapaGrossistBeställning(final Grossist grossist, final String frukt) {
        System.out.println("\nBeställer från grossist: " + grossist.getNamn());
        bekräftaBesällning(frukt, grossist.getOrderMinimiantal());
    }

    private void bekräftaBesällning(final String frukt, final int antal) {
        System.out.println("\nVill du beställa " + antal + " " + frukt + "? (y/n)");
        String svar;
        Boolean loop = true;
        do{
            svar = GeneralPurposeTools.promptForString();
            svar = svar.toLowerCase();
            if(!svar.equals("y") && !svar.equals("n")){
                System.out.println("Var god ange y/n!");
                continue;
            }
            if(svar.equals("n")){
                return;
            }
            loop = false;
        }while(loop);

        uppdateraLagerData(frukt, antal);
    }

    private void uppdateraLagerData(final String frukt, final int antal) {
        HanteraLager.getInstance().getFruktSomÄrSlut().remove(frukt);
        Lager.getInstance().lagerMap.get(frukt).setAmount(antal);
        System.out.println("Beställning har genomförts och lagret har uppdaterats.");
    }

    private Grossist hittaNågonGrossistSomHarFrukten(final String fruktType) {
        Grossist grossistAttReturnera = null;

        for(Grossist grossist : GrossistStorage.getInstance().grossistMap.values()){
            if(grossist.getVaruMap().containsKey(fruktType)){
                grossistAttReturnera = grossist;
                break;
            }
        }
        return grossistAttReturnera;
    }

    private Grossist hittaBästaGrossist(final HashMap<String, Vara> frukterSomÄrSlut) {
        for (Grossist grossist : GrossistStorage.getInstance().grossistMap.values()){
            int träffCounter = 0;
            for( Vara vara : frukterSomÄrSlut.values()) {
                if(grossist.getVaruMap().containsKey(vara.getType())){
                    ++träffCounter;
                }
            }

            if(träffCounter == frukterSomÄrSlut.size()){
                return grossist;
            }

        }

        return null;
    }

}
