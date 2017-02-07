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
   * Añadimos una tarjeta con Conekta, capturamos las excepciones del objeto {@link Card} si un
   * campo es invalido, en caso contrario enviamos los datos de la tarjeta  y obtenemos el Token o
   * procesamos el error obtenido.
   */
  @Override public Observable<PaymentEntity> addCard(CardEntity cardEntity) {
    return Observable.create(new Observable.OnSubscribe<PaymentEntity>() {
      @Override public void call(Subscriber<? super PaymentEntity> subscriber) {

        Conekta.setPublicKey(BuildConfig.CONEKTA_KEY);
        Conekta.collectDevice(mActivity);
        Token token = new Token(mActivity);
        token.onCreateTokenListener(data -> {

          // Obtenemos el Id o Token de la respuesta, si no existe entonces obtenemos el mensaje
          // de error
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

        // Añadimos la tarjeta a Conekta, pero si alguna campo es invalido al momento de crear
        // nuestro objeto tarjeta, procesamos la Excepción y enviamos el error.
        try {
          token.create(cardEntity.provideCardConekta());
        } catch (RuntimeException e) {
          subscriber.onError(getTypeError(e.getMessage()));
        }
      }
    })
        // Guardamos los datos obtenidos localmente.
        .doOnNext(this::savePayment);
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