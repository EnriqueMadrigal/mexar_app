package com.datalabor.soporte.mexar.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by soporte on 14/08/2017.
 */



public class MyPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private Context _context;

    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {

        super(fm);

        this.fragments = fragments;

    }

    @Override

    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }

    @Override

    public int getCount() {

        return this.fragments.size();

    }
@Override
public int getItemPosition(Object object) {
    return POSITION_NONE;
}

}
