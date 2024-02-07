//package declaration
package ch.nolix.tech.relationaldoc.datatool;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datatoolapi.IAbstractableObjectTool;

//class
public final class AbstractableObjectTool implements IAbstractableObjectTool {

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredSubTypesUsingSimplerMethods(
    IAbstractableObject abstractableObject) {

    final var subTypes = new LinkedList<IAbstractableObject>();

    fillUpSubTypesIntoListUsingSimplerMethods(abstractableObject, subTypes);

    return subTypes;
  }

  //method
  private void fillUpSubTypesIntoListUsingSimplerMethods(
    final IAbstractableObject abstractableObject,
    final ILinkedList<IAbstractableObject> list) {
    for (final var dst : abstractableObject.getStoredDirectSubTypes()) {
      if (!list.contains(dst)) {
        list.addAtEnd(dst);
        fillUpSubTypesIntoListUsingSimplerMethods(dst, list);
      }
    }
  }
}
