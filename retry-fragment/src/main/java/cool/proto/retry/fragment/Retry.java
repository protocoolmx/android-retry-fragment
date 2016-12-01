package cool.proto.retry.fragment;

/**
 * Created by moises on 25/11/16.
 */


import cool.proto.retry.fragment.callbacks.RetryAsyncTaskCallback;
import cool.proto.retry.fragment.callbacks.RetryCriteriaCallback;
import cool.proto.retry.fragment.callbacks.RetryEventListener;
import cool.proto.retry.fragment.callbacks.RetrySyncTaskCallback;
import cool.proto.retry.fragment.callbacks.RetryTaskCallback;

/**
 * Integration of callbacks to handle flow of `Retry` pattern.
 */
public class Retry implements RetryTaskRunner.TaskCompleteCallback {

    private RetryEventListener retryEventListener;
    private RetryCriteriaCallback retryCriteriaCallback;
    private RetryTaskCallback retryTaskCallback;

    public Retry() {
    }

    /**
     * Starts retry flow and executes retry task in background.
     */
    public void exec() {
        RetryTaskRunner retryTaskRunner = new RetryTaskRunner(retryTaskCallback);
        retryTaskRunner.registerTaskCompleteCallback(this);

        new Thread(retryTaskRunner).start();
    }

    /**
     * Set instance of {@link RetryEventListener}
     *
     * @param retryEventListener listener for retry task events.
     */
    public void registerRetryEventListener(RetryEventListener retryEventListener) {
        this.retryEventListener = retryEventListener;
    }

    /**
     * Set instance of {@link RetryCriteriaCallback}
     *
     * @param retryCriteriaCallback callback for retry criteria.
     */
    public void registerRetryCriteriaCallback(RetryCriteriaCallback retryCriteriaCallback) {
        this.retryCriteriaCallback = retryCriteriaCallback;
    }

    /**
     * Set instance of {@link RetrySyncTaskCallback}
     *
     * @param retrySyncTaskCallback callback for synchronous retry task.
     */
    public void registerRetryTask(RetrySyncTaskCallback retrySyncTaskCallback) {
        this.retryTaskCallback = retrySyncTaskCallback;
    }

    /**
     * * Set instance of {@link RetryAsyncTaskCallback}
     *
     * @param retryAsyncTaskCallback callback for asynchronous retry task.
     */
    public void registerRetryTask(RetryAsyncTaskCallback retryAsyncTaskCallback) {
        this.retryTaskCallback = retryAsyncTaskCallback;
    }

    @Override
    public void taskCompleted() {
        // If implementation of `retryCriteria()` returns
        // `false` then call `onRetrySucceed()` otherwise
        // call `onRetryFailed()`.
        if (retryCriteriaCallback.retryCriteria()) {
            retryEventListener.onRetrySucceed();
        } else {
            retryEventListener.onRetryFailed();
        }
    }

}

