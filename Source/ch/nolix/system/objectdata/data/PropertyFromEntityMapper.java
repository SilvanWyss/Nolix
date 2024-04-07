//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.reflection.GlobalFieldTool;

//class
final class PropertyFromEntityMapper {

  //method
  public LinkedList<Field> getStoredPropertiesFrom(final Object entity) {

    final var properties = new LinkedList<Field>();

    fillUpPropertiesFromGivenObjectIntoGivenList(entity, properties);

    return properties;
  }

  //method
  private void fillUpPropertiesFromGivenObjectIntoGivenList(
    final Object object,
    final LinkedList<Field> list) {
    Class<?> lClass = object.getClass();
    while (lClass != null) {
      fillUpPropertiesFromGivenObjectAndForGivenClassIntoGivenList(object, lClass, list);
      lClass = lClass.getSuperclass();
    }
  }

  //method
  private void fillUpPropertiesFromGivenObjectAndForGivenClassIntoGivenList(
    final Object object,
    final Class<?> pClass,
    final LinkedList<Field> list) {
    for (final var f : pClass.getDeclaredFields()) {
      fillUpPotentialPropertyFromGivenObjectForGivenFieldIntoGivenList(object, f, list);
    }
  }

  //method
  private void fillUpPotentialPropertyFromGivenObjectForGivenFieldIntoGivenList(
    final Object object,
    final java.lang.reflect.Field field,
    final LinkedList<Field> list) {
    if (fieldIsProperty(field)) {
      fillUpPropertyFromGivenObjectForGivenFieldIntoGivenList(object, field, list);
    }
  }

  //method
  private boolean fieldIsProperty(final java.lang.reflect.Field field) {
    return GlobalFieldTool.hasGivenTypeOrSuperType(field, Field.class);
  }

  //method
  private void fillUpPropertyFromGivenObjectForGivenFieldIntoGivenList(
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
