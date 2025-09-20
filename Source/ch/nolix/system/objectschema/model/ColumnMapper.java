package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;

public final class ColumnMapper {
  private ColumnMapper() {
  }

  public static IContainer<Column> mapMidSchemaTableDtoToLoadedColumns(
    final TableDto midTableDto,
    final IContainer<Table> tables) {
    final var tableName = midTableDto.name();
    final var table = tables.getStoredFirst(t -> t.hasName(tableName));
    final var midColumns = midTableDto.columns();

    return midColumns.to(c -> mapMidSchemaColumnDtoToLoadedColumn(table, c, tables));
  }

  private static Column mapMidSchemaColumnDtoToLoadedColumn(
    final Table parentTable,
    final ColumnDto midColumnDto,
    final IContainer<Table> tables) {
    final var id = midColumnDto.id();
    final var name = midColumnDto.name();
    final var fieldType = midColumnDto.fieldType();
    final var dataType = midColumnDto.dataType();
    final var referenceableTableIds = midColumnDto.referenceableTableIds();
    final var backReferenceableColumnsIds = midColumnDto.backReferenceableColumnIds();

    final var midContentModel = //
    new ContentModelDto(fieldType, dataType, referenceableTableIds, backReferenceableColumnsIds);

    final var contentModel = ContentModelMapper.mapMidSchemaContentModelDtoToContentModel(midContentModel, tables);
    final var column = Column.withIdAndNameAndContentModel(id, name, contentModel);

    column.setLoaded();
    column.setParentTableAttribute(parentTable);

    return column;
  }
}
