package json.anand.com.jsonsampleapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import json.anand.com.jsonsampleapp.R;
import json.anand.com.jsonsampleapp.utils.Navigator;

/**
 * Splash screen
 */
public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startHomeActivity();
    }

    /**
     * Method to start home activity
     */
    private void startHomeActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigator.launchHome(SplashActivity.this);
                finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}
