package androidtitlan.gdg.com.processpayments_example.payments.view.presenter;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import androidtitlan.gdg.com.processpayments_example.payments.domain.usecase.GetPayments;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.mapper.PaymentsResponseToPaymentViewModelMapper;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.PaymentsView;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

public class PaymentsPresenter extends Presenter<PaymentsView> {

  private GetPayments getPaymentsUseCase;
  private PaymentsResponseToPaymentViewModelMapper mMapper;

  @Inject public PaymentsPresenter(GetPayments getPaymentsUseCase,
      PaymentsResponseToPaymentViewModelMapper mapper) {
    this.getPaymentsUseCase = getPaymentsUseCase;
    mMapper = mapper;
  }

  @Override public void initialize() {
    super.initialize();
    getPaymentsUseCase.execute(new GetPaymentsSubscriber());
  }

  public void addPayment() {
    getView().showAddPaymentScreen();
  }

  @Override public void destroy() {
    getPaymentsUseCase.unSubscribe();
  }

  private class GetPaymentsSubscriber extends Subscriber<List<PaymentResponse>> {
    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {
      e.printStackTrace();
    }

    @Override public void onNext(List<PaymentResponse> paymentResponses) {
      getView().showPayments(mMapper.map(paymentResponses));
    }
  }
}
