package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.system.sqlrawdata.datamapper.ValueMapper;
import ch.nolix.systemapi.rawdataapi.dto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.sqlrawdataapi.datamapperapi.IValueMapper;

public final class ContentFieldMapper {

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  public ContentFieldDto<Object> createContentFieldFromString(
    final String string,
    final IColumnView contentColumnDefinition) {
    return //
    ContentFieldDto.withColumnNameAndContent(
      contentColumnDefinition.getColumnName(),
      VALUE_MAPPER.mapValueToString(string, contentColumnDefinition.getColumnDataType()));
  }
}
