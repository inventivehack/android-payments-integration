package androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;

/**
 * Abstracción de la vista con métodos para la comunicación de la vista con el presentador al
 * añadir un tarjeta a Stripe o Conecta.
 */
public interface AddCardStripeView extends Presenter.View {

  void showMessageNumberCardEmpty();

  void showMessageMonthEmpty();

  void showMessageYearEmpty();

  void showMessageCVCEmpty();

  void hideKeyBoard();

  void showLoginAddCard();

  void hideLoginAddCard();

  void showToken(String tokenPayment);

  void returnPaymentPreviousScreen();

  void showMessageNumberCardInvalid();

  void showMessageDateInvalid();

  void showMessageCardInvalid();

  void showMessageCVCInvalid();

  void showMessageStripeError(String message);

  void showMessageStripeUnknownError();

}
