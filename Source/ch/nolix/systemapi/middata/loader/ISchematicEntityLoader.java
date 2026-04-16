/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.loader;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface ISchematicEntityLoader {
  IContainer<EntityLoadingDto> loadEntitiesByTable(TableInfoDto table);

  EntityLoadingDto loadEntityByTableAndId(TableInfoDto table, String id);
}
