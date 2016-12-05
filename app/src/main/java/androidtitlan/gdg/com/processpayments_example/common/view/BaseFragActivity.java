package androidtitlan.gdg.com.processpayments_example.common.view;

import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

/**
 * Actividad base que añade fusiones de manejo de almenos un fragmento de {@link BaseActivity}
 */
public abstract class BaseFragActivity extends BaseActivity {

  /**
   * Tag por default del {@link Fragment} dentro de la Actividad.
   */
  protected static final String DEFAULT_TAG_FRAGMENT = "default_tag";

  @Override protected void initView() {
    super.initView();
    initializeFragment();
  }

  /**
   * Obtiene la instancia del {@link Fragment} que será mostrado.
   *
   * @return {@link Fragment} que séra mostrado.
   */
  protected abstract Fragment getFragmentInstance();

  /**
   * Obtiene el id del {@link FrameLayout} en que cual será colocado el fragment obtenido en {@link
   * #getFragmentInstance()}
   *
   * @return Id del {@link FrameLayout}
   */
  protected abstract int getIdFragmentContainer();

  /**
   * Método encargado de añadir un {@link Fragment} (obtenido en {@link #getFragmentInstance()}) al
   * iniciar la actividad en el {@link FrameLayout} que contiene esta actividad (obtenido en {@link
   * #getIdFragmentContainer()})
   */
  private void initializeFragment() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(getIdFragmentContainer());
    if (fragment == null) {
      fm.beginTransaction()
          .add(getIdFragmentContainer(), getFragmentInstance(), DEFAULT_TAG_FRAGMENT)
          .commit();
    }
  }

  /**
   * Recuperar el {@link Fragment} actual.
   *
   * @return {@link Fragment} recuperado.
   */
  protected Fragment recoverFragment() {
    return getSupportFragmentManager().findFragmentById(getIdFragmentContainer());
  }

  /**
   * Añade un {@link Fragment} a la Actividad
   *
   * @param fragment Fragment a añadir.
   */
  public void addFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(getIdFragmentContainer(), fragment)
        .addToBackStack("")
        .commitAllowingStateLoss();
  }

  /**
   * Añade un {@link Fragment} a la Actividad con animación
   *
   * @param fragment Fragment a añadir.
   * @param enter Id del recurso con animación al añadir el {@link Fragment}.
   * @param exit Id del recurso con  animación al remover el {@link Fragment}.
   * @param popEnter Id del recurso con animación al añadir el {@link Fragment} a  la pila de
   * Fragments.
   * @param popExit Id del recurso con animación al remover el {@link Fragment} de la pila de
   * Fragments.
   */
  public void addFragment(Fragment fragment, @AnimRes int enter, @AnimRes int exit,
      @AnimRes int popEnter, @AnimRes int popExit) {
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(enter, exit, popEnter, popExit)
        .replace(getIdFragmentContainer(), fragment)
        .addToBackStack("")
        .commitAllowingStateLoss();
  }

  /**
   * Remplaza el {@link Fragment} anterior en la Actividad si es que existe.
   *
   * @param fragment Fragment que va a remplazar al {@link Fragment} anterior.
   */
  public void replaceFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(getIdFragmentContainer(), fragment)
        .commitAllowingStateLoss();
  }

  /**
   * Añade un {@link Fragment} a la Actividad con animación.
   *
   * @param fragment Fragment a añadir.
   * @param enter Id del recurso con animación al añadir el {@link Fragment}
   * @param exit Id del recurso con  animación al remover el {@link Fragment}
   */
  public void replaceFragment(final Fragment fragment, @AnimRes int enter, @AnimRes int exit) {
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(enter, exit)
        .replace(getIdFragmentContainer(), fragment)
        .commitAllowingStateLoss();
  }

  /**
   * Remueve el primer {@link Fragment} que se encuentra en la pila.
   */
  public void popFragment() {
    getSupportFragmentManager().popBackStackImmediate();
  }
}