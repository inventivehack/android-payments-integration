package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataImple;
import androidtitlan.gdg.com.processpayments_example.payments.data.disk.PaymentDataLocal;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import java.util.List;
import rx.Observable;

public class DiskGetPaymentsDataSource implements GetPaymentsDataSource {

  @Override public Observable<List<PaymentEntity>> getPayments() {
    return Observable.create(subscriber -> {
      PaymentDataLocal paymentDataLocal = new PaymentDataImple();
      subscriber.onNext(paymentDataLocal.getPayments());
      subscriber.onCompleted();
    });
  }

  @Override public Observable<PaymentEntity> addPayPalAccount(PaymentEntity paymentEntity) {
    return Observable.create(subscriber -> {
      PaymentDataLocal paymentDataLocal = new PaymentDataImple();
      paymentDataLocal.savePayment(paymentEntity);
      subscriber.onNext(paymentEntity);
      subscriber.onCompleted();
    });
  }
}
