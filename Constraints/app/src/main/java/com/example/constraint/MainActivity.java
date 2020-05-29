package com.example.constraint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout layout;
    Placeholder placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout);
        placeholder = findViewById(R.id.placeholder);
    }

    public void onSwap(View view) {
        TransitionManager.beginDelayedTransition(layout);
        placeholder.setContentId(view.getId());
    }

}
