package androidtitlan.gdg.com.processpayments_example.payments.view.presenter;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import androidtitlan.gdg.com.processpayments_example.payments.domain.usecase.GetPayments;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.mapper.PaymentsResponseToPaymentViewModelMapper;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmvp.PaymentsView;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

/**
 * Presentador para obtener todos los métodos de pago, maneja toda la lógica de la vista que se
 * conecta a la capa de Dominio y está a la capa de Datos.
 *
 * @see <p>Para más información investigar más sobre el patrón <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">Model
 * View Presenter</a></p>
 */

public class PaymentsPresenter extends Presenter<PaymentsView> {

  private GetPayments getPaymentsUseCase;
  private PaymentsResponseToPaymentViewModelMapper mMapper;

  @Inject public PaymentsPresenter(GetPayments getPaymentsUseCase,
      PaymentsResponseToPaymentViewModelMapper mapper) {
    this.getPaymentsUseCase = getPaymentsUseCase;
    mMapper = mapper;
  }

  /**
   * Obtiene los métodos de pago.
   */
  @Override public void initialize() {
    super.initialize();
    getPaymentsUseCase.execute(new GetPaymentsSubscriber());
  }

  /**
   * Muestra la pantalla para añadir una método de pago.
   */
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

    /**
     * Muestra los métodos de pago.
     */
    @Override public void onNext(List<PaymentResponse> paymentResponses) {
      getView().showPayments(mMapper.map(paymentResponses));
    }
  }
}
