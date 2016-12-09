package androidtitlan.gdg.com.processpayments_example.payments.data.repository;

import androidtitlan.gdg.com.processpayments_example.common.domain.Mapper;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource.AddCardDataSource;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource.GetPaymentsDataSource;
import androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource.PaymentsDataSourceFactory;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.PaymentResponse;
import androidtitlan.gdg.com.processpayments_example.payments.domain.model.mapper.PaymentEntityToPaymentResponseMapper;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

/**
 * Implementación del repositorio para manejar las opciones de pago.
 *
 * <p>En esta parte los datos obtenidos son convertidos a otro Modelo de datos Para la Capa de
 * dominio con un {@link Mapper} y en {@link Observable#map(Func1)}, según el principio de
 * independencia de Capas en Clean Architecture.</p>
 *
 * @see <a href="http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/">Clean
 * Architecture</a>
 */
public class PaymentsDataRepository implements PaymentsRepository {

  private final PaymentsDataSourceFactory mSourceFactory;
  private final PaymentEntityToPaymentResponseMapper mMapper;

  @Inject public PaymentsDataRepository(PaymentsDataSourceFactory sourceFactory,
      PaymentEntityToPaymentResponseMapper mapper) {
    mSourceFactory = sourceFactory;
    mMapper = mapper;
  }

  @Override public Observable<PaymentResponse> addCard(CardEntity cardEntity) {
    final AddCardDataSource paymentsDataSource =
        mSourceFactory.createCloudAddPaymentsDataSource(cardEntity.getTypePayment());
    return paymentsDataSource.addCard(cardEntity).map(mMapper::map);
  }

  @Override public Observable<PaymentResponse> addPayPalAccount(PaymentEntity paymentEntity) {
    final GetPaymentsDataSource paymentsDataSource =
        mSourceFactory.createDiskGetPaymentsDataSource();
    return paymentsDataSource.addPayPalAccount(paymentEntity).map(mMapper::map);
  }

  @Override public Observable<List<PaymentResponse>> getPayments() {
    final GetPaymentsDataSource paymentsDataSource =
        mSourceFactory.createDiskGetPaymentsDataSource();
    return paymentsDataSource.getPayments().map(mMapper::map);
  }
}