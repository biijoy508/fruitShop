package se.sjv.happyblueberry.models;

import java.util.HashMap;

public class Grossist {
    private String namn;
    //private List<String> varuLista;
    private HashMap<String, Vara> varuMap;
    private int orderMinimiantal = 0;

    public Grossist(){
        varuMap = new HashMap<>();
    }
    public String getNamn() {
        return namn;
    }

    public void setNamn(final String namn) {
        this.namn = namn;
    }

    public HashMap<String, Vara> getVaruMap() {
        return varuMap;
    }
    public void setVaruMap(final HashMap<String, Vara> varuMap) {
        this.varuMap = varuMap;
    }

    public void läggTillVara(final Vara vara){
        varuMap.put(vara.getType(), vara);
    }

    public void läggTillVara(final String vara){
        varuMap.put(vara, new Vara(vara));
    }


    public int getOrderMinimiantal() {
        return orderMinimiantal;
    }
    public void setOrderMinimiantal(final int orderMinimiantal) {
        this.orderMinimiantal = orderMinimiantal;
    }
    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(namn);
        for(Vara vara : varuMap.values()) {
            sBuilder.append(String.format("%n-%s", vara.getType()));
        }

        return sBuilder.toString() + "\n";
    }

}
