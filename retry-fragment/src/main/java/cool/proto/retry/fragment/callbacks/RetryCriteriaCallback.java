package cool.proto.retry.fragment.callbacks;

/**
 * Created by moises on 25/11/16.
 */

/**
 * Used to decide which method of {@link RetryEventListener} to call.
 */
public interface RetryCriteriaCallback {

    /**
     * @return if <code>true</code> internally calls {@link RetryEventListener#onRetrySucceed()}
     * otherwise {@link RetryEventListener#onRetryFailed()}.
     */
    boolean retryCriteria();
}