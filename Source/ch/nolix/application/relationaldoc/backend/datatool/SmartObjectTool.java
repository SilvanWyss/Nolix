package ch.nolix.application.relationaldoc.backend.datatool;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datatoolapi.ISmartObjectTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

public final class SmartObjectTool implements ISmartObjectTool {

  @Override
  public IContainer<? extends ISmartObject> getStoredSubTypesUsingSimplerMethods(
    ISmartObject smartObject) {

    final ILinkedList<ISmartObject> subTypes = LinkedList.createEmpty();

    fillUpSubTypesIntoListUsingSimplerMethods(smartObject, subTypes);

    return subTypes;
  }

  private void fillUpSubTypesIntoListUsingSimplerMethods(
    final ISmartObject smartObject,
    final ILinkedList<ISmartObject> list) {
    for (final var dst : smartObject.getStoredDirectSubTypes()) {
      if (!list.contains(dst)) {
        list.addAtEnd(dst);
        fillUpSubTypesIntoListUsingSimplerMethods(dst, list);
      }
    }
  }
}
