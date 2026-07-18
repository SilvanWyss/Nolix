/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IColumnSchemaViewDtoMapper {
  /**
   * @param columnNode
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnInfoDto} from the given columnNode and
   *         oneBasedColumnOrdinalIndex
   * @throws RuntimeException if the given columnNode is null
   */
  ColumnInfoDto mapColumnNodeToColumnViewDto(IMutableNode<?> columnNode, int oneBasedColumnOrdinalIndex);
}
