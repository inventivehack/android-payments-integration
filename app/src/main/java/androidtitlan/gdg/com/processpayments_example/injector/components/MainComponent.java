package androidtitlan.gdg.com.processpayments_example.injector.components;

import android.content.Context;
import androidtitlan.gdg.com.processpayments_example.injector.modules.MainModule;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import dagger.Component;
import javax.inject.Singleton;

/**
 * 04/05/16.
 */

@Singleton @Component(modules = MainModule.class) public interface MainComponent {
  Context contest();
}
