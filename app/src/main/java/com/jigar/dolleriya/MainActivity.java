package com.jigar.dolleriya;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.

    private static String LOG_TAG = "EXAMPLE";

    private Fragment contentFragment;
    //   String testDevice = "D0A04359EA1ECE9BA0CD4B6F457A9991";
    //  String testDevice2 = "63C3530DA03C191310DB9AB8F0672E5C";
    // String testDevice3 = "801F2141A1DC3F743363AFDFDC42AF3A";

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
private AdView nAdView;
    boolean displayAd = false;

    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Request window feature action bar
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the application context
        mContext = getApplicationContext();
        // Get the activity
        mActivity = MainActivity.this;

        mAdView = (AdView) findViewById(R.id.ad_view);
nAdView=(AdView)findViewById(R.id.ad_view2);

        AdRequest adRequest = new AdRequest.Builder()
                .build();


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayAd = true;
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (!displayAd) {
                }
            }

            @Override
            public void onAdClosed() {
            }
        });

        mAdView.loadAd(adRequest);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        nAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayAd = true;
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (!displayAd) {
                }
            }

            @Override
            public void onAdClosed() {
            }
        });

        nAdView.loadAd(adRequest);

    } //BHargavBundleEnd

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdClosed() {
                finish();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        } else {
            finish();
        }
    }

    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(testDevice)
                //.addTestDevice(testDevice2)
                //.addTestDevice(testDevice3)
                //.setRequestAgent("android_studio:ad_template")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?");
            builder.setCancelable(false);
            builder.setPositiveButton(android.R.string.yes,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            showInterstitial();
                            finish();
                        }
                    });
            builder.setNegativeButton(android.R.string.no,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}

