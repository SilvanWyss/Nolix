package ch.nolix.coreapi.state.statemutation;

/**
 * A {@link Resettable} can be reset.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public interface Resettable {

  /**
   * Resets the current {@link Resettable}.
   */
  void reset();
}
