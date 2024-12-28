package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemaview.ColumnView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;

final class ColumnDefinitionMapper {

  public IColumnView createColumnDefinitionFrom(final ColumnDto column) {

    final var contentModel = column.contentModel();

    return //
    new ColumnView(
      column.id(),
      column.name(),
      contentModel.getContentType(),
      contentModel.getDataType(),
      0);
  }
}
