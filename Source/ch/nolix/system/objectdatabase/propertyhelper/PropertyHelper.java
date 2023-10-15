//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//Java imports
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.reflection.GlobalReflectionHelper;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.BaseCardinality;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;

//class
public class PropertyHelper extends DatabaseObjectHelper implements IPropertyHelper {

  // method
  @Override
  public boolean belongsToEntity(final IProperty property) {
    return property != null
        && property.belongsToEntity();
  }

  // method
  @Override
  public final boolean belongsToLoadedEntity(final IProperty property) {
    return property.belongsToEntity()
        && isLoaded(property.getStoredParentEntity());
  }

  // method
  @Override
  public final Class<?> getDataType(final IProperty property) {
    return switch (property.getType().getBaseType()) {
      case BASE_VALUE ->
        getDataTypeWhenIsBaseValue(property);
      case BASE_REFERENCE, BASE_BACK_REFERENCE ->
        String.class;
      default ->
        throw InvalidArgumentException.forArgument(property);
    };
  }

  // method
  @Override
  public final boolean isForMultiContent(final IProperty property) {
    return (property.getType().getCardinality().getBaseCardinality() == BaseCardinality.MULTI);
  }

  // method
  @Override
  public final boolean isForSingleContent(final IProperty property) {
    return (property.getType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE);
  }

  // method
  @Override
  public final boolean isMandatoryAndEmptyBoth(final IProperty property) {
    return property.isMandatory()
        && property.isEmpty();
  }

  // method
  @Override
  public final boolean isSetForCaseIsNewOrEditedAndMandatory(final IProperty property) {
    return !property.isMandatory()
        || !isNewOrEdited(property)
        || property.containsAny();
  }

  // method
  private Class<?> getDataTypeWhenDoesNotBelongToEntity(IMultiValue<?> multiValue) {

    if (multiValue.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot know its data type");
    }

    return multiValue.getStoredValues().getStoredFirst().getClass();
  }

  // method
  private Class<?> getDataTypeWhenDoesNotBelongToEntity(IOptionalValue<?> optionalValue) {

    if (optionalValue.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValue, "cannot know its data type");
    }

    return optionalValue.getStoredValue().getClass();
  }

  // method
  private Class<?> getDataTypeWhenDoesNotBelongToEntity(final IValue<?> value) {

    if (value.isEmpty()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot know its data type");
    }

    return value.getStoredValue().getClass();
  }

  // method
  private Class<?> getDataTypeWhenIsBaseValue(final IProperty property) {

    if (!property.belongsToEntity()) {
      return getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(property);
    }

    return getDataTypeWhenIsBaseValueAndBelongsToEntity(property);
  }

  // method
  private Class<?> getDataTypeWhenIsBaseValueAndBelongsToEntity(final IProperty property) {
    final var propertyParentEntity = property.getStoredParentEntity();

    final var propertyField = GlobalReflectionHelper.getStoredField(propertyParentEntity, property);

    final var propertyDeclaredType = (ParameterizedType) propertyField.getGenericType();

    final var typeArguments = propertyDeclaredType.getActualTypeArguments();

    return (Class<?>) typeArguments[typeArguments.length - 1];
  }

  // method
  private Class<?> getDataTypeWhenIsBaseValueAndDoesNotBelongToEntity(final IProperty property) {
    return switch (property.getType()) {
      case VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IValue<?>) property);
      case OPTIONAL_VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IOptionalValue<?>) property);
      case MULTI_VALUE ->
        getDataTypeWhenDoesNotBelongToEntity((IMultiValue<?>) property);
      default ->
        throw InvalidArgumentException.forArgumentAndErrorPredicate(property, "is not a base value");
    };
  }
}
