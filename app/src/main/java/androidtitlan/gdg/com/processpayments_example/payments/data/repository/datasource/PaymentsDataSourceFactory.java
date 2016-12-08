package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import android.app.Activity;
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

  public AddPaymentsDataSource createCloudAddPaymentsDataSource() {
    return new CloudAddPaymentsDataSource(mActivity);
  }
}