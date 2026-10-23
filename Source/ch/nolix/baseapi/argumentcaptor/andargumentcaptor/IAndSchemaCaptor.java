/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <T> the type of the schema of a {@link AndSchemaCaptor}
 * @param <S> the type of the successor of a {@link AndSchemaCaptor}
 */
public interface IAndSchemaCaptor<T, S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndSchemaCaptor} capture the given schema.
   * 
   * @param schema
   * @return the successor of the current {@link IAndSchemaCaptor}
   * @throws RuntimeException if the current {@link IAndSchemaCaptor} does not
   *                          have a successor
   */
  S andSchema(T schema);

  /**
   * @return the schema the current {@link IAndSchemaCaptor} has captured
   * @throws RuntimeException if the current {@link IAndSchemaCaptor} has not
   *                          captured a schema
   */
  T getStoredSchema();
}
