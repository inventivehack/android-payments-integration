package androidtitlan.gdg.com.processpayments_example.payments.domain.usecase;

import androidtitlan.gdg.com.processpayments_example.common.domain.UseCase;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import com.stripe.android.model.Card;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del dominio con los casos de uso para añadir un método de pago.
 */
public class AddStripeCard extends UseCaseAddPayment<PaymentResponse> {

  private PaymentsRepository mRepository;
  private Card card;

  @Inject
  public AddStripeCard(PaymentsRepository repository) {
    mRepository = repository;
  }

  public void executeAddCardStripe(String numberCard, int month, int year, String cvv,
      Subscriber<PaymentResponse> useCaseSubscriber) {
    card = new Card(numberCard, month, year, cvv);
    super.execute(useCaseSubscriber);
  }

  @Override protected Observable<PaymentResponse> buildObservableUseCase() {
    return mRepository.addCardStripe(card);
  }
}
