package androidtitlan.gdg.com.processpayments_example.injector.modules;

import android.content.Context;
import androidtitlan.gdg.com.processpayments_example.PaymentsApplication;
import androidtitlan.gdg.com.processpayments_example.common.domain.UseCase;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsDataRepository;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import androidtitlan.gdg.com.processpayments_example.payments.domain.usecase.GetPayments;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Singleton;

/**
 * 04/05/16.
 */
@Module public class MainModule {

  private final PaymentsApplication application;

  public MainModule(PaymentsApplication application) {
    this.application = application;
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return application;
  }

  @Provides @Singleton
  public PaymentsRepository provideActionRepository(PaymentsDataRepository actionsRepository) {
    return actionsRepository;
  }

  @Provides @Singleton
  public UseCase<List<PaymentResponse>> provideGetPaymentsUseCase(GetPayments getPayments) {
    return getPayments;
  }
}
