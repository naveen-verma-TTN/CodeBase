package com.ttn.dagger2.ui.main;

/*
 * Created by Naveen Verma on 11/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ttn.dagger2.BaseActivity;
import com.ttn.dagger2.R;
import com.ttn.dagger2.ui.main.posts.PostsFragments;
import com.ttn.dagger2.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testFragment();
    }

    private void testFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new PostsFragments())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                sessionManager.logOut();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
