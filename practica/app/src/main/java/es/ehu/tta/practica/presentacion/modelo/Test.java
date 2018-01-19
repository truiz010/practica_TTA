package es.ehu.tta.practica.presentacion.modelo;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by strav on 13/01/2018.
 */

public class Test{

    private String wording;
    private List<Choices> choices=new ArrayList<>();

    public Test(){

    }

    public Test(String wording,List<Choices> choices){
        this.wording=wording;
        this.choices=choices;
    }


    public void setWording(String wording){

            this.wording=wording;
    }

    public String getWording(){
        return wording;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public static class Choices implements Serializable {

        private String answer;
        private int id;
        private String advise;
        private Boolean correct;
        private String mime;

        public Choices(){

        }

        public Choices(String answer, int id, String advise, Boolean correct, String mime){
            this.answer=answer;
            this.id=id;
            this.advise=advise;
            this.correct=correct;
            this.mime=mime;
        }

        public void setAnswer(String answer){
                this.answer=answer;
        }

        public String getAnswer(){
            return answer;
        }

        public void setAdvise(String advise){
                this.advise=advise;
        }

        public String getAdvise(){
            return advise;
        }

        public void setId(int id){
                this.id=id;
        }

        public int getId(){
            return id;
        }

        public void setCorrect(Boolean correct){
            this.correct=correct;
        }

        public Boolean getCorrect(){
            return correct;
        }

        public void setMime(String mime){
                this.mime=mime;
        }

        public String getMime(){
            return mime;
        }
    }


}
