package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link VoidnessRequestable} can be asked if it is void.
 * 
 * @author Silvan Wyss
 */
public interface VoidnessRequestable {
  /**
   * @return true if the current {@link VoidnessRequestable} is not void, false
   *         otherwise.
   */
  default boolean isEffectual() {
    return !isVoid();
  }

  /**
   * @return true if the current {@link VoidnessRequestable} is void, false
   *         otherwise.
   */
  boolean isVoid();
}
