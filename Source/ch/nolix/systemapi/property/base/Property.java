/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.base;

import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalstate.staterequest.MaterializationRequestable;

/**
 * @author Silvan Wyss
 */
public interface Property extends MaterializationRequestable {
  /**
   * Adds or changes the given attribute to the current {@link Property} if the
   * given attribute is for the current {@link Property}.
   * 
   * @param attribute
   * @return true if the given attribute was added or changed to the current
   *         {@link Property}, false otherwise
   * @throws RuntimeException if the given attribute is for the current
   *                          {@link Property}, but is not valid
   */
  boolean addedOrChangedAttribute(Node<?> attribute);

  /**
   * Fills up the attributes of the current {@link Property} into the given list.
   * 
   * @param list
   * @throws RuntimeException if the given list is null
   */
  void fillUpAttributesIntoList(ILinkedList<Node<?>> list);
}
