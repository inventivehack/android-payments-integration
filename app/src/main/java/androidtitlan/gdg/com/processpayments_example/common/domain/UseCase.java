package androidtitlan.gdg.com.processpayments_example.common.domain;

import androidtitlan.gdg.com.processpayments_example.common.view.Presenter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Dominio con un caso de uso por default. Para comunicar las capa de presentación con las capa de
 * dominio utiliza {@link Observable} de RxJava.
 *
 * @see <p>Para más información investigar más sobre <a href="http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/">Clean
 * Architecture</a> y <a href="http://reactivex.io/intro.html">Programción Reactiva con
 * RxJava</a></p>
 */
public abstract class UseCase<T> {

  private Subscription subscription = Subscriptions.empty();

  protected UseCase() {
  }

  /**
   * Ejecuta el {@link Observable} del caso de uso y configura el {@link Subscriber}, como es el
   * hilo en el cual se ejecutara el observable y en que hilo responderá. el cual esta implementado
   * en el {@link Presenter}.
   *
   * @param useCaseSubscriber Es el {@link Subscriber} donde responderá el {@link Observable},
   */
  @SuppressWarnings("unchecked") public void execute(Subscriber<T> useCaseSubscriber) {
    this.subscription = buildObservableUseCase().subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(useCaseSubscriber);
  }

  /**
   * Cancela la suscrición del actual  {@link Subscription}.
   */
  public void unSubscribe() {
    if (!subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }

  /**
   * Crea la configuración del {@link Observable} que se comunicará de la capa de Datos a la capa
   * de Presentación, la clase que implemente este método definiera su comportamiento, pero generar
   * se comunica por medio del patrón repositorio.
   *
   * @return {@link Observable} construido.
   */
  protected abstract Observable<T> buildObservableUseCase();
}
