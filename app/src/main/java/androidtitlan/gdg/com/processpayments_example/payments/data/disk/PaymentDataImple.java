package androidtitlan.gdg.com.processpayments_example.payments.data.disk;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import io.realm.Realm;
import java.util.List;

/**
 * Implementación  de las operaciones sobre la base de datos local de los métodos de pago. Para esto se
 * utiliza {@link Realm} una alternativa para base de datos en dispositivos móviles.
 *
 * @see <p>Para más información sobre Realm <a href="https://realm.io/docs/java/latest/">ver su
 * documentación</a></p>
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
