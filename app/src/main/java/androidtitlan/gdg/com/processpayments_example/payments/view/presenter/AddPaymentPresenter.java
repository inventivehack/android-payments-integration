package androidtitlan.gdg.com.processpayments_example.payments.view.presenter;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.AddPaymentsView;
import javax.inject.Inject;

/**
 * Presentador al obtener para añadir un tarjeta como método de pago que maneja toda la lógica de
 * la vista que se conecta a la capa de Dominio y está a la capa de Datos.
 *
 * @see <p>Para más información investigar más sobre el patrón <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">Model
 * View Presenter</a></p>
 */
public class AddPaymentPresenter extends Presenter<AddPaymentsView> {

  @Inject
  public AddPaymentPresenter() {
  }

  public void selectStripe() {
    getView().showAddStripeCardScreen();
  }

  public void selectConekta() {
    getView().showAddConektaCardScreen();
  }

  public void selectPaypal() {
    getView().showAddPaypalScreen();
  }

  public void errorAddPaypal() {
    getView().showErrorAddPaypal();
  }

  @Override public void destroy() {
  }
}
