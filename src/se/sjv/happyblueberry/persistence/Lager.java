package se.sjv.happyblueberry.persistence;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import se.sjv.happyblueberry.models.Vara;
import se.sjv.happyblueberry.presenter.HanteraLager;

public class Lager {

    private static Lager lager;

    public Map<String, Vara> lagerMap;

    private Lager() {
        lagerMap = new HashMap<String, Vara>();
    }

    public static Lager getInstance() {
        if (lager == null) {
            lager = new Lager();
        }

        return lager;
    }

    public void taVara(final Vara vara, final int antal) {
        int nyttAntal = lagerMap.get(vara.getType()).getAmount() - antal;
        lagerMap.get(vara.getType()).setAmount(nyttAntal);
        HanteraLager.getInstance().varnaFörLågtLagerSaldo(vara.getType());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-10s%10s%10s%n", "Typ", "Pris", "Antal"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        for (Map.Entry<String, Vara> entry: lagerMap.entrySet()) {
            builder.append(String.format("%-10s%10s%10d%n", entry.getKey(), currencyFormat.format(entry.getValue().getPrice()), entry.getValue().getAmount()));
        }

        return builder.toString();
    }
}
