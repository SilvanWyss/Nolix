package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtype.ParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;

public final class ParameterizedBackReferenceTypeMapper
implements IParameterizedFieldTypeMapper<IBaseParameterizedBackReferenceTypeDto> {

  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromDto(
    final IBaseParameterizedBackReferenceTypeDto parameterizedFieldTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var backReferencedColumnId = parameterizedFieldTypeDto.getBackReferencedColumnId();
    final var referencableColumns = referencableTables.toMultiple(ITable::getStoredColumns);
    final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

    return ParameterizedBackReferenceType.forBackReferencedColumn(backReferencedColumn);
  }
}
