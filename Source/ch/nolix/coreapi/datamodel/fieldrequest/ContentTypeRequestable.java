package ch.nolix.coreapi.datamodel.fieldrequest;

import ch.nolix.coreapi.datamodel.fieldproperty.FieldType;

/**
 * A {@link ContentTypeRequestable} can be asked if it either is for holding
 * values or references.
 * 
 * @author Silvan Wyss
 * @version 2023-09-08
 */
public interface ContentTypeRequestable {

  /**
   * @return the content type of the current {@link ContentTypeRequestable}.
   */
  FieldType getContentType();

  /**
   * @return true if the current {@link ContentTypeRequestable} is for references,
   *         false otherwise.
   */
  default boolean isForReferences() {
    return !isForValues();
  }

  /**
   * @return true if the current {@link ContentTypeRequestable} is for values,
   *         false otherwiese.
   */
  boolean isForValues();
}
