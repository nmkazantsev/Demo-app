package com.example.androiddemo;

import android.annotation.SuppressLint;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.nikitos.Engine;
import com.nikitos.GamePageClass;
import com.nikitos.MainRenderer;
import com.nikitos.main.touch.TouchProcessor;
import com.seal.gl_engine.platform.AndroidLauncher;
import com.seal.gl_engine.platform.AndroidLauncherParams;
import com.seal.gl_engine.touch.AndroidMotionEventAdapter;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    Engine engine;
    GLSurfaceView v;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("activity", "on create " + savedInstanceState);
        AndroidLauncherParams androidLauncherParams = new AndroidLauncherParams(getApplicationContext())
                .setDebug(false)
                .setStartPage(unused -> new MainRenderer())
                .setUseBSOD(true)
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
        v.setOnTouchListener(this);
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
         TouchProcessor.onTouch(new AndroidMotionEventAdapter(event));
         return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}