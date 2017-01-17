package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import android.app.Activity;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardEntity;
import javax.inject.Inject;

/**
 * Proveee el tipo de fuente en el cual se obtendrá los datos para manejar las opciones de pago.
 */
public class PaymentsDataSourceFactory {

  private Activity mActivity;

  @Inject public PaymentsDataSourceFactory(Activity activity) {
    mActivity = activity;
  }

  /**
   * Provee un tipo de fuente de datos local.
   *
   * @return Tipo de fuente.
   */
  public GetPaymentsDataSource createDiskGetPaymentsDataSource() {
    return new DiskGetPaymentsDataSource();
  }

  /**
   * Provee un tipo de fuente de datos de Stripe o Conekta.
   *
   * @return Tipo de fuente.
   */
  public AddCardDataSource createCloudAddPaymentsDataSource(int typePayment) {
    if (typePayment == CardEntity.TYPE_PAYMENT_STRIPE) {
      return new CloudAddCardStripeDataSource();
    } else {
      return new CloudAddCardConektaDataSource(mActivity);
    }
  }
}