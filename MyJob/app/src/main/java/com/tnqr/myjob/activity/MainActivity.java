package com.tnqr.myjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.tnqr.myjob.R;
import com.tnqr.myjob.databinding.ActivityLoginBinding;
import com.tnqr.myjob.databinding.ActivityMainBinding;
import com.tnqr.myjob.databinding.ActivityRegisterBinding;
import com.tnqr.myjob.fragment.AccountFragment;
import com.tnqr.myjob.fragment.HomeFragment;
import com.tnqr.myjob.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth=FirebaseAuth.getInstance();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //drawer nav dinleme
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            handleNavigation(item);
            return true;
        });

        //bottom nav dinleme
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            handleBottomNavigation(item);
            return true;
        });

        // Varsayılan Fragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }


    }

    //drawer naw isteği işleme
    private void handleNavigation(MenuItem item) {
        Fragment selectedFragment = null;

       if(item.getItemId() == R.id.nav_home)
           selectedFragment = new HomeFragment();
       else if (item.getItemId()==R.id.nav_profile) {
           selectedFragment = new AccountFragment();

       } else if (item.getItemId()== R.id.sign_out) {
           auth.signOut();
           Intent intent = new Intent(this, loginActivity.class);
           startActivity(intent);
           finish();
       }

        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }


    }


    //bottom nav isteği işleme
    private void handleBottomNavigation(MenuItem item) {
        Fragment selectedFragment = null;

        if(item.getItemId() == R.id.nav_home)
            selectedFragment = new HomeFragment();
        else if (item.getItemId()==R.id.account) {
            selectedFragment = new AccountFragment();

        } else if (item.getItemId()== R.id.settings) {
            selectedFragment = new SettingsFragment();
        }

        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }


    }

    //fragment yükleme
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}
