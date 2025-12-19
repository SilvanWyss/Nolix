package ch.nolix.coreapi.resourcecontrol.closecontroller;

/**
 * A {@link CloseStateRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 */
public interface CloseStateRequestable {
  /**
   * @return true if the current {@link CloseStateRequestable} is closed, false
   *         otherwise.
   */
  boolean isClosed();

  /**
   * @return true if the current {@link CloseStateRequestable} is not closed,
   *         false otherwise.
   */
  default boolean isOpen() {
    return !isClosed();
  }
}
