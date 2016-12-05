package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.exception.StripeException;
import com.stripe.android.model.Card;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del server para las operaciones al manejar las opciones de pago.
 */
public class CloudAddPaymentsDataSource implements AddPaymentsDataSource {

  /**
   * Añadimos una tarjeta con Stripe, pero antes de eso hace las validaciones con {@link Card} e
   * igualmente si ocurre un error.
   *
   * @see <p>Para más información investigar más sobre <a href=" https://stripe.com/docs/mobile/android">Stripe</a>.
   * </p>
   */
  @Override public Observable<PaymentEntity> addCardStripe(Card cardStripeEntity) {
    //return Observable.create(subscriber -> {
    //  if (validateCard(cardStripeEntity, subscriber)) {
    //    new Stripe().createToken(cardStripeEntity, BuildConfig.STRIPE_KEY, new TokenCallback() {
    //      @Override public void onError(Exception error) {
    //        subscriber.onError(
    //            new StripeException(error.getMessage(), StripeException.STRIPE_ERROR));
    //      }
    //
    //      @Override public void onSuccess(Token token) {
    //        subscriber.onNext(new PaymentEntity());
    //        subscriber.onCompleted();
    //      }
    //    });
    //  }
    //});

    return null;
  }

  private boolean validateCard(Card card, Subscriber subscriber) {
    if (!card.validateNumber()) {
      subscriber.onError(new StripeException(StripeException.MESSAGE_INVALIDATE_NUMBER_ERROR,
          StripeException.INVALIDATE_NUMBER_ERROR));
      return false;
    } else if (!card.validateExpiryDate()) {
      subscriber.onError(new StripeException(StripeException.MESSAGE_INVALIDATE_DATE_ERROR,
          StripeException.INVALIDATE_DATE_ERROR));
      return false;
    } else if (!card.validateCVC()) {
      subscriber.onError(new StripeException(StripeException.MESSAGE_INVALIDATE_CVC_ERROR,
          StripeException.INVALIDATE_CVC_ERROR));
      return false;
    } else if (!card.validateCard()) {
      subscriber.onError(new StripeException(StripeException.MESSAGE_INVALIDATE_DETAIL_ERROR,
          StripeException.INVALIDATE_DETAIL_ERROR));
      return false;
    }

    return true;
  }
}