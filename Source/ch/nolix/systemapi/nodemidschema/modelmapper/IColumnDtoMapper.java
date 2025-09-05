package ch.nolix.systemapi.nodemidschema.modelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.model.ColumnDto;

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
