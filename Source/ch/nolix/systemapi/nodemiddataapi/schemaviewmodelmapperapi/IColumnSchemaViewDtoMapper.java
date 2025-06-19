package ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.middataapi.schemaviewapi.ColumnViewDto;

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
