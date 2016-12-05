package androidtitlan.gdg.com.processpayments_example.payments.data.disk;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import io.realm.Realm;
import java.util.List;

/**
 * 05/12/2016.
 */

public class PaymentDataImple implements PaymentDataLocal {

  private Realm realm;

  public PaymentDataImple() {
    realm = Realm.getDefaultInstance();
  }

  @Override public void savePayment(PaymentEntity paymentEntity) {
    //Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();
    realm.copyToRealm(paymentEntity);
    realm.commitTransaction();
  }

  @Override public List<PaymentEntity> getPayments() {
    //Realm realm = Realm.getDefaultInstance();
    return realm.where(PaymentEntity.class).findAll();
  }
}
