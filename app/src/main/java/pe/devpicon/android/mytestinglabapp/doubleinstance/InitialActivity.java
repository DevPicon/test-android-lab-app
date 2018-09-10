package pe.devpicon.android.mytestinglabapp.doubleinstance;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import pe.devpicon.android.mytestinglabapp.R;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewResult, textViewHashCode, textViewStack;
    private Button buttonA, buttonB, buttonC, buttonD, buttonE;
    private CheckBox checkBoxReorderToFront, checkBoxSingleTask;

    private int hashcode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        textViewResult = (TextView) findViewById(R.id.text_view_result);
        textViewHashCode = (TextView) findViewById(R.id.text_view_hashcode);
        checkBoxReorderToFront = (CheckBox) findViewById(R.id.checkbox_reorder_to_front);
        textViewStack = (TextView) findViewById(R.id.text_view_stack);
        checkBoxSingleTask = (CheckBox) findViewById(R.id.checkbox_singleTask);

        buttonA = (Button) findViewById(R.id.button_open_initial);
        buttonB = (Button) findViewById(R.id.button_open_second);
        buttonC = (Button) findViewById(R.id.button_open_third);
        buttonD = (Button) findViewById(R.id.button_open_fourth);
        buttonE = (Button) findViewById(R.id.button_open_fifth);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);

        hashcode = this.hashCode();
        textViewHashCode.setText(getString(R.string.hash_code, hashcode));
        textViewResult.setText(getClass().getCanonicalName());

        textViewStack.setText(printActivityStack());

    }

    private String printActivityStack() {
        StringBuilder stack = new StringBuilder("Stack:::\n");
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        assert activityManager != null;
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = activityManager.getRunningTasks(10);
        Iterator<ActivityManager.RunningTaskInfo> itr = runningTaskInfoList.iterator();
        while (itr.hasNext()) {
            ActivityManager.RunningTaskInfo runningTaskInfo = itr.next();
            int id = runningTaskInfo.id;
            CharSequence desc = runningTaskInfo.description;
            int numOfActivities = runningTaskInfo.numActivities;
            String topActivity = runningTaskInfo.topActivity
                    .getShortClassName();

            stack.append("[id=")
                    .append(id)
                    .append(", desc=").append(desc)
                    .append(",numOfActivities=").append(numOfActivities)
                    .append(",topActivity=").append(topActivity).append("]\n");
        }

        stack.append("\n");
        return stack.toString();
    }


    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.button_open_initial:
                intent = new Intent(this, InitialActivity.class);

                if (checkBoxReorderToFront.isChecked()) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                }
                break;
            case R.id.button_open_second:
                intent = new Intent(this, SecondActivity.class);

                if (checkBoxReorderToFront.isChecked()) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                }
                break;
            case R.id.button_open_third:
                intent = new Intent(this, ThirdActivity.class);

                if (checkBoxReorderToFront.isChecked()) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                }
                break;
            case R.id.button_open_fourth:
                intent = new Intent(this, FourthActivity.class);

                if (checkBoxReorderToFront.isChecked()) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                }
                break;
            case R.id.button_open_fifth:
            default:
                intent = new Intent(this, FifthActivity.class);
                break;

        }

        startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "onNewIntent", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
}
