//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.reflection.GlobalFieldTool;

//class
final class FieldFromEntityMapper {

  //method
  public LinkedList<Field> getStoredFieldsFrom(final Object entity) {

    final var fields = new LinkedList<Field>();

    fillUpFieldsFromObjectIntoList(entity, fields);

    return fields;
  }

  //method
  private void fillUpFieldsFromObjectIntoList(
    final Object object,
    final LinkedList<Field> list) {
    Class<?> lClass = object.getClass();
    while (lClass != null) {
      fillUpFieldsFromGivenObjectAndForGivenClassIntoGivenList(object, lClass, list);
      lClass = lClass.getSuperclass();
    }
  }

  //method
  private void fillUpFieldsFromGivenObjectAndForGivenClassIntoGivenList(
    final Object object,
    final Class<?> pClass,
    final LinkedList<Field> list) {
    for (final var f : pClass.getDeclaredFields()) {
      fillUpPotentialFieldFromObjectForFieldIntoList(object, f, list);
    }
  }

  //method
  private void fillUpPotentialFieldFromObjectForFieldIntoList(
    final Object object,
    final java.lang.reflect.Field field,
    final LinkedList<Field> list) {
    if (isField(field)) {
      fillUpFieldFromObjectForFieldIntoList(object, field, list);
    }
  }

  //method
  private boolean isField(final java.lang.reflect.Field field) {
    return GlobalFieldTool.hasGivenTypeOrSuperType(field, Field.class);
  }

  //method
  private void fillUpFieldFromObjectForFieldIntoList(
    final Object object,
    final java.lang.reflect.Field field,
    final LinkedList<Field> list) {

    field.setAccessible(true);

    try {
      list.addAtEnd((Field) field.get(object));
    } catch (final IllegalArgumentException | IllegalAccessException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
