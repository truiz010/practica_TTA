package es.ehu.tta.practica.presentacion.presentacion;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import es.ehu.tta.practica.R;

public class TestActivity extends AppCompatActivity  {

    String question="¿Cuál de las siguientes opciones NO se indica en el fichero de minifiesto de la app?";
    String[] answers={"Versión de la aplicación","Listado de componentes de la aplicación","Opciones del menú de ajustes","Nivel mínimo de la API Android requerida","Nombre del paquete java de la aplicación"};
    String advise="<html><body>The manifest describes the components of the application: the activities, services, broadcast receivers, and content providers that...</body></html>";
    String adviseHtml="https://developer.android.com/guide/topics/manifest/manifest-intro.html";
    String adviseVideo="http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView testWording=(TextView)findViewById(R.id.test_wording);
        testWording.setText(question);
        RadioGroup group=(RadioGroup)findViewById(R.id.test_choices);
        for(int i=0;i<answers.length;i++){
            RadioButton radio=new RadioButton(this);
            radio.setText(answers[i]);
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
                }
            });
            group.addView(radio);
        }
    }

    public void send(View v){

        int correcto=2;

        RadioGroup group=(RadioGroup)findViewById(R.id.test_choices);
        int choice=group.getChildCount();
        for(int i=0;i<choice;i++){
            group.getChildAt(i).setEnabled(false);
        }
        ViewGroup pantalla=(ViewGroup)findViewById(R.id.pantalla_test);
        pantalla.removeView(findViewById(R.id.button_send_test));

        group.getChildAt(correcto).setBackgroundColor(Color.GREEN);
        int selected=group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));

        if(selected!=correcto){
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),"¡Has fallado!",Toast.LENGTH_SHORT).show();;
            if(advise!=null && !advise.isEmpty()){
                if(selected==0){
                    findViewById(R.id.button_ayuda).setVisibility(View.VISIBLE);
                }
                if(selected==1){
                    findViewById(R.id.button_ayuda2).setVisibility(View.VISIBLE);
                }
                if(selected==3){
                    findViewById(R.id.button_ayuda3).setVisibility(View.VISIBLE);
                }
                if(selected==4){
                    findViewById(R.id.button_ayuda4).setVisibility(View.VISIBLE);
                }
            }
        }
        else
            Toast.makeText(getApplicationContext(),"¡Correcto!",Toast.LENGTH_SHORT).show();;
        }

        public void showText(View view){

            WebView web=new WebView(this);
            web.loadData(advise,"text/html",null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
            ViewGroup layout=(ViewGroup)findViewById(R.id.pantalla_test);
            layout.addView(web);
    }

    public void showHtml(View view){


        if(adviseHtml.substring(0,10).contains("://")){
            Uri uri=Uri.parse(adviseHtml);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
        //else{
         //   WebView web=new WebView(this);
           // web.loadData(advise,"text/html",null);
            //web.setBackgroundColor(Color.TRANSPARENT);
            //web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
            //ViewGroup layout=(ViewGroup)findViewById(R.id.pantalla_test);
            //layout.addView(web);
        //}
    }

    public void showVideo(View view){

        VideoView video=new VideoView(this);
        video.setVideoURI(Uri.parse(adviseVideo));
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        video.setLayoutParams(params);

        MediaController controller=new MediaController(this){
            @Override
            public void hide(){

            }

            @Override
            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
                    finish();
                }
                return super.dispatchKeyEvent(event);
            }
        };
        controller.setAnchorView(video);
        video.setMediaController(controller);

        ViewGroup layout=(ViewGroup)findViewById(R.id.pantalla_test);
        layout.addView(video);
        video.start();
    }

    public void showAudio(View view){

        View view1=new View(this);
        AudioPlayer audio=new AudioPlayer(view1);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        //audio.setAudioUri(Uri.parse(adviseVideo));

        //MediaPlayer media=new MediaPlayer();
        try{
            audio.setAudioUri(Uri.parse(adviseVideo));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        ViewGroup layout=(ViewGroup)findViewById(R.id.pantalla_test);
        layout.addView(view1);
        audio.start();
    }
}
