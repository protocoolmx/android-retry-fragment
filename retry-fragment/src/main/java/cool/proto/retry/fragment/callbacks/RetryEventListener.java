package cool.proto.retry.fragment.callbacks;

/**
 * Created by moises on 25/11/16.
 */

/**
 * Listener for retry events
 */
public interface RetryEventListener {

    /**
     * Will be executed when {@link RetryCriteriaCallback#retryCriteria()} returns false.
     */
    void onRetryFailed();

    /**
     * Will be executed when {@link RetryCriteriaCallback#retryCriteria()} returns true.
     */
    void onRetrySucceed();

}