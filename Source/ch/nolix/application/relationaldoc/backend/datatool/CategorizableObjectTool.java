package ch.nolix.application.relationaldoc.backend.datatool;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datatoolapi.ICategorizableObjectTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

public final class CategorizableObjectTool implements ICategorizableObjectTool {

  @Override
  public IContainer<? extends ICategorizableObject> getStoredSubTypesUsingSimplerMethods(
    ICategorizableObject categorizableObject) {

    final ILinkedList<ICategorizableObject> subTypes = LinkedList.createEmpty();

    fillUpSubTypesIntoListUsingSimplerMethods(categorizableObject, subTypes);

    return subTypes;
  }

  private void fillUpSubTypesIntoListUsingSimplerMethods(
    final ICategorizableObject categorizableObject,
    final ILinkedList<ICategorizableObject> list) {
    for (final var dst : categorizableObject.getStoredDirectSubTypes()) {
      if (!list.contains(dst)) {
        list.addAtEnd(dst);
        fillUpSubTypesIntoListUsingSimplerMethods(dst, list);
      }
    }
  }
}
