/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithDatabaseCaptor;

/**
 * @author Silvan Wyss
 * @param <D> the type of the database of a {@link WithDatabaseCaptor}
 * @param <S> the type of the next thing of a {@link WithDatabaseCaptor}
 */
public class WithDatabaseCaptor<D, S> extends AbstractArgumentCaptor<D, S> implements IWithDatabaseCaptor<D, S> {
  public WithDatabaseCaptor() {
  }

  public WithDatabaseCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final D getStoredDatabase() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withDatabase(final D database) {
    return setArgumentAndGetStoredSuccessor(database);
  }
}
