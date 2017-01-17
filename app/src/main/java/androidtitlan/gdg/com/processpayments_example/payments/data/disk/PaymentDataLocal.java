package androidtitlan.gdg.com.processpayments_example.payments.data.disk;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import java.util.List;

/**
 * Operaciones sobre la base de datos local de los métodos de pago.
 */
public interface PaymentDataLocal {

  void savePayment(PaymentEntity paymentEntity);

  List<PaymentEntity> getPayments();

}
