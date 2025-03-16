package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemaview.BackReferenceModelView;
import ch.nolix.systemapi.midschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;

public final class BackReferenceModelMapper
implements IContentModelDtoToContentModelMapper<BackReferenceModelDto> {

  @Override
  public IContentModelView<ITable<IEntity>> mapContentModelDtoToContentModelView(
    final BackReferenceModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var backReferencedColumnId = contentModelDto.backReferencedColumnId();
    final var referencableColumns = referencableTables.toMultiples(ITable::getStoredColumns);
    final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

    return BackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
  }
}
