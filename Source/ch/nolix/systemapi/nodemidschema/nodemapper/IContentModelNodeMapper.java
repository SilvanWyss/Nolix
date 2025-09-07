package ch.nolix.systemapi.nodemidschema.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.ContentModelDto;

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
  INode<?> mapContentModelDtoToNode(ContentModelDto contentModelDto);
}
