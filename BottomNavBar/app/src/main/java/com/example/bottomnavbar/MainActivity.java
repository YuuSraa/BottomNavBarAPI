package com.example.bottomnavbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements HospitalFragment.HospitalsListener, DoctorFragment.DoctorsListener , PoliceFragment.PolicesListener ,PharmacyFragment.PharmacysListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HospitalFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_hospital:
                            selectedFragment = new HospitalFragment();
                            break;
                        case R.id.nav_police:
                            selectedFragment = new PoliceFragment();
                            break;
                        case R.id.nav_doctor:
                            selectedFragment = new DoctorFragment();
                            break;
                        case R.id.nav_pharmacy:
                            selectedFragment = new PharmacyFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void gotoHospitalDetails(Hospital hospital) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, DetailsHospitalFragment.newInstance(hospital))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoDoctorDetails(Doctor doctor) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, DetailsDoctorFragment.newInstance(doctor))
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void gotoPoliceDetails(Police police) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, DetailsPoliceFragment.newInstance(police))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoPharmacyDetails(Pharmacy pharmacy) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, DetailsPharmacyFragment.newInstance(pharmacy))
                .addToBackStack(null)
                .commit();

    }
}
