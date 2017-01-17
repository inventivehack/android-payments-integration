package androidtitlan.gdg.com.processpayments_example.payments.domain.model;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;

/**
 * Nuevo modelo de datos filtrado a partir del modelo de datos obtenido en la capa de datos {@link
 * PaymentEntity}
 */

public class PaymentResponse {

  private String titlePayment;
  private String brand;
  private String tokenPayment;

  public PaymentResponse(String titlePayment, String brand, String tokenPayment) {
    this.titlePayment = titlePayment;
    this.brand = brand;
    this.tokenPayment = tokenPayment;
  }

  public String getTitlePayment() {
    return titlePayment;
  }

  public String getBrand() {
    return brand;
  }

  public String getTokenPayment() {
    return tokenPayment;
  }
}
