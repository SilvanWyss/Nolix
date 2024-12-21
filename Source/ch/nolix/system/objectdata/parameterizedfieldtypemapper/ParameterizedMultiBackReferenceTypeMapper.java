package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.MultiBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;

public final class ParameterizedMultiBackReferenceTypeMapper
implements IParameterizedFieldTypeMapper<MultiBackReferenceModelDto> {

  @Override
  public IContentModel createParameterizedFieldTypeFromDto(
    final MultiBackReferenceModelDto parameterizedFieldTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var backReferencedColumnId = parameterizedFieldTypeDto.backReferencedColumnId();
    final var referencableColumns = referencableTables.toMultiple(ITable::getStoredColumns);
    final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

    return MultiBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
  }
}
