package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemaview.ColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnInfo;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;

final class ColumnDefinitionMapper {

  public IColumnInfo createColumnDefinitionFrom(final ColumnDto column) {

    final var contentModel = column.contentModel();

    return //
    new ColumnInfo(
      column.id(),
      column.name(),
      contentModel.getContentType(),
      contentModel.getDataType(),
      0);
  }
}
