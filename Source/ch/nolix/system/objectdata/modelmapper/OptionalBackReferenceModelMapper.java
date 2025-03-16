package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemaview.OptionalBackReferenceModelView;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IContentModelDtoToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;

public final class OptionalBackReferenceModelMapper
implements IContentModelDtoToContentModelMapper<OptionalBackReferenceModelDto> {

  @Override
  public IContentModelView<ITable<IEntity>> mapContentModelDtoToContentModelView(
    final OptionalBackReferenceModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var backReferencedColumnId = contentModelDto.backReferencedColumnId();
    final var referencableColumns = referencableTables.toMultiples(ITable::getStoredColumns);
    final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

    return OptionalBackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
  }
}
