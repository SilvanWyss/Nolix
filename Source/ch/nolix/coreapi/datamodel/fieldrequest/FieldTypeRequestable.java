/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.datamodel.fieldrequest;

import ch.nolix.coreapi.datamodel.fieldproperty.FieldType;

/**
 * A {@link FieldTypeRequestable} can be asked if it either is for holding
 * values or references.
 * 
 * @author Silvan Wyss
 */
public interface FieldTypeRequestable {
  /**
   * @return the field type of the current {@link FieldTypeRequestable}.
   */
  FieldType getFieldType();

  /**
   * @return true if the current {@link FieldTypeRequestable} is for references,
   *         false otherwise.
   */
  default boolean isForReferences() {
    return !isForValues();
  }

  /**
   * @return true if the current {@link FieldTypeRequestable} is for values, false
   *         otherwise.
   */
  boolean isForValues();
}
