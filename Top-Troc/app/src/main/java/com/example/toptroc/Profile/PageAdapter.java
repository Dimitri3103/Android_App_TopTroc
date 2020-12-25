package com.example.toptroc.Profile;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.toptroc.Fragments.InformationsFragment;
import com.example.toptroc.Fragments.MyObjectsFragment;
import com.example.toptroc.Fragments.ParametersFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private  int numOfTabs;

    public PageAdapter(FragmentManager fragmentManager,int numOfTabs){
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MyObjectsFragment();
            case 1:
                return new ParametersFragment();
            case 2:
                return new InformationsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
