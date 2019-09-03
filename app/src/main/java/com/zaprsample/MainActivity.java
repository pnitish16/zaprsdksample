package com.zaprsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.redbricklane.zapr.bannersdk.ZaprBannerAd;
import com.redbricklane.zapr.bannersdk.ZaprBannerAdEventListener;
import com.redbricklane.zapr.bannersdk.ZaprInterstitialAd;
import com.redbricklane.zapr.bannersdk.ZaprInterstitialAdEventListener;
import com.redbricklane.zaprSdkBase.Zapr;


public class MainActivity extends AppCompatActivity implements ZaprInterstitialAdEventListener {

    private ZaprInterstitialAd mInterstitialAd;
    ZaprBannerAd mBannerAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Zapr service
        Zapr.start(getApplicationContext());
        Zapr.enablePolicyDialogWithMessage("http://zapr.in/privacy/");
        Zapr.setRequestForPermissionsEnabled(false);


        // Initialize ZaprInterstitialAd in onCreate() method of your Activity
        // Pass your activity context in the constructor
        mInterstitialAd = new ZaprInterstitialAd(this);
        // Set Ad unit ID
        mInterstitialAd.setAdUnitId("536a6910-9fab-418e-a382-0e10ebba2f20");

        // Request for Interstitial ad from server
        mInterstitialAd.loadInterstitialAd();

        //setting test mode enabled
        mInterstitialAd.setTestModeEnabled(true);

        // Set Interstitial ad event listener to Ad instance
        mInterstitialAd.setInterstitialAdEventListener(this);

        (findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // In onCreate() method...
                mBannerAd = findViewById(R.id.zaprAdView);
                // Set Ad unit id
                mBannerAd.setAdUnitId("9d4cd9fc-91e4-4b10-9a0d-92ad872a3894");
                // Load Banner Ad
                mBannerAd.loadAd();

                mBannerAd.setBannerAdEventListener(new ZaprBannerAdEventListener() {
                    @Override
                    public void onBannerAdLoaded() {
                        Log.d("zapad", "adloaded");
                    }

                    @Override
                    public void onBannerAdClicked() {
                        Log.d("zapad", "adclicked");
                    }

                    @Override
                    public void onFailedToLoadBannerAd(int i, String s) {
                        Log.d("zapad", "adfailed error code:" + i + ",error message:" + s);
                        Toast.makeText(MainActivity.this, "adfailed error code:" + i + ",error message:" + s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onInterstitialAdLoaded() {
        Log.d("zapad", "adloaded");
        mInterstitialAd.showInterstitialAd();
    }

    @Override
    public void onInterstitialAdShown() {
        Log.d("zapad", "adshown");
    }

    @Override
    public void onInterstitialAdClicked() {
        Log.d("zapad", "adclicked");
    }

    @Override
    public void onInterstitialAdClosed() {
        Log.d("zapad", "adclosed");
    }

    @Override
    public void onFailedToLoadInterstitialAd(int i, String s) {
        Log.d("zapad", "adfailed error code:" + i + ",error message:" + s);
        Toast.makeText(MainActivity.this, "adfailed error code:" + i + ",error message:" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mInterstitialAd != null) {
            mInterstitialAd.destroy();
        }

    }
}
