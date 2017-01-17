package androidtitlan.gdg.com.processpayments_example.common.view;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.injector.HasComponent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Frament base con modificaciones de la clase {@link Fragment}, esta clase provee una fácil manejo
 * en todos los Fragments que realicen la inyección de vistas con {@link ButterKnife} e igualmente
 * cotiene manejo de Fragments, muestra mensajes {@link Toast} y {@link Snackbar}.
 *
 * @see ButterKnife
 */
public abstract class BaseFragment extends Fragment {

  private Unbinder mUnbinder;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(getFragmentLayout(), container, false);
  }

  /**
   * Método que inicia la inyección de vistas y la configuración del @{@link ActionBar}.
   */
  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    bindViews(view);
    setupActionBar(getActionBar());
    initView(view, savedInstanceState);
  }

  /**
   * Método que inicia la inyección de vistas y la configuración del @{@link ActionBar}.
   */
  protected void setupActionBar(ActionBar actionBar) {

  }

  /**
   * Usar este método para iniciar componentes de la vista. Este método es llamado después de
   * {@link BaseFragment#bindViews(View)}.
   */
  protected void initView(View view, Bundle savedInstanceState) {
  }

  @SuppressWarnings("unchecked")
  protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
  }

  /**
   * Habilita la opción de tener menús en el {@link Fragment}.
   */
  protected void enableOptionsMenu() {
    setHasOptionsMenu(true);
  }

  /**
   * Habilita la opción de retener el {@link Fragment} en la Actividad.
   */
  protected void retainInstance() {
    setRetainInstance(true);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbindViews();
  }

  /**
   * Todas las anotaciones de objetos con {@link BindView} serán inyectadas con{@link ButterKnife}.
   */
  private void bindViews(View rootView) {
    mUnbinder = ButterKnife.bind(this, rootView);
  }

  /**
   * Remueve las dependencias de las vistas del {@link Fragment}
   */
  private void unbindViews() {
    mUnbinder.unbind();
  }

  /**
   * Regresa los datos y resultado a la Actividad anterior.
   *
   * @param resultCode Código de resultado.
   * @param data Datos a regresar.
   */
  public void setResultActivity(int resultCode, Intent data) {
    getActivity().setResult(resultCode, data);
  }

  /**
   * Finaliza la actividad
   */
  public void finishActivity() {
    getActivity().finish();
  }

  /**
   * Instancia del {@link ActionBar} que se encuentra en la Actividad.
   *
   * @return ActionBar de la Actividad.
   */
  public ActionBar getActionBar() {
    return ((BaseActivity) getActivity()).getSupportActionBar();
  }

  /**
   * Recrea el ménu
   */
  protected void invalidateOptionsMenu() {
    getActivity().invalidateOptionsMenu();
  }

  /**
   * Añade un {@link Fragment} a la Actividad
   *
   * @param fragment Fragment a añadir.
   */
  public void addFragment(Fragment fragment) {
    ((BaseFragActivity) getActivity()).addFragment(fragment);
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

    ((BaseFragActivity) getActivity()).addFragment(fragment, enter, exit, popEnter, popExit);
  }

  /**
   * Remplaza el {@link Fragment} anterior en la Actividad si es que existe.
   *
   * @param fragment Fragment que va a remplazar al {@link Fragment} anterior.
   */
  public void replaceFragment(Fragment fragment) {
    ((BaseFragActivity) getActivity()).replaceFragment(fragment);
  }

  /**
   * Añade un {@link Fragment} a la Actividad con animación.
   *
   * @param fragment Fragment a añadir.
   * @param enter Id del recurso con animación al añadir el {@link Fragment}.
   * @param exit Id del recurso con animación al remover el {@link Fragment}.
   */
  public void replaceFragment(Fragment fragment, @AnimRes int enter, @AnimRes int exit) {
    ((BaseFragActivity) getActivity()).replaceFragment(fragment, enter, exit);
  }

  /**
   * Remueve el primer {@link Fragment} que se encuentra en la pila.
   */
  public void popConcurrentFragment() {
    ((BaseFragActivity) getActivity()).popFragment();
  }


  public void clearFragmentStack() {
    int countStack = getFragmentManager().getBackStackEntryCount();

    for (int i = 0; i < countStack; i++) {
      getFragmentManager().popBackStackImmediate();
    }
  }

  /**
   * Recupera la instancia de {@link Toolbar} dentro de la Actividad.
   *
   * @return Toolbar dentro de la Actividad.
   */
  @Nullable public Toolbar getToolbar() {
    return ((BaseActivity) getActivity()).getToolbar();
  }

  /**
   * Obtiene el layout que será mostrado en la {@link Fragment} que implemente {@link
   * BaseFragment}.
   *
   * @return Identificador del layout.
   */
  protected abstract int getFragmentLayout();

  /**
   * Muestra un SnackBar.
   *
   * @param idString Id del texto a mostrar.
   */
  protected void showDefaultMessageSnackBar(@StringRes int idString) {

    Snackbar snackbar = Snackbar.make(getView(), idString, Snackbar.LENGTH_SHORT);

    TextView textView =
        ButterKnife.findById(snackbar.getView(), android.support.design.R.id.snackbar_text);
    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.primary_text_white));

    snackbar.show();
  }

  /**
   * Muestra un SnackBar.
   *
   * @param text String del message a mostrar.
   */
  protected void showDefaultMessageSnackBar(String text) {

    Snackbar snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_SHORT);

    TextView textView =
        ButterKnife.findById(snackbar.getView(), android.support.design.R.id.snackbar_text);
    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.primary_text_white));

    snackbar.show();
  }

  /**
   * Muestra un Toast.
   *
   * @param idString Id del texto a mostrar.
   */
  protected void showDefaultToast(@StringRes int idString) {
    Toast.makeText(getContext(), idString, Toast.LENGTH_LONG).show();
  }

  /**
   * Muestra un Toast.
   *
   * @param text String del message a mostrar.
   */
  protected void showDefaultToast(String text) {
    Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
  }

  /**
   * Muestra el taclado.
   *
   * @param label View que mostrara el teclado.
   */
  public void showKeyBoard(View label) {
    if (label != null) {
      label.requestFocus();
      InputMethodManager imm =
          (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(label, InputMethodManager.SHOW_IMPLICIT);
    }
  }

  /**
   * Oculta el teclado.
   */
  public void hideKeyBoard() {
    View view = getActivity().getCurrentFocus();
    if (view != null) {
      view.clearFocus();
      InputMethodManager imm =
          (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  /**
   * Remueve todas las notificaciones presentes.
   */
  public void removeAllNotifications() {
    NotificationManager notificationManager =
        (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.cancelAll();
  }
}
