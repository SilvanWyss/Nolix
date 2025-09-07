package ch.nolix.systemapi.nodemidschema.modelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.model.ContentModelDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IContentModelDtoMapper {
  /**
   * @param contentModelNode
   * @return a new {@link ContentModelDto} from the given contentModelNode.
   * @throws RuntimeException if the given contentModelNode is not valid.
   */
  ContentModelDto mapContentModelNodeToContentModelDto(IMutableNode<?> contentModelNode);
}
