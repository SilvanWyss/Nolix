/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <T> the type of the schema of a {@link IForSchemaCaptor}
 * @param <S> the type of the successor of a {@link IForSchemaCaptor}
 */
public interface IForSchemaCaptor<T, S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForSchemaCaptor} capture the given schema.
   * 
   * @param schema
   * @return the successor of the current {@link IForSchemaCaptor}
   * @throws RuntimeException if the current {@link IForSchemaCaptor} does not
   *                          have a successor
   */
  S forSchema(T schema);

  /**
   * @return the schema the current {@link IForSchemaCaptor} has captured
   * @throws RuntimeException if the current {@link IForSchemaCaptor} has not
   *                          captured a schema
   */
  T getStoredSchema();
}
