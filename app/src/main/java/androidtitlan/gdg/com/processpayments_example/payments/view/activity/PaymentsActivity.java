package androidtitlan.gdg.com.processpayments_example.payments.view.activity;

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.common.view.BaseFragActivity;
import androidtitlan.gdg.com.processpayments_example.injector.HasComponent;
import androidtitlan.gdg.com.processpayments_example.injector.components.DaggerPaymentsComponent;
import androidtitlan.gdg.com.processpayments_example.injector.components.PaymentsComponent;
import androidtitlan.gdg.com.processpayments_example.injector.modules.PaymentsModule;
import androidtitlan.gdg.com.processpayments_example.payments.view.fragment.PaymentsFragment;

public class PaymentsActivity extends BaseFragActivity implements HasComponent<PaymentsComponent> {

  private PaymentsComponent mPaymentsComponent;

  @Override protected int getLayout() {
    return R.layout.activity_payments;
  }

  @Override protected Fragment getFragmentInstance() {
    return PaymentsFragment.newInstance();
  }

  @Override protected int getIdFragmentContainer() {
    return R.id.fragment_container;
  }

  @Override protected void initView() {
    super.initView();
    initializeInjector();
  }

  private void initializeInjector() {
    mPaymentsComponent = DaggerPaymentsComponent.builder()
        .mainComponent(getApplicationComponent())
        .paymentsModule(new PaymentsModule(this))
        .build();
  }

  public PaymentsComponent getComponent() {
    return mPaymentsComponent;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }
}
