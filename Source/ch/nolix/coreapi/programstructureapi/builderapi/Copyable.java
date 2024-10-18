package ch.nolix.coreapi.programstructureapi.builderapi;

/**
 * A {@link Copyable} can be copied.
 * 
 * @author Silvan Wyss
 * @version 2023-02-12
 * @param <C> is the type of a {@link Copyable}.
 */
public interface Copyable<C extends Copyable<C>> {

  /**
   * @return a copy of the current {@link Copyable}.
   */
  C getCopy();
}
