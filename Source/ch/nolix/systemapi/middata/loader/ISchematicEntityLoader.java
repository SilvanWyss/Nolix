/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.loader;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface ISchematicEntityLoader {
  /**
   * @param table
   * @return the entities of the given table
   */
  ExtendedIterable<EntityLoadingDto> loadEntitiesByTable(TableInfoDto table);

  /**
   * @param table
   * @param id
   * @return the entity with the given id from the given table
   * @throws RuntimeException if the given table does not contain an entity with
   *                          the given id
   */
  EntityLoadingDto loadEntityByTableAndId(TableInfoDto table, String id);
}
