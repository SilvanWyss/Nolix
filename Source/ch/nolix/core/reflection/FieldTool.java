//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class FieldTool {

  //method
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

  //method
  public boolean hasGivenTypeOrSuperType(final Field field, final Class<?> type) {
    return type.isAssignableFrom(field.getType());
  }

  //method
  public boolean isStatic(final Field field) {

    if (field == null) {
      throw ArgumentIsNullException.forArgumentType(Field.class);
    }

    return Modifier.isStatic(field.getModifiers());
  }

  //method
  public boolean isStaticAndStoresValueOfGivenType(final Field field, final Class<?> type) {

    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.TYPE);
    }

    if (!isStatic(field)) {
      return false;
    }

    final var value = getValueFromStaticField(field);

    return (value != null && type.isAssignableFrom(value.getClass()));
  }
}
