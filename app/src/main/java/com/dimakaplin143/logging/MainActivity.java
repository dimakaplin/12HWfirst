package com.dimakaplin143.logging;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String LOG = "Lifecycle";
    private SharedPreferences sh;
    private static final String PREFERENCES = "logs";
    private final String PRESS = "press";

    private final String LOGS_STR = "logs";

    private TextView logs;
    private CheckBox ch;

    private boolean pressTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        logging("Bundle is null " + Boolean.toString(savedInstanceState == null));
        logging("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logging("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logging("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logging("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logging("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logging("onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        logging("onSaveInstanceState");

        logs = findViewById(R.id.logs);

        outState.putString(LOGS_STR, logs.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        logging("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        logs = findViewById(R.id.logs);
        logs.setText(savedInstanceState.getString(LOGS_STR));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logging("onRestart");
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        logging("onPostCreate");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        logging("onPostResume");
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {

        logging("onKeyLongPress");

        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        logging("onKeyDown");
        if(pressTrue) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public void onBackPressed()
    {

        logging("onBackPressed");

        super.onBackPressed();
    }



    private void init() {
        sh = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

        pressTrue = getSharedBool();

        ch = findViewById(R.id.press_key);
        ch.setChecked(pressTrue);
        ch.setOnCheckedChangeListener((v, ch)-> {
            pressTrue = ch;
            saveSharedBool(ch);
        });

        logs = findViewById(R.id.logs);
    }

    private void logging(String message) {
        Log.d(LOG, message);
        logs.append(message + "\n");
    }

    private void saveSharedBool(boolean var) {
        SharedPreferences.Editor editor = sh.edit();
        editor.putBoolean(PRESS, var);
        editor.apply();
    }

    private boolean getSharedBool() {
        return sh.getBoolean(PRESS, false);
    }
}
