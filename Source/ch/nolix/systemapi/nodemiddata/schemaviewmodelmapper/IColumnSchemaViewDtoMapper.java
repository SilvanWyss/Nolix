package ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnSchemaViewDtoMapper {
  /**
   * @param columnNode
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnViewDto} from the given columnNode and
   *         oneBasedColumnOrdinalIndex.
   * @throws RuntimeException if the given columnNode is null.
   */
  ColumnViewDto mapColumnNodeToColumnViewDto(IMutableNode<?> columnNode, int oneBasedColumnOrdinalIndex);
}
