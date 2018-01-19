package es.ehu.tta.practica.presentacion.presentacion;

import android.content.Intent;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import es.ehu.tta.practica.R;
import es.ehu.tta.practica.presentacion.modelo.Data;
import es.ehu.tta.practica.presentacion.modelo.ProgressTask;
import es.ehu.tta.practica.presentacion.modelo.RestClient;
import es.ehu.tta.practica.presentacion.modelo.User;

public class LoginActivity extends AppCompatActivity {

    public final static  String EXTRA_USER="user";
    public final static String EXTRA_LESSON_TITLE="title";
    public final static String EXTRA_LESSON_NUMBER="number";
    public final static String EXTRA_LOGIN="login";
    public final static String EXTRA_PASSWORD="password";
    public final static Data server=new Data();

   // public final static  Data server=;
    //public final static  User user=null;
   // public final static  String login="";
    //public final static  String passwd="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view)throws IOException{
       // Intent intent=new Intent(this,MenuActivity.class);
        final String login=((EditText)findViewById(R.id.login)).getText().toString();
        final String passwd=((EditText)findViewById(R.id.passwd)).getText().toString();
        if(login.matches("")||passwd.matches("")){
            Toast.makeText(
                    this,
                    "Acceso incorrecto",
                    Toast.LENGTH_SHORT
            ).show();

        }
        else{
            new ProgressTask<User>(this){

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                protected User work()throws Exception{

                    server.getRest().setHttpBasicAuth(login,passwd);
                    return server.getUser(login);
                }

                @Override
                protected void onFinish(User result){
                    Intent data=new Intent(getApplicationContext(),MenuActivity.class);
                    data.putExtra(LoginActivity.EXTRA_USER,result.getUser());
                    data.putExtra(LoginActivity.EXTRA_LESSON_TITLE,result.getLessonTitle());
                    int i=result.getLessonNumber();
                    data.putExtra(LoginActivity.EXTRA_LESSON_NUMBER,Integer.toString(i));
                    data.putExtra(LoginActivity.EXTRA_LOGIN,login);
                    data.putExtra(LoginActivity.EXTRA_PASSWORD,passwd);

                    startActivity(data);

                }

            }.execute();

            //intent.putExtra(LoginActivity.EXTRA_LOGIN,user);
            /*Toast.makeText(
                    this,
                    "Acceso correcto",
                    Toast.LENGTH_SHORT
            ).show();*/
        }

       // startActivity(intent);
    }

}
