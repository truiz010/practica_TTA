package es.ehu.tta.practica.presentacion.presentacion;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import es.ehu.tta.practica.R;
import es.ehu.tta.practica.presentacion.modelo.Data;
import es.ehu.tta.practica.presentacion.modelo.Exercise;
import es.ehu.tta.practica.presentacion.modelo.ProgressTask;

public class ExerciseActivity extends AppCompatActivity {

   // String exercise_question="Explica cómo aplicarías el patrón de diseño MVP en el desarrollo de una app para Android";

    final int READ_REQUEST_CODE=1;
    final int PICTURE_REQUEST_CODE=4;
    final int AUDIO_REQUEST_CODE=3;
    final int VIDEO_REQUEST_CODE=2;
    private Uri pictureUri;
    private String TAG="tag";
    private String fileName;
    String login;
    String passwd;
    int id=1;
    public final static Data server=new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent=getIntent();
        login=intent.getStringExtra(MenuActivity.EXTRA_LOGIN);
        passwd=intent.getStringExtra(MenuActivity.EXTRA_PASSWORD);

        //TextView question=(TextView)findViewById(R.id.exercise_wording);
        //question.setText(exercise_question);

        new ProgressTask<Exercise>(this){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected Exercise work()throws Exception{
                server.getRest().setHttpBasicAuth(login,passwd);
                return server.getExercise(id);
            }

            @Override
            protected void onFinish(Exercise result){
                TextView question=(TextView)findViewById(R.id.exercise_wording);
                question.setText(result.getWording());
            }

        }.execute();
    }

    public void takePhoto(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,"El dispositivo no tiene camara",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try{
                    File file=File.createTempFile("tta",".jpg",dir);
                    pictureUri= Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(intent,PICTURE_REQUEST_CODE);
                }
                catch (IOException e){

                }
            }
            else{
                Toast.makeText(this,"No hay ningun programa para sacer fotos",Toast.LENGTH_SHORT).show();;
            }
        }
    }

    public void videoRecord(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this, "El dispositivo no tiene camara",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(intent,VIDEO_REQUEST_CODE);
            }
            else{
                Toast.makeText(this,"No hay ningun programa para grabar video",Toast.LENGTH_SHORT).show();;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        switch (requestCode){
            case READ_REQUEST_CODE:
                Uri uri=data.getData();
                fileName=dumpMetaData(uri);
                break;
            case VIDEO_REQUEST_CODE:
                break;
            case AUDIO_REQUEST_CODE:
                //sendFile(data.getData());
                break;
            case PICTURE_REQUEST_CODE:
               // dumpImageMetaData(pictureUri);
                break;
        }
    }

    public void audioRecord(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            Toast.makeText(this, "El dispositivo no tiene microfono",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null){
                 startActivityForResult(intent,AUDIO_REQUEST_CODE);
            }
            else{
                Toast.makeText(this,"No hay ningun programa para grabar audio",Toast.LENGTH_SHORT).show();;
            }
        }
    }

    public void sendFile(View view){
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }

    public String dumpMetaData(Uri uri){

        Cursor cursor=getContentResolver().query(uri,null,null,null,null,null);
        String displayName="";
        try{
            if(cursor!=null && cursor.moveToFirst()){
                displayName=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.i(TAG,"Display Name: "+displayName);

                int sizeIndex=cursor.getColumnIndex(OpenableColumns.SIZE);
                String size=null;
                if(!cursor.isNull(sizeIndex)){
                    size=cursor.getString(sizeIndex);
                }
                else{
                    size="unknown";
                }
                Log.i(TAG,"Size: "+size);
            }
        }

        finally {
            cursor.close();
        }
        return displayName;
    }
}
