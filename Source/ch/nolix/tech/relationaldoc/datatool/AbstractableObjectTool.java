package ch.nolix.tech.relationaldoc.datatool;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datatoolapi.IAbstractableObjectTool;

public final class AbstractableObjectTool implements IAbstractableObjectTool {

  @Override
  public IContainer<? extends IAbstractableObject> getStoredSubTypesUsingSimplerMethods(
    IAbstractableObject abstractableObject) {

    final ILinkedList<IAbstractableObject> subTypes = LinkedList.createEmpty();

    fillUpSubTypesIntoListUsingSimplerMethods(abstractableObject, subTypes);

    return subTypes;
  }

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
