package pe.devpicon.android.mytestinglabapp.doubleinstance;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import pe.devpicon.android.mytestinglabapp.R;

public class SingleResultActivity extends AppCompatActivity {

    private int hashcode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_result);

        TextView textViewHashCode = (TextView) findViewById(R.id.text_view_hashcode);
        TextView textViewResult = (TextView) findViewById(R.id.text_view_result);
        TextView textViewStack = (TextView) findViewById(R.id.text_view_stack);


        hashcode = this.hashCode();
        textViewHashCode.setText(getString(R.string.hash_code, hashcode));
        textViewResult.setText(getClass().getCanonicalName());

        textViewStack.setText(printActivityStack());
    }

    public void finishWithResult(View view) {
        setResult(RESULT_OK);
        finish();
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
}
