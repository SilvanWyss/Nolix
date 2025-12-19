package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
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
