/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.schemaviewmodelmapper;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface ITableSchemaViewDtoMapper {
  /**
   * @param tableNode
   * @return a new {@link TableInfoDto} from the given tableNode.
   * @throws RuntimeException if the given tableNode is null.
   */
  TableInfoDto mapTableNodeToTableViewDto(IMutableNode<?> tableNode);
}
