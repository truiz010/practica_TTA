package es.ehu.tta.practica.presentacion.presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.ehu.tta.practica.R;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_LOGIN="login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        Intent intent=new Intent(this,MenuActivity.class);
        String login=((EditText)findViewById(R.id.login)).getText().toString();
        String passwd=((EditText)findViewById(R.id.passwd)).getText().toString();
        if(login.matches("")||passwd.matches("")){
            Toast.makeText(
                    this,
                    "Acceso incorrecto",
                    Toast.LENGTH_SHORT
            ).show();

        }
        else
            intent.putExtra(LoginActivity.EXTRA_LOGIN,login);
            Toast.makeText(
                this,
                "Acceso correcto",
                Toast.LENGTH_SHORT
            ).show();
        startActivity(intent);
    }
}
