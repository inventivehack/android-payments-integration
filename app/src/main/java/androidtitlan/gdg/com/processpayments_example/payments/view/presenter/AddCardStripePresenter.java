package androidtitlan.gdg.com.processpayments_example.payments.view.presenter;

import android.text.TextUtils;
import androidtitlan.gdg.com.processpayments_example.common.domain.UseCase;
import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import androidtitlan.gdg.com.processpayments_example.payments.data.exception.StripeErrorHandling;
import androidtitlan.gdg.com.processpayments_example.payments.data.exception.StripeException;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import androidtitlan.gdg.com.processpayments_example.payments.domain.usecase.AddStripeCard;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.AddCardStripeView;
import javax.inject.Inject;
import rx.Subscriber;

/**
 * Presentador al obtener para añadir un tarjeta como método de pago que maneja toda la lógica de
 * la vista que se conecta a la capa de Dominio y está a la capa de Datos.
 *
 * @see <p>Para más información investigar más sobre el patrón <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">Model
 * View Presenter</a></p>
 */
public class AddCardStripePresenter extends Presenter<AddCardStripeView> {

  private AddStripeCard mUseCaseAddPayment;

  @Inject
  public AddCardStripePresenter(AddStripeCard useCaseAddPayment) {
    mUseCaseAddPayment = useCaseAddPayment;
  }

  /**
   * Si los datos de la tarjeta son validos procede a añadirla a Stripe.
   *
   * @param numberCard Número de la tarjeta.
   * @param month Mes de vencimiento de la tarjeta.
   * @param year Año de vencimiento de la tarjeta.
   * @param cvv CVV de la tarjeta.
   */
  public void addPayment(String numberCard, String month, String year, String cvv) {
    if (isValidCard(numberCard, month, year, cvv)) {
      getView().hideKeyBoard();
      getView().showLoginAddCard();
      mUseCaseAddPayment.executeAddCardStripe(numberCard, Integer.parseInt(month),
          Integer.parseInt(year), cvv, new AddCardStripeSubscriber());
    }
  }

  private boolean isValidCard(String numberCard, String month, String year, String cvc) {
    if (TextUtils.isEmpty(numberCard)) {
      getView().showMessageNumberCardEmpty();
      return false;
    } else if (TextUtils.isEmpty(month)) {
      getView().showMessageMonthEmpty();
      return false;
    } else if (TextUtils.isEmpty(year)) {
      getView().showMessageYearEmpty();
      return false;
    } else if (TextUtils.isEmpty(cvc)) {
      getView().showMessageCVCEmpty();
      return false;
    }
    return true;
  }

  @Override public void destroy() {
    getView().hideKeyBoard();
    mUseCaseAddPayment.unSubscribe();
  }

  private class AddCardStripeSubscriber extends Subscriber<PaymentResponse> {
    @Override public void onCompleted() {
      getView().hideLoginAddCard();
    }

    @Override public void onError(Throwable e) {
      e.printStackTrace();
      getView().hideLoginAddCard();
      showStripeMessageError(e);
    }

    /**
     * Al obtener el token de stripe, se procede a enviarlo al servidor.
     */
    @Override public void onNext(PaymentResponse tokenStripeResponse) {
      getView().showToken(tokenStripeResponse.getTokenPayment());
      getView().returnPaymentPreviousScreen();
    }
  }

  /**
   * Al ocurrir un error, obtiene el tipo de erro que puede ser de Stripe, del servidor o de
   * conexión.
   */
  private void showStripeMessageError(Throwable e) {
    StripeErrorHandling stripeErrorHandling = new StripeErrorHandling(e);

    switch (stripeErrorHandling.getTypeError()) {
      case StripeException.INVALIDATE_NUMBER_ERROR:
        getView().showMessageNumberCardInvalid();
        break;
      case StripeException.INVALIDATE_DATE_ERROR:
        getView().showMessageDateInvalid();
        break;
      case StripeException.INVALIDATE_CVC_ERROR:
        getView().showMessageCVCInvalid();
        break;
      case StripeException.INVALIDATE_DETAIL_ERROR:
        getView().showMessageCardInvalid();
        break;
      case StripeException.STRIPE_ERROR:
        getView().showMessageStripeError(stripeErrorHandling.getMessage());
        break;
      case StripeException.UNKNOWN_ERROR:
        getView().showMessageStripeUnknownError();
        break;
    }
  }
}
