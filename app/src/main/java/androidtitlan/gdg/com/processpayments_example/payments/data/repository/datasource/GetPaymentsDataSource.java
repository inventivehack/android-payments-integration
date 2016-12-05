package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import java.util.List;
import rx.Observable;

/**
 * Operaciones posibles para la fuente de datos al manejar las opciones de pago.
 */
public interface GetPaymentsDataSource {

  Observable<List<PaymentEntity>> getPayments();
}
