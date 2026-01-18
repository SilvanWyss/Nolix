/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <D> is the type of the database of a {@link WithDatabaseCaptor}.
 * @param <N> is the type of the next thing of a {@link WithDatabaseCaptor}.
 */
public class WithDatabaseCaptor<D, N> extends ArgumentCaptor<D, N> {
  public WithDatabaseCaptor() {
  }

  public WithDatabaseCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final D getStoredDatabase() {
    return getStoredArgument();
  }

  public final N withDatabase(final D database) {
    return setArgumentAndGetNext(database);
  }
}
