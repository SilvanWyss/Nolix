package ch.nolix.core.structurecontrol.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class FieldTool {

  @SuppressWarnings("unchecked")
  public <V> V getValueFromStaticField(final Field paramField) {

    if (!isStatic(paramField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(paramField, "is not ");
    }

    try {
      paramField.setAccessible(true);
      return (V) paramField.get(null);
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }

  public boolean hasGivenTypeOrSuperType(final Field field, final Class<?> type) {
    return type.isAssignableFrom(field.getType());
  }

  public boolean isStatic(final Field field) {

    if (field == null) {
      throw ArgumentIsNullException.forArgumentType(Field.class);
    }

    return Modifier.isStatic(field.getModifiers());
  }

  public boolean isStaticAndStoresValueOfGivenType(final Field field, final Class<?> type) {

    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.TYPE);
    }

    if (!isStatic(field)) {
      return false;
    }

    final var value = getValueFromStaticField(field);

    return (value != null && type.isAssignableFrom(value.getClass()));
  }
}
