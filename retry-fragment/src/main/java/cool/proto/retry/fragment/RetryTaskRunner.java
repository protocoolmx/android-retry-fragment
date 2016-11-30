package cool.proto.retry.fragment;

/**
 * Created by moises on 25/11/16.
 */


import cool.proto.retry.fragment.callbacks.RetryAsyncTaskCallback;
import cool.proto.retry.fragment.callbacks.RetrySyncTaskCallback;
import cool.proto.retry.fragment.callbacks.RetryTaskCallback;

/**
 * Runner for synchronous and asynchronous tasks.
 */
public class RetryTaskRunner implements Runnable {

    /**
     * Listener for when retry tasks have finished.
     */
    public interface TaskCompleteCallback {

        /**
         * Will be automatically executed after {@link RetrySyncTaskCallback#retryTask()}
         */
        void taskCompleted();
    }

    private RetryTaskCallback retryTaskCallback;
    private TaskCompleteCallback taskCompleteCallback;

    /**
     * @param retryTaskCallback retry task interface instance
     */
    public RetryTaskRunner(RetryTaskCallback retryTaskCallback) {
        this.retryTaskCallback = retryTaskCallback;
    }

    /**
     * Set instance of {@link TaskCompleteCallback}
     *
     * @param taskCompleteCallback listener for task completion.
     */
    public void registerTaskCompleteCallback(TaskCompleteCallback taskCompleteCallback) {
        this.taskCompleteCallback = taskCompleteCallback;
    }

    /**
     * @return <code>true</code> if {@link this#retryTaskCallback} if of type
     * {@link RetryAsyncTaskCallback}, <code>false</code> otherwise.
     */
    private boolean isRetryAsyncTaskCallback() {
        return this.retryTaskCallback instanceof RetryAsyncTaskCallback;
    }

    @Override
    public void run() {
        if (isRetryAsyncTaskCallback()) {
            // Call `retryTask()` with `taskCompleteCallback` to let user
            // indicate when to proceed.
            ((RetryAsyncTaskCallback) retryTaskCallback).retryTask(taskCompleteCallback);
        } else {
            // Execute `retryTask()` and when finished call `taskCompleted()`.
            try {
                ((RetrySyncTaskCallback) retryTaskCallback).retryTask();
            } finally {
                taskCompleteCallback.taskCompleted();
            }
        }
    }

}
