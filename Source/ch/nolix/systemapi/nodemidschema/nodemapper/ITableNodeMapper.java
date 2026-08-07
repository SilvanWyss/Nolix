/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodemapper;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ITableNodeMapper {
  /**
   * @param tableDto
   * @return a new {@link Node} from the given tableDto
   * @throws RuntimeException if the given tableDto is null
   */
  Node<?> mapTableDtoToNode(TableDto tableDto);
}
