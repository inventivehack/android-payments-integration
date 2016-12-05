package androidtitlan.gdg.com.processpayments_example.payments.view.util;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.stripe.android.model.Card;

/**
 * Custom {@link TextWatcher} para cambiar el forma de un número de tarjeta.
 * <p>Ejemplo de 4012888888881881 a 4012 8888 8888 1881.</p>
 */

public class NumberCardChangeListener implements TextWatcher {
  private boolean mSelfChange = false;

  @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override public void afterTextChanged(Editable editable) {

    if (!mSelfChange) {
      mSelfChange = true;
      String typeCard = getTypeCard(editable.toString());
      editable.setFilters(getNewLength(typeCard));

      String numberCardFormat = formatCreditCard(editable.toString(), typeCard);
      editable.replace(0, editable.length(), numberCardFormat, 0, numberCardFormat.length());
      mSelfChange = false;
    }
  }

  private String getTypeCard(String numberCard) {
    if (!TextUtils.isEmpty(numberCard)) {
      Card card = new Card(numberCard, 0, 0, "");
      return card.getType();
    } else {
      return Card.UNKNOWN;
    }
  }

  private InputFilter[] getNewLength(String typeCard) {

    int length;
    if (typeCard.equals(Card.AMERICAN_EXPRESS) || typeCard.equals(Card.DINERS_CLUB)) {
      length = 17;
    } else {
      length = 19;
    }

    return new InputFilter[] { new InputFilter.LengthFilter(length) };
  }

  private String formatCreditCard(String numberCard, String typeCard) {

    StringBuilder formatBuilder = new StringBuilder();
    char[] numbers = numberCard.replaceAll("\\s+", "").toCharArray();

    if (typeCard.equals(Card.AMERICAN_EXPRESS)) {
      for (int i = 0; i < numbers.length; i++) {
        if (i > 0 && (i == 4 || i == 10)) {
          formatBuilder.append(" ");
        }
        formatBuilder.append(numbers[i]);
      }
    } else {
      for (int i = 0; i < numbers.length; i++) {
        if (i > 0 && i % 4 == 0) {
          formatBuilder.append(" ");
        }
        formatBuilder.append(numbers[i]);
      }
    }

    return formatBuilder.toString();
  }
}
