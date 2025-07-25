package ch.nolix.systemapi.nodemidschemaapi.nodemapperapi;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IColumnNodeMapper {

  /**
   * @param columnDto
   * @return a new {@link INode} from the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToColumnNode(final ColumnDto columnDto);
}
