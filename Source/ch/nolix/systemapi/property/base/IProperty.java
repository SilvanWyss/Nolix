/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.base;

import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.state.staterequest.MaterializationRequestable;

/**
 * @author Silvan Wyss
 */
public interface IProperty extends MaterializationRequestable {
  /**
   * Adds or changes the given attribute to the current {@link IProperty} if the
   * given attribute is for the current {@link IProperty}.
   * 
   * @param attribute
   * @return true if the given attribute was added or changed to the current
   *         {@link IProperty}, false otherwise.
   * @throws RuntimeException if the given attribute is for the current
   *                          {@link IProperty}, but is not valid.
   */
  boolean addedOrChangedAttribute(INode<?> attribute);

  /**
   * Fills up the attributes of the current {@link IProperty} into the given list.
   * 
   * @param list
   * @throws RuntimeException if the given list is null.
   */
  void fillUpAttributesIntoList(ILinkedList<INode<?>> list);
}
