package ch.nolix.coreapi.structure.typerequest;

/**
 * A {@link StateRequestable} has a certain state.
 * 
 * @author Silvan Wyss
 * @version 2024-12-21
 * @param <S> is the type of the state of a {@link StateRequestable}.
 */
public interface StateRequestable<S extends Enum<S>> {

  /**
   * @return the state of the current {@link StateRequestable}.
   */
  S getState();
}
