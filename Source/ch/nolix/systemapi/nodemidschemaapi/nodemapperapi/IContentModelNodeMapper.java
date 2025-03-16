package ch.nolix.systemapi.nodemidschemaapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IContentModelNodeMapper {

  /**
   * @param contentModelDto
   * @return a new {@link INode} from the given contentModelDto.
   * @throws RuntimeException if the given contentModelDto is null.
   */
  INode<?> mapContentModelDtoToNode(IContentModelDto contentModelDto);
}
