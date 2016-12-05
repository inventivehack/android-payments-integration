package androidtitlan.gdg.com.processpayments_example.payments.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.PaymentViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View Holder que maneja el ítem para mostrar la la información de método de pago.
 */
public class PaymentsViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.image_type_payment) ImageView mImageTypePayment;
  @BindView(R.id.label_payment) TextView mLabelPayment;
  @BindView(R.id.label_token_payment) TextView mLabelTokenPayment;

  private PaymentViewModel mViewModel;

  public PaymentsViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void render(PaymentViewModel viewModel) {
    mViewModel = viewModel;
    renderTitlePayment(mViewModel.getTitlePayment());
    renderTypePayment(mViewModel.getBrand());
    renderTokenPayment(mViewModel.getTokenPayment());
  }

  private void renderTypePayment(String brand) {

    switch (brand) {
      case PaymentViewModel.AMERICAN_EXPRESS:
        mImageTypePayment.setImageResource(R.drawable.card_amex);
        break;
      case PaymentViewModel.DISCOVER:
        mImageTypePayment.setImageResource(R.drawable.card_discover);
        break;
      case PaymentViewModel.JCB:
        mImageTypePayment.setImageResource(R.drawable.card_jcb);
        break;
      case PaymentViewModel.DINERS_CLUB:
        mImageTypePayment.setImageResource(R.drawable.card_diners);
        break;
      case PaymentViewModel.VISA:
        mImageTypePayment.setImageResource(R.drawable.card_visa);
        break;
      case PaymentViewModel.MASTERCARD:
        mImageTypePayment.setImageResource(R.drawable.card_mastercard);
        break;
      case PaymentViewModel.PAYPAL:
        mImageTypePayment.setImageResource(R.drawable.card_paypal);
        break;
      case PaymentViewModel.UNKNOWN:
        mImageTypePayment.setImageResource(android.R.color.transparent);
        break;
    }
  }

  private void renderTitlePayment(String titlePayment) {
    mLabelPayment.setText(titlePayment);
  }

  private void renderTokenPayment(String tokenPayment) {
    mLabelTokenPayment.setText(tokenPayment);
  }
}
