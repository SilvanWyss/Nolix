package ch.nolix.systemapi.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.objectschema.model.IContentModel;

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
