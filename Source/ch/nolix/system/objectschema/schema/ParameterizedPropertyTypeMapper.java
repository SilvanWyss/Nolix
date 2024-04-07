//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
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
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeMapper {

  //method
  public ParameterizedFieldType createParameterizedPropertyTypeFromDto(
    final IParameterizedPropertyTypeDto parameterizedPropertyType,
    final IContainer<ITable> tables) {
    return switch (parameterizedPropertyType.getPropertyType()) {
      case VALUE ->
        ParameterizedValueType.forDataType(parameterizedPropertyType.getDataType());
      case OPTIONAL_VALUE ->
        ParameterizedOptionalValueType.forDataType(parameterizedPropertyType.getDataType());
      case MULTI_VALUE ->
        ParameterizedMultiValueType.forDataType(parameterizedPropertyType.getDataType());
      case REFERENCE ->
        ParameterizedReferenceType.forReferencedTable(
          getStoredReferencedTableFromParameterizedPropertyType(parameterizedPropertyType, tables));
      case OPTIONAL_REFERENCE ->
        ParameterizedOptionalReferenceType.forReferencedTable(
          getStoredReferencedTableFromParameterizedPropertyType(parameterizedPropertyType, tables));
      case MULTI_REFERENCE ->
        ParameterizedMultiReferenceType.forReferencedTable(
          getStoredReferencedTableFromParameterizedPropertyType(parameterizedPropertyType, tables));
      case BACK_REFERENCE ->
        ParameterizedBackReferenceType.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedPropertyType(parameterizedPropertyType, tables));
      case OPTIONAL_BACK_REFERENCE ->
        ParameterizedOptionalBackReferenceType.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedPropertyType(parameterizedPropertyType, tables));
      case MULTI_BACK_REFERENCE ->
        ParameterizedMultiBackReferenceType.forBackReferencedColumn(
          getStoredBackReferencedColumnFromParameterizedPropertyType(parameterizedPropertyType, tables));
      default ->
        throw InvalidArgumentException.forArgument(parameterizedPropertyType);
    };
  }

  //method
  private Column getStoredBackReferencedColumnFromParameterizedPropertyType(
    final IParameterizedPropertyTypeDto parameterizedPropertyType,
    final IContainer<ITable> tables) {

    final var baseParameterizedBackReferenceType = (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyType;
    final var backReferencedColumnId = baseParameterizedBackReferenceType.getBackReferencedColumnId();

    return (Column) tables.toFromGroups(ITable::getStoredColumns).getStoredFirst(c -> c.hasId(backReferencedColumnId));
  }

  //method
  private ITable getStoredReferencedTableFromParameterizedPropertyType(
    final IParameterizedPropertyTypeDto parameterizedPropertyType,
    final IContainer<ITable> tables) {

    final var baseParameterizedReferenceType = (IBaseParameterizedReferenceTypeDto) parameterizedPropertyType;

    return tables.getStoredFirst(t -> t.hasId(baseParameterizedReferenceType.getReferencedTableId()));
  }
}
