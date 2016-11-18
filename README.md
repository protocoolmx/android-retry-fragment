# Retry-Fragment
## 0.3.0

General retry fragment with available customization for the loading image, loading message, optional retry image, retry message, and retry button text.

## Add to dependencies:

```java
compile 'cool.proto:retry-fragment:0.3.0'
```

## How to use:

First you must include the `retry_fragment_container` in your xml layout at the same level of your content.

Also you must have your content and the include inside a RelativeLayout with context of the activity, in case you don't have have you must add it.

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:layout_behavior="@string/appbar_scrolling_view_behavior"
                tools:context=".MainActivity">

<!-- this is the content -->
                <android.support.v7.widget.RecyclerView
                    android:id="@+id/content"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"/>

<!-- and this is the include you must add -->
                <include layout="@layout/retry_fragment_container"/>
```

Then in the activity you must add the listeners.

```java
LoadingFragment.OnLoadingListener, RetryFragment.OnRetryListener



    public void onLoadingFinish() {
        //do something while loading callback
    }

    public void onRetry() {
        //restart loading or do something else
    }
```

### Example

**NOTE:** the **usage** module is an android project you can launch to check how it works.

You can declare a variable for the LoadingFragment, and the RetryFragment but they are not necessary.

```java
    private LoadingFragment loadingFragment;
```
In the onCreate of the activity you can add a method for easier reading.

```java
  @Override
  public void onCreate(Bundle bundle){
      super.onCreate(bundle);
      setContentView(R.layout.activity);

      // something else

      loading();
  }
```

And this is the method of loading.

```java
    public void loading() {
      LoadingContainer.loadingStart(this);

      loadingFragment = new LoadingBuilder()
              .withIcon(android.R.drawable.btn_star)
              .withMessage("LOADING")
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
                    .withButtonMessage("Retry?")
                    .withIcon(android.R.drawable.ic_media_ff)
                    .build().show(this);
        }
    }

    public void onRetry() {
        //restart loading or do something else
        loadingFragment.show(this);
        i += 1;
    }
```

**NOTE:** All options of the Builder are optionals except for build(), you can omit whatever you don't need.

The order above is first we appear the fragment container with `Loadingcontainer.loadingStart(this)`. Then we use the `LoadingBuilder()` to create a `LoadingFragment` with custom attributes and use show so it appears. then after the delay the `onLoadingFinish()` code is executed in which we check if `i == 2` then we disappear the fragment container with `Loadingcontainer.loadingEnd(this)`. if i isn't 2 yet we execute the `RetryErrorBuilder()` the same way we used the `LoadingBuilder()`, this show us the error screen with a button that executes the `onRetry()` function and this simply restarts the `loadingFragment` and adds 1 to i.

Make sure to have a `Loadingcontainer.loadingStart(this);`
before the `loadingFragment`.

And that's it. Now all the things you wish to do in the loading fragment you do them in the `onLoadingFinished`.

And if there is an error you call the you replace the fragment with the error fragment. and the `onRetry()` listener happens when the user clicks on the retry button so you can also control what to do.

### Done by:

Moises Salas

moises.salas@proto.cool

moisesgsaa@gmail.com
