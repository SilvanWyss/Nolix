package ch.nolix.coreapi.resourcecontrol.resourceclosing;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programcontrol.processproperty.CloseState;

/**
 * @author Silvan Wyss
 * @version 2022-07-08
 */
public interface IClosePool {

  /**
   * Adds the given elements to the current {@link IClosePool}.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   * @throws RuntimeException if the current {@link IClosePool} contains already
   *                          one of the given elements.
   */
  void addElements(IContainer<GroupCloseable> elements);

  /**
   * Closes the elements of the current {@link IClosePool} if the state of the
   * current {@link IClosePool} is {@link CloseState#OPEN}.
   */
  void closeElementsIfStateIsOpen();

  /**
   * @return the elements of the current {@link IClosePool}.
   */
  IContainer<GroupCloseable> getStoredElements();

  /**
   * @return the state of the current {@link IClosePool}.
   */
  CloseState getState();
}
