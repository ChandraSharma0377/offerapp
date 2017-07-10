package com.example.dummy.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dummy.R;
import com.example.dummy.database.DBOpenHelperClass;
import com.example.dummy.fragments.HomeFragment;
import com.example.dummy.fragments.LoginFragment;
import com.example.dummy.utility.Commons;
import com.example.dummy.utility.Helper;
import com.example.dummy.utility.NetworkHelper;

import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Boolean exit = false;
    private static MainActivity mainActivity;
    private static NetworkHelper networkHelper;
    public static final String MyPREFERENCES = "AppPref";
    private static SharedPreferences sharedpreferences;

    public LinearLayout toplay;
    protected Fragment mFrag;
    protected Fragment cFrag, rootFragment;
    private HashMap<String, Stack<Fragment>> mFragmentsStack;
    private TextView actionBarTitle;
    private ImageButton btn_logout;
    private FloatingActionButton fab;


    private final String IS_LOGIN = "IsLoggedIn";
    private final String KEY_MOBILE = "mobileNo";
    private final String KEY_USER_NAME = "userName";
    private final String KEY_EMAIL_ID = "emailID";
    protected static final String CONTENT_TAG = "contenFragments";
    public DBOpenHelperClass dbHandler;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ActionBar ab;
    private BroadcastReceiver broadcastReceiver;

    public static MainActivity getMainScreenActivity() {
        return mainActivity;
    }

    public static NetworkHelper getNetworkHelper() {
        return networkHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarTitle = toolbar.findViewById(R.id.toolbar_title);
        //  btn_logout = toolbar.findViewById(R.id.btn_logout);
        // Get the ActionBar here to configure the way it behaves.
        //   ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
//        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
        mainActivity = this;
        networkHelper = new NetworkHelper(this);
        dbHandler = DBOpenHelperClass.getSharedObject(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mFragmentsStack = new HashMap<String, Stack<Fragment>>();
        mFragmentsStack.put(CONTENT_TAG, new Stack<Fragment>());
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUserProfile();
        setDrawerEnabled(isLoggedIn());
        setLogoutEnabled(isLoggedIn());
        if (isLoggedIn()) {
            changeNavigationContentFragment(new HomeFragment(), false);
        } else {
            changeNavigationContentFragment(new LoginFragment(), false);
        }

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Commons.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    updateListByNotification(intent);
                }
            }
        };
    }

    public void updateListByNotification(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
//            String message = intent.getStringExtra(Constants.MESSAGE);
//            String qrcode = intent.getStringExtra(Constants.QRCODE);
//            String status = intent.getStringExtra(Constants.STATUS);
//            String current_serving = intent.getStringExtra(Constants.CURRENT_SERVING);
//            String lastno = intent.getStringExtra(Constants.LASTNO);
//            String payload = intent.getStringExtra(Constants.MSG_TYPE_F);
//            Log.v("Notify msg background",
//                    "Push notification: " + message + "\n" + "qrcode : " + qrcode
//                            + "\n" + "status : " + status
//                            + "\n" + "current_serving : " + current_serving
//                            + "\n" + "lastno : " + lastno
//                            + "\n" + "payload : " + payload
//            );


        }
    }


    @Override
    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            updateListByNotification(intent);

        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
            removeFragment();
        }

    }

    public void removeFragment() {
        int statckSize = mFragmentsStack.get(CONTENT_TAG).size();
        if (statckSize == 0) {
            if (exit) {
                finish(); // finish activity
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }

        } else {
            Fragment fragment;
            if (statckSize > 1)
                fragment = mFragmentsStack.get(CONTENT_TAG).elementAt(statckSize - 2);
            else
                fragment = mFragmentsStack.get(CONTENT_TAG).elementAt(statckSize - 1);
            mFragmentsStack.get(CONTENT_TAG).pop();
            FragmentManager fm = this.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.detach(cFrag);
            ft.commitAllowingStateLoss();
            if (statckSize > 1) {
                cFrag = fragment;
                fragment.onResume();
            } else {
                cFrag = rootFragment;
                rootFragment.onResume();
            }
            super.onBackPressed();
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            changeNavigationContentFragment(new HomeFragment(), false);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Helper.shareApp(this);
        } else if (id == R.id.nav_rate_app) {
            Helper.rateApp(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeNavigationContentFragment(Fragment frgm, boolean shouldAdd) {

        if (shouldAdd) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (null != fm.findFragmentById(R.id.main_fragment))
                ft.hide(fm.findFragmentById(R.id.main_fragment));
            ft.add(R.id.main_fragment, frgm, frgm.getClass().getSimpleName());
            ft.addToBackStack(null);
            // ft.commitAllowingStateLoss();
            ft.commit();
            mFragmentsStack.get(CONTENT_TAG).push(frgm);
        } else {
            mFragmentsStack.get(CONTENT_TAG).clear();
            FragmentManager manager = getSupportFragmentManager();
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            manager.beginTransaction().replace(R.id.main_fragment, frgm).commitAllowingStateLoss();
            rootFragment = frgm;
        }

        cFrag = frgm;

    }

    public static SharedPreferences getSharePreferance() {
        return sharedpreferences;
    }

    public String getUserName() {
        return sharedpreferences.getString(KEY_USER_NAME, "");
    }

    public String getEmailID() {
        return sharedpreferences.getString(KEY_EMAIL_ID, "");
    }

    public boolean isLoggedIn() {
        return sharedpreferences.getBoolean(IS_LOGIN, false);
    }

    public void setSharPreferancename(String userName, String emailID, String mobileNo, boolean isLogin) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_EMAIL_ID, emailID);
        editor.putString(KEY_MOBILE, mobileNo);
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public void setUserProfile() {
        View header = navigationView.getHeaderView(0);
        TextView tv_username = (TextView) header.findViewById(R.id.tv_username);
        TextView tv_email = (TextView) header.findViewById(R.id.tv_email);
        tv_username.setText(getUserName());
        tv_email.setText(getEmailID());
    }

    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawer.setDrawerLockMode(lockMode);
        toggle.setDrawerIndicatorEnabled(enabled);
        // ab.setDisplayShowHomeEnabled(enabled);
        // ab.setDisplayHomeAsUpEnabled(enabled);
    }

    public void setActionBarTitle(String title) {
        //actionBarTitle.setText(title);
        getSupportActionBar().setTitle(title);
    }

    public String getUniqueId() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = telephonyManager.getDeviceId();
        return deviceid;
    }

    public void setLogoutEnabled(boolean enabled) {
        if (enabled) {
            // btn_logout.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
        } else {
            // btn_logout.setVisibility(View.INVISIBLE);
            fab.setVisibility(View.INVISIBLE);
        }
    }
}
