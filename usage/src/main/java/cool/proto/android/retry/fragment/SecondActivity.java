package cool.proto.android.retry.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.FrameLayout;

import cool.proto.retry.fragment.LoadingBuilder;
import cool.proto.retry.fragment.LoadingContainer;
import cool.proto.retry.fragment.LoadingFragment;
import cool.proto.retry.fragment.RetryErrorBuilder;
import cool.proto.retry.fragment.RetryFragment;

/**
 * Created by moises on 26/09/16.
 */

public class SecondActivity extends AppCompatActivity implements LoadingFragment.OnLoadingListener,
        RetryFragment.OnRetryListener {

    private LoadingFragment loadingFragment;

    private int i = 0;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_second);

        loading();
    }

    public void loading() {
        LoadingContainer.loadingStart(this);

        loadingFragment = new LoadingBuilder()
                .withIcon(android.R.drawable.btn_star)
                .withMessage("Hola")
                .withDelayTime(1000)
                .build().show(this);
    }

    public void onLoadingFinish() {
        // Do something while loading callback
        if (i == 2) {
            LoadingContainer.loadingEnd(this);
        } else {
            new RetryErrorBuilder()
                    .withMessage("custom error message")
                    .withButtonMessage("JAJA")
                    .withIcon(android.R.drawable.ic_media_ff)
                    .build().show(this);
        }
    }

    public void onRetry() {
        // Restart loading
        loadingFragment.show(this);
        i += 1;
    }
}
