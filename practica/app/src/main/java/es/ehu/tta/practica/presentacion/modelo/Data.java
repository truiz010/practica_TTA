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
    public Test getTest(int id)throws IOException, JSONException{
        try {
            Test test=new Test();
            JSONObject json=rest.getJSON(String.format("getTest?id=%d",id));
            test.setWording(json.getString("wording"));
            JSONArray array=json.getJSONArray("choices");

            for(int i=0;i<array.length();i++){
                JSONObject item=array.getJSONObject(i);
                Test.Choices choice=new Test.Choices();
                choice.setId(item.getInt("id"));
                choice.setAnswer(item.getString("answer"));
                choice.setCorrect(item.getBoolean("correct"));
                choice.setAdvise(item.optString("advise",null));

                if(item.getString("resourceType").matches("null")){
                    choice.setMime("null");
                }
                else{
                    JSONObject resourceType=item.getJSONObject("resourceType");
                    choice.setMime(resourceType.getString("mime"));
                }

                test.getChoices().add(choice);
            }
            return test;
        }
        catch (JSONException e){
             return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Exercise getExercise(int id)throws IOException, JSONException{
        JSONObject json=rest.getJSON(String.format("getExercise?id=%d",id));
        Exercise exercise=new Exercise();
        exercise.setWording(json.getString("wording"));
        return exercise;
    }

    public void uploadChoice(int userId, int choiceId)throws IOException, JSONException{
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        json.put("choiceId",choiceId);
        rest.postJSON(json,"postChoice");
    }


}
