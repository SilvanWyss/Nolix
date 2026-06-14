/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.schemamapper;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableMapper {
  ITable mapEntityTypeToEmptyTable(Class<? extends IEntity> entityType);

  IWellOrderContainer<ITable> mapSchemaToEmptyTables(IEntityTypeSet entityTypeSet);
}
