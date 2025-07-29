package ch.nolix.coreapi.resourcecontrol.closecontroller;

/**
 * @author Silvan Wyss
 * @version 2022-07-05
 */
public interface ICloseController {

  /**
   * Closes all elements of the current {@link ICloseController}.
   */
  void close();

  /**
   * Adds the given element to the current {@link ICloseController}.
   * 
   * @param element
   * @throws RuntimeException if the current {@link ICloseController} is already
   *                          closed.
   * @throws RuntimeException if the current {@link ICloseController} contains
   *                          already the given element.
   */
  void createCloseDependencyTo(GroupCloseable element);

  /**
   * @return the parent {@link IClosePool} of the current
   *         {@link ICloseController}.
   */
  IClosePool getParentClosePool();

  /**
   * @return true if the current {@link ICloseController} has closed its elements.
   */
  boolean hasClosed();

  /**
   * Sets the {@link IClosePool} the current {@link ICloseController} will belong
   * to.
   * 
   * @param parentClosePool
   * @throws RuntimeException if the given parentClosePool is null.
   */
  void setParentClosePool(IClosePool parentClosePool);
}
