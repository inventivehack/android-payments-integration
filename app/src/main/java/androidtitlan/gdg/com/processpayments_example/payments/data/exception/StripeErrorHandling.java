package androidtitlan.gdg.com.processpayments_example.payments.data.exception;


/**
 * Manejador de errores con Stripe de la capa de datos.
 */
public class StripeErrorHandling {

  private Throwable cause;

  public StripeErrorHandling(Throwable cause) {
    this.cause = cause;
  }

  public int getTypeError() {
    if (cause instanceof StripeException) {
      return (((StripeException) cause).getTypeError());
    } else {
      return StripeException.UNKNOWN_ERROR;
    }
  }

  public String getMessage() {
    return cause.getMessage();
  }
}
