package androidtitlan.gdg.com.processpayments_example.payments.data.entity;

/**
 * 06/12/2016.
 */

public class CardEntity {

  public static final int TYPE_PAYMENT_STRIPE = 1;
  public static final int TYPE_PAYMENT_CONEKTA = 2;

  private String number;
  private String cvc;
  private String expMonth;
  private String expYear;
  private int typePayment;

  public CardEntity(String number, String cvc, String expMonth, String expYear, int typePayment) {
    this.number = number;
    this.cvc = cvc;
    this.expMonth = expMonth;
    this.expYear = expYear;
    this.typePayment = typePayment;
  }

  public io.conekta.conektasdk.Card provideCardConekta() {
    return new io.conekta.conektasdk.Card("Name Dummy", number, cvc, expMonth, expYear);
  }

  public com.stripe.android.model.Card provideCardStripe() {
    return new com.stripe.android.model.Card(number, Integer.parseInt(expMonth),
        Integer.parseInt(expYear), cvc);
  }

  public String getNumber() {
    return number;
  }

  public String getCvc() {
    return cvc;
  }

  public String getExpMonth() {
    return expMonth;
  }

  public String getExpYear() {
    return expYear;
  }

  public int getTypePayment() {
    return typePayment;
  }
}
