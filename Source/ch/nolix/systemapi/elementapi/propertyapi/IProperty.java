package ch.nolix.systemapi.elementapi.propertyapi;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

public interface IProperty {

  boolean addedOrChangedAttribute(INode<?> attribute);

  void fillUpAttributesInto(ILinkedList<INode<?>> list);
}
