package com.example.madcamp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    Fragment fragment1, fragment2, fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MadCamp1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment1).commit();

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // TODO : process tab selection event.
                int pos = tab.getPosition();

                Fragment selected=null;
                if(pos==0){
                    selected = fragment1;
                    tab.setIcon(R.drawable.icon_contact_selected);
                }
                else if(pos==1){
                    selected = fragment2;
                    tab.setIcon(R.drawable.icon_gallery_selected);
                }
                else if(pos==2){
                    selected = fragment3;
                    tab.setIcon(R.drawable.icon_puzzle_selected);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // do nothing
                int pos = tab.getPosition();
                if(pos==0){
                    tab.setIcon(R.drawable.icon_contact);
                }
                else if(pos==1){
                    tab.setIcon(R.drawable.icon_gallery);
                }
                else if(pos==2){
                    tab.setIcon(R.drawable.icon_puzzle);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // do nothing
            }
        }) ;

    }
}