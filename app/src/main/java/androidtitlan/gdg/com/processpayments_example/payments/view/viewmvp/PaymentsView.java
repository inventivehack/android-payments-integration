package androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.PaymentViewModel;
import java.util.List;

/**
 * 04/12/2016.
 */

public interface PaymentsView extends Presenter.View {
  void showPayments(List<PaymentViewModel> map);
}
