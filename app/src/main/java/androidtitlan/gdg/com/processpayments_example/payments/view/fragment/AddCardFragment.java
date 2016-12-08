package androidtitlan.gdg.com.processpayments_example.payments.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.common.view.BaseFragment;
import androidtitlan.gdg.com.processpayments_example.injector.components.PaymentsComponent;
import androidtitlan.gdg.com.processpayments_example.payments.view.presenter.AddCardPresenter;
import androidtitlan.gdg.com.processpayments_example.payments.view.util.NumberCardChangeListener;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.AddCardStripeView;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.stripe.android.model.Card;
import javax.inject.Inject;

/**
 * 05/12/2016.
 */
public class AddCardFragment extends BaseFragment implements AddCardStripeView {

  public static final int TYPE_PAYMENT_STRIPE = 1;
  public static final int TYPE_PAYMENT_CONEKTA = 2;
  private static final String ARG_TYPE_PAYMENT = "type_payment";

  @BindInt(android.R.integer.config_shortAnimTime) int ANIMATION_DURATION;

  @BindView(R.id.input_number_card) TextInputEditText mInputNumberCard;
  @BindView(R.id.input_month) TextInputEditText mInputMonth;
  @BindView(R.id.input_year) TextInputEditText mInputYear;
  @BindView(R.id.input_ccv) TextInputEditText mInputCCV;

  @BindView(R.id.container_input_number_card) TextInputLayout mContainerInputNumberCard;
  @BindView(R.id.label_error_card) TextView mLabelErrorCard;

  private ProgressDialog mProgressDialogAddCard;
  @Inject AddCardPresenter mPresenter;

