/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 */
public interface ITableSchemaViewDtoMapper {
  /**
   * @param tableNode
   * @return a new {@link TableViewDto} from the given tableNode.
   * @throws RuntimeException if the given tableNode is null.
   */
  TableViewDto mapTableNodeToTableViewDto(IMutableNode<?> tableNode);
}
