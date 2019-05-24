package com.example.clienthttp.treatment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        //Here you can set what is displayed on the fragment e.g. check for index i and if it is 0 do something
        MesurementsFragment fragment = new MesurementsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text","Mesurement "+ ++i);
        bundle.putInt("distance_to_today", getCount() - i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        //return how many is necessary
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        Calendar calendar = Calendar.getInstance();

        Date todayDate = calendar.getTime();
        String todayText = new SimpleDateFormat("EE", Locale.ENGLISH).format(todayDate.getTime());
        calendar.add(Calendar.DATE, -1);

        Date yesterday = calendar.getTime();
        String yestText = new SimpleDateFormat("EE", Locale.ENGLISH).format(yesterday.getTime());
        calendar.add(Calendar.DATE, -1);

        Date twoDayBefore = calendar.getTime();
        String twoBefText = new SimpleDateFormat("EE", Locale.ENGLISH).format(twoDayBefore.getTime());

        switch (position) {
            case 0:
                return twoBefText;
            case 1:
                return yestText;
            case 2:
                return todayText;
            default:
                return "???";
        }
    }
}
