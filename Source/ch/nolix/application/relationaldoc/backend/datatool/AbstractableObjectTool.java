package ch.nolix.application.relationaldoc.backend.datatool;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datatoolapi.IAbstractableObjectTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

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
