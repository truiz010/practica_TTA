package es.ehu.tta.practica.presentacion.modelo;

/**
 * Created by strav on 13/01/2018.
 */

public class Exercise {

    private String wording;
    private LessonBean lessonBean;

    public Exercise(){

    }

    public Exercise(String wording, LessonBean lessonBean){
        this.wording=wording;
        this.lessonBean=lessonBean;
    }

    public String getWording(){
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public LessonBean getLessonBean(){
        return lessonBean;
    }

    public void setLessonBean(LessonBean lessonBean){
        this.lessonBean=lessonBean;
    }

    public class LessonBean {

        private  int id;
        private int number;
        private String title;

        public LessonBean(){

        }

        public LessonBean(int id, int number, String title){
            this.id=id;
            this.number=number;
            this.title=title;
        }

        public int getId(){
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
