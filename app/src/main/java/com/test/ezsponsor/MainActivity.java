package com.test.ezsponsor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.auth.SignInResult;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.auth.Auth;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = new DonationsFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCenter.start(getApplication(), "a24aa30c-220e-4534-a84e-b931c6cba18d",
            Analytics.class, Crashes.class, Auth.class); // microsoft app center integration


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Attaching the layout to the toolbar object
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.header, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            //Back button
            case android.R.id.home:
                //If this activity started from other activity
                finish();

            /*If you wish to open new activity and close this one
            startNewActivity();
            */
                return true;
            case R.id.action_favorite:
                Auth.signIn().thenAccept(new AppCenterConsumer<SignInResult>() {

                    @Override
                    public void accept(SignInResult signInResult) {

                        if (signInResult.getException() == null) {

                            // Sign-in succeeded.
                            String accountId = signInResult.getUserInformation().getAccountId();
                        } else {

                            // Do something with sign in failure.
                            Exception signInFailureException = signInResult.getException();
                        }
                    }
                });
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
