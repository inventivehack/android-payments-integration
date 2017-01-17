package androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.mapper;

import androidtitlan.gdg.com.processpayments_example.common.domain.Mapper;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.PaymentViewModel;
import javax.inject.Inject;

/**
 * Mapper que convierte {@link PaymentResponse} a {@link
 * PaymentViewModel}
 */
public class PaymentsResponseToPaymentViewModelMapper
    extends Mapper<PaymentResponse, PaymentViewModel> {

  @Inject public PaymentsResponseToPaymentViewModelMapper() {
  }

  @Override public PaymentViewModel map(PaymentResponse value) {
    return new PaymentViewModel(value.getTitlePayment(), value.getBrand(), value.getTokenPayment());
  }

  @Override public PaymentResponse reverseMap(PaymentViewModel value) {
    throw new UnsupportedOperationException();
  }
}
