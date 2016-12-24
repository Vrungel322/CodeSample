package com.alexeymorozua.codesample.injection.components;

import com.alexeymorozua.codesample.injection.modules.AppModule;
import com.alexeymorozua.codesample.injection.modules.BusModule;
import com.alexeymorozua.codesample.injection.modules.DataManagerModule;
import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.mvp.data.DataManager;
import com.alexeymorozua.codesample.mvp.presenters.HomePresenter;
import com.alexeymorozua.codesample.mvp.presenters.RepositoriesPresenter;
import com.alexeymorozua.codesample.mvp.presenters.SignInPresenter;
import com.alexeymorozua.codesample.mvp.presenters.SplashPresenter;
import dagger.Component;

/**
 * Created by john on 25.11.2016.
 */

@PerApplication @Component(modules = { AppModule.class, DataManagerModule.class, BusModule.class })
public interface AppComponent {

  void inject(SignInPresenter presenter);

  void inject(SplashPresenter presenter);

  void inject(HomePresenter presenter);

  void inject(RepositoriesPresenter presenter);

  void inject(DataManager dataManager);
}
