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
  ExtendedIterable<EntityLoadingDto> loadEntitiesByTable(TableInfoDto table);

  EntityLoadingDto loadEntityByTableAndId(TableInfoDto table, String id);
}
