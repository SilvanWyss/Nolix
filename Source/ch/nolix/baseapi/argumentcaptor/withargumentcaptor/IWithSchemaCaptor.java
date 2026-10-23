/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <T> the type of the schema of a {@link IWithSchemaCaptor}
 * @param <S> the type of the successor of a {@link IWithSchemaCaptor}
 */
public interface IWithSchemaCaptor<T, S> extends ArgumentCaptor<S> {
  /**
   * @return the schema the current {@link IWithSchemaCaptor} has captured
   * @throws RuntimeException if the current {@link IWithSchemaCaptor} has not
   *                          captured a schema
   */
  T getStoredSchema();

  /**
   * Lets the current {@link IWithSchemaCaptor} capture the given schema.
   * 
   * @param schema
   * @return the successor of the current {@link IWithSchemaCaptor}
   * @throws RuntimeException if the current {@link IWithSchemaCaptor} does not
   *                          have a successor
   */
  S withSchema(T schema);
}
