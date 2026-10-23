/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.schemamapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableMapper {
  /**
   * @param entityType
   * @return a new empty {@link ITable} for the given entityType
   */
  ITable mapEntityTypeToEmptyTable(Class<? extends Entity> entityType);

  /**
   * @param entityTypeSet
   * @return new empty {@link ITable}s for the entity types of the given
   *         entityTypeSet
   */
  ExtendedIterable<ITable> mapSchemaToEmptyTables(IEntityTypeSet entityTypeSet);
}
