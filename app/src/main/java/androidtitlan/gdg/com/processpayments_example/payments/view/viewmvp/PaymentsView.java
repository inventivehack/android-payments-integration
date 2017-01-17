package androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.PaymentViewModel;
import java.util.List;

/**
 * Abstracción de la vista con métodos para la comunicación de la vista con el presentador al
 * obtener los métodos de pago.
 */
public interface PaymentsView extends Presenter.View {
  void showPayments(List<PaymentViewModel> map);

  void showAddPaymentScreen();
}
