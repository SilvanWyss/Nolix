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
  BaseFieldType getBaseFieldType(IColumn column);
}
