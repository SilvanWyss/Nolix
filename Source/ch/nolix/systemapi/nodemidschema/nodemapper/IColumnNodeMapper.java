/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 */
public interface IColumnNodeMapper {
  /**
   * @param columnDto
   * @return a new {@link INode} from the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  INode<?> mapColumnDtoToColumnNode(final ColumnDto columnDto);
}
