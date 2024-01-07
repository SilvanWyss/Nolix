//package declaration
package ch.nolix.coreapi.programstructureapi.builderapi;

//interface
/**
 * A {@link Copyable} can be copied.
 * 
 * @author Silvan Wyss
 * @date 2023-02-12
 * @param <C> is the type of a {@link Copyable}.
 */
public interface Copyable<C extends Copyable<C>> {

  //method declaration
  /**
   * @return a copy of the current {@link Copyable}.
   */
  C getCopy();
}
