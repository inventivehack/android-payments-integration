package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import android.app.Activity;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardEntity;
import javax.inject.Inject;

/**
 * Proveee el tipo de fuente en el cual se obtendrá los datos para manejar las opciones de pago.
 */
public class PaymentsDataSourceFactory {

  /**
   * Provee un tipo de fuente de datos del sevidor.
   *
   * @return Tipo de fuente.
   */
  private Activity mActivity;

  @Inject public PaymentsDataSourceFactory(Activity activity) {
    mActivity = activity;
  }

  public GetPaymentsDataSource createDiskGetPaymentsDataSource() {
    return new DiskGetPaymentsDataSource();
  }

  public AddCardDataSource createCloudAddPaymentsDataSource(int typePayment) {

    if (typePayment == CardEntity.TYPE_PAYMENT_STRIPE) {
      return new CloudAddCardStripeDataSource();
    } else {
      return new CloudAddCardConektaDataSource(mActivity);
    }
  }
}