package androidtitlan.gdg.com.processpayments_example;

import android.app.Application;
import androidtitlan.gdg.com.processpayments_example.injector.components.DaggerMainComponent;
import androidtitlan.gdg.com.processpayments_example.injector.components.MainComponent;
import androidtitlan.gdg.com.processpayments_example.injector.modules.MainModule;

/**
 * 04/12/2016.
 */

public class PaymentsApplication extends Application {

  private MainComponent mainComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjector();
  }

  private void initializeInjector() {
    mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
  }

  public MainComponent getMainComponent() {
    return mainComponent;
  }
}
