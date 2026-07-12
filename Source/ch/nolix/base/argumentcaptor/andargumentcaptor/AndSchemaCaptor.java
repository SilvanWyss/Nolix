/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndSchemaCaptor;

/**
 * @author Silvan Wyss
 * @param <C> the type of the schema of a {@link AndSchemaCaptor}
 * @param <S> the type of the successor of a {@link AndSchemaCaptor}
 */
public class AndSchemaCaptor<C, S> extends AbstractArgumentCaptor<C, S> implements IAndSchemaCaptor<C, S> {
  public AndSchemaCaptor() {
  }

  public AndSchemaCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andSchema(final C schema) {
    return setArgumentAndGetStoredSuccessor(schema);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C getStoredSchema() {
    return getStoredArgument();
  }
}
