package androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;

/**
 * Abstracción de la vista con métodos para la comunicación de la vista con el presentador al
 * añadir una opción de pago.
 */
public interface AddPaymentsView extends Presenter.View {

  void showAddStripeCardScreen();

  void showAddConektaCardScreen();

  void showAddPaypalScreen();

  void showErrorAddPaypal();

  //void showLoadingAddPaypalAccount();
  //
  //void hideLoadingAddPaypalAccount();

  void returnPaymentPreviousScreen();

}
