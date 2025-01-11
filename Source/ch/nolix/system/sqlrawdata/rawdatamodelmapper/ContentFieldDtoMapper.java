package ch.nolix.system.sqlrawdata.rawdatamodelmapper;

import ch.nolix.system.rawdata.valuemapper.ValueMapper;
import ch.nolix.systemapi.rawdataapi.model.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.valuemapperapi.IValueMapper;
import ch.nolix.systemapi.sqlrawdataapi.rawdatamodelmapperapi.IContentFieldDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public final class ContentFieldDtoMapper implements IContentFieldDtoMapper {

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ContentFieldDto<Object> mapStringToContentFieldDtoUsingColumnView(
    final String string,
    final ColumnViewDto columnView) {

    final var columnName = columnView.name();
    final var dataType = columnView.dataType();
    final var content = VALUE_MAPPER.mapStringToValue(string, dataType);

    return ContentFieldDto.withColumnNameAndContent(columnName, content);
  }
}
