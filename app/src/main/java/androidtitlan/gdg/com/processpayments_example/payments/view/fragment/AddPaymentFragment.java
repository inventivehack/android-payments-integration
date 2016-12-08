package androidtitlan.gdg.com.processpayments_example.payments.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import androidtitlan.gdg.com.processpayments_example.BuildConfig;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.common.view.BaseFragment;
import androidtitlan.gdg.com.processpayments_example.injector.components.PaymentsComponent;
import androidtitlan.gdg.com.processpayments_example.payments.view.presenter.AddPaymentPresenter;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.AddPaymentsView;
import butterknife.OnClick;
import butterknife.Optional;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalService;
import javax.inject.Inject;

/**
 * 05/12/2016.
 */

public class AddPaymentFragment extends BaseFragment implements AddPaymentsView {

  private static final int REQUEST_CODE_PAYPAL = 12;
  private static final String NAME_COMPANY_PAYPAL = "Androidtitlan";
  private static final String URL_PRIVACY_POLICY_PAYPAL = "https://www.example.com/privacy";
  private static final String URL_USER_AGREEMENT_PAYPAL = "https://www.example.com/legal";

  @Inject AddPaymentPresenter mPresenter;

  private PayPalConfiguration mPayPalConfiguration;

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
    initializeConfigurationPaypal();
    initializeServicePaypal();
  }

  private void initializeDagger() {
    getComponent(PaymentsComponent.class).inject(this);
  }

  private void initializePresenter() {
    mPresenter.setView(this);
  }

  private void initializeConfigurationPaypal() {
    mPayPalConfiguration =
        new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(BuildConfig.PAYPAL_CLIENT_ID)
            .merchantName(NAME_COMPANY_PAYPAL)
            .merchantPrivacyPolicyUri(Uri.parse(URL_PRIVACY_POLICY_PAYPAL))
            .merchantUserAgreementUri(Uri.parse(URL_USER_AGREEMENT_PAYPAL));
  }

  private void initializeServicePaypal() {
    Intent intent = new Intent(getContext(), PayPalService.class);
    intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, mPayPalConfiguration);
    getContext().startService(intent);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE_PAYPAL) {
      if (resultCode == Activity.RESULT_OK) {
        PayPalAuthorization auth =
            data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
        if (auth != null) {
          mPresenter.sendAuthorizationCodePaypal(auth.getAuthorizationCode());
        } else {
          mPresenter.errorAddPaypal();
        }
      } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
        mPresenter.errorAddPaypal();
      }
    }
  }

  @Override public void onDestroyView() {
    finishPaypalService();
    super.onDestroyView();
  }

  private void finishPaypalService() {
    getContext().stopService(new Intent(getContext(), PayPalService.class));
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
    addFragment(AddCardFragment.newInstance(AddCardFragment.TYPE_PAYMENT_STRIPE),
        R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
  }

  @Override public void showAddConektaCardScreen() {
    addFragment(AddCardFragment.newInstance(AddCardFragment.TYPE_PAYMENT_CONEKTA),
        R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
  }

  @Override public void showAddPaypalScreen() {
    Intent intent = new Intent(getContext(), PayPalFuturePaymentActivity.class);
    intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, mPayPalConfiguration);
    startActivityForResult(intent, REQUEST_CODE_PAYPAL);
  }

  @Override public void showErrorAddPaypal() {
    showDefaultMessageSnackBar(R.string.message_error_add_paypal);
  }

  @Override public void showToken(String tokenPayment) {
    showDefaultToast(tokenPayment);
  }

  @Override public void returnPaymentPreviousScreen() {
    clearFragmentStack();
  }
}
