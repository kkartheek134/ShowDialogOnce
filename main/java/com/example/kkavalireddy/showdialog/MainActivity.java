package com.example.kkavalireddy.showdialog;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textView.setText(date);

        SharedPreferences sharedPreferences = getSharedPreferences("Pref",MODE_PRIVATE);
        boolean res = sharedPreferences.getBoolean("Show",true);

        if(res) {
            showDialogs();
        }
    }

    private void showDialogs() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("This dialog will be shown only once..!")
                .setTitle("Display Dialog Once")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();

        SharedPreferences sharedPreferences = getSharedPreferences("Pref",MODE_PRIVATE);
        SharedPreferences.Editor  editor =  sharedPreferences.edit();
        editor.putBoolean("Show",false);
        editor.apply();


    }
}
