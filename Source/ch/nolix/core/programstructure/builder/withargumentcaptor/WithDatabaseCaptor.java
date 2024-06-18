//package declaration
package ch.nolix.core.programstructure.builder.withargumentcaptor;

//own imports
import ch.nolix.core.programstructure.builder.main.ArgumentCaptor;

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
