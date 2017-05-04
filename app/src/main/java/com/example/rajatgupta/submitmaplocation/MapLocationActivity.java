package com.example.rajatgupta.submitmaplocation;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by rajatgupta on 4/29/17.
 */

public class MapLocationActivity extends LifecycleLoggingActivity {
    private final String TAG = getClass().getSimpleName();

    private ImageButton mAddButton;
    private EditText mEditTextReveal;
    private boolean mIsEditTextVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mAddButton = (ImageButton) findViewById(R.id.btn_add);
        mEditTextReveal = (EditText) findViewById(R.id.location);
        mEditTextReveal.setVisibility(View.INVISIBLE);
        mIsEditTextVisible = false;
    }

    public void mapAddress(View view){
        Animatable mAnimatable;
        if (mIsEditTextVisible) {
            UiUtils.hideEditText(mEditTextReveal);
            mIsEditTextVisible = false;
            mAddButton.setImageResource(R.drawable.icon_morph_reverse);
            mAnimatable = (Animatable)(mAddButton).getDrawable();
            mAnimatable.start();

            startMap();
        } else {
            UiUtils.revealEditText(mEditTextReveal);
            mIsEditTextVisible = true;
            mEditTextReveal.requestFocus();

            mAddButton.setImageResource(R.drawable.icon_morph);
            mAnimatable = (Animatable) (mAddButton).getDrawable();
            mAnimatable.start();
        }
    }

    public void startMap(){
        try {
            String address = mEditTextReveal.getText().toString();
            address = address.replace(" ", "+");
            final Intent geoIntent = makeMapsIntent(address);
            if (geoIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(geoIntent);
            } else {
                startActivity(makeBrowserIntent(address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Intent makeMapsIntent(String address) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(address)));
    }


    public Intent makeBrowserIntent(String address) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q="+ Uri.encode(address)));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return i;
    }
}
