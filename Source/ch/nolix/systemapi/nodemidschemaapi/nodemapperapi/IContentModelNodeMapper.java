package ch.nolix.systemapi.nodemidschemaapi.nodemapperapi;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IContentModelNodeMapper {

  /**
   * @param columnDto
   * @return a new content model {@link INode} from the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToContentModelNode(ColumnDto columnDto);

  /**
   * @param contentModelDto
   * @return a new content model {@link INode} from the given contentModelDto.
   * @throws RuntimeException if the given contentModelDto is null.
   */
  INode<?> mapContentModelDtoToNode(IContentModelDto contentModelDto);
}
