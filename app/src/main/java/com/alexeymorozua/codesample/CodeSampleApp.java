package com.alexeymorozua.codesample;

import android.app.Application;
import com.alexeymorozua.codesample.injection.components.AppComponent;
import com.alexeymorozua.codesample.injection.components.DaggerAppComponent;
import com.alexeymorozua.codesample.injection.modules.AppModule;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by john on 24.11.2016.
 */

public class CodeSampleApp extends Application {

  private static AppComponent sAppComponent;

  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Fabric.with(this, new Crashlytics());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }
}
