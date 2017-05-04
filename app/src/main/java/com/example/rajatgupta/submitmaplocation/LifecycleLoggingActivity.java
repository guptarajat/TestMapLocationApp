package com.example.rajatgupta.submitmaplocation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by rajatgupta on 4/29/17.
 */

public class LifecycleLoggingActivity extends Activity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            Log.d(TAG, "onCreate: recreating activity");
        } else {
            Log.d(TAG, "onCreate: creating fresh activity");
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart - activity is about to become visible");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume- activity has become visible-its now resumed");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause - another activity is taking focus. This will be paused");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop - the activity is no longer visible. It is now stopped");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart - the activity is just about to restart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy - activity is about to be destroyed");
    }
}
