package androidtitlan.gdg.com.processpayments_example.common.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper genérico que convierte un modelo de datos a otro, normalmente es usado al pasar otro tipo
 * de datos de una capa a otra (de la capa de datos a la capa de dominio y/o de la capa de dominio
 * a  la capa de presentación).
 *
 * @see <p>Para más información investigar más sobre <a href="http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/">Clean
 * Architecture</a></p>
 *
 * @param <T1> Modelo de datos origen.
 * @param <T2> Modelo de datos final.
 */
public abstract class Mapper<T1, T2> {

  /**
   * Método abstracto que convierte del modelo de datos {@link T1} al modelo de datos {@link T2}.
   * La clase que implemente este método definirá su comportamiento.
   *
   * @param value es el modelo de datos origen.
   * @return Modelo de datos final.
   */
  public abstract T2 map(T1 value);

  /**
   * Método abstracto que revierte el modelo de datos {@link T2} al modelo de datos {@link T1}. La
   * clase que implemente este método definirá su comportamiento.
   *
   * @param value es el modelo de datos origen.
   * @return Modelo de datos final.
   */
  public abstract T1 reverseMap(T2 value);

  /**
   * Método abstracto que convierte una lista del modelo de datos {@link T1} a una lista del modelo
   * de datos {@link T2}.
   *
   * @param values es la lista del modelo de datos de origen.
   * @return Lista del modelo de datos final
   */
  public List<T2> map(List<T1> values) {
    List<T2> returnValues = new ArrayList<>(values.size());
    for (T1 value : values) {
      returnValues.add(map(value));
    }
    return returnValues;
  }

  /**
   * Método abstracto que revierte una lista del modelo de datos {@link T2} a una lista del modelo
   * de
   * datos {@link T1}.
   *
   * @param values es la lista del modelo de datos de origen.
   * @return Lista del modelo de datos final
   */
  public List<T1> reverseMap(List<T2> values) {
    List<T1> returnValues = new ArrayList<>(values.size());
    for (T2 value : values) {
      returnValues.add(reverseMap(value));
    }
    return returnValues;
  }
}