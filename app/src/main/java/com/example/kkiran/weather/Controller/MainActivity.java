package com.example.kkiran.weather.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kkiran.weather.Model.Data;
import com.example.kkiran.weather.R;
import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private Button crashButton;
    private final String TAG = getClass().getSimpleName();
    private final String APIkey = "1c253f310ab1c802d18bb2bbf0476fd8";
    private final String APIhead = "https://api.forecast.io/forecast/";
    private double latitude, longitude;
    private TextView timeView;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeView = (TextView)findViewById(R.id.time);
        progress = (ProgressBar)findViewById(R.id.progressBar2);
        progress.setVisibility(View.INVISIBLE);
       SetupInstabug setupInstaBug = new SetupInstabug();
        setupInstaBug.initiate(getApplication());
        crashButton = (Button)findViewById(R.id.button);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int[] array = new int[10];
//               // throw  new RuntimeException("hehehehehe");
//               Log.d(TAG,4/1+""+array[100]);
                makecallandshowdata();
            }
        });
       // makecallandshowdata();

    }

    private void makecallandshowdata() {
        if (progress.getVisibility() == View.INVISIBLE) {
            progress.setVisibility(View.VISIBLE);
            crashButton.setVisibility(View.INVISIBLE);
            if (isNetworkAvailable()) {
                OkHttpClient client = new OkHttpClient();
                latitude = 37.8267;
                longitude = -122.423;
                String APIUrl = APIhead + APIkey + "/" + latitude + "," + longitude;
                Request request = new Request.Builder()
                        .url(APIUrl)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "the response from the API is being null");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseString = response.body().string();
                        Log.d(TAG, "The string representation of the response is " + responseString);
                        final Data data = populateData(responseString);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.setVisibility(View.INVISIBLE);
                                crashButton.setVisibility(View.VISIBLE);
                                timeView.setText(data.getDate().toString() + "");
                            }
                        });

                        Log.d(TAG, data.toString());

                    }
                });
                Log.d(TAG, "normal execution continues");


            } else
                notifyUserNoNetwork();
        }
        else
        {

        }
    }

    private Data populateData(String string) {

        Data data = new Data();
        try {
            JSONObject jsonObject = new JSONObject(string);
            Log.d(TAG,"working on "+ jsonObject.toString());
            data.setTimeZone(jsonObject.getString("timezone"));
            JSONObject currently = jsonObject.getJSONObject("currently");
            data.setTemperature(currently.getDouble("temperature"));
            data.setSummary(currently.getString("summary"));
            data.setPrecipitation(currently.getDouble("precipIntensity"));
            data.setHumidity(currently.getDouble("humidity"));
            data.setTime(currently.getLong("time"));
            data.setDate(currently.getLong("time")*1000);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    /*
    Below are helper functions
     */

    private boolean isNetworkAvailable() {
        boolean available =false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info!=null && info.isConnected())
            available = true;
        return available;
    }

    private void notifyUserNoNetwork() {
        CustomDialog dialog = new CustomDialog();
        dialog.show(getFragmentManager(),"Error Dialog");
    }
}
