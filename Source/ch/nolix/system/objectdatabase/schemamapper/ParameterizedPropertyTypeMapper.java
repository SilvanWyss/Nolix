//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedBackReferenceTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedMultiBackReferenceTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedMultiReferenceTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedMultiValueTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedOptionalBackReferenceTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedOptionalReferenceTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedOptionalValueTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedReferenceTypeMapper;
import ch.nolix.system.objectdatabase.parameterizedpropertytypemapper2.ParameterizedValueTypeMapper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IParameterizedPropertyTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedPropertyTypeMapper //NOSONAR: A ParameterizedPropertyTypeMapper is a central object with many dependencies.
implements IParameterizedPropertyTypeMapper {

  //constant
  private static final ParameterizedValueTypeMapper PARAMETERIZED_VALUE_TYPE_MAPPER = //
  new ParameterizedValueTypeMapper();

  //constant
  private static final ParameterizedOptionalValueTypeMapper PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER = //
  new ParameterizedOptionalValueTypeMapper();

  //constant
  private static final ParameterizedMultiValueTypeMapper PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER = //
  new ParameterizedMultiValueTypeMapper();

  //constant
  private static final ParameterizedReferenceTypeMapper PARAMETERIZED_REFERENCE_TYPE_MAPPER = //
  new ParameterizedReferenceTypeMapper();

  //constant
  private static final ParameterizedOptionalReferenceTypeMapper PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalReferenceTypeMapper();

  //constant
  private static final ParameterizedMultiReferenceTypeMapper PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiReferenceTypeMapper();

  //constant
  private static final ParameterizedBackReferenceTypeMapper PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedBackReferenceTypeMapper();

  //constant
  private static final ParameterizedOptionalBackReferenceTypeMapper //
  PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalBackReferenceTypeMapper();

  //constant
  private static final ParameterizedMultiBackReferenceTypeMapper PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiBackReferenceTypeMapper();

  //method
  @Override
  @SuppressWarnings("unchecked")
  public IParameterizedPropertyType createParameterizedPropertyTypeFromProperty(
    final IProperty property,
    IContainer<ITable> referencedTables) {

    final var propertyType = property.getType();

    return switch (propertyType) {
      case VALUE ->
        PARAMETERIZED_VALUE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IValue<?>) property,
          referencedTables);
      case OPTIONAL_VALUE ->
        PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IOptionalValue<?>) property,
          referencedTables);
      case MULTI_VALUE ->
        PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IMultiValue<?>) property,
          referencedTables);
      case REFERENCE ->
        PARAMETERIZED_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IReference<IEntity>) property,
          referencedTables);
      case OPTIONAL_REFERENCE ->
        PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IOptionalReference<IEntity>) property,
          referencedTables);
      case MULTI_REFERENCE ->
        PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IMultiReference<IEntity>) property,
          referencedTables);
      case BACK_REFERENCE ->
        PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IBackReference<IEntity>) property,
          referencedTables);
      case OPTIONAL_BACK_REFERENCE ->
        PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IOptionalBackReference<IEntity>) property,
          referencedTables);
      case MULTI_BACK_REFERENCE ->
        PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromProperty(
          (IMultiBackReference<IEntity>) property,
          referencedTables);
      default ->
        throw InvalidArgumentException.forArgument(property);
    };
  }
}
