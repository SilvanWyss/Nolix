package ch.nolix.system.objectschema.rawschemadtomapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IContentModelDtoMapper;

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
  public IContainer<ColumnDto> mapColumnToColumnDtos(final IColumn column) {

    final var id = column.getId();
    final var name = column.getName();
    final var contentModels = column.getContentModels();
    final var contentModelDtos = contentModels.to(CONTENT_MODEL_DTO_MAPPER::mapContentModelToContentModelDto);

    return contentModelDtos.to(c -> new ColumnDto(id, name, c));
  }
}
