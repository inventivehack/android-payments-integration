package androidtitlan.gdg.com.processpayments_example.injector.modules;

import android.content.Context;
import androidtitlan.gdg.com.processpayments_example.PaymentsApplication;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsDataRepository;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * 04/05/16.
 */
@Module public class MainModule {

  private final PaymentsApplication application;

  public MainModule(PaymentsApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return application;
  }
}
