package com.caeless.monstrinhos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public final class MainActivity extends Activity {
    private MonstrinhosView gameView;
    @Override public void onCreate(Bundle b){
        super.onCreate(b);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameView=new MonstrinhosView(this);
        if(b!=null) gameView.restoreSession(b);
        setContentView(gameView);
    }
    @Override protected void onSaveInstanceState(Bundle out){
        if(gameView!=null) gameView.saveSession(out);
        super.onSaveInstanceState(out);
    }
    @Override protected void onPause(){
        if(gameView!=null) gameView.persistProgress();
        super.onPause();
    }
}
