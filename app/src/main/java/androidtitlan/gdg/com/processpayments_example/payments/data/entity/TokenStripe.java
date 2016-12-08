package androidtitlan.gdg.com.processpayments_example.payments.data.entity;

import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import java.util.Date;

/**
 * 06/12/2016.
 */

public class TokenStripe extends Token{
  /**
   * This method should not be invoked in your code.  This is used by Stripe to
   * create tokens using a Stripe API response
   */
  public TokenStripe(String id, boolean livemode, Date created, Boolean used, Card card) {
    super(id, livemode, created, used, card);
  }
}
