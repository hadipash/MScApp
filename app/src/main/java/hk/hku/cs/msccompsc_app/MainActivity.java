package hk.hku.cs.msccompsc_app;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import hk.hku.cs.msccompsc_app.Curriculum.GAActivity;
import hk.hku.cs.msccompsc_app.Curriculum.CurriculumActivity;
import hk.hku.cs.msccompsc_app.MainPage.SliderAdmission;
import hk.hku.cs.msccompsc_app.MainPage.SliderIntro;

import hk.hku.cs.msccompsc_app.About.MainAbout;
import hk.hku.cs.msccompsc_app.Admission.MainAdmission;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private final static int PAGENUM = 2;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.header_slider);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % PAGENUM, true);
                handler.postDelayed(this, 4000);
            }
        }, 4000);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ContactUs.class));
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Log.d(TAG, "About clicked.");
            Intent intent_about = new Intent(this, MainAbout.class);
            startActivity(intent_about);
        } else if (id == R.id.nav_admission) {
            Log.d(TAG, "Admission clicked.");
            Intent intent_admission = new Intent(this, MainAdmission.class);
            startActivity(intent_admission);
        } else if (id == R.id.nav_curriculum){
            Log.d(TAG, "Curriculum clicked.");
            Intent intent = new Intent(this, CurriculumActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_graduate_alumni){
            Log.d(TAG, "GA clicked.");
            Intent intent_ga = new Intent(this, GAActivity.class);
            startActivity(intent_ga);
        } else if (id == R.id.nav_contact_us){
            startActivity(new Intent(this, ContactUs.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new SliderIntro();
                case 1:
                    return new SliderAdmission();
                default:
                    return new SliderIntro();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void messageFromDirectorButton(View view) {
        Intent intent = new Intent(this, MainAbout.class);
        intent.putExtra("tabID", 1);
        startActivity(intent);
    }

    public void programmeOverviewButton(View view) {
        startActivity(new Intent(this, CurriculumActivity.class));
    }

    public void admissionButton(View view) {
        startActivity(new Intent(this, MainAdmission.class));
    }

    public void programmeScheduleCard(View view) {
        Intent intent = new Intent(this, CurriculumActivity.class);
        intent.putExtra("tabID", 2);
        startActivity(intent);
    }

    public void compositionFeesCard(View view) {
        Intent intent = new Intent(this, MainAdmission.class);
        intent.putExtra("tabID", 2);
        startActivity(intent);
    }

    public void applicationDeadlineCard(View view) {
        Intent intent = new Intent(this, MainAdmission.class);
        intent.putExtra("tabID", 1);
        startActivity(intent);
    }
}
