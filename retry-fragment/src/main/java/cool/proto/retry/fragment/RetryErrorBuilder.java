package cool.proto.retry.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by moises on 17/11/16.
 */

public class RetryErrorBuilder {
    private String errorMessage;
    private String errorButtonMessage;

    private int errorIcon;
    private Bitmap errorIconBitmap;

    public RetryErrorBuilder(){
            /* Empty constructor */
    }

    public RetryErrorBuilder withButtonMessage(String buttonMessage){
        this.errorButtonMessage = buttonMessage;
        return this;
    }

    public RetryErrorBuilder withMessage(String message){
        this.errorMessage = message;
        return this;
    }

    public RetryErrorBuilder withIcon(int imageId){
        this.errorIcon = imageId;
        return this;
    }

    public RetryErrorBuilder withIcon(Bitmap imageBitmap){
        this.errorIconBitmap = imageBitmap;
        return this;
    }

    public RetryFragment build(){
        RetryFragment retryFragment = new RetryFragment();
        Bundle args = new Bundle();

        if (errorMessage != null){
            args.putString(RetryFragment.MESSAGE_KEY, errorMessage);
        }
        if (errorButtonMessage != null){
            args.putString(RetryFragment.BUTTON_MESSAGE_KEY, errorButtonMessage);
        }
        if (errorIconBitmap != null){
            args.putParcelable(RetryFragment.IMAGE_BITMAP_KEY, errorIconBitmap);
        } else if (errorIcon != 0){
            args.putInt(RetryFragment.IMAGE_KEY, errorIcon);
        }

        retryFragment.setArguments(args);
        return retryFragment;
    }
}