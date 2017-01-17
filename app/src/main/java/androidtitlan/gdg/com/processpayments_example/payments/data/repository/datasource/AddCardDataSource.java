package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardEntity;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import rx.Observable;

/**
 * Operaciones posibles para la fuente de datos al añadir una opción de pago con Stripe y Conekta.
 */
public interface AddCardDataSource {
  Observable<PaymentEntity> addCard(CardEntity cardEntityEntity);
}
