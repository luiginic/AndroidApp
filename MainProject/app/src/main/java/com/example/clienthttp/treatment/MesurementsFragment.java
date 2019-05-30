/*
    Nicola Luigi
    Gabriel Agache
 */
package com.example.clienthttp.treatment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clienthttp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import data_collection.PacientDailyInfo;
import data_collection.PacientDataEntryClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MesurementsFragment extends Fragment {

    Context context;
    int distance2Today;


    public MesurementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mesurements, container, false);
        //Here you can display what you set on the ViewPagerAdapter (declare and use the necessary views)
        TextView titleText = view.findViewById(R.id.text_on_fragment);

        this.distance2Today = getArguments().getInt("distance_to_today");

        switch (distance2Today) {
            case 0:
                titleText.setText("Today");
                break;
            case 1:
                titleText.setText("Yesterday");
                break;
            case 2:
                titleText.setText("2 days ago");
                break;
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final TextView title = view.findViewById(R.id.text_on_fragment);
        final TextView pulse = view.findViewById(R.id.pulseFragTV);
        final TextView weight = view.findViewById(R.id.weightFragTV);
        final TextView temperature = view.findViewById(R.id.temperatureFragTV);
        final TextView calories = view.findViewById(R.id.caloriesFragTV);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * distance2Today);

        String dayAssignedToFragment = dateFormat.format(cal.getTime());

        SharedPreferences prefs = context.getSharedPreferences("info.log", MODE_PRIVATE);
        String idText = prefs.getString("pacientId", null);


        int pacientId = Integer.valueOf(idText);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://medicad.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PacientDataEntryClient client = retrofit.create(PacientDataEntryClient.class);

        Call<List<PacientDailyInfo>> call = client.getDailyInfo("all", pacientId, dayAssignedToFragment);

        call.enqueue(new Callback<List<PacientDailyInfo>>() {
            @Override
            public void onResponse(Call<List<PacientDailyInfo>> call, Response<List<PacientDailyInfo>> response) {
                List<PacientDailyInfo> dataList = response.body();
                if (dataList.isEmpty()) {
                    title.setText("No data today");
                } else {
                    PacientDailyInfo info = dataList.get(0);
                    pulse.setText("Pulse: " + info.getPulse() +" bpm");
                    weight.setText("Weight: " + info.getWeight() + " kg");
                    temperature.setText("Temperature: " + info.getTemperature()+ " C");
                    calories.setText("Calories: " + info.getCalories()+ " kcal");
                }

            }

            @Override
            public void onFailure(Call<List<PacientDailyInfo>> call, Throwable t) {
                Toast.makeText(context, "No internet or smth", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }
}
