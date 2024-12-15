package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.contentmodel.BackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.MultiBackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.MultiReferenceModel;
import ch.nolix.system.objectschema.contentmodel.MultiValueModel;
import ch.nolix.system.objectschema.contentmodel.OptionalBackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.OptionalReferenceModel;
import ch.nolix.system.objectschema.contentmodel.OptionalValueModel;
import ch.nolix.system.objectschema.contentmodel.ReferenceModel;
import ch.nolix.system.objectschema.contentmodel.ValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public final class ParameterizedFieldTypeMapper {

  public IContentModel createParameterizedFieldTypeFromDto(
    final IParameterizedFieldTypeDto parameterizedFieldType,
    final IContainer<ITable> tables) {
    return switch (parameterizedFieldType.getFieldType()) {
      case VALUE ->
        ValueModel.forDataType(parameterizedFieldType.getDataType());
      case OPTIONAL_VALUE ->
        OptionalValueModel.forDataType(parameterizedFieldType.getDataType());
      case MULTI_VALUE ->
        MultiValueModel.forDataType(parameterizedFieldType.getDataType());
      case REFERENCE ->
        ReferenceModel.forReferencedTable(
          getStoredReferencedTableFromParameterizedFieldType(parameterizedFieldType, tables));
      case OPTIONAL_REFERENCE ->
        OptionalReferenceModel.forReferencedTable(
          getStoredReferencedTableFromParameterizedFieldType(parameterizedFieldType, tables));
      case MULTI_REFERENCE ->
        MultiReferenceModel.forReferencedTable(
          getStoredReferencedTableFromParameterizedFieldType(parameterizedFieldType, tables));
      case BACK_REFERENCE ->
        BackReferenceModel.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedFieldType(parameterizedFieldType, tables));
      case OPTIONAL_BACK_REFERENCE ->
        OptionalBackReferenceModel.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedFieldType(parameterizedFieldType, tables));
      case MULTI_BACK_REFERENCE ->
        MultiBackReferenceModel.forBackReferencedColumn(
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

    return (Column) tables.toMultiple(ITable::getStoredColumns).getStoredFirst(c -> c.hasId(backReferencedColumnId));
  }

  private ITable getStoredReferencedTableFromParameterizedFieldType(
    final IParameterizedFieldTypeDto parameterizedFieldType,
    final IContainer<ITable> tables) {

    final var baseParameterizedReferenceType = (IBaseParameterizedReferenceTypeDto) parameterizedFieldType;

    return tables.getStoredFirst(t -> t.hasId(baseParameterizedReferenceType.getReferencedTableId()));
  }
}
