//package declaration
package ch.nolix.system.objectdata.fieldtool;

//Java imports
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.reflection.GlobalObjectTool;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.BaseCardinality;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;

//class
public class FieldTool extends DatabaseObjectTool implements IFieldTool {

  //method
  @Override
  public boolean belongsToEntity(final IField field) {
    return //
    field != null
    && field.belongsToEntity();
  }

  //method
  @Override
  public final boolean belongsToLoadedEntity(final IField field) {
    return //
    field.belongsToEntity()
    && field.getStoredParentEntity().isLoaded();
  }

  //method
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

  //method
  @Override
  public final boolean isForMultiContent(final IField field) {
    return (field.getType().getCardinality().getBaseCardinality() == BaseCardinality.MULTI);
  }

  //method
  @Override
  public final boolean isForSingleContent(final IField field) {
    return (field.getType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE);
  }

  //method
  @Override
  public final boolean isMandatoryAndEmptyBoth(final IField field) {
    return field.isMandatory()
    && field.isEmpty();
  }

  //method
  @Override
  public final boolean isSetForCaseIsNewOrEditedAndMandatory(final IField field) {
    return !field.isMandatory()
    || !isNewOrEdited(field)
    || field.containsAny();
  }

  //method
  private Class<?> getDataTypeWhenDoesNotBelongToEntity(IMultiValue<?> multiValue) {

    if (multiValue.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot know its data type");
    }

    return multiValue.getAllStoredValues().getStoredFirst().getClass();
  }

  //method
  private Class<?> getDataTypeWhenDoesNotBelongToEntity(IOptionalValue<?> optionalValue) {

    if (optionalValue.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValue, "cannot know its data type");
    }

    return optionalValue.getStoredValue().getClass();
  }

  //method
  private Class<?> getDataTypeWhenDoesNotBelongToEntity(final IValue<?> value) {

    if (value.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot know its data type");
    }

    return value.getStoredValue().getClass();
  }

  //method
  private Class<?> getDataTypeWhenIsBaseValue(final IField field) {

    if (!field.belongsToEntity()) {
      return getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(field);
    }

    return getDataTypeWhenIsBaseValueAndBelongsToEntity(field);
  }

  //method
  private Class<?> getDataTypeWhenIsBaseValueAndBelongsToEntity(final IField field) {

    final var fieldParentEntity = field.getStoredParentEntity();

    final var entityField = GlobalObjectTool.getFirstFieldOfObjectThatStoresValue(fieldParentEntity, field);

    final var fieldDeclaredType = (ParameterizedType) entityField.getGenericType();

    final var typeArguments = fieldDeclaredType.getActualTypeArguments();

    return (Class<?>) typeArguments[typeArguments.length - 1];
  }

  //method
  private Class<?> getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(final IField field) {
    return switch (field.getType()) {
      case VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IValue<?>) field);
      case OPTIONAL_VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IOptionalValue<?>) field);
      case MULTI_VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IMultiValue<?>) field);
      default ->
        throw InvalidArgumentException.forArgumentAndErrorPredicate(field, "is not a base value");
    };
  }
}
