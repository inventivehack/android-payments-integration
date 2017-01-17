package androidtitlan.gdg.com.processpayments_example.injector;

/**
 * Componente genérico manejar los componentes de una inyección de dependencias.
 */
public interface HasComponent<C> {
  C getComponent();
}
