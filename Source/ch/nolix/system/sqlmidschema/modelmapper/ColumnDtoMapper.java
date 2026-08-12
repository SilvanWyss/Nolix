/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.ColumnColumn;
import ch.nolix.systemapi.sqlmidschema.modelmapper.IColumnDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapJoinedColumnSqlRecordToColumnDto(final ISqlRecord joinedColumnSqlRecord) {
    final var id = joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnColumn.ID.getOneBasedIndex());
    final var name = joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnColumn.NAME.getOneBasedIndex());

    final var fieldTypeString = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnColumn.FIELD_TYPE.getOneBasedIndex());

    final var fieldType = FieldType.valueOf(fieldTypeString);

    final var dataTypeString = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnColumn.DATA_TYPE.getOneBasedIndex());

    final var dataType = DataType.valueOf(dataTypeString);
    final var referenceableTableIdsString = joinedColumnSqlRecord.getStoredAtOneBasedIndex(6);
    ImmutableList<String> referenceableTableIds;

    if (referenceableTableIdsString != null) {
      referenceableTableIds = ImmutableList.fromArray(referenceableTableIdsString.split(","));
    } else {
      referenceableTableIds = ImmutableList.createEmpty();
    }

    final var backReferenceableColumnIdsString = joinedColumnSqlRecord.getStoredAtOneBasedIndex(7);
    ImmutableList<String> backReferenceableColumnIds;

    if (backReferenceableColumnIdsString != null) {
      backReferenceableColumnIds = ImmutableList.fromArray(backReferenceableColumnIdsString.split(","));
    } else {
      backReferenceableColumnIds = ImmutableList.createEmpty();
    }

    return new ColumnDto(id, name, fieldType, dataType, referenceableTableIds, backReferenceableColumnIds);
  }
}
