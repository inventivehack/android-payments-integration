package androidtitlan.gdg.com.processpayments_example.injector.modules;

import android.app.Activity;
import androidtitlan.gdg.com.processpayments_example.injector.PerActivity;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsDataRepository;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Componente de los métodos de pago para crear las dependencias necesarias para la actividad y
 * fragmentos.
 *
 * @see <p>Para más información sobre inyección de dependencias, ver el siguiente post <a
 * href="http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/ "> Tasting Dagger 2 on
 * Android</a></p>
 */
@Module public class PaymentsModule {

  private final Activity activity;

  public PaymentsModule(Activity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity Activity provideActivity() {
    return activity;
  }

  @Provides @PerActivity PaymentsRepository provideActionRepository(
      PaymentsDataRepository actionsRepository) {
    return actionsRepository;
  }
}
