/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.objectschema.midschemamodelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapColumnToMidSchemaColumnDto(final IColumn column) {
    final var id = column.getId();
    final var name = column.getName();
    final var fieldType = column.getFieldType();
    final var dataType = column.getDataType();
    final var referenceableTableIds = column.getStoredReferenceableTables().to(ITable::getId);
    final var backReferencebleColumnIds = column.getStoredBackReferenceableColumns().to(IColumn::getId);

    return new ColumnDto(id, name, fieldType, dataType, referenceableTableIds, backReferencebleColumnIds);
  }
}
