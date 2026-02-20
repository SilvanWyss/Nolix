/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.property;

import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public interface IProperty {
  boolean addedOrChangedAttribute(INode<?> attribute);

  void fillUpAttributesInto(ILinkedList<INode<?>> list);
}
