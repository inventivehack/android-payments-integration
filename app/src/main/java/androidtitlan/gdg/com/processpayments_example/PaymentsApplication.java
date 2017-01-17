package androidtitlan.gdg.com.processpayments_example;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import androidtitlan.gdg.com.processpayments_example.injector.components.DaggerMainComponent;
import androidtitlan.gdg.com.processpayments_example.injector.components.MainComponent;
import androidtitlan.gdg.com.processpayments_example.injector.modules.MainModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * {@link Application} que inicia la configuración de {@link Realm} y el {@link MainComponent} para
 * la injección de dependencias.
 */
public class PaymentsApplication extends MultiDexApplication {

  private MainComponent mainComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjector();
    setupRealm();
  }

  private void initializeInjector() {
    mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
  }

  public MainComponent getMainComponent() {
    return mainComponent;
  }

  private void setupRealm() {
    RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
    Realm.setDefaultConfiguration(realmConfiguration);
  }
}
