package com.ttn.dagger2.ui.main;

/*
 * Created by Naveen Verma on 11/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ttn.dagger2.BaseActivity;
import com.ttn.dagger2.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show();
    }
}
