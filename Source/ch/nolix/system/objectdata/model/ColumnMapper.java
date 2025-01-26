package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.modelmapper.ContentModelMapper;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

final class ColumnMapper {

  private static final ContentModelMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ContentModelMapper();

  public IColumnView<ITable<IEntity>> createColumnFromDtoForParentTableUsingGivenReferencableTables(
    final ColumnDto columnDto,
    final Table<IEntity> parentTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var contentModelView = //
    PARAMETERIZED_FIELD_TYPE_MAPPER.mapContentModelDtoToContentModel(columnDto.contentModel(), referencableTables);

    return //
    Column.withIdAndNameAndContentModelViewAndParentTableAndRawDataReader(
      columnDto.name(),
      columnDto.id(),
      contentModelView,
      parentTable,
      parentTable.internalGetStoredDataAndSchemaAdapter());
  }
}
