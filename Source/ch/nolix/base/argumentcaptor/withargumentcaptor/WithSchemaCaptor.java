/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithSchemaCaptor;

/**
 * @author Silvan Wyss
 * @param <T> the type of the schema of a {@link WithSchemaCaptor}
 * @param <S> the type of the successor of a {@link WithSchemaCaptor}
 */
public class WithSchemaCaptor<T, S> extends AbstractArgumentCaptor<T, S> implements IWithSchemaCaptor<T, S> {
  public WithSchemaCaptor() {
  }

  public WithSchemaCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final T getStoredSchema() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withSchema(final T schema) {
    return setArgumentAndGetStoredSuccessor(schema);
  }
}
