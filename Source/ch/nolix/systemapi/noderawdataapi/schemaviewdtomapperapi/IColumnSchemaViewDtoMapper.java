package ch.nolix.systemapi.noderawdataapi.schemaviewdtomapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnSchemaViewDtoMapper {

  /**
   * @param columnNode
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnSchemaViewDto} from the given columnNode and
   *         oneBasedColumnOrdinalIndex.
   * @throws RuntimeException if the given columnNode is null.
   */
  ColumnSchemaViewDto mapColumnNodeToColumnViewDto(IMutableNode<?> columnNode, int oneBasedColumnOrdinalIndex);
}
