package androidtitlan.gdg.com.processpayments_example.payments.view.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.common.view.BaseFragment;
import androidtitlan.gdg.com.processpayments_example.payments.view.presenter.AddPaymentPresenter;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.AddPaymentsView;
import butterknife.OnClick;
import butterknife.Optional;
import javax.inject.Inject;

/**
 * 05/12/2016.
 */

public class AddPaymentFragment extends BaseFragment implements AddPaymentsView {

  @Inject AddPaymentPresenter mPresenter;

  public static AddPaymentFragment newInstance() {
    return new AddPaymentFragment();
  }

  @Override protected void setupActionBar(ActionBar actionBar) {
    super.setupActionBar(actionBar);
    if (actionBar != null) {
      actionBar.setTitle(R.string.title_add_payment);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_add_payment;
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {
    super.initView(view, savedInstanceState);
    initializeDagger();
    initializePresenter();
  }

  private void initializeDagger() {
    getMainComponent().inject(this);
  }

  private void initializePresenter() {
    mPresenter.setView(this);
  }

  @OnClick(R.id.btn_add_stripe) public void clickOptionStripe() {
    mPresenter.selectStripe();
  }

  @OnClick(R.id.btn_add_conekta) public void clickOptionConekta() {
    mPresenter.selectConekta();
  }

  @Optional @OnClick(R.id.btn_add_paypal) public void clickOptionPayPal() {
    mPresenter.selectPaypal();
  }

  @Override public void showAddStripeCardScreen() {

  }

  @Override public void showAddConektaCardScreen() {

  }

  @Override public void showAddPaypalScreen() {

  }

  @Override public void showErrorAddPaypal() {

  }

  @Override public void returnPaymentPreviousScreen() {

  }
}
