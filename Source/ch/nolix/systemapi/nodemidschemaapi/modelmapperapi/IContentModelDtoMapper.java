package ch.nolix.systemapi.nodemidschemaapi.modelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IContentModelDtoMapper {

  /**
   * @param contentModelNode
   * @return a new {@link IContentModelDto} from the given contentModelNode.
   * @throws RuntimeException if the given contentModelNode is not valid.
   */
  IContentModelDto mapContentModelNodeToContentModelDto(IMutableNode<?> contentModelNode);
}
