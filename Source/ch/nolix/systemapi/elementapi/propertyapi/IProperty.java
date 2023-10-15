//package declaration
package ch.nolix.systemapi.elementapi.propertyapi;

//own imports
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//interface
public interface IProperty {

  // method declaration
  boolean addedOrChangedAttribute(INode<?> attribute);

  // method declaration
  void fillUpAttributesInto(ILinkedList<INode<?>> list);
}
