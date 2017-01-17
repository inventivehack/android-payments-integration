package androidtitlan.gdg.com.processpayments_example.payments.domain.usecase;

import androidtitlan.gdg.com.processpayments_example.common.domain.UseCase;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del dominio con los casos de uso para obtener los métodos de pago.
 */
public class GetPayments extends UseCase<List<PaymentResponse>> {

  private PaymentsRepository mPaymentsRepository;

  @Inject
  public GetPayments(PaymentsRepository paymentsRepository) {
    mPaymentsRepository = paymentsRepository;
  }

  public void execute(Subscriber<List<PaymentResponse>> useCaseSubscriber) {
    super.execute(useCaseSubscriber);
  }

  @Override protected Observable<List<PaymentResponse>> buildObservableUseCase() {
    return mPaymentsRepository.getPayments();
  }
}
