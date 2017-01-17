package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import android.app.Activity;
import androidtitlan.gdg.com.processpayments_example.BuildConfig;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataImple;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataLocal;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.exception.CardException;
import com.stripe.android.model.Card;
import io.conekta.conektasdk.Conekta;
import io.conekta.conektasdk.Token;
import org.json.JSONException;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementación del server para las operaciones al manejar las opciones de pago.
 */
public class CloudAddCardConektaDataSource implements AddCardDataSource {

  private Activity mActivity;

  public CloudAddCardConektaDataSource(Activity activity) {
    mActivity = activity;
  }

  /**
   * Añadimos una tarjeta con Conekta, pero antes de eso capturamos las exepciones del objeto
   * {@link Card} si un campo es invalido.Sí hubo exito obtenemos el Token, sino procesamos el
   * error.
   *
   * @see <p>Para más información investigar más sobre <a href="https://www.conekta.io/es/docs/referencias/conekta-android">Conekta</a>.
   * </p>
   */
  @Override public Observable<PaymentEntity> addCard(CardEntity cardEntity) {
    return Observable.create(new Observable.OnSubscribe<PaymentEntity>() {
      @Override public void call(Subscriber<? super PaymentEntity> subscriber) {

        Conekta.setPublicKey(BuildConfig.CONEKTA_KEY);
        Conekta.collectDevice(mActivity);
        Token token = new Token(mActivity);
        token.onCreateTokenListener(data -> {
          try {
            if (data.has("id")) {
              Card cardStripe = new Card(cardEntity.getNumber(), 0, 0, "");
              subscriber.onNext(new PaymentEntity(cardStripe.getLast4(), cardStripe.getType(),
                  data.getString("id")));
            } else {
              subscriber.onError(new CardException(data.getString("message_to_purchaser"),
                  CardException.CARD_SERVER_ERROR));
            }
          } catch (JSONException e) {
            e.printStackTrace();
            subscriber.onError(new CardException(e.getMessage(), CardException.UNKNOWN_ERROR));
          }
          subscriber.onCompleted();
        });

        try {
          token.create(cardEntity.provideCardConekta());
        } catch (RuntimeException e) {
          subscriber.onError(getTypeError(e.getMessage()));
        }
      }
    }).doOnNext(this::savePayment);
  }

  private CardException getTypeError(String message) {

    CardException cardException;
    switch (message) {
      case "name":
        cardException = new CardException(CardException.MESSAGE_INVALIDATE_NUMBER_ERROR,
            CardException.INVALIDATE_NUMBER_ERROR);
        break;
      case "number":
        cardException = new CardException(CardException.MESSAGE_INVALIDATE_NAME_ERROR,
            CardException.INVALIDATE_NAME_ERROR);
        break;
      case "cvc":
        cardException = new CardException(CardException.MESSAGE_INVALIDATE_CVC_ERROR,
            CardException.INVALIDATE_CVC_ERROR);
        break;
      case "expMonth":
        cardException = new CardException(CardException.MESSAGE_INVALIDATE_DATE_ERROR,
            CardException.INVALIDATE_DATE_ERROR);
        break;
      case "expYear":
        cardException = new CardException(CardException.MESSAGE_INVALIDATE_DATE_ERROR,
            CardException.INVALIDATE_DATE_ERROR);
        break;
      default:
        cardException = new CardException(message, CardException.UNKNOWN_ERROR);
        break;
    }

    return cardException;
  }

  private void savePayment(PaymentEntity paymentEntity) {
    PaymentDataLocal paymentDataLocal = new PaymentDataImple();
    paymentDataLocal.savePayment(paymentEntity);
  }
}