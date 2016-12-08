package androidtitlan.gdg.com.processpayments_example.payments.domain.model;

/**
 * 06/12/2016.
 */

public class Card {
  private String number;
  private String expMonth;
  private String extYear;
  private String cvc;
  private int typePayment;

  public Card(String number, String expMonth, String extYear, String cvc, int typePayment) {
    this.number = number;
    this.expMonth = expMonth;
    this.extYear = extYear;
    this.cvc = cvc;
    this.typePayment = typePayment;
  }

  public String getNumber() {
    return number;
  }

  public String getExpMonth() {
    return expMonth;
  }

  public String getExtYear() {
    return extYear;
  }

  public String getCvc() {
    return cvc;
  }

  public int getTypePayment() {
    return typePayment;
  }

  @Override public String toString() {
    return "Card{" +
        "number='" + number + '\'' +
        ", expMonth='" + expMonth + '\'' +
        ", extYear='" + extYear + '\'' +
        ", cvc='" + cvc + '\'' +
        ", typePayment=" + typePayment +
        '}';
  }
}

