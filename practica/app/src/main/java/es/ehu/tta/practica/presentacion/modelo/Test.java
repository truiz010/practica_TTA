package es.ehu.tta.practica.presentacion.modelo;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by strav on 13/01/2018.
 */

public class Test{

    private String wording;
    private List<Choise> choices=new ArrayList<>();




    public Test(){

    }


    public void setWording(String wording){


    }

    public String getWording(){
        return wording;
    }

    public List<Choise> getChoices() {
        return choices;
    }

    public void setChoices(List<Choise> choices) {
        this.choices = choices;
    }


}
