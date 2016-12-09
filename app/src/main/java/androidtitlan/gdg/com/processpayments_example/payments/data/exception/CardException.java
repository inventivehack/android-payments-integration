package androidtitlan.gdg.com.processpayments_example.payments.data.exception;

/**
 * Interfaz para el manejo de errores con Stripe de la capa de datos, mayormente son errores de
 * datos invalidos de una tarjeta.
 */
public class CardException extends Exception {

  public static final int CARD_SERVER_ERROR = 1;
  public static final int INVALIDATE_NAME_ERROR = 2;
  public static final int INVALIDATE_NUMBER_ERROR = 3;
  public static final int INVALIDATE_DATE_ERROR = 4;
  public static final int INVALIDATE_CVC_ERROR = 5;
  public static final int INVALIDATE_DETAIL_ERROR = 6;
  public static final int UNKNOWN_ERROR = 7;

  public static final String MESSAGE_INVALIDATE_NAME_ERROR = "Invalid Card Name";
  public static final String MESSAGE_INVALIDATE_NUMBER_ERROR = "Invalid Card Number";
  public static final String MESSAGE_INVALIDATE_DATE_ERROR = "Invalid Date Expiration";
  public static final String MESSAGE_INVALIDATE_CVC_ERROR = "Invalid CVC code";
  public static final String MESSAGE_INVALIDATE_DETAIL_ERROR = "Invalid Detail card";
  public static final String MESSAGE_UNKNOWN = "Invalid Detail card";

  private int typeError;

  public CardException(String message, int typeError) {
    super(message);
    this.typeError = typeError;
  }

  public int getTypeError() {
    return typeError;
  }
}
