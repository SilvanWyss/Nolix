package ch.nolix.systemapi.nodemidschema.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface ITableNodeMapper {
  /**
   * @param tableDto
   * @return a new {@link INode} from the given tableDto.
   * @throws RuntimeException if the given tableDto is null.
   */
  INode<?> mapTableDtoToNode(TableDto tableDto);
}
