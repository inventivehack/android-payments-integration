package androidtitlan.gdg.com.processpayments_example.payments.domain.usecase;

import android.util.Log;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardConekta;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardStripe;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.Card;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del dominio con los casos de uso para añadir un método de pago.
 */
public class AddCard extends UseCaseAddPayment<PaymentResponse> {

  public static final int TYPE_PAYMENT_STRIPE = 1;

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
    if (card.getTypePayment() == TYPE_PAYMENT_STRIPE) {
      return getObservableUseCaseStripe();
    } else {
      return getObservableUseCaseConekta();
    }
  }

  private Observable<PaymentResponse> getObservableUseCaseStripe() {
    return mRepository.addCardStripe(
        new CardStripe(card.getNumber(), Integer.parseInt(card.getExpMonth()),
            Integer.parseInt(card.getExtYear()), card.getCvc()));
  }

  private Observable<PaymentResponse> getObservableUseCaseConekta() {
    return mRepository.addCardConekta(
        new CardConekta(card.getNumber(), card.getCvc(), card.getExpMonth(), card.getExtYear()));
  }
}
