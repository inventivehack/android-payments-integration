package androidtitlan.gdg.com.processpayments_example.injector.modules;

import android.app.Activity;
import androidtitlan.gdg.com.processpayments_example.injector.PerActivity;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsDataRepository;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import dagger.Module;
import dagger.Provides;

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
