package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import android.app.Activity;
import android.util.Log;
import androidtitlan.gdg.com.processpayments_example.BuildConfig;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataImple;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataLocal;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardConekta;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardStripe;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.exception.StripeException;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import io.conekta.conektasdk.Conekta;
import io.conekta.conektasdk.Token;
import org.json.JSONException;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del server para las operaciones al manejar las opciones de pago.
 */
public class CloudAddPaymentsDataSource implements AddPaymentsDataSource {

  private static final String LOG_TAG = CloudAddPaymentsDataSource.class.getSimpleName();
  private Activity mActivity;

  public CloudAddPaymentsDataSource(Activity activity) {
    mActivity = activity;
  }

  /**
   * Añadimos una tarjeta con Stripe, pero antes de eso hace las validaciones con {@link Card} e
   * igualmente si ocurre un error.
   *
   * @see <p>Para más información investigar más sobre <a href=" https://stripe.com/docs/mobile/android">Stripe</a>.
   * </p>
   */
  @Override public Observable<PaymentEntity> addCardStripe(CardStripe cardStripeEntity) {
    return Observable.create(new Observable.OnSubscribe<PaymentEntity>() {
      @Override public void call(Subscriber<? super PaymentEntity> subscriber) {
        if (validateCard(cardStripeEntity, subscriber)) {
          new Stripe().createToken(cardStripeEntity, BuildConfig.STRIPE_KEY, new TokenCallback() {
            @Override public void onError(Exception error) {
              subscriber.onError(
                  new StripeException(error.getMessage(), StripeException.STRIPE_ERROR));
            }

            @Override public void onSuccess(com.stripe.android.model.Token token) {
              subscriber.onNext(
                  new PaymentEntity(cardStripeEntity.getLast4(), cardStripeEntity.getType(),
                      token.getId()));
              subscriber.onCompleted();
            }
          });
        }
      }
    }).doOnNext(this::savePayment);
  }

  @Override public Observable<PaymentEntity> addCardConekta(CardConekta cardConektaEntity) {
    return Observable.create(new Observable.OnSubscribe<PaymentEntity>() {
      @Override public void call(Subscriber<? super PaymentEntity> subscriber) {

        Conekta.setPublicKey(BuildConfig.CONEKTA_KEY);
        Conekta.collectDevice(mActivity);
        Token token = new Token(mActivity);
        token.onCreateTokenListener(data -> {
          Log.d(LOG_TAG, data.toString());
          try {
            CardStripe cardStripe = new CardStripe(cardConektaEntity.getNumber(), 0, 0, "");
            subscriber.onNext(new PaymentEntity(cardStripe.getLast4(), cardStripe.getType(),
                data.getString("id")));
          } catch (JSONException e) {
            e.printStackTrace();
            subscriber.onError(new StripeException(e.getMessage(), StripeException.STRIPE_ERROR));
          }
          subscriber.onCompleted();
        });
        token.create(cardConektaEntity.provideCardConekta());
      }
    }).doOnNext(this::savePayment);
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

  private void savePayment(PaymentEntity paymentEntity) {
    PaymentDataLocal paymentDataLocal = new PaymentDataImple();
    paymentDataLocal.savePayment(paymentEntity);
  }
}