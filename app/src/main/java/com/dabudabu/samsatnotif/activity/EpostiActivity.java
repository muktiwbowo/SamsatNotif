package com.dabudabu.samsatnotif.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.adapter.ViewPagerAdapter;
import com.dabudabu.samsatnotif.fragment.FragmentEposti;
import com.dabudabu.samsatnotif.fragment.FragmentLogin;
import com.dabudabu.samsatnotif.fragment.FragmentRegister;
import com.dabudabu.samsatnotif.fragment.FragmentSamsatDesa;

public class EpostiActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eposti);
        toolBar = (Toolbar) findViewById(R.id.etoolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.eviewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.etablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentEposti(),"Eposti");
        adapter.addFragment(new FragmentSamsatDesa(),"Samsat Desa");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
