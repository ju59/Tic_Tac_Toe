package com.julienb.gamecenter.Morpion;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.julienb.gamecenter.MainActivity;
import com.julienb.gamecenter.R;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Morpion extends AppCompatActivity {

    int joueur = 1;
    private static final int DIALOG_ALERT = 10;
    private static final int EGALITE = 20;
    private static final String TAG = "Morpion";
   Engine engine = new Engine();
    Boolean verif;
    DisplayMetrics metrics = new DisplayMetrics();
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_morpion2);

        mVisible = true;

        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        //on recupere la taille de l'écran
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int WidthScreen = metrics.widthPixels;
        int HeigthScreen = metrics.heightPixels;

        //on redéfini la taille des bouton par rapport a la taille de l'ecran
        View mybutton1 = findViewById(R.id.button1);
        //ViewGroup.LayoutParams buttonparam = new ViewGroup.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout
          //      .LayoutParams.WRAP_CONTENT);
        mybutton1.getLayoutParams().width = (WidthScreen/4);
        mybutton1.getLayoutParams().height = (WidthScreen/4);
        //mybutton.setLayoutParams(buttonparam);

        View mybutton2 = findViewById(R.id.button2);
        mybutton2.getLayoutParams().width = (WidthScreen/4);
        mybutton2.getLayoutParams().height = (WidthScreen/4);

        View mybutton3 = findViewById(R.id.button3);
        mybutton3.getLayoutParams().width = (WidthScreen/4);
        mybutton3.getLayoutParams().height = (WidthScreen/4);

        View mybutton4 = findViewById(R.id.button4);
        mybutton4.getLayoutParams().width = (WidthScreen/4);
        mybutton4.getLayoutParams().height = (WidthScreen/4);

        View mybutton5 = findViewById(R.id.button5);
        mybutton5.getLayoutParams().width = (WidthScreen/4);
        mybutton5.getLayoutParams().height = (WidthScreen/4);

        View mybutton6 = findViewById(R.id.button6);
        mybutton6.getLayoutParams().width = (WidthScreen/4);
        mybutton6.getLayoutParams().height = (WidthScreen/4);

        View mybutton7 = findViewById(R.id.button7);
        mybutton7.getLayoutParams().width = (WidthScreen/4);
        mybutton7.getLayoutParams().height = (WidthScreen/4);

        View mybutton8 = findViewById(R.id.button8);
        mybutton8.getLayoutParams().width = (WidthScreen/4);
        mybutton8.getLayoutParams().height = (WidthScreen/4);

        View mybutton9 = findViewById(R.id.button9);
        mybutton9.getLayoutParams().width = (WidthScreen/4);
        mybutton9.getLayoutParams().height = (WidthScreen/4);
        // on redefini la marge du gridlayout contenant les boutons


        GridLayout mygridlayout = (GridLayout) findViewById(R.id.GridLayout1);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout
                .LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 0;
        layoutParams.rightMargin = 0;
        layoutParams.topMargin = 0;
        layoutParams.bottomMargin = 0;
        layoutParams.gravity= Gravity.CENTER;

        mygridlayout.setLayoutParams(layoutParams);

        //GridLayout.LayoutParams params = new GridLayout.LayoutParams();
       //params.setMargins(0,0 ,(WidthScreen/4)/2 , (WidthScreen/4)/2);
        //mygridlayout.setLayoutParams(params);


        Log.d(TAG,"WIDTH SCREEN /4 /2 = "+(WidthScreen/4)/2+"");
        Log.d(TAG,"WIDTH SCREEN /4 = "+WidthScreen/4+"");




    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }



        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    public void PlayerSwitcher(){
        Log.d(TAG, "Player Switcher : "+joueur);
        switch (joueur){
            case 1: joueur=2;
                Log.d(TAG, "Player Switcher 1 à 2: "+joueur);
                break;
            case 2 : joueur=1;
                Log.d(TAG, "Player Switcher 2 à 1: "+joueur);
                break;
                    }

 // return joueur==1?2:1;
    }



    public void Status(){
      if (engine.getStatus().equals("gagne")){
          Log.d(TAG,"status gagne");
            showDialog(DIALOG_ALERT);}
        if (engine.getStatus().equals("egalite")){
            Log.d(TAG,"status égalité");
            showDialog(EGALITE);}

    }



    public void aa (View v){
        Log.d(TAG, "Bouton1");
        verif=engine.Engine(0,0,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button1);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
                //animation cross
            {
                Button CrossImage = (Button) findViewById(R.id.button1);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }

        }
        PlayerSwitcher();
        Status();
    }



    public void ab (View v){
        Log.d(TAG, "Bouton2");
        verif =engine.Engine(0,1,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button2);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button2);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }

    public  void ac (View v){
        Log.d(TAG, "Bouton3");
         verif =engine.Engine(0,2,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button3);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button3);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }

    public void ba (View v){
        Log.d(TAG, "Bouton4");
        verif =engine.Engine(1,0,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button4);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button4);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }

    public void bb (View v){
        Log.d(TAG, "Bouton5");
        verif =engine.Engine(1,1,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button5);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button5);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }

    public  void bc (View v){
        Log.d(TAG, "Bouton6");
        Boolean verif =engine.Engine(1,2,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button6);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button6);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }

    public void ca (View v){
        Log.d(TAG, "Bouton7");
        Boolean verif =engine.Engine(2,0,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button7);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button7);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }

    public void cb (View v){
        Log.d(TAG, "Bouton8");
        Boolean verif =engine.Engine(2,1,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button8);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button8);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }

    public  void cc (View v){
        Log.d(TAG, "Bouton9");
        Boolean verif =engine.Engine(2,2,joueur);
        if (verif == true) {
            if (joueur == 1)
            //animation circle
            {
                Button CrossImage = (Button) findViewById(R.id.button9);
                CrossImage.setBackgroundResource(R.drawable.circle_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
            if (joueur == 2)
            {
                Button CrossImage = (Button) findViewById(R.id.button9);
                CrossImage.setBackgroundResource(R.drawable.cross_animation);
                AnimationDrawable frameAnimation = (AnimationDrawable) CrossImage.getBackground();
                frameAnimation.start();
            }
        }
        PlayerSwitcher();
        Status();
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ALERT:
                // Create out AlterDialog
                Log.d(TAG,"Case gagne");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Joueur"+joueur+" à gagné");
                builder.setCancelable(false);
                builder.setPositiveButton("Nouvelle Partie", new OkOnClickListener());
                builder.setNegativeButton("Menu Principal", new CancelOnClickListener());
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case EGALITE:
                Log.d(TAG,"Case Egalité");
                 builder = new AlertDialog.Builder(this);
                builder.setMessage("Egalité");
                builder.setCancelable(false);
                builder.setPositiveButton("Nouvelle Partie", new OkOnClickListener());
                builder.setNegativeButton("Menu Principal", new CancelOnClickListener());
                dialog = builder.create();
                dialog.show();
                break;
        }
        return super.onCreateDialog(id);
    }

    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent(Morpion.this,MainActivity.class);
            startActivity(intent);

        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            onRestart();
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Morpion.this.recreate();
    }

}
