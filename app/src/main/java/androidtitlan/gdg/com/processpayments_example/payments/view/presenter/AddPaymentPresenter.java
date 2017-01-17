package androidtitlan.gdg.com.processpayments_example.payments.view.presenter;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import androidtitlan.gdg.com.processpayments_example.payments.domain.usecase.AddPayPalAccount;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.AddPaymentsView;
import javax.inject.Inject;
import rx.Subscriber;

/**
 * Presentador para añadir un método de pago, maneja toda la lógica de la vista que se conecta a la
 * capa de Dominio y está a la capa de Datos.
 *
 * @see <p>Para más información investigar más sobre el patrón <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">Model
 * View Presenter</a></p>
 */
public class AddPaymentPresenter extends Presenter<AddPaymentsView> {

  private AddPayPalAccount mAddPayPalAccount;

  @Inject public AddPaymentPresenter(AddPayPalAccount addPayPalAccount) {
    mAddPayPalAccount = addPayPalAccount;
  }

  /**
   * Muestra la pantalla para añadir una tarjeta con Stripe.
   */
  public void selectStripe() {
    getView().showAddStripeCardScreen();
  }

  /**
   * Muestra la pantalla para añadir una tarjeta con Conekta.
   */
  public void selectConekta() {
    getView().showAddConektaCardScreen();
  }

  /**
   * Muestra la pantalla para añadir una cuenta de PayPal.
   */
  public void selectPaypal() {
    getView().showAddPaypalScreen();
  }

  /**
   * Muestra un mensaje de error de PayPal.
   */
  public void errorAddPaypal() {
    getView().showErrorAddPaypal();
  }

  @Override public void destroy() {
  }

  /**
   * Enviar el código de autorización al servidor.
   */
  public void sendAuthorizationCodePaypal(String authorizationCode) {
    mAddPayPalAccount.executeAddPayPalAccount(authorizationCode, new AddPayPalAccountSubscriber());
  }

  private class AddPayPalAccountSubscriber extends Subscriber<PaymentResponse> {
    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {

    }

    @Override public void onNext(PaymentResponse paymentResponse) {
      getView().showToken(paymentResponse.getTokenPayment());
      getView().returnPaymentPreviousScreen();
    }
  }
}
