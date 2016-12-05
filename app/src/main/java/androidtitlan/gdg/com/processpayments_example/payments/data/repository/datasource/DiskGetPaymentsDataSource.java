package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import com.stripe.android.model.Card;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class DiskGetPaymentsDataSource implements GetPaymentsDataSource {
  @Override public Observable<List<PaymentEntity>> getPayments() {
    return Observable.create(subscriber -> {
      List<PaymentEntity> paymentEntities = new ArrayList<PaymentEntity>();

      paymentEntities.add(new PaymentEntity("4242", Card.AMERICAN_EXPRESS, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("4242", Card.AMERICAN_EXPRESS, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("4242", Card.AMERICAN_EXPRESS, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("4242", Card.AMERICAN_EXPRESS, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("8282", Card.VISA, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("8282", Card.VISA, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("8282", Card.VISA, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("8282", Card.VISA, "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("PayPal", "Paypal", "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("PayPal", "Paypal", "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("PayPal", "Paypal", "sdfdsfddsfdsfdsf"));
      paymentEntities.add(new PaymentEntity("PayPal", "Paypal", "sdfdsfddsfdsfdsf"));

      subscriber.onNext(paymentEntities);
      subscriber.onCompleted();
    });
  }
}
