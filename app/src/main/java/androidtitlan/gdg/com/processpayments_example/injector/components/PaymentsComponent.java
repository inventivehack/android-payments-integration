/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package androidtitlan.gdg.com.processpayments_example.injector.components;

import android.app.Activity;
import androidtitlan.gdg.com.processpayments_example.injector.PerActivity;
import androidtitlan.gdg.com.processpayments_example.injector.modules.MainModule;
import androidtitlan.gdg.com.processpayments_example.injector.modules.PaymentsModule;
import androidtitlan.gdg.com.processpayments_example.payments.view.fragment.AddCardFragment;
import androidtitlan.gdg.com.processpayments_example.payments.view.fragment.AddPaymentFragment;
import androidtitlan.gdg.com.processpayments_example.payments.view.fragment.PaymentsFragment;
import dagger.Component;

@PerActivity @Component(dependencies = MainComponent.class, modules = PaymentsModule.class)
public interface PaymentsComponent {
  Activity activity();

  void inject(PaymentsFragment paymentsFragment);

  void inject(AddPaymentFragment addPaymentFragment);

  void inject(AddCardFragment addCardStripeFragment);
}
