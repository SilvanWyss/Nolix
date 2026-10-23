/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelsearcher;

import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;

/**
 * @author Silvan Wyss
 */
public interface IColumnSearcher {
  /**
   * @param column
   * @return the {@link BaseFieldType} of the given column
   */
  BaseFieldType getBaseFieldType(IColumn column);
}
