package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link MutabilityRequestable} can be asked if it is mutable.
 * 
 * @author Silvan Wyss
 * @version 2021-03-19
 */
public interface MutabilityRequestable {
  /**
   * @return true if the current {@link MutabilityRequestable} is not mutable.
   */
  default boolean isImmutable() {
    return !isMutable();
  }

  /**
   * @return true if the current {@link MutabilityRequestable} is mutable.
   */
  boolean isMutable();
}
