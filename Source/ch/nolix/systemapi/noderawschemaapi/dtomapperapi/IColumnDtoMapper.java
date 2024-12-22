package ch.nolix.systemapi.noderawschemaapi.dtomapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-22
 */
public interface IColumnDtoMapper {

  /**
   * @param columnNode
   * @return a new {@link ColumnDto} from the given columnNode.
   * @throws RuntimeException if the given columnNode is null.
   */
  ColumnDto mapColumnNodeToColumnDto(IMutableNode<?> columnNode);
}
