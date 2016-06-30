package com.example.kkiran.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
private Button exceptionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Instabug.Builder(getApplication(), "4484b84ed248a5dbb0d1c9a22bfa9560")
                .setInvocationEvent(IBGInvocationEvent.IBGInvocationEventShake)
                .build();

        exceptionButton = (Button)findViewById(R.id.button);
        //TODO: why is this exception not working?

        Log.d("<<<","started");
        exceptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    int[] array = new int[10];
                    Log.d("<<<","clicked");
                    Toast.makeText(getApplicationContext(), "KiranK" + 4 / 1+ array[9], Toast.LENGTH_SHORT).show();
                    throw new RuntimeException("hehe");

            }
        });


    }
}
