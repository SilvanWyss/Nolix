package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

final class ColumnDefinitionMapper {

  public IColumnInfo createColumnDefinitionFrom(final IColumnDto column) {
    return new ColumnInfo(
      column.getId(),
      column.getName(),
      column.getParameterizedFieldType().getContentType(),
      column.getParameterizedFieldType().getDataType(),
      0);
  }
}
