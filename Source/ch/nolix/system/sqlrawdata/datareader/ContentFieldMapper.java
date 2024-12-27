package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.systemapi.rawdataapi.dto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnInfo;

public final class ContentFieldMapper {

  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  public ContentFieldDto<Object> createContentFieldFromString(
    final String string,
    final IColumnInfo contentColumnDefinition) {
    return //
    ContentFieldDto.withColumnNameAndContent(
      contentColumnDefinition.getColumnName(),
      VALUE_MAPPER.createValueFromString(string, contentColumnDefinition));
  }
}
