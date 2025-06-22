package ch.nolix.coreapi.creationapi.copierapi;

/**
 * A {@link Copyable} can create a copy of itself.
 * 
 * @author Silvan Wyss
 * @version 2023-02-12
 * @param <C> is the type of a {@link Copyable}.
 */
public interface Copyable<C extends Copyable<C>> {

  /**
   * @return a new copy of the current {@link Copyable}.
   */
  C createCopy();
}
