/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class ColumnMapper {
  private ColumnMapper() {
  }

  public static ExtendedIterable<Column> mapMidSchemaTableDtoToLoadedColumns(
    final TableDto midTableDto,
    final ExtendedIterable<Table> tables) {
    final var tableName = midTableDto.name();
    final var table = tables.getStoredFirst(t -> t.hasName(tableName));
    final var midColumns = midTableDto.columns();

    return midColumns.to(c -> mapMidSchemaColumnDtoToLoadedColumn(table, c, tables));
  }

  private static Column mapMidSchemaColumnDtoToLoadedColumn(
    final Table parentTable,
    final ColumnDto midColumnDto,
    final ExtendedIterable<Table> tables) {
    final var id = midColumnDto.id();
    final var name = midColumnDto.name();
    final var fieldType = midColumnDto.fieldType();
    final var dataType = midColumnDto.dataType();
    final var referenceableTableIds = midColumnDto.referenceableTableIds();
    final var backReferenceableColumnsIds = midColumnDto.backReferenceableColumnIds();

    final var referenceableTables = tables.getStoredSelected(t -> referenceableTableIds.containsMatching(t::hasId));

    final var columns = tables.toMultiples(ITable::getStoredColumns);
    final var backReferenceableColumns = //
    columns.getStoredSelected(c -> backReferenceableColumnsIds.containsMatching(c::hasId));

    final var column = //
    Column.withIdAndNameAndContentModel(id, name, fieldType, dataType, referenceableTables, backReferenceableColumns);

    column.setLoaded();
    column.setParentTableAttribute(parentTable);

    return column;
  }
}
