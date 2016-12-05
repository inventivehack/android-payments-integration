package androidtitlan.gdg.com.processpayments_example.common.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import androidtitlan.gdg.com.processpayments_example.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Actividad base con modificaciones de la clase {@link AppCompatActivity},
 * esta clase provee una fácil manejo en todas las actividades que realicen la inyección de vistas
 * con {@link ButterKnife} e igualmente comprueba si existe un {@link Toolbar} si existe en la UI.
 *
 * @see ButterKnife
 */

public abstract class BaseActivity extends AppCompatActivity {

  @Nullable @BindView(R.id.toolbar) Toolbar mToolbar;

  /**
   * Método que inicializa inyección de vistas y la configuración del {@link #mToolbar}
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    bindViews();
    setupToolbar();
    setupActionBar(getSupportActionBar());
    initView();
    initView(savedInstanceState);
  }

  /**
   * Usar este método para iniciar componentes de la vista. Este método es llamado después de
   * {@link BaseActivity#bindViews()}
   */
  protected void initView() {
  }

  /**
   * Usar este método para iniciar componentes de la vista. Este método es llamado después de
   * {@link BaseActivity#bindViews()}
   */
  protected void initView(Bundle savedInstanceState) {

  }

  /**
   * Its common use a toolbar within activity, if it exists in the
   * layout this will be configured
   */
  protected void setupToolbar() {
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }
  }

  protected void setupActionBar(ActionBar actionBar) {

  }

  /**
   * Todas las anotaciones de objetos con {@link BindView} serán inyectadas con{@link ButterKnife}
   */
  private void bindViews() {
    ButterKnife.bind(this);
  }

  /**
   * Recupera la instancia de {@link Toolbar} dentro de la Actividad.
   * @return Toolbar dentro de la Actividad.
   */
  @Nullable public Toolbar getToolbar() {
    return mToolbar;
  }

  /**
   * Obtiene el layout que será mostrado en la actividad que implemente {@link BaseActivity}
   *
   * @return Identificador del layout
   */
  protected abstract int getLayout();
}
