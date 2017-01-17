package androidtitlan.gdg.com.processpayments_example.payments.data.exception;


/**
 * Manejador de errores con Stripe y Conekta de la capa de datos.
 */
public class CardErrorHandling {

  private Throwable cause;

  public CardErrorHandling(Throwable cause) {
    this.cause = cause;
  }

  public int getTypeError() {
    if (cause instanceof CardException) {
      return (((CardException) cause).getTypeError());
    } else {
      return CardException.UNKNOWN_ERROR;
    }
  }

  public String getMessage() {
    return cause.getMessage();
  }
}
