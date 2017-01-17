package androidtitlan.gdg.com.processpayments_example.payments.domain.usecase;

import android.util.Log;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.Card;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del dominio con los casos de uso para añadir una tarjeta a Stripe o Conekta.
 */
public class AddCard extends UseCaseAddPayment<PaymentResponse> {

  private PaymentsRepository mRepository;
  private Card card;

  @Inject public AddCard(PaymentsRepository repository) {
    mRepository = repository;
  }

  public void executeAddCard(String numberCard, String month, String year, String cvv,
      int typePayment, Subscriber<PaymentResponse> useCaseSubscriber) {
    card = new Card(numberCard, month, year, cvv, typePayment);
    Log.d(AddCard.class.getSimpleName(), card.toString());
    super.execute(useCaseSubscriber);
  }

  @Override protected Observable<PaymentResponse> buildObservableUseCase() {
    return mRepository.addCard(
        new CardEntity(card.getNumber(), card.getCvc(), card.getExpMonth(), card.getExtYear(),
            card.getTypePayment()));
  }
}
