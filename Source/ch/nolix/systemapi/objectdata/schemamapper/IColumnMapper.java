/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.schemamapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnMapper {
  /**
   * @param field
   * @param columnId
   * @param referencedTables
   * @return a new {@link IColumn} with the given columnId from the given field,
   *         that can reference the given referencedTables
   */
  IColumn mapFieldToColumn(Field field, String columnId, ExtendedIterable<ITable> referencedTables);
}
