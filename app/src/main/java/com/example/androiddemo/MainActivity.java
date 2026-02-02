package com.example.androiddemo;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.nikitos.Engine;
import com.nikitos.GamePageClass;
import com.nikitos.MainRenderer;
import com.seal.gl_engine.platform.AndroidLauncher;
import com.seal.gl_engine.platform.AndroidLauncherParams;


public class MainActivity extends AppCompatActivity {
    Engine engine;
    GLSurfaceView v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("activity", "on create " + savedInstanceState);
        AndroidLauncherParams androidLauncherParams = new AndroidLauncherParams(getApplicationContext())
                .setDebug(true)
                .setLandscape(true)
                .setStartPage(unused -> new MainRenderer())
                .setMSAA(true);

        AndroidLauncher androidLauncher = new AndroidLauncher(androidLauncherParams);
        engine = androidLauncher.getEngine();
        // if (savedInstanceState == null) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility(uiOptions);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        v = androidLauncher.launch();
        //}
        setContentView(v);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("print", "on pause");
        engine.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("print", "on resume");
        engine.onResume();
    }
}