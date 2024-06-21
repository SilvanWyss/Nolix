//package declaration
package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;

//class
public class WithDatabaseCaptor<D, N> extends ArgumentCaptor<D, N> {

  //constructor
  public WithDatabaseCaptor() {
  }

  //constructor
  public WithDatabaseCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final D getStoredDatabase() {
    return getStoredArgument();
  }

  //method
  public final N withDatabase(final D database) {
    return setArgumentAndGetNext(database);
  }
}
