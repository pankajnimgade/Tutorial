package listview.test.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nimgade.pk.mytutorialapplication.R;

import java.io.File;

import jp.drmh.wajin.gizmorph.GizMorphProcess;

public class DecryptImageActivity extends AppCompatActivity {

    private Button button;
    private File picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        getAlbumStorageDir();

        button = (Button)findViewById(R.id.DecryptImageActivity_start_decryption_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (picture != null) {

                    System.out.println("before");
                    GizMorphProcess gizMorphProcess=new GizMorphProcess(picture.getAbsolutePath(),picture.getAbsolutePath()+"/jpg_reshake_images/bg_test.jpg",picture.getAbsolutePath(),1920,1080);
                    Bitmap bitmap = gizMorphProcess.doReshakeWithThread();
                    System.out.println("after");
                }

            }
        });

        File picture = getAlbumStorageDir("Pictures");
        if (picture != null) {
            System.out.println(""+picture.getAbsolutePath());
        }else{
            System.out.println("picture is null");
        }





    }

    public File getAlbumStorageDir() {
        // Get the directory for the user's public pictures directory.
        File file = new File(""+Environment.getExternalStorageDirectory());
        System.out.println(""+file.getAbsolutePath());

        return file;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),albumName);
        if (!file.mkdirs()) {
            Log.e("LOG_TAG", "Directory not created");
        }
        return file;
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
