package cool.proto.retry.fragment.builders;

import android.graphics.Bitmap;
import android.os.Bundle;

import cool.proto.retry.fragment.fragments.LoadingFragment;

/**
 * Created by moises on 17/11/16.
 */

public class LoadingFragmentBuilder {

    private String customMessage;
    private int delayTime;
    private int icon;
    private Bitmap iconBitmap;

    public LoadingFragmentBuilder() {
        /* Empty constructor*/
    }

    public LoadingFragmentBuilder withMessage(String customMessage) {
        this.customMessage = customMessage;
        return this;
    }

    public LoadingFragmentBuilder withIcon(int iconId) {
        this.icon = iconId;
        return this;
    }

    public LoadingFragmentBuilder withIcon(Bitmap iconBitmap) {
        this.iconBitmap = iconBitmap;
        return this;
    }

    public LoadingFragmentBuilder withDelayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public LoadingFragment build() {
        LoadingFragment loadingFragment = new LoadingFragment();
        Bundle args = new Bundle();

        if (customMessage != null) {
            args.putString(LoadingFragment.MESSAGE_KEY, customMessage);
        }
        if (iconBitmap != null) {
            args.putParcelable(LoadingFragment.IMAGE_BITMAP_KEY, iconBitmap);
        } else {
            args.putInt(LoadingFragment.IMAGE_KEY, icon);
        }
        if (delayTime != 0) {
            args.putInt(LoadingFragment.DELAY_TIME_KEY, delayTime);
        }
        loadingFragment.setArguments(args);
        return loadingFragment;
    }
}
