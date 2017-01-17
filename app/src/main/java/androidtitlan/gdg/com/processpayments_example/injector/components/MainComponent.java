package androidtitlan.gdg.com.processpayments_example.injector.components;

import android.content.Context;
import androidtitlan.gdg.com.processpayments_example.injector.modules.MainModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Componente principal de la aplicación para brindar las dependencias necesarias a otros
 * componentes.
 *
 * @see <p>Para más información sobre inyección de dependencias, ver el siguiente post <a
 * href="http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/ "> Tasting Dagger 2 on
 * Android</a></p>
 */
@Singleton @Component(modules = MainModule.class) public interface MainComponent {
  Context contest();
}
