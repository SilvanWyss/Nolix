package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedBackReferenceTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedMultiBackReferenceTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedMultiReferenceTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedMultiValueTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedOptionalBackReferenceTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedOptionalReferenceTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedOptionalValueTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedReferenceTypeMapper;
import ch.nolix.system.objectdata.parameterizedfieldtypemapper2.ParameterizedValueTypeMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedFieldTypeMapper //NOSONAR: A ParameterizedFieldTypeMapper provides many cases and thus many dependencies.
implements IParameterizedFieldTypeMapper {

  private static final ParameterizedValueTypeMapper PARAMETERIZED_VALUE_TYPE_MAPPER = //
  new ParameterizedValueTypeMapper();

  private static final ParameterizedOptionalValueTypeMapper PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER = //
  new ParameterizedOptionalValueTypeMapper();

  private static final ParameterizedMultiValueTypeMapper PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER = //
  new ParameterizedMultiValueTypeMapper();

  private static final ParameterizedReferenceTypeMapper PARAMETERIZED_REFERENCE_TYPE_MAPPER = //
  new ParameterizedReferenceTypeMapper();

  private static final ParameterizedOptionalReferenceTypeMapper PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalReferenceTypeMapper();

  private static final ParameterizedMultiReferenceTypeMapper PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiReferenceTypeMapper();

  private static final ParameterizedBackReferenceTypeMapper PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedBackReferenceTypeMapper();

  private static final ParameterizedOptionalBackReferenceTypeMapper //
  PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalBackReferenceTypeMapper();

  private static final ParameterizedMultiBackReferenceTypeMapper PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiBackReferenceTypeMapper();

  @Override
  @SuppressWarnings("unchecked")
  public IParameterizedFieldType createParameterizedFieldTypeFromField(
    final IField field,
    IContainer<ITable> referencedTables) {

    final var fieldType = field.getType();

    return switch (fieldType) {
      case VALUE ->
        PARAMETERIZED_VALUE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IValue<?>) field,
          referencedTables);
      case OPTIONAL_VALUE ->
        PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IOptionalValue<?>) field,
          referencedTables);
      case MULTI_VALUE ->
        PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IMultiValue<?>) field,
          referencedTables);
      case REFERENCE ->
        PARAMETERIZED_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IReference<IEntity>) field,
          referencedTables);
      case OPTIONAL_REFERENCE ->
        PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IOptionalReference<IEntity>) field,
          referencedTables);
      case MULTI_REFERENCE ->
        PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IMultiReference<IEntity>) field,
          referencedTables);
      case BACK_REFERENCE ->
        PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IBackReference<IEntity>) field,
          referencedTables);
      case OPTIONAL_BACK_REFERENCE ->
        PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IOptionalBackReference<IEntity>) field,
          referencedTables);
      case MULTI_BACK_REFERENCE ->
        PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromField(
          (IMultiBackReference<IEntity>) field,
          referencedTables);
      default ->
        throw InvalidArgumentException.forArgument(field);
    };
  }
}
