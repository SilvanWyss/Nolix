package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedFieldType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedMultiBackReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedMultiReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedMultiValueType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedOptionalBackReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedOptionalReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedOptionalValueType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedReferenceType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public final class ParameterizedFieldTypeMapper {

  public ParameterizedFieldType createParameterizedFieldTypeFromDto(
    final IParameterizedFieldTypeDto parameterizedFieldType,
    final IContainer<ITable> tables) {
    return switch (parameterizedFieldType.getFieldType()) {
      case VALUE ->
        ParameterizedValueType.forDataType(parameterizedFieldType.getDataType());
      case OPTIONAL_VALUE ->
        ParameterizedOptionalValueType.forDataType(parameterizedFieldType.getDataType());
      case MULTI_VALUE ->
        ParameterizedMultiValueType.forDataType(parameterizedFieldType.getDataType());
      case REFERENCE ->
        ParameterizedReferenceType.forReferencedTable(
          getStoredReferencedTableFromParameterizedFieldType(parameterizedFieldType, tables));
      case OPTIONAL_REFERENCE ->
        ParameterizedOptionalReferenceType.forReferencedTable(
          getStoredReferencedTableFromParameterizedFieldType(parameterizedFieldType, tables));
      case MULTI_REFERENCE ->
        ParameterizedMultiReferenceType.forReferencedTable(
          getStoredReferencedTableFromParameterizedFieldType(parameterizedFieldType, tables));
      case BACK_REFERENCE ->
        ParameterizedBackReferenceType.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedFieldType(parameterizedFieldType, tables));
      case OPTIONAL_BACK_REFERENCE ->
        ParameterizedOptionalBackReferenceType.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedFieldType(parameterizedFieldType, tables));
      case MULTI_BACK_REFERENCE ->
        ParameterizedMultiBackReferenceType.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedFieldType(parameterizedFieldType, tables));
      default ->
        throw InvalidArgumentException.forArgument(parameterizedFieldType);
    };
  }

  private Column getStoredBackReferencedColumnFromParameterizedFieldType(
    final IParameterizedFieldTypeDto parameterizedFieldType,
    final IContainer<ITable> tables) {

    final var baseParameterizedBackReferenceType = (IBaseParameterizedBackReferenceTypeDto) parameterizedFieldType;
    final var backReferencedColumnId = baseParameterizedBackReferenceType.getBackReferencedColumnId();

    return (Column) tables.toFromGroups(ITable::getStoredColumns).getStoredFirst(c -> c.hasId(backReferencedColumnId));
  }

  private ITable getStoredReferencedTableFromParameterizedFieldType(
    final IParameterizedFieldTypeDto parameterizedFieldType,
    final IContainer<ITable> tables) {

    final var baseParameterizedReferenceType = (IBaseParameterizedReferenceTypeDto) parameterizedFieldType;

    return tables.getStoredFirst(t -> t.hasId(baseParameterizedReferenceType.getReferencedTableId()));
  }
}
