package androidtitlan.gdg.com.processpayments_example.payments.data.entity;

import io.realm.RealmObject;

public class PaymentEntity extends RealmObject {

  private String titlePayment;
  private String brand;
  private String tokenPayment;

  public PaymentEntity() {
  }

  public PaymentEntity(String titlePayment, String brand, String tokenPayment) {
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

  @Override public String toString() {
    return "PaymentEntity{" +
        "titlePayment='" + titlePayment + '\'' +
        ", brand='" + brand + '\'' +
        ", tokenPayment='" + tokenPayment + '\'' +
        '}';
  }
}
