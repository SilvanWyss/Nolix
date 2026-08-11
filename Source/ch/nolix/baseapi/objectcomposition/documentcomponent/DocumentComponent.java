/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.documentcomponent;

/**
 * A {@link DocumentComponent} can belong to a document.
 * 
 * @author Silvan Wyss
 * @param <D> the type of the document a {@link DocumentComponent} can belong to
 */
public interface DocumentComponent<D> {
  /**
   * @return true if the current {@link DocumentComponent} belongs to a document,
   *         false otherwise
   */
  boolean belongsToDocument();

  /**
   * @return the parent document of the current {@link DocumentComponent}
   * @throws RuntimeException if the current {@link DocumentComponent} does not
   *                          belong to a document
   */
  D getStoredParentDocument();
}
