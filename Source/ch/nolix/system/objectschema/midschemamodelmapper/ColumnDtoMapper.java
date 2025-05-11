package ch.nolix.system.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  private static final IContentModelDtoMapper CONTENT_MODEL_DTO_MAPPER = new ContentModelDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapColumnToMidSchemaColumnDto(final IColumn column) {

    final var id = column.getId();
    final var name = column.getName();
    final var contentModel = column.getContentModel();
    final var contentModelDto = CONTENT_MODEL_DTO_MAPPER.mapContentModelToContentModelDto(contentModel);

    return new ColumnDto(id, name, contentModelDto);
  }
}
