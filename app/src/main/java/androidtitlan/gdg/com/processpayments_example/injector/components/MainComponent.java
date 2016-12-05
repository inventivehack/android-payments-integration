package androidtitlan.gdg.com.processpayments_example.injector.components;

import androidtitlan.gdg.com.processpayments_example.injector.modules.MainModule;
import androidtitlan.gdg.com.processpayments_example.payments.view.fragment.AddCardStripeFragment;
import androidtitlan.gdg.com.processpayments_example.payments.view.fragment.AddPaymentFragment;
import androidtitlan.gdg.com.processpayments_example.payments.view.fragment.PaymentsFragment;
import dagger.Component;
import javax.inject.Singleton;

/**
 * 04/05/16.
 */

@Singleton @Component(modules = MainModule.class) public interface MainComponent {

  void inject(PaymentsFragment paymentsFragment);

  void inject(AddPaymentFragment addPaymentFragment);

  void inject(AddCardStripeFragment addCardStripeFragment);
}
