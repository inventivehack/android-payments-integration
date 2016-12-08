package androidtitlan.gdg.com.processpayments_example.payments.data.entity;

import io.conekta.conektasdk.Card;

/**
 * 06/12/2016.
 */

public class CardConekta {
  private String number;
  private String cvc;
  private String expMonth;
  private String expYear;

  public CardConekta(String number, String cvc, String expMonth, String expYear) {
    this.number = number;
    this.cvc = cvc;
    this.expMonth = expMonth;
    this.expYear = expYear;
  }

  public Card provideCardConekta() {
    return new Card("Name Dummy", number, cvc, expMonth, expYear);
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
}
