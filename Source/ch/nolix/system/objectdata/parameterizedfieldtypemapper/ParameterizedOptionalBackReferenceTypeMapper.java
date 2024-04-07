//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtype.ParameterizedOptionalBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;

//class
public final class ParameterizedOptionalBackReferenceTypeMapper
implements IParameterizedFieldTypeMapper<IBaseParameterizedBackReferenceTypeDto> {

  //method
  @Override
  public IParameterizedFieldType createParameterizedPropertyTypeFromDto(
    final IBaseParameterizedBackReferenceTypeDto parameterizedPropertyTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var backReferencedColumnId = parameterizedPropertyTypeDto.getBackReferencedColumnId();
    final var referencableColumns = referencableTables.toFromGroups(ITable::getStoredColumns);
    final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

    return ParameterizedOptionalBackReferenceType.forBackReferencedColumn(backReferencedColumn);
  }
}
