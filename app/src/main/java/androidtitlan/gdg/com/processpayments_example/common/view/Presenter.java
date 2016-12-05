package androidtitlan.gdg.com.processpayments_example.common.view;

import android.content.Context;

/**
 * Presentador genérico que contiene métodos comunes para todos los presentadores usados en la
 * aplicación. También maneja la instancia de la abstracción de vista.
 *
 * @see <p>Para más información investigar más sobre el patrón <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">Model
 * View Presenter</a></p>
 */
public abstract class Presenter<T extends Presenter.View> {

  /**
   * Instancia genérica de la abstracción de la Vista.
   */
  private T view;

  /**
   *
   * @return Obtiene la instancia de la vista.
   */
  public T getView() {
    return view;
  }

  /**
   *
   * @param view Es la instancia de la vista que se comunica con el presentador.
   */
  public void setView(T view) {
    this.view = view;
  }

  /**
   * Método genérico que podrá ser llamado al iniciar el Presentador. La idea es iniciar los
   * componentes o acciones de la clase que extienda de este presentador al momento de que se
   * inicie una UI de la aplicación.
   */
  public void initialize() {

  }

  /**
   * Método genérico que podrá ser llamado al destruir el Presentador.  La idea es liberar
   * componentes o terminar acciones de la clase que extienda de este presentador al momento de que
   * una UI de la aplicación sea destruida.
   */
  public abstract void destroy();

  /**
   * Vista genérica que contiene métodos comunes para todas las abstracciones de la vista usados en
   * la aplicación.
   */
  public interface View {
    Context getContext();
  }
}
