package com.example.rajatgupta.submitmaplocation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by rajatgupta on 4/29/17.
 */

public class UiUtils {
    private static final String TAG = UiUtils.class.getSimpleName();

    private UiUtils() {
        throw new AssertionError();
    }

    /**
     * Return an uppercase version of the input or null if user gave
     * no input. If user gave no input and @a showToast is true a
     * toast is displayed to this effect.
     */
    public static String uppercaseInput(Context context,
                                        String input,
                                        boolean showToast) {
        if (input.isEmpty()) {
            if (showToast)
                UiUtils.showToast(context,
                        "no input provided");
            return null;
        } else
            // Convert the input entered by the user so it's in
            // uppercase.
            return input.toUpperCase(Locale.ENGLISH);
    }

    /**
     * Show a toast message.
     */
    public static void showToast(Context context,
                                 String message) {
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * @return True if the caller is running on the UI thread, else
     * false.
     */
    public static boolean runningOnUiThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    /**
     * Reveals the EditText.
     * @param text EditText to be revealed
     */
    public static void revealEditText (EditText text) {
        // Get x and y positions of the view with a slight offset
        // to give the illusion of reveal happening from FAB.
        int cx = text.getRight() - 30;
        int cy = text.getBottom() - 60;

        // Radius gives the reveal the circular outline.
        int finalRadius = Math.max(text.getWidth(),
                text.getHeight());

        // This creates a circular reveal that is used starting from
        // cx and cy with a radius of 0 and then expanding to finalRadius.
        Animator anim =
                ViewAnimationUtils.createCircularReveal(text,
                        cx,
                        cy,
                        0,
                        finalRadius);
        text.setVisibility(View.VISIBLE);
        anim.start();
    }

    /**
     * Hides the EditText
     * @param text EditText to be hidden.
     */
    public static void hideEditText(final EditText text) {
        // Get x and y positions of the view with a slight offset
        // to give the illusion of reveal happening from FAB.
        int cx = text.getRight() - 30;
        int cy = text.getBottom() - 60;

        // Gets the initial radius for the circular reveal.
        int initialRadius = text.getWidth();

        // This creates a circular motion that appears to be going back into the
        // FAB from cx and cy with the initial radius as the width and final radius
        // as 0 since it is animating back into the FAB.
        Animator anim =
                ViewAnimationUtils.createCircularReveal(text,
                        cx,
                        cy,
                        initialRadius,
                        0);

        // Create a listener so that we can make the EditText
        // invisible once the circular animation is over.
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                text.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }


}
