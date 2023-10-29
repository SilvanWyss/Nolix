//package declaration
package ch.nolix.system.objectdatabase.database;

//Java imports
import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.reflection.GlobalFieldHelper;

//class
final class PropertyFromEntityExtractor {

  //method
  public LinkedList<Property> getStoredPropertiesFrom(final Object entity) {

    final var properties = new LinkedList<Property>();

    fillUpPropertiesFromGivenObjectIntoGivenList(entity, properties);

    return properties;
  }

  //method
  private void fillUpPropertiesFromGivenObjectIntoGivenList(
    final Object object,
    final LinkedList<Property> list) {
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
    final LinkedList<Property> list) {
    for (final var f : pClass.getDeclaredFields()) {
      fillUpPotentialPropertyFromGivenObjectForGivenFieldIntoGivenList(object, f, list);
    }
  }

  //method
  private void fillUpPotentialPropertyFromGivenObjectForGivenFieldIntoGivenList(
    final Object object,
    final Field field,
    final LinkedList<Property> list) {
    if (fieldIsProperty(field)) {
      fillUpPropertyFromGivenObjectForGivenFieldIntoGivenList(object, field, list);
    }
  }

  //method
  private boolean fieldIsProperty(final Field field) {
    return GlobalFieldHelper.hasGivenTypeOrSuperType(field, Property.class);
  }

  //method
  private void fillUpPropertyFromGivenObjectForGivenFieldIntoGivenList(
    final Object object,
    final Field field,
    final LinkedList<Property> list) {

    field.setAccessible(true);

    try {
      list.addAtEnd((Property) field.get(object));
    } catch (final IllegalArgumentException | IllegalAccessException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
