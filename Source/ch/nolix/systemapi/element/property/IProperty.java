package ch.nolix.systemapi.element.property;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public interface IProperty {
  boolean addedOrChangedAttribute(INode<?> attribute);

  void fillUpAttributesInto(ILinkedList<INode<?>> list);
}
