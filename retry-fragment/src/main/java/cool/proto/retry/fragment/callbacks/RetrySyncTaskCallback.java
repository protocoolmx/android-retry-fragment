package cool.proto.retry.fragment.callbacks;

/**
 * Created by moises on 25/11/16.
 */

import cool.proto.retry.fragment.RetryTaskRunner;

/**
 * Retry task for automatic flow, when finished we will call
 * {@link RetryTaskRunner.TaskCompleteCallback}, usually used
 * when we want to run synchronous task.
 *
 * @implNote All executions of {@link RetrySyncTaskCallback#retryTask()}
 * will be executed on background.
 */
public interface RetrySyncTaskCallback extends RetryTaskCallback {

    /**
     * Task to execute before running {@link RetryCriteriaCallback#retryCriteria()}.
     */
    void retryTask();
}