package cool.proto.retry.fragment.fragments;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cool.proto.retry.fragment.R;

/**
 * Created by moises on 26/09/16.
 */

public class RetryFragment extends Fragment {

    public static final String MESSAGE_KEY = "message";
    public static final String IMAGE_KEY = "image";
    public static final String IMAGE_BITMAP_KEY = "imageBitmap";
    public static final String BUTTON_MESSAGE_KEY = "button";


    private OnRetryListener callBack;

    private View view;
    private Bundle args;
    private String errorMessage;
    private String buttonMessage;
    private int imageId;

    public interface OnRetryListener {
        void onRetry();
    }

    public void setCallBack(OnRetryListener callBack) {
        this.callBack = callBack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        view = inflater.inflate(R.layout.fragment_retry, container, false);

        args = getArguments();
        errorMessage = getString(R.string.cool_proto_surf_retry_fragment_retry_message);
        buttonMessage = getString(R.string.cool_proto_surf_retry_fragment_retry_button);

        if (args != null) {
            customize();
        }

        Button button = (Button) view.findViewById(R.id.btn_retry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onRetry();
            }
        });
        return view;
    }

    private void customize() {
        if (!args.getString(MESSAGE_KEY, errorMessage).equals(errorMessage)) {
            errorMessage = args.getString(MESSAGE_KEY);
            TextView messageText = (TextView) view.findViewById(R.id.retry_message);
            messageText.setText(errorMessage);
        } else {
            TextView messageText = (TextView) view.findViewById(R.id.retry_message);
            messageText.setText(errorMessage);
        }
        if (args.getParcelable(IMAGE_BITMAP_KEY) != null) {
            Bitmap bitmap = args.getParcelable(IMAGE_BITMAP_KEY);
            ImageView imageView = (ImageView) view.findViewById(R.id.retry_image);
            imageView.setImageBitmap(bitmap);
        } else if (args.getInt(IMAGE_KEY, 0) != 0) {
            imageId = args.getInt(IMAGE_KEY);
            ImageView imageView = (ImageView) view.findViewById(R.id.retry_image);
            imageView.setImageResource(imageId);
        }
        if (!args.getString(BUTTON_MESSAGE_KEY, buttonMessage).equals(buttonMessage)) {
            buttonMessage = args.getString(BUTTON_MESSAGE_KEY);
            Button button = (Button) view.findViewById(R.id.btn_retry);
            button.setText(buttonMessage);
        }
    }

    public RetryFragment show(AppCompatActivity activity) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, this)
                .commit();
        return this;
    }


}
