package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

final class FieldFromEntityMapper {

  public ILinkedList<AbstractField> getStoredFieldsFrom(final Object entity) {

    final ILinkedList<AbstractField> fields = LinkedList.createEmpty();

    fillUpFieldsFromObjectIntoList(entity, fields);

    return fields;
  }

  private void fillUpFieldsFromObjectIntoList(
    final Object object,
    final ILinkedList<AbstractField> list) {
    Class<?> lClass = object.getClass();
    while (lClass != null) {
      fillUpFieldsFromGivenObjectAndForGivenClassIntoGivenList(object, lClass, list);
      lClass = lClass.getSuperclass();
    }
  }

  private void fillUpFieldsFromGivenObjectAndForGivenClassIntoGivenList(
    final Object object,
    final Class<?> pClass,
    final ILinkedList<AbstractField> list) {
    for (final var f : pClass.getDeclaredFields()) {
      fillUpPotentialFieldFromObjectForFieldIntoList(object, f, list);
    }
  }

  private void fillUpPotentialFieldFromObjectForFieldIntoList(
    final Object object,
    final java.lang.reflect.Field field,
    final ILinkedList<AbstractField> list) {
    if (isField(field)) {
      fillUpFieldFromObjectForFieldIntoList(object, field, list);
    }
  }

  private boolean isField(final java.lang.reflect.Field field) {
    return GlobalReflectionTool.hasGivenTypeOrSuperType(field, AbstractField.class);
  }

  private void fillUpFieldFromObjectForFieldIntoList(
    final Object object,
    final java.lang.reflect.Field field,
    final ILinkedList<AbstractField> list) {

    field.setAccessible(true);

    try {
      list.addAtEnd((AbstractField) field.get(object));
    } catch (final IllegalArgumentException | IllegalAccessException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
