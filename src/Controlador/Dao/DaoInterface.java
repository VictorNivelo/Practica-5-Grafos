
package Controlador.Dao;

import Controlador.TDA.ListaDinamica.ListaDinamica;

/**
 *
 * @author Victor
 * @param <T>
 */
public interface DaoInterface <T> {
    public Boolean Persist(T dato);
    public Boolean Merge(T data, Integer index);
    public ListaDinamica<T> all();
    public T get(Integer id);
    
}
