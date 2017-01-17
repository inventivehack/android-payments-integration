package androidtitlan.gdg.com.processpayments_example.injector.modules;

import android.content.Context;
import androidtitlan.gdg.com.processpayments_example.PaymentsApplication;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsDataRepository;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Componente principal de la aplicación para brindar las dependencias necesarias a otros
 * componentes.
 *
 * @see <p>Para más información sobre inyección de dependencias, ver el siguiente post <a
 * href="http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/ "> Tasting Dagger 2 on
 * Android</a></p>
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
