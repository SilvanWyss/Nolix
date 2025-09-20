package ch.nolix.system.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.objectschema.midschemamodelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.objectschema.midschemamodelmapper.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschema.model.IColumn;

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
    final var fieldType = column.getContentModel().getFieldType();
    final var dataType = column.getContentModel().getDataType();
    final var contentModel = column.getContentModel();
    final var contentModelDto = CONTENT_MODEL_DTO_MAPPER.mapContentModelToContentModelDto(contentModel);

    return new ColumnDto(id, name, fieldType, dataType, contentModelDto);
  }
}
