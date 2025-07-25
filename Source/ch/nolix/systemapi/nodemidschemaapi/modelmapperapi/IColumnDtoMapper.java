package ch.nolix.systemapi.nodemidschemaapi.modelmapperapi;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-22
 */
public interface IColumnDtoMapper {

  /**
   * @param columnNode
   * @return a new {@link ColumnDto} from the given columnNode.
   * @throws RuntimeException if the given columnNode is not valid.
   */
  ColumnDto mapColumnNodeToColumnDto(IMutableNode<?> columnNode);
}
