package ch.nolix.systemapi.noderawschemaapi.nodemapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

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
