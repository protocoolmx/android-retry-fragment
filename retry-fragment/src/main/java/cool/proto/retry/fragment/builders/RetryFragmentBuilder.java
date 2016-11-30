package cool.proto.retry.fragment.builders;

import android.graphics.Bitmap;
import android.os.Bundle;

import cool.proto.retry.fragment.fragments.RetryFragment;

/**
 * Created by moises on 17/11/16.
 */

public class RetryFragmentBuilder {
    private String errorMessage;
    private String errorButtonMessage;

    private int errorIcon;
    private Bitmap errorIconBitmap;

    public RetryFragmentBuilder() {
            /* Empty constructor */
    }

    public RetryFragmentBuilder withButtonMessage(String buttonMessage) {
        this.errorButtonMessage = buttonMessage;
        return this;
    }

    public RetryFragmentBuilder withMessage(String message) {
        this.errorMessage = message;
        return this;
    }

    public RetryFragmentBuilder withIcon(int imageId) {
        this.errorIcon = imageId;
        return this;
    }

    public RetryFragmentBuilder withIcon(Bitmap imageBitmap) {
        this.errorIconBitmap = imageBitmap;
        return this;
    }

    public RetryFragment build() {
        RetryFragment retryFragment = new RetryFragment();
        Bundle args = new Bundle();

        if (errorMessage != null) {
            args.putString(RetryFragment.MESSAGE_KEY, errorMessage);
        }
        if (errorButtonMessage != null) {
            args.putString(RetryFragment.BUTTON_MESSAGE_KEY, errorButtonMessage);
        }
        if (errorIconBitmap != null) {
            args.putParcelable(RetryFragment.IMAGE_BITMAP_KEY, errorIconBitmap);
        } else if (errorIcon != 0) {
            args.putInt(RetryFragment.IMAGE_KEY, errorIcon);
        }

        retryFragment.setArguments(args);
        return retryFragment;
    }
}