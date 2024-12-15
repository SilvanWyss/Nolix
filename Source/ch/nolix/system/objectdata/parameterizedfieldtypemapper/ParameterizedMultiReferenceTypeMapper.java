package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtype.ParameterizedMultiReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractReferenceModelDto;

public final class ParameterizedMultiReferenceTypeMapper
implements IParameterizedFieldTypeMapper<IAbstractReferenceModelDto> {

  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromDto(
    IAbstractReferenceModelDto parameterizedFieldTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables) {

    final var referencedTableId = parameterizedFieldTypeDto.getReferencedTableId();
    final var referencedTable = referencableTables.getStoredFirst(t -> t.hasId(referencedTableId));

    return ParameterizedMultiReferenceType.forReferencedTable(referencedTable);
  }
}
