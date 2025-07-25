package ch.nolix.core.structurecontrol.reflectiontool;

import java.lang.reflect.Field;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.structurecontrol.reflectionexaminer.FieldExaminer;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.structurecontrol.reflectionexaminer.IFieldExaminer;

public final class FieldTool {

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  @SuppressWarnings("unchecked")
  public <V> V getValueFromStaticField(final Field paramField) {

    if (!FIELD_EXAMINER.isStatic(paramField)) {
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

  public boolean isStaticAndStoresValueOfGivenType(final Field field, final Class<?> type) {

    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.TYPE);
    }

    if (!FIELD_EXAMINER.isStatic(field)) {
      return false;
    }

    final var value = getValueFromStaticField(field);

    return (value != null && type.isAssignableFrom(value.getClass()));
  }
}
