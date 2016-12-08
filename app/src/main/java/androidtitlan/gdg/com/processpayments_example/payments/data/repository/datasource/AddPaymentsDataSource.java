package androidtitlan.gdg.com.processpayments_example.payments.data.repository.datasource;

import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardConekta;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.CardStripe;
import androidtitlan.gdg.com.processpayments_example.payments.data.entity.PaymentEntity;
import rx.Observable;

/**
 * Operaciones posibles para la fuente de datos al manejar las opciones de pago.
 */
public interface AddPaymentsDataSource {

  Observable<PaymentEntity> addCardStripe(CardStripe cardStripeEntity);

  Observable<PaymentEntity> addCardConekta(CardConekta cardConektaEntity);
}
