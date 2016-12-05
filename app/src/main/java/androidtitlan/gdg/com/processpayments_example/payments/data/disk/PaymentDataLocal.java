package androidtitlan.gdg.com.processpayments_example.payments.data.disk;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import java.util.List;

/**
 * 05/12/2016.
 */

public interface PaymentDataLocal {

  void savePayment(PaymentEntity paymentEntity);

  List<PaymentEntity> getPayments();

}
