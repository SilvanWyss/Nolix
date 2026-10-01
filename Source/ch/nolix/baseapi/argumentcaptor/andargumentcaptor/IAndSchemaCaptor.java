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
  S andSchema(T schema);

  T getStoredSchema();
}
