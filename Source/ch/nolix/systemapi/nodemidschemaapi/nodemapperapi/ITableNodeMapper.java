package ch.nolix.systemapi.nodemidschemaapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

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
