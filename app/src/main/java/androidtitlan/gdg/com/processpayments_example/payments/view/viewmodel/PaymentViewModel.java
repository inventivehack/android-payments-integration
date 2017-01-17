package androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel;

import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;

/**
 * Modelo de datos obtenido de {@link PaymentResponse}, estos datos será mostrar en la UI este
 * caso será en un lista y será enviado a otras actividades.
 */
public class PaymentViewModel {

  public static final String AMERICAN_EXPRESS = "American Express";
  public static final String DISCOVER = "Discover";
  public static final String JCB = "JCB";
  public static final String DINERS_CLUB = "Diners Club";
  public static final String VISA = "Visa";
  public static final String MASTERCARD = "MasterCard";
  public static final String UNKNOWN = "Unknown";
  public static final String PAYPAL = "PayPal";

  private String titlePayment;
  private String brand;
  private String tokenPayment;

  public PaymentViewModel(String titlePayment, String brand, String tokenPayment) {
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
