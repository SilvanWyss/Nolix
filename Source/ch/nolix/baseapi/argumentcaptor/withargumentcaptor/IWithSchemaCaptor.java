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
  T getStoredSchema();

  S withSchema(T schema);
}
