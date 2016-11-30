package cool.proto.android.retry.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cool.proto.retry.fragment.Retry;
import cool.proto.retry.fragment.builders.LoadingFragmentBuilder;
import cool.proto.retry.fragment.builders.RetryFragmentBuilder;
import cool.proto.retry.fragment.callbacks.RetryAsyncTaskCallback;
import cool.proto.retry.fragment.callbacks.RetryCriteriaCallback;
import cool.proto.retry.fragment.RetryMain;
import cool.proto.retry.fragment.RetryTaskRunner;
import cool.proto.retry.fragment.callbacks.RetrySyncTaskCallback;

/**
 * Created by moises on 26/09/16.
 */

public class SecondActivity extends AppCompatActivity implements
        RetryCriteriaCallback,
//        RetrySyncTaskCallback,
        RetryAsyncTaskCallback {

    RetryMain retryMain;
    private int numberOfRetries;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_second);

        retryMain = new RetryMain(this);
        retryMain.setCustomLoading(
                new LoadingFragmentBuilder()
                        .withMessage("JAHA")
                        .withDelayTime(2000)
                        .withIcon(android.R.drawable.ic_btn_speak_now)
                        .build())
                .startAsyncTask();
//                .startSyncTask();

    }

    @Override
    public boolean retryCriteria() {

        return numberOfRetries == 2;
    }

    @Override
    public void retryTask(RetryTaskRunner.TaskCompleteCallback taskCompleteCallback) {

        numberOfRetries++;
        retryMain.setCustomRetry(
                new RetryFragmentBuilder()
                        .withIcon(android.R.drawable.ic_delete)
                        .withMessage("HHUEHUE")
                        .build());
        taskCompleteCallback.taskCompleted();
    }

//    @Override
//    public void retryTask() {
//
//        numberOfRetries++;
//
//    }
}
