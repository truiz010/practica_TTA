package es.ehu.tta.practica.presentacion.presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import es.ehu.tta.practica.R;

public class MenuActivity extends AppCompatActivity {

    public final static String EXTRA_LOGIN = "login";
    public final static String EXTRA_PASSWORD="password";
    String login;
    String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent=getIntent();
        login=intent.getStringExtra(LoginActivity.EXTRA_LOGIN);
        passwd=intent.getStringExtra(LoginActivity.EXTRA_PASSWORD);
        TextView textLogin=(TextView)findViewById(R.id.menu_login);
        TextView textLeccion=(TextView)findViewById(R.id.menu_leccion);
        textLogin.setText("Bienvenido "+intent.getStringExtra(LoginActivity.EXTRA_USER));
        textLeccion.setText("Leccion "+intent.getStringExtra(LoginActivity.EXTRA_LESSON_NUMBER)+": "+intent.getStringExtra(LoginActivity.EXTRA_LESSON_TITLE));
    }

    public void test(View view){
        Intent intent = new Intent(this,TestActivity.class);
        intent.putExtra(MenuActivity.EXTRA_LOGIN, login);
        intent.putExtra(MenuActivity.EXTRA_PASSWORD, passwd);
        startActivity(intent);
    }

    public void ejercicio(View view){
        Intent intent=new Intent(this,ExerciseActivity.class);
        intent.putExtra(MenuActivity.EXTRA_LOGIN, login);
        intent.putExtra(MenuActivity.EXTRA_PASSWORD, passwd);
        startActivity(intent);
    }
}
