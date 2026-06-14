/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.loader;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface ISchematicEntityLoader {
  IWellOrderContainer<EntityLoadingDto> loadEntitiesByTable(TableInfoDto table);

  EntityLoadingDto loadEntityByTableAndId(TableInfoDto table, String id);
}
