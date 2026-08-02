/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.closecontroller;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.resourceproperty.Openness;

/**
 * @author Silvan Wyss
 */
public interface IClosePool {
  /**
   * Adds the given elements to the current {@link IClosePool}.
   * 
   * @param elements
   * @throws RuntimeException if one of the given elements is null
   * @throws RuntimeException if the current {@link IClosePool} contains already
   *                          one of the given elements.
   */
  void addElements(ExtendedIterable<GroupCloseable> elements);

  /**
   * Closes the elements of the current {@link IClosePool} if the state of the
   * current {@link IClosePool} is {@link Openness#OPEN}.
   */
  void closeElementsIfStateIsOpen();

  /**
   * @return the elements of the current {@link IClosePool}.
   */
  ExtendedIterable<GroupCloseable> getStoredElements();

  /**
   * @return the state of the current {@link IClosePool}.
   */
  Openness getState();
}
