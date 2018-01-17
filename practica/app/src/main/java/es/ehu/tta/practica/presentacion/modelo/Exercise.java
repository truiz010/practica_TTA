package es.ehu.tta.practica.presentacion.modelo;

/**
 * Created by strav on 13/01/2018.
 */

public class Exercise {

    private String wording;
    private int id;

    public Exercise(String wording, int id){
        this.wording=wording;
        this.id=id;
    }

    public String getWording(){
        return wording;
    }

    public int getId(){
        return id;
    }
}
