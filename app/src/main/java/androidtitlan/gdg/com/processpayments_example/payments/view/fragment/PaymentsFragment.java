package androidtitlan.gdg.com.processpayments_example.payments.view.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.common.view.BaseFragment;
import androidtitlan.gdg.com.processpayments_example.payments.view.adapter.PaymentsAdapter;
import androidtitlan.gdg.com.processpayments_example.payments.view.presenter.PaymentsPresenter;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.PaymentViewModel;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.PaymentsView;
import androidtitlan.gdg.com.processpayments_example.view.ItemLinearDecorator;
import butterknife.BindView;
import butterknife.OnClick;
import java.util.List;
import javax.inject.Inject;

public class PaymentsFragment extends BaseFragment implements PaymentsView {

  @BindView(R.id.list_payments) RecyclerView mListPayments;
  PaymentsAdapter mAdapter;
  @Inject PaymentsPresenter mPresenter;

  @Override protected void setupActionBar(ActionBar actionBar) {
    super.setupActionBar(actionBar);
    if (actionBar != null) {
      actionBar.setTitle(R.string.app_name);
      actionBar.setDisplayHomeAsUpEnabled(false);
    }
  }

  public static PaymentsFragment newInstance() {
    return new PaymentsFragment();
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_payments;
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {
    super.initView(view, savedInstanceState);
    initializeDagger();
    initializePresenter();
    initializeAdapter();
    initializeList();

    mPresenter.initialize();
  }

  private void initializeDagger() {
    getMainComponent().inject(this);
  }


  private void initializePresenter() {
    mPresenter.setView(this);
  }

  private void initializeAdapter() {
    if (mAdapter == null) mAdapter = new PaymentsAdapter(getContext());
  }

  private void initializeList() {
    mListPayments.setLayoutManager(new LinearLayoutManager(getContext()));
    mListPayments.setAdapter(mAdapter);
    mListPayments.addItemDecoration(
        new ItemLinearDecorator(getContext(), ItemLinearDecorator.VERTICAL_LIST));
  }

  @OnClick(R.id.btn_add_payment) public void clickAddPayment() {
    mPresenter.addPayment();
  }

  @Override public void showPayments(List<PaymentViewModel> viewModelList) {
    mAdapter.setAll(viewModelList);
  }

  @Override public void showAddPaymentScreen() {
    addFragment(AddPaymentFragment.newInstance(), R.anim.slide_in_left, R.anim.slide_out_left,
        R.anim.slide_in_right, R.anim.slide_out_right);
  }
}
