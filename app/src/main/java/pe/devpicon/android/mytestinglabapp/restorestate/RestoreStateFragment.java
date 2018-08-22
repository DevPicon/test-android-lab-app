package pe.devpicon.android.mytestinglabapp.restorestate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import pe.devpicon.android.mytestinglabapp.R;

public class RestoreStateFragment extends Fragment {
    private static final String TAG = "RestoreStateFragment";
    public static final String EXTRA_KEY_DATA = "key_data";
    public static final String EXTRA_KEY_DESCRIPTION = "key_data_description";

    private EditText editTextDataId, editTextDataDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restore_state, container, false);
        editTextDataId = rootView.findViewById(R.id.edit_text_data_id);
        editTextDataDescription = rootView.findViewById(R.id.edit_text_data_description);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            editTextDataId.setText(String.valueOf(arguments.getInt(EXTRA_KEY_DATA)));
            editTextDataDescription.setText(arguments.getString(EXTRA_KEY_DESCRIPTION));
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        int value = (TextUtils.isEmpty(editTextDataId.getText().toString())) ? 0 : Integer.parseInt(editTextDataId.getText().toString());
        String description = (TextUtils.isEmpty(editTextDataDescription.getText())) ? "Empty" : editTextDataDescription.getText().toString();
        outState.putInt(EXTRA_KEY_DATA, value);
        outState.putString(EXTRA_KEY_DESCRIPTION, description);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewStateRestored() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXTRA_KEY_DATA)) {
                int anInt = savedInstanceState.getInt(EXTRA_KEY_DATA);
                String anString = savedInstanceState.getString(EXTRA_KEY_DESCRIPTION);

                editTextDataId.setText(String.valueOf(anInt));
                editTextDataDescription.setText(anString);
            }
        }
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView() called");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
}
