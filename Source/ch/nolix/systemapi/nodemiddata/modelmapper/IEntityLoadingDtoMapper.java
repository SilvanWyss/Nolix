/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.modelmapper;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IEntityLoadingDtoMapper {
  /**
   * @param entityNode
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given entityNode
   * @throws RuntimeException if the given entityNode is null.
   */
  EntityLoadingDto mapEntityNodeToEntityLoadingDto(IMutableNode<?> entityNode, TableInfoDto tableView);
}
