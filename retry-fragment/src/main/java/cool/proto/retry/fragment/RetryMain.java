package cool.proto.retry.fragment;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import cool.proto.retry.fragment.builders.LoadingFragmentBuilder;
import cool.proto.retry.fragment.builders.RetryFragmentBuilder;
import cool.proto.retry.fragment.callbacks.RetryAsyncTaskCallback;
import cool.proto.retry.fragment.callbacks.RetryCriteriaCallback;
import cool.proto.retry.fragment.callbacks.RetryEventListener;
import cool.proto.retry.fragment.callbacks.RetrySyncTaskCallback;
import cool.proto.retry.fragment.fragments.LoadingFragment;
import cool.proto.retry.fragment.fragments.RetryFragment;

/**
 * Created by moises on 28/11/16.
 */

public class RetryMain implements
        LoadingFragment.OnLoadingListener,
        RetryFragment.OnRetryListener,
        RetryEventListener {

    private LoadingFragment loadingFragment;
    private RetryFragment retryFragment;
    private AppCompatActivity activity;
    private Retry retry;

    public RetryMain(Activity activity) {
        this.activity = (AppCompatActivity) activity;

        retry = new Retry();
        retry.registerRetryCriteriaCallback((RetryCriteriaCallback) activity);
        retry.registerRetryEventListener(this);

        try {
            retry.registerRetryEventListener((RetryEventListener) activity);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        this.loadingFragment = new LoadingFragmentBuilder().build();
        loadingFragment.setCallBack(this);
        this.retryFragment = new RetryFragmentBuilder().build();
        retryFragment.setCallBack(this);
    }

    public void startAsyncTask() {
        retry.registerRetryTask((RetryAsyncTaskCallback) this.activity);
        LoadingContainer.loadingStart(activity);
        loadingFragment.show(activity);
    }

    public void startSyncTask() {
        retry.registerRetryTask((RetrySyncTaskCallback) this.activity);
        LoadingContainer.loadingStart(activity);
        loadingFragment.show(activity);
    }

    public RetryMain setCustomLoading(LoadingFragment loadingFragment) {
        this.loadingFragment = loadingFragment;
        this.loadingFragment.setCallBack(this);
        return this;
    }

    public RetryMain setCustomRetry(RetryFragment retryFragment) {
        this.retryFragment = retryFragment;
        this.retryFragment.setCallBack(this);
        return this;
    }

    public void onLoadingFinish() {
        // Do something while loading callback
        retry.exec();
    }

    public void onRetry() {
        // Restart loading
        loadingFragment.show(activity);
    }

    //show error
    @Override
    public void onRetryFailed() {
        retryFragment.show(activity);
    }

    // disappear frame layout
    @Override
    public void onRetrySucceed() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadingContainer.loadingEnd(activity);
            }
        });
    }

}
