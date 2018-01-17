package es.ehu.tta.practica.presentacion.modelo;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.ehu.tta.practica.presentacion.modelo.RestClient;
import es.ehu.tta.practica.presentacion.modelo.User;

/**
 * Created by strav on 14/01/2018.
 */

public class Data {

    private RestClient rest=new RestClient("http://u017633.ehu.eus:28080/ServidorTta/rest/tta");
    //private String server="http://u017633.ehu.eus:28080/ServidorTta/rest/tta";

    //Informacion del usuario
    private  int id;
    private String user;
    private int lessonNumber;
    private String lessonTitle;
    private int nextTest;
    private int nextExercise;

    //Obtener un determiando test
    private int testId=1;

    public Data(){

    }

    public RestClient getRest(){
        return  rest;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public User getUser(String dni) throws IOException, JSONException{

        JSONObject json=rest.getJSON(String.format("getStatus?dni=%s",dni));
        id=json.getInt("id");
        user=json.getString("user");
        lessonNumber=json.getInt("lessonNumber");
        lessonTitle=json.getString("lessonTitle");
        nextTest=json.getInt("nextTest");
        nextExercise=json.getInt("nextExercise");

        User status=new User(id,user,lessonNumber,lessonTitle,nextTest,nextExercise);
        return status;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Test getTest()throws IOException, JSONException{
        try {
            Test test=new Test();
            JSONObject json=rest.getJSON(String.format("getTest?id=1"));
            test.setWording(json.getString("wording"));
            JSONArray array=json.getJSONArray("choise");

            for(int i=0;i<array.length();i++){
                JSONObject item=array.getJSONObject(i);
                Choise choise=new Choise();
                choise.setId(item.getInt("id"));
                choise.setAnswer(item.getString("answer"));
                choise.setCorrect(item.getBoolean("correct"));
                choise.setAdvise(item.optString("advise",null));
                choise.setMime(item.optString("mime",null));
                //test.getChoise().add(choise);
            }
            return test;
        }
        catch (JSONException e){
            return null;
        }
    }

   /* public void putTest(Test test)throws IOException, JSONException{
        try {
            JSONObject json=new JSONObject();
            json.put("wording",test.getWording());
            JSONArray array=new JSONArray();

            for(Choise choise:test.getChoise()){
                JSONObject item=new JSONObject();
                item.put("id",choise.getId());
                item.put("answer",choise.getAnswer());
                item.put("correct",choise.getCorrect());
                item.put("advise",choise.getAdvise());
                item.put("mime",choise.getMime());
                array.put(item);
            }
            json.put("choice",array);
           // bundle.putString(EXTRA_TEST,json.toString());
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }*/
}
