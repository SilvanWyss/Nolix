package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

final class FieldFromEntityMapper {

  public ILinkedList<Field> getStoredFieldsFrom(final Object entity) {

    final ILinkedList<Field> fields = LinkedList.createEmpty();

    fillUpFieldsFromObjectIntoList(entity, fields);

    return fields;
  }

  private void fillUpFieldsFromObjectIntoList(
    final Object object,
    final ILinkedList<Field> list) {
    Class<?> lClass = object.getClass();
    while (lClass != null) {
      fillUpFieldsFromGivenObjectAndForGivenClassIntoGivenList(object, lClass, list);
      lClass = lClass.getSuperclass();
    }
  }

  private void fillUpFieldsFromGivenObjectAndForGivenClassIntoGivenList(
    final Object object,
    final Class<?> pClass,
    final ILinkedList<Field> list) {
    for (final var f : pClass.getDeclaredFields()) {
      fillUpPotentialFieldFromObjectForFieldIntoList(object, f, list);
    }
  }

  private void fillUpPotentialFieldFromObjectForFieldIntoList(
    final Object object,
    final java.lang.reflect.Field field,
    final ILinkedList<Field> list) {
    if (isField(field)) {
      fillUpFieldFromObjectForFieldIntoList(object, field, list);
    }
  }

  private boolean isField(final java.lang.reflect.Field field) {
    return GlobalReflectionTool.hasGivenTypeOrSuperType(field, Field.class);
  }

  private void fillUpFieldFromObjectForFieldIntoList(
    final Object object,
    final java.lang.reflect.Field field,
    final ILinkedList<Field> list) {

    field.setAccessible(true);

    try {
      list.addAtEnd((Field) field.get(object));
    } catch (final IllegalArgumentException | IllegalAccessException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
