package ch.nolix.coreapi.datamodelapi.fieldrequestapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.ContentType;
import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link ContentTypeRequestable} can be asked if it either is for holding
 * values or references.
 * 
 * @author Silvan Wyss
 * @version 2023-09-08
 */
@AllowDefaultMethodsAsDesignPattern
public interface ContentTypeRequestable {

  /**
   * @return the content type of the current {@link ContentTypeRequestable}.
   */
  ContentType getContentType();

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
