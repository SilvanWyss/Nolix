package ch.nolix.system.objectdata.contentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.contentmodel.MultiBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;

public final class MultiBackReferenceModelMapper
implements IContentModelDtoToContentModelMapper<MultiBackReferenceModelDto> {

  @Override
  public IContentModel mapContentModelDtoToContentModel(
    final MultiBackReferenceModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var backReferencedColumnId = contentModelDto.backReferencedColumnId();
    final var referencableColumns = referencableTables.toMultiple(ITable::getStoredColumns);
    final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

    return MultiBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
  }
}
