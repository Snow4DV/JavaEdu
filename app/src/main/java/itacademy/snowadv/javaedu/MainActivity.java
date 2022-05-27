package itacademy.snowadv.javaedu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import itacademy.snowadv.javaedu.data.UserData;
import itacademy.snowadv.javaedu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private UserData userData;
    private static final Gson gson = new Gson();
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPrefs = getPreferences(MODE_PRIVATE);
        getUserDataFromSharedPrefs();
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_theory, R.id.navigation_practice)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void getUserDataFromSharedPrefs() {
        try {
            userData = gson.fromJson(mPrefs.getString("userData", ""),
                    UserData.class);

        } catch(JsonSyntaxException exception) {
            Log.e(TAG, "getUserData failed to parse data from shared prefs:",
                    exception);
        }
        if(userData == null) {
            userData = new UserData(new SimpleDateFormat("yyyy.MM.dd")
                    .format(new Timestamp(System.currentTimeMillis())));
        } else {
            userData.updateDailyData(new SimpleDateFormat("yyyy.MM.dd")
                    .format(new Timestamp(System.currentTimeMillis())));
        }
    }

    private void saveUserDataToSharedPrefs() {
        if(userData == null) {
            Log.e(TAG, "saveUserData: userData is null");
            return;
        }
        SharedPreferences.Editor sharedPrefsEditor = mPrefs.edit();
        sharedPrefsEditor.putString("userData", gson.toJson(userData));
        sharedPrefsEditor.apply();
    }

    public UserData getUserDataInstance() {
        if(userData == null) {
            Log.e(TAG, "getUserDataInstance: Passed null userdata!");
        }
        UserData.saveUserDataInstance(userData);
        return userData;
    }



    @Override
    protected void onPause() {
        super.onPause();
        saveUserDataToSharedPrefs();
    }
}