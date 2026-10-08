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
  S forSchema(T schema);

  T getStoredSchema();
}
