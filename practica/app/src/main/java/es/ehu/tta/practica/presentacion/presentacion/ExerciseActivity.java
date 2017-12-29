package es.ehu.tta.practica.presentacion.presentacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import es.ehu.tta.practica.R;

public class ExerciseActivity extends AppCompatActivity {

    String exercise_question="Explica cómo aplicarías el patrón de diseño MVP en el desarrollo de una app para Android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        TextView question=(TextView)findViewById(R.id.exercise_wording);
        question.setText(exercise_question);
    }
}
