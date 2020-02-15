package com.tutorial.flipanim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout belowLayout;
    private CardView aboveLayout,btn;
    private int height;
    private boolean isFlipped=false;
    private OvershootInterpolator interpolator=new OvershootInterpolator();
    private int duration=400;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //initialize
        belowLayout = findViewById(R.id.belowLayout);
        aboveLayout = findViewById(R.id.aboveLayout);
        btn = findViewById(R.id.btn);

        aboveLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //fetch height
                height=(aboveLayout.getHeight())/2;
            }
        });

        //button click listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn.animate().setInterpolator(interpolator).translationY(50).start();

                aboveLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(height).start();
                belowLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(-1*height)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {

                                if(!isFlipped) {
                                    aboveLayout.setTranslationZ(-50);
                                    belowLayout.setTranslationZ(0);
                                    btn.animate().setInterpolator(interpolator).rotation(45).start();
                                }else {
                                    aboveLayout.setTranslationZ(0);
                                    belowLayout.setTranslationZ(-50);
                                    btn.animate().setInterpolator(interpolator).rotation(0).start();
                                }

                                aboveLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(0).start();
                                belowLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(0).start();
                                btn.animate().setInterpolator(interpolator).translationY(0).start();


                                isFlipped=!isFlipped;
                            }
                        })
                        .start();
            }
        });
        
    }
}
