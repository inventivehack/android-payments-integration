package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import android.app.Activity;
import androidtitlan.gdg.com.processpayments_example.BuildConfig;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataImple;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataLocal;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.exception.CardException;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del server para las operaciones al manejar las opciones de pago.
 */
public class CloudAddCardStripeDataSource implements AddCardDataSource {

  private static final String LOG_TAG = CloudAddCardStripeDataSource.class.getSimpleName();


  /**
   * Añadimos una tarjeta con Stripe, pero antes de eso hace las validaciones con {@link Card} e
   * igualmente si ocurre un error.
   *
   * @see <p>Para más información investigar más sobre <a href=" https://stripe.com/docs/mobile/android">Stripe</a>.
   * </p>
   */
  @Override public Observable<PaymentEntity> addCard(CardEntity cardEntity) {
    return Observable.create(new Observable.OnSubscribe<PaymentEntity>() {
      @Override public void call(Subscriber<? super PaymentEntity> subscriber) {

        Card card = cardEntity.provideCardStripe();

        if (validateCard(card, subscriber)) {
          new Stripe().createToken(card, BuildConfig.STRIPE_KEY, new TokenCallback() {
            @Override public void onError(Exception error) {
              subscriber.onError(
                  new CardException(error.getMessage(), CardException.CARD_SERVER_ERROR));
            }

            @Override public void onSuccess(Token token) {
              subscriber.onNext(new PaymentEntity(card.getLast4(), card.getType(), token.getId()));
              subscriber.onCompleted();
            }
          });
        }
      }
    }).doOnNext(this::savePayment);
  }

  private boolean validateCard(Card card, Subscriber subscriber) {
    if (!card.validateNumber()) {
      subscriber.onError(new CardException(CardException.MESSAGE_INVALIDATE_NUMBER_ERROR,
          CardException.INVALIDATE_NUMBER_ERROR));
      return false;
    } else if (!card.validateExpiryDate()) {
      subscriber.onError(new CardException(CardException.MESSAGE_INVALIDATE_DATE_ERROR,
          CardException.INVALIDATE_DATE_ERROR));
      return false;
    } else if (!card.validateCVC()) {
      subscriber.onError(new CardException(CardException.MESSAGE_INVALIDATE_CVC_ERROR,
          CardException.INVALIDATE_CVC_ERROR));
      return false;
    } else if (!card.validateCard()) {
      subscriber.onError(new CardException(CardException.MESSAGE_INVALIDATE_DETAIL_ERROR,
          CardException.INVALIDATE_DETAIL_ERROR));
      return false;
    }

    return true;
  }

  private void savePayment(PaymentEntity paymentEntity) {
    PaymentDataLocal paymentDataLocal = new PaymentDataImple();
    paymentDataLocal.savePayment(paymentEntity);
  }
}