package ch.nolix.system.sqlmiddata.rawdatamodelmapper;

import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.systemapi.middataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.middataapi.valuemapperapi.IValueMapper;
import ch.nolix.systemapi.sqlmiddataapi.rawdatamodelmapperapi.IContentFieldDtoMapper;

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
    final ColumnSchemaViewDto columnView) {

    final var columnName = columnView.name();
    final var dataType = columnView.dataType();
    final var content = VALUE_MAPPER.mapStringToValue(string, dataType);

    return new ContentFieldDto<>(columnName, content);
  }
}
