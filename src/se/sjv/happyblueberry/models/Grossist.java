package se.sjv.happyblueberry.models;

import java.util.ArrayList;
import java.util.List;

public class Grossist {
    private String namn;
    private List<String> varuLista;

    public Grossist(){
        varuLista = new ArrayList<String>();
    }
    public String getNamn() {
        return namn;
    }

    public void setNamn(final String namn) {
        this.namn = namn;
    }

    public List<String> getVaruLista() {
        return varuLista;
    }

    public void setVaruLista(final List<String> varuLista) {
        this.varuLista = varuLista;
    }

    public void läggTillVara(final String vara){
        varuLista.add(vara);
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(namn);
        for(String vara : varuLista) {
            sBuilder.append(String.format("%n-%s", vara));
        }

        return sBuilder.toString();
    }

}
