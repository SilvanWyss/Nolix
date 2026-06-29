/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the schema of a {@link AndSchemaCaptor}.
 * @param <N> the type of the next thing of a {@link AndSchemaCaptor}.
 */
public class AndSchemaCaptor<S, N> extends AbstractArgumentCaptor<S, N> {
  public AndSchemaCaptor() {
  }

  public AndSchemaCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andSchema(final S schema) {
    return setArgumentAndGetStoredSuccessor(schema);
  }

  public final S getStoredSchema() {
    return getStoredArgument();
  }
}
