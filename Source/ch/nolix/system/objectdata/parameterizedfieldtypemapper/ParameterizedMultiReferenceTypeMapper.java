package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.parameterizedfieldtype.ParameterizedMultiReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;

public final class ParameterizedMultiReferenceTypeMapper
implements IParameterizedFieldTypeMapper<MultiReferenceModelDto> {

  @Override
  public IParameterizedFieldType createParameterizedFieldTypeFromDto(
    MultiReferenceModelDto parameterizedFieldTypeDto,
    IContainer<? extends ITable<IEntity>> referencableTables) {

    final var tableIds = parameterizedFieldTypeDto.referencedTableIds();
    final var tables = referencableTables.getStoredSelected(t -> tableIds.containsEqualing(t.getId()));

    //TODO: Handle multiple referenced tables
    return ParameterizedMultiReferenceType.forReferencedTable(tables.getStoredFirst());
  }
}
