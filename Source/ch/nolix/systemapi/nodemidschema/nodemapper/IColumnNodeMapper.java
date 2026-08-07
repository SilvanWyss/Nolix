/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodemapper;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.midschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 */
public interface IColumnNodeMapper {
  /**
   * @param columnDto
   * @return a new {@link Node} from the given columnDto
   * @throws RuntimeException if the given columnDto is null
   */
  Node<?> mapColumnDtoToColumnNode(final ColumnDto columnDto);
}
