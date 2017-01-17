package androidtitlan.gdg.com.processpayments_example.payments.domain.model.mapper;

import androidtitlan.gdg.com.processpayments_example.common.domain.Mapper;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import javax.inject.Inject;

/**
 * Mapper que convierte {@link PaymentEntity} a {@link PaymentResponse}
 */
public class PaymentEntityToPaymentResponseMapper extends Mapper<PaymentEntity, PaymentResponse> {

  @Inject public PaymentEntityToPaymentResponseMapper() {
  }

  @Override public PaymentResponse map(PaymentEntity value) {
    return new PaymentResponse(value.getTitlePayment(), value.getBrand(), value.getTokenPayment());
  }

  @Override public PaymentEntity reverseMap(PaymentResponse value) {
    throw new UnsupportedOperationException();
  }
}
