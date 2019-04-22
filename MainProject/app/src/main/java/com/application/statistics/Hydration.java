package com.application.statistics;

import android.view.View;
import android.view.View.OnClickListener;
import dataEntryActivity;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;

public class Hydratation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        applicationContext = this.getApplicationContext();

        TextView hearthRateValue = (TextView) findViewById(R.id.title);
        hearthRateValue.setText(R.string.hydratation)

        TextView specificDateValue = (TextView) findViewById(R.id.specificDate);
        TextView spefificTypeValue = (TextView) findViewById(R.id.type);
        TextView spefificValue = (TextView) findViewById(R.id.specificValue);

        TextView weekMinValue = (TextView) findViewById(R.id.weekMinValue);
        TextView weekMaxValue = (TextView) findViewById(R.id.weekMaxValue);
        TextView weekLastValue = (TextView) findViewById(R.id.weekLastValue);
        TextView weekAvgValue = (TextView) findViewById(R.id.weekAvgValue);
		
        TextView monthMinValue = (TextView) findViewById(R.id.monthMinValue);
        TextView monthMaxValue = (TextView) findViewById(R.id.monthMaxValue);
        TextView monthLastValue = (TextView) findViewById(R.id.monthLastValue);
        TextView monthAvgValue = (TextView) findViewById(R.id.monthAvgValue);



        Button updateBtn = (Button) findViewById(R.id.updateBtn);
        sendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
				displayGraph();
                displaySelectedInfo();
				displayWeeklyInfo();
				displayWeeklyChart();
				displayMonthlyInfo();
				displayMonthlyChart();
            }
        });

    }
	
    public void displaySelectedInfo()
    {
        //Display info about a chosen date
        specificDateValue.setText("data");
        int value ;//Get value on specific date from database
        if(value<0.5)
        {
            specificTypeValue.setText("Very Low");
        }else if(value<1.5)
        {
            specificTypeValue.setText("Low");
        }else if(value<2.5)
        {
            specificTypeValue.setText("Normal");
        }else if(value<3.5) {
            specificTypeValue.setText("High");
        }else
        {
            specificTypeValue.setText("Very High");
        }

        specificValue.setText(value+R.string.hydratation_units);
    }

    public void displayWeeklyInfo()
    {
        //Display info for a week
		
		//Get data from the database
		int min;
		int max;
		int last;
		int avg;
		
        minValue.setText(min+R.string.hydratation_units);
        maxValue.setText(max+R.string.hydratation_units);
        lastValue.setText(last+R.string.hydratation_units);
        avgValue.setText(avg+R.string.hydratation_units);
    }
	
	public void displayWeeklyChart
	{
		//Display a pie chart for the current week 
	}
	
	public void displayMonthlyInfo()
	{
		//Display info for a month
		
		//Get data from the database		
		int min;
		int max;
		int last;
		int avg;
		
        minValue.setText(min+R.string.hydratation_units);
        maxValue.setText(max+R.string.hydratation_units);
        lastValue.setText(last+R.string.hydratation_units);
        avgValue.setText(avg+R.string.hydratation_units);
	}
	
	public void displayMonthlyChart
	{
		//Display a pie chart for the current month 
	}
	
	public void displayGraph
	{
		//Display a linear graph for all the date 
	}
	
}
