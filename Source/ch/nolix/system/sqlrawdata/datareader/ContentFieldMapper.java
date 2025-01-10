package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.system.sqlrawdata.datamapper.ValueMapper;
import ch.nolix.systemapi.rawdataapi.dto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.sqlrawdataapi.datamapperapi.IValueMapper;

public final class ContentFieldMapper {

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  public ContentFieldDto<Object> createContentFieldFromString(
    final String string,
    final ColumnViewDto contentColumnDefinition) {
    return //
    ContentFieldDto.withColumnNameAndContent(
      contentColumnDefinition.name(),
      VALUE_MAPPER.mapValueToString(string, contentColumnDefinition.dataType()));
  }
}
