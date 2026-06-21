/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.schemamapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableMapper {
  ITable mapEntityTypeToEmptyTable(Class<? extends IEntity> entityType);

  ExtendedIterable<ITable> mapSchemaToEmptyTables(IEntityTypeSet entityTypeSet);
}
