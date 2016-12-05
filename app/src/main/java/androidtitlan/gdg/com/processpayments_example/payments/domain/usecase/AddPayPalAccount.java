package androidtitlan.gdg.com.processpayments_example.payments.domain.usecase;

import androidtitlan.gdg.com.processpayments_example.common.domain.UseCase;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.PaymentsRepository;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * 05/12/2016.
 */

public class AddPayPalAccount extends UseCase<PaymentResponse> {

  private PaymentsRepository mRepository;
  private PaymentEntity mPaymentEntity;

  @Inject public AddPayPalAccount(PaymentsRepository repository) {
    mRepository = repository;
  }

  public void executeAddPayPalAccount(String tokenPayPal,
      Subscriber<PaymentResponse> useCaseSubscriber) {
    mPaymentEntity = new PaymentEntity("PayPal", "PayPal", tokenPayPal);
    super.execute(useCaseSubscriber);
  }

  @Override protected Observable<PaymentResponse> buildObservableUseCase() {
    return mRepository.addPayPalAccount(mPaymentEntity);
  }
}
