package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataImple;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataLocal;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import java.util.List;
import rx.Observable;


/**
 * Implementación local para manejar los métodos de pago.
 */
public class DiskGetPaymentsDataSource implements GetPaymentsDataSource {

  /**
   * Obtenemos los métodos de pago almacenados localmente.
   */
  @Override public Observable<List<PaymentEntity>> getPayments() {
    return Observable.create(subscriber -> {
      PaymentDataLocal paymentDataLocal = new PaymentDataImple();
      subscriber.onNext(paymentDataLocal.getPayments());
      subscriber.onCompleted();
    });
  }


  /**
   * Guardamos un método de pago.
   */
  @Override public Observable<PaymentEntity> addPayPalAccount(PaymentEntity paymentEntity) {
    return Observable.create(subscriber -> {
      PaymentDataLocal paymentDataLocal = new PaymentDataImple();
      paymentDataLocal.savePayment(paymentEntity);
      subscriber.onNext(paymentEntity);
      subscriber.onCompleted();
    });
  }
}
