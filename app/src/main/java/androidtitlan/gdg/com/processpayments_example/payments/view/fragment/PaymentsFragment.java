package androidtitlan.gdg.com.processpayments_example.payments.view.fragment;

import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.common.view.BaseFragment;

public class PaymentsFragment extends BaseFragment {

  public static PaymentsFragment newInstance() {
    return new PaymentsFragment();
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_payments;
  }
}
