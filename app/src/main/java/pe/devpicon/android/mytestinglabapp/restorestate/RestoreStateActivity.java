package pe.devpicon.android.mytestinglabapp.restorestate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import pe.devpicon.android.mytestinglabapp.R;

import static pe.devpicon.android.mytestinglabapp.restorestate.RestoreStateFragment.EXTRA_KEY_DATA;
import static pe.devpicon.android.mytestinglabapp.restorestate.RestoreStateFragment.EXTRA_KEY_DESCRIPTION;

public class RestoreStateActivity extends AppCompatActivity {
    private static final String TAG = "RestoreStateActivity";
    private static final String FRAGMENT_SAVED_STATE = "fragment_saved_state";
    private static final String FRAGMENT_TAG = "fragment_tag";
    private FrameLayout frameLayoutFragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_state);

        frameLayoutFragmentContainer = findViewById(R.id.fragment_contrainer);

        loadFragment();
    }

    private void loadFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        RestoreStateFragment restoreStateFragment = new RestoreStateFragment();
        Bundle extraBundle = new Bundle();
        extraBundle.putInt(EXTRA_KEY_DATA, 123);
        extraBundle.putString(EXTRA_KEY_DESCRIPTION, "This is a initial description");
        restoreStateFragment.setArguments(extraBundle);
        fragmentTransaction.replace(R.id.fragment_contrainer, restoreStateFragment, FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        Fragment.SavedState savedState = supportFragmentManager.saveFragmentInstanceState(fragmentByTag);
        outState.putParcelable(FRAGMENT_SAVED_STATE, savedState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(FRAGMENT_SAVED_STATE)) {
                Fragment.SavedState savedState = savedInstanceState.getParcelable(FRAGMENT_SAVED_STATE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                if (fragmentByTag != null && fragmentByTag instanceof RestoreStateFragment) {
                    fragmentTransaction.remove(fragmentByTag);
                    fragmentByTag = new RestoreStateFragment();
                    fragmentByTag.setInitialSavedState(savedState);
                    fragmentTransaction.replace(R.id.fragment_contrainer, fragmentByTag, FRAGMENT_TAG);
                }
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
}
