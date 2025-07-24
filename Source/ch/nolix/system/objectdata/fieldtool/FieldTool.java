package ch.nolix.system.objectdata.fieldtool;

import java.lang.reflect.ParameterizedType;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.structurecontrol.reflectiontool.ReflectionTool;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueField;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;
import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public class FieldTool extends DatabaseObjectExaminer implements IFieldTool {

  @Override
  public final Class<?> getDataType(final IField field) {
    return switch (field.getType().getBaseType()) {
      case BASE_VALUE ->
        getDataTypeWhenIsBaseValue(field);
      case BASE_REFERENCE, BASE_BACK_REFERENCE ->
        String.class;
      default ->
        throw InvalidArgumentException.forArgument(field);
    };
  }

  private Class<?> getDataTypeWhenDoesNotBelongToEntity(IMultiValueField<?> multiValue) {

    if (multiValue.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot know its data type");
    }

    return multiValue.getAllStoredValues().getStoredFirst().getClass();
  }

  private Class<?> getDataTypeWhenDoesNotBelongToEntity(IOptionalValueField<?> optionalValue) {

    if (optionalValue.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValue, "cannot know its data type");
    }

    return optionalValue.getStoredValue().getClass();
  }

  private Class<?> getDataTypeWhenDoesNotBelongToEntity(final IValueField<?> value) {

    if (value.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot know its data type");
    }

    return value.getStoredValue().getClass();
  }

  private Class<?> getDataTypeWhenIsBaseValue(final IField field) {

    if (!field.belongsToEntity()) {
      return getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(field);
    }

    return getDataTypeWhenIsBaseValueAndBelongsToEntity(field);
  }

  private Class<?> getDataTypeWhenIsBaseValueAndBelongsToEntity(final IField field) {

    final var fieldParentEntity = field.getStoredParentEntity();

    final var entityField = ReflectionTool.getFirstFieldOfObjectThatStoresValue(fieldParentEntity, field);

    final var fieldDeclaredType = (ParameterizedType) entityField.getGenericType();

    final var typeArguments = fieldDeclaredType.getActualTypeArguments();

    return (Class<?>) typeArguments[typeArguments.length - 1];
  }

  private Class<?> getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(final IField field) {
    return switch (field.getType()) {
      case VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IValueField<?>) field);
      case OPTIONAL_VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IOptionalValueField<?>) field);
      case MULTI_VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IMultiValueField<?>) field);
      default ->
        throw InvalidArgumentException.forArgumentAndErrorPredicate(field, "is not a base value");
    };
  }
}
