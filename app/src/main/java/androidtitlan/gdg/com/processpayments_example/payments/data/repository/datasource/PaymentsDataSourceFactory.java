package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

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

  @Inject
  public PaymentsDataSourceFactory() {
  }

  public GetPaymentsDataSource createDiskGetPaymentsDataSource() {
    return new DiskGetPaymentsDataSource();
  }

  public AddPaymentsDataSource createCloudAddPaymentsDataSource() {
    return new CloudAddPaymentsDataSource();
  }
}