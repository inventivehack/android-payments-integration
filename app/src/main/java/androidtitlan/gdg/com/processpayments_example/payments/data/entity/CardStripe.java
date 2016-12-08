package androidtitlan.gdg.com.processpayments_example.payments.data.entity;

import com.stripe.android.model.Card;

/**
 * 06/12/2016.
 */

public class CardStripe extends Card {
  public CardStripe(String number, Integer expMonth, Integer expYear, String cvc, String name,
      String addressLine1, String addressLine2, String addressCity, String addressState,
      String addressZip, String addressCountry, String last4, String type, String fingerprint,
      String country) {
    super(number, expMonth, expYear, cvc, name, addressLine1, addressLine2, addressCity,
        addressState, addressZip, addressCountry, last4, type, fingerprint, country);
  }

  public CardStripe(String number, Integer expMonth, Integer expYear, String cvc, String name,
      String addressLine1, String addressLine2, String addressCity, String addressState,
      String addressZip, String addressCountry, String currency) {
    super(number, expMonth, expYear, cvc, name, addressLine1, addressLine2, addressCity,
        addressState, addressZip, addressCountry, currency);
  }

  public CardStripe(String number, Integer expMonth, Integer expYear, String cvc) {
    super(number, expMonth, expYear, cvc);
  }


}
