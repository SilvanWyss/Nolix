package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link TransparencyRequestable} can be asked if it is transparent.
 * 
 * @author Silvan Wyss
 * @version 2022-07-15
 */
public interface TransparencyRequestable {
  /**
   * @return true if the current {@link TransparencyRequestable} is opaque, false
   *         otherwise.
   */
  default boolean isOpaque() {
    return !isTransparent();
  }

  /**
   * @return true if the current {@link TransparencyRequestable} is transparent,
   *         false otherwise.
   */
  boolean isTransparent();
}
