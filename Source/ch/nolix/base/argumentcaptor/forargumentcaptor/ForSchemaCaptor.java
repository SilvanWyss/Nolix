/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForSchemaCaptor;

/**
 * @author Silvan Wyss
 * @param <T> the type of the schema of a {@link ForSchemaCaptor}
 * @param <S> the type of the successor of a {@link ForSchemaCaptor}
 */
public class ForSchemaCaptor<T, S> extends AbstractArgumentCaptor<T, S> implements IForSchemaCaptor<T, S> {
  public ForSchemaCaptor() {
  }

  public ForSchemaCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forSchema(final T schema) {
    return setArgumentAndGetStoredSuccessor(schema);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final T getStoredSchema() {
    return getStoredArgument();
  }
}
