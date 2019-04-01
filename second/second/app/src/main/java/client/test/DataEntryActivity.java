package client.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import client.test.model.PacientDailyInfo;
import client.test.networking.PacientDataEntryClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataEntryActivity extends AppCompatActivity {

    private TextView numberOfGlassesTV;
    private TextView glassesText;
    private Button minusButton;
    private SeekBar seekBarWeight;
    private TextView weightInKgTv;
    private TextView pulseBPM;
    private SeekBar seekBarPulse;
    private TextView tempInCelsius;
    private SeekBar seekBarTemp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        makeToolbarWhite();
        numberOfGlassesTV = findViewById(R.id.number_of_glasses);
        glassesText = findViewById(R.id.glasses_text);

        seekBarWeight = findViewById(R.id.seekBarWeight);
        weightInKgTv = findViewById(R.id.weightInKg);

        seekBarPulse =findViewById(R.id.seekBarPulse);
        pulseBPM = findViewById(R.id.pulseBPM);

        tempInCelsius = findViewById(R.id.tempInCelsius);
        seekBarTemp = findViewById(R.id.seekBarTemp);

        seekBarWeight.setMax(200);
        seekBarTemp.setMax(20);

        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                weightInKgTv.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarPulse.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pulseBPM.setText("" + (50 + i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tempInCelsius.setText("" + (28 + i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }


    private void makeToolbarWhite() {
        ActionBar topBar = getSupportActionBar();
        topBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        topBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Tell us what you do" + "</font>"));
    }

    //This method gets called whenever plus or minus buttons
    // in the 'Water' cardview are pressed
    public void changeNumberOfGlasses(View view) {
        int viewId = view.getId();
        int nrOfGlasses = Integer.parseInt(numberOfGlassesTV.getText().toString());

        if (viewId == R.id.minus_glass_button && nrOfGlasses > 0){
            //decrement number of glasses
            numberOfGlassesTV.setText("" + (--nrOfGlasses));
        } else if (viewId == R.id.plus_glass_button) {
            //imcrement number of glasses
            numberOfGlassesTV.setText("" + (++nrOfGlasses));
        }

        if (nrOfGlasses == 1) glassesText.setText("glass");
        else glassesText.setText("glasses");


    }


    //This method gets called whenever user presses send button
    public void sendDailyData(View view) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PacientDataEntryClient client = retrofit.create(PacientDataEntryClient.class);
        PacientDailyInfo info = getAllPacientDataInfo();

        Call<PacientDailyInfo> call = client.sendDailyData(info);

        call.enqueue(new Callback<PacientDailyInfo>() {
            @Override
            public void onResponse(Call<PacientDailyInfo> call, Response<PacientDailyInfo> response) {
                Toast.makeText(DataEntryActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PacientDailyInfo> call, Throwable t) {

            }
        });

    }

    private PacientDailyInfo getAllPacientDataInfo() {
        Integer water       = Integer.parseInt(numberOfGlassesTV.getText().toString());
        Integer weight      = Integer.parseInt(weightInKgTv.getText().toString());
        Integer pulse       = Integer.parseInt(pulseBPM.getText().toString());
        Integer temperature = Integer.parseInt(tempInCelsius.getText().toString());

        return new PacientDailyInfo(water, weight, pulse, temperature);
    }
}