  public static AddCardFragment newInstance(int typePayment) {
    AddCardFragment addCardFragment = new AddCardFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_TYPE_PAYMENT, typePayment);
    addCardFragment.setArguments(args);
    return addCardFragment;
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_add_card_stripe;
  }

  @Override protected void setupActionBar(ActionBar actionBar) {
    super.setupActionBar(actionBar);
    if (actionBar != null) {
      actionBar.setTitle(R.string.title_add_card);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {
    super.initView(view, savedInstanceState);
    initializeDagger();
    initializePresenter();
    setupInputNumberCard();
  }

  private void initializeDagger() {
    getComponent(PaymentsComponent.class).inject(this);
  }

  private void initializePresenter() {
    mPresenter.setView(this);
  }

  private void setupInputNumberCard() {
    mInputNumberCard.addTextChangedListener(new NumberCardChangeListener());
  }

  @OnClick(R.id.btn_add_payment) public void addPayment() {
    mPresenter.addPayment(mInputNumberCard.getText().toString(), mInputMonth.getText().toString(),
        mInputYear.getText().toString(), mInputCCV.getText().toString(), getTypePaymentFromArgs());
  }

  private int getTypePaymentFromArgs() {
    return getArguments().getInt(ARG_TYPE_PAYMENT);
  }

  @OnTextChanged(R.id.input_number_card) public void changeTextNumberCard(CharSequence numberCard) {
    showTypeCard(numberCard.toString());
    clearErrorTextInputLayout(mContainerInputNumberCard);
  }

  private void showTypeCard(String numberCard) {
    int imageRes = 0;

    if (!TextUtils.isEmpty(numberCard)) {
      Card card = new Card(numberCard, 0, 9, "");

      switch (card.getType()) {
        case Card.AMERICAN_EXPRESS:
          imageRes = R.drawable.amex;
          break;
        case Card.DISCOVER:
          imageRes = R.drawable.discover;
          break;
        case Card.JCB:
          imageRes = R.drawable.jcb;
          break;
        case Card.DINERS_CLUB:
          imageRes = R.drawable.diners;
          break;
        case Card.VISA:
          imageRes = R.drawable.visa;
          break;
        case Card.MASTERCARD:
          imageRes = R.drawable.mastercard;
          break;
        case Card.UNKNOWN:
          imageRes = 0;
          break;
      }
    }

    mInputNumberCard.setCompoundDrawablesWithIntrinsicBounds(0, 0, imageRes, 0);
  }

  private void clearErrorTextInputLayout(TextInputLayout containerInputName) {
    containerInputName.setErrorEnabled(false);
  }

  @OnTextChanged(R.id.input_month) public void changeTextMonth(CharSequence text) {
    hideMessageErrorCard();
  }

  @OnTextChanged(R.id.input_year) public void changeTextYear(CharSequence text) {
    hideMessageErrorCard();
  }

  @OnTextChanged(R.id.input_ccv) public void changeTextCvv(CharSequence text) {
    hideMessageErrorCard();
  }

  @Override public void showMessageNumberCardEmpty() {
    mContainerInputNumberCard.setError(getString(R.string.message_number_card_empty));
  }

  @Override public void showMessageMonthEmpty() {
    showMessageErrorCard(R.string.message_month_empty);
  }

  @Override public void showMessageYearEmpty() {
    showMessageErrorCard(R.string.message_year_empty);
  }

  @Override public void showMessageCVCEmpty() {
    showMessageErrorCard(R.string.message_cvv_empty);
  }

  @Override public void showLoginAddCard() {
    if (mProgressDialogAddCard == null) {
      mProgressDialogAddCard = new ProgressDialog(getContext());
      mProgressDialogAddCard.setMessage(getString(R.string.message_loading_add_card));
      mProgressDialogAddCard.setCancelable(false);
    }

    mProgressDialogAddCard.show();
  }

  @Override public void hideLoginAddCard() {
    if (mProgressDialogAddCard != null) mProgressDialogAddCard.dismiss();
  }

  @Override public void showToken(String tokenPayment) {
    showDefaultToast(tokenPayment);
  }

  private void showMessageErrorCard(@StringRes int idRes) {
    mLabelErrorCard.setVisibility(View.VISIBLE);
    mLabelErrorCard.setText(idRes);

    ViewCompat.setAlpha(mLabelErrorCard, 0f);
    ViewCompat.animate(mLabelErrorCard)
        .alpha(1f)
        .setDuration(ANIMATION_DURATION)
        .setInterpolator(new LinearOutSlowInInterpolator())
        .setListener(new ViewPropertyAnimatorListenerAdapter() {
          @Override public void onAnimationEnd(View view) {
            mLabelErrorCard.setVisibility(View.VISIBLE);
          }
        })
        .start();
  }

  private void hideMessageErrorCard() {
    ViewCompat.setAlpha(mLabelErrorCard, 1f);
    ViewCompat.animate(mLabelErrorCard)
        .alpha(0f)
        .setDuration(ANIMATION_DURATION)
        .setInterpolator(new FastOutLinearInInterpolator())
        .setListener(new ViewPropertyAnimatorListenerAdapter() {
          @Override public void onAnimationEnd(View view) {
            mLabelErrorCard.setText("");
            mLabelErrorCard.setVisibility(View.GONE);
          }
        })
        .start();
  }

  @Override public void returnPaymentPreviousScreen() {
    clearFragmentStack();
  }

  @Override public void showMessageNumberCardInvalid() {
    mContainerInputNumberCard.setError(getString(R.string.message_number_card_invalid));
  }

  @Override public void showMessageDateInvalid() {
    showMessageErrorCard(R.string.message_date_card_invalid);
  }

  @Override public void showMessageCVCInvalid() {
    showMessageErrorCard(R.string.message_cvc_card_invalid);
  }

  @Override public void showMessageCardInvalid() {
    showMessageErrorCard(R.string.message_card_invalid);
  }

  @Override public void showMessageStripeError(String message) {
    showDefaultMessageSnackBar(message);
  }

  @Override public void showMessageStripeUnknownError() {
    showDefaultMessageSnackBar(R.string.message_error_response_network);
  }
}
