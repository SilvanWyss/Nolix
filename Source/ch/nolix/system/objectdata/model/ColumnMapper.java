package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.modelmapper.ContentModelMapper;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public final class ColumnMapper {

  private static final ContentModelMapper CONTENT_MODEL_MAPPER = new ContentModelMapper();

  public IColumnView<ITable<IEntity>> mapMidSchemaColumnDtoToColumnView(
    final ColumnDto midSchemaColumnDto,
    final Table<IEntity> parentTable,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var contentModelView = //
    CONTENT_MODEL_MAPPER.mapContentModelDtoToContentModel(midSchemaColumnDto.contentModel(), referencableTables);

    return //
    Column.withIdAndNameAndContentModelViewAndParentTableAndMidDataReader(
      midSchemaColumnDto.name(),
      midSchemaColumnDto.id(),
      contentModelView,
      parentTable,
      parentTable.getStoredMidDataAdapterAndSchemaReader());
  }
}
