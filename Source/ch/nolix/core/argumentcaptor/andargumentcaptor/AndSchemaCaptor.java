//package declaration
package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;

//class
public class AndSchemaCaptor<S, N> extends ArgumentCaptor<S, N> {

  //constructor
  public AndSchemaCaptor() {
  }

  //constructor
  public AndSchemaCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final N andSchema(final S schema) {
    return setArgumentAndGetNext(schema);
  }

  //method
  public final S getStoredSchema() {
    return getStoredArgument();
  }
}
