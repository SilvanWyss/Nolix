//package declaration
package ch.nolix.coreapi.programcontrolapi.resourcecontrolapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.processproperty.CloseState;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-07-08
 */
public interface IClosePool {

  //method declaration
  /**
   * Adds the given elements to the current {@link IClosePool}.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null.
   * @throws RuntimeException if the current {@link IClosePool} contains already
   *                          one of the given elements.
   */
  void addElements(IContainer<GroupCloseable> elements);

  //method declaration
  /**
   * Closes the elements of the current {@link IClosePool} if the state of the
   * current {@link IClosePool} is {@link CloseState#OPEN}.
   */
  void closeElementsIfStateIsOpen();

  //method declaration
  /**
   * @return the elements of the current {@link IClosePool}.
   */
  IContainer<GroupCloseable> getStoredElements();

  //method declaration
  /**
   * @return the state of the current {@link IClosePool}.
   */
  CloseState getState();
}
