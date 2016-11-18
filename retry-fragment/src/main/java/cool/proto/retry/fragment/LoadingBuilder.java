package cool.proto.retry.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by moises on 17/11/16.
 */

public class LoadingBuilder {

    private String customMessage;
    private int delayTime;
    private int icon;
    private Bitmap iconBitmap;

    public LoadingBuilder(){
        /* Empty constructor*/
    }

    public LoadingBuilder withMessage(String customMessage){
        this.customMessage = customMessage;
        return this;
    }

    public LoadingBuilder withIcon(int iconId){
        this.icon = iconId;
        return this;
    }

    public LoadingBuilder withIcon(Bitmap iconBitmap){
        this.iconBitmap = iconBitmap;
        return this;
    }

    public LoadingBuilder withDelayTime(int delayTime){
        this.delayTime = delayTime;
        return this;
    }

    public LoadingFragment build(){
        LoadingFragment loadingFragment = new LoadingFragment();
        Bundle args = new Bundle();

        if (customMessage != null){
            args.putString(LoadingFragment.MESSAGE_KEY, customMessage);
        }
        if (iconBitmap != null){
            args.putParcelable(LoadingFragment.IMAGE_BITMAP_KEY, iconBitmap);
        } else {
            args.putInt(LoadingFragment.IMAGE_KEY, icon);
        }
        if (delayTime != 0){
            args.putInt(LoadingFragment.DELAY_TIME_KEY, delayTime);
        }
        loadingFragment.setArguments(args);
        return loadingFragment;
    }
}
