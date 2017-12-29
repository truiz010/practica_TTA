package es.ehu.tta.practica.presentacion.presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import es.ehu.tta.practica.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent=getIntent();
        TextView textLogin=(TextView)findViewById(R.id.menu_login);
        textLogin.setText("Bienvenido "+intent.getStringExtra(LoginActivity.EXTRA_LOGIN));
    }

    public void test(View view){
        Intent intent=new Intent(this,TestActivity.class);
        startActivity(intent);
    }

    public void ejercicio(View view){
        Intent intent=new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }
}
