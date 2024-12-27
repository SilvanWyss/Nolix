package ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi;

import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.rawschemaapi.dto.IContentModelDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-27
 */
public interface IContentModelDtoMapper {

  /**
   * @param contentModel
   * @return a new {@link IContentModelDto} from the given contentModel.
   * @throws RuntimeException if the given contentModel is null.
   */
  IContentModelDto mapContentModelToContentModelDto(IContentModel contentModel);
}
